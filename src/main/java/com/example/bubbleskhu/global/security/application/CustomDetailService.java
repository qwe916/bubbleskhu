package com.example.bubbleskhu.global.security.application;

import com.example.bubbleskhu.global.security.role.Role;
import com.example.bubbleskhu.member.application.UserService;
import com.example.bubbleskhu.member.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomDetailService implements UserDetailsService {
    private final UserService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findUser(username).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        user.setAuthorities(
                Stream.concat(
                        getRoles(user.getRoles()).stream(),
                                getPrivileges(user.getRoles()).stream()
                ).collect(Collectors.toList())
        );
        return user;
    }

    private List<SimpleGrantedAuthority> getRoles(List<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<SimpleGrantedAuthority> getPrivileges(List<Role> roles) {
        return roles.stream()
                .flatMap(role -> role.getPrivileges().stream())
                .map(privilege -> new SimpleGrantedAuthority(privilege.getName()))
                .collect(Collectors.toList());
    }
}
