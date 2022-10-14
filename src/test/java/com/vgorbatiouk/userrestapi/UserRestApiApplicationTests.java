package com.vgorbatiouk.userrestapi;

import com.vgorbatiouk.userrestapi.dto.PhoneDTO;
import com.vgorbatiouk.userrestapi.dto.UserDTO;
import com.vgorbatiouk.userrestapi.model.UserResponse;
import com.vgorbatiouk.userrestapi.service.UserService;
import jakarta.validation.Payload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class UserRestApiApplicationTests {

    @Mock
    private UserService userService;

    @Mock
    private UserDTO userDTO;

    private UserDTO userDTO() {
        return userDTO.builder().name("pepe").email("pepe@nisum.org").password("AAAaaa222").phones(
            Arrays.asList(PhoneDTO.builder().number("33333").citycode("33").countrycode("33").build())).build();
    }

    @Test
    void contextLoads() {
        UserDTO userDTO = userDTO();
        Mockito.when(userService.createUser(Mockito.any())).thenReturn(
            UserResponse.builder().userId(111).build());
        UserResponse response = userService.createUser(userDTO);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getUserId(), 111);
    }

}
