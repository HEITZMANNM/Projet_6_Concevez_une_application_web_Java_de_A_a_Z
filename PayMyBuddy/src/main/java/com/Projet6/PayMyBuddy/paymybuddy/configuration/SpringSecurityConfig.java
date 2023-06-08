//package com.Projet6.PayMyBuddy.paymybuddy.configuration;
//
//import com.Projet6.PayMyBuddy.paymybuddy.service.MyUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeRequests()
//                .antMatchers("http://localhost:4200/login").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .and()
//                .oauth2Login()
//                .and()
//                .csrf().disable()
//                .build();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//
//    @Autowired
//    public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("springuser").password(passwordEncoder().encode("spring123"))
////                .roles("USER")
////                .and()
////                .withUser("springadmin").password(passwordEncoder().encode("admin123"))
////                .roles("ADMIN", "USER");
//
//
//        // using custom UserDetailsService DAO
//         auth.userDetailsService(myUserDetailsService);
//
//
//    }
//
//
//
//}
//
//
//
//
////@Configuration
////@EnableWebSecurity
////public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
////
////
////
////    @Bean
////    public SecurityFilterChain securityFilterChain()
////    {
////
////    }
////
////    @Override
////    public void configure(HttpSecurity httpSecurity) throws Exception
////    {
//////        httpSecurity
//////                .authorizeRequests()
//////                .antMatchers("/admin").hasRole("ADMIN")
//////                .antMatchers("/user").hasRole("USER")
//////                .anyRequest().authenticated()
//////                .and()
//////                .formLogin()
//////
////
////
////        httpSecurity
////                .authorizeRequests()
////                .antMatchers("/").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login");
//    }
//

//}
