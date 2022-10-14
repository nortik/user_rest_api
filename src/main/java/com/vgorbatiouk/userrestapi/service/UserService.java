package com.vgorbatiouk.userrestapi.service;

import com.vgorbatiouk.userrestapi.dto.UserDTO;
import com.vgorbatiouk.userrestapi.entity.User;
import com.vgorbatiouk.userrestapi.exception.UserException;
import com.vgorbatiouk.userrestapi.jwt.JwtTokenUtil;
import com.vgorbatiouk.userrestapi.model.UserResponse;
import com.vgorbatiouk.userrestapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

import static java.lang.Boolean.TRUE;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Properties prop;

    @Autowired
    JwtTokenUtil jwtUtil;

    public UserResponse createUser(final UserDTO userDTO) {

        String accessToken = jwtUtil.generateAccessToken(userDTO);

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new UserException(userDTO.getEmail() + " exist already.", HttpStatus.CONFLICT);
        }
        if (!userDTO.getEmail().matches(prop.getProperty("emailRegex"))) {
            throw new UserException("Email doesn't have a correct format:" + userDTO.getEmail(), HttpStatus.CONFLICT);
        }
        if (!userDTO.getPassword().matches(prop.getProperty("password"))) {
            throw new UserException("Password doesn't have a correct format:" + userDTO.getPassword() +
                ".The password must contain at least one letter and after at least one digit", HttpStatus.CONFLICT);
        }
        prop.getProperty("emailRegex");
        User user = modelMapper.map(userDTO, User.class);
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLast_login(new Date());
        user.setToken(accessToken);
        user.setIsactive(TRUE);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(password);
        try {
            this.userRepository.save(user);
        } catch (Exception ex) {
            throw new UserException("Error with DB", HttpStatus.CONFLICT);
        }
        return UserResponse.builder().userId(user.getId()).created(user.getCreated()).modified(user.getModified())
            .lastLogin(user.getLast_login()).token(user.getToken()).isActive(user.getIsactive()).build();
    }

}
