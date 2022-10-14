package com.vgorbatiouk.userrestapi.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user5")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name="created")
    private Date created;

    @Temporal(TemporalType.DATE)
    @Column(name="modified")
    private Date modified;

    @Temporal(TemporalType.DATE)
    @Column(name="last_login")
    private Date last_login;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones;

    private String token;
    private Boolean isactive;
}
