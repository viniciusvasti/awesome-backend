package com.vas.aos.core.domain.entities;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Value
public class User implements UserDetails {
    private String username;
    private String password;
    private boolean enabled = true;

    public boolean passwordIsValid() {
        return password.length() >= 8 && password.matches(".*[A-Za-z]+.*") && password.matches(
                ".*[0-9]+.*");
    }

    public boolean userNameIsValid() {
        return username.length() >= 8 && username.matches(
                "[A-Za-z0-9_]+");
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}