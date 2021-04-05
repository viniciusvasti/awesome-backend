package com.vas.aos.infraestructure.persistence.models;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class UserJpaModel {
    @Id
    private String userName;
    private String password;
}