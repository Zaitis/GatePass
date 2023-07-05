package com.sk.GatePass.security;

import com.sk.GatePass.model.Employee;
import com.sk.GatePass.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    List<? extends GrantedAuthority> roles = new ArrayList<>();

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        System.out.println("test "+ employeeRepository.findByMail(username));
        Employee user = employeeRepository.findByMail(username);


        return org.springframework.security.core.userdetails.User
                .withUsername(user.getMail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }


}
