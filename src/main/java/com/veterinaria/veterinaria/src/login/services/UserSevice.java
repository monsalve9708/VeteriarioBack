package com.veterinaria.veterinaria.src.login.services;

import com.veterinaria.veterinaria.src.login.entity.Login;
import com.veterinaria.veterinaria.src.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSevice implements UserDetailsService {
    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.getLogin(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        System.out.println(login.getPassword());
        roles.add(new SimpleGrantedAuthority(login.getDstipousuario()));
        return new User(login.getUsername(),login.getPassword(),true,true,true,true,roles);
    }
}
