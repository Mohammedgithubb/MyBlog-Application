package com.example.myblog.myblog.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity      // It signals to Spring that you want to configure web-based security for your application.
@EnableMethodSecurity
public class SecurityConfig {



    //This method is used for Authorization
    //Instead of @Override of Config method we have created securityFilterChain.
    @Bean
    public HttpBasicConfigurer<HttpSecurity> securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                //Here below we have used .requestMatcher instead of .antMatcher bcz we are not extending security config class to WebSecurityConfigAdapter() class.
                .requestMatchers(HttpMethod.GET, "/api/posts").permitAll()/* OR .permitAll() method can be used instead of .hasAnyRole()*/
                .requestMatchers("api/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    //This method is used for In-Memory Authentication.
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("nawaz")
                .password(encoder.encode("Nawaz@2001"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    //To encript the password we have to create this method PasswordEncoder method apply @Bean and use that in user and admin objects.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
