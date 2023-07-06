package com.sk.GatePass.security;


import com.sk.GatePass.model.Role;
import com.sk.GatePass.view.commons.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import java.io.IOException;


@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/images/*.png").permitAll()
                .requestMatchers("/favicon.ico").permitAll();

        super.configure(http);
        setLoginView(http, LoginView.class);

        http.formLogin()
                .successHandler((request, response, authentication) -> {
                    DefaultSavedRequest savedRequest =
                            (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                    if (authentication.getAuthorities().stream()
                            .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
                        response.sendRedirect("/parking/admin-dashboard");
                    } else {
                        response.sendRedirect("/parking/dashboard");
                    }
                });
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                String redirectUrl = null;
                for (String role : AuthorityUtils.authorityListToSet(authentication.getAuthorities())) {
                    if (role.equals("USER")) {
                        redirectUrl = "/dashboard";
                        break;
                    } else if (role.equals("ADMIN")) {
                        redirectUrl = "/admin-dashboard";
                        break;
                    }
                }
                if (response.isCommitted()) {
                    return;
                }
                response.sendRedirect(redirectUrl);
            }
        };
    }


    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles(String.valueOf(Role.USER))
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
