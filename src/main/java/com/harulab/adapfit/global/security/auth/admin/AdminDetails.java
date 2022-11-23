package com.harulab.adapfit.global.security.auth.admin;

import com.harulab.adapfit.domain.user.domain.type.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class AdminDetails implements UserDetails {

    private final String adminId;

    private final Authority adminRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + adminRole)
        );
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return adminId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}