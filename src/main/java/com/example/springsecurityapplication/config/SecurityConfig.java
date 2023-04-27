package com.example.springsecurityapplication.config;


import com.example.springsecurityapplication.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final PersonDetailsService personDetailsService;
    @Bean
    public PasswordEncoder getPasswordEncode(){ //вернули шифрование паролей
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests() //все страницы защищены аутентификацией
                .requestMatchers("/admin").hasRole("ADMIN")//список страниц доступные админу
                .requestMatchers("/authentication","/registration","/error","/resources/**","/static/**","/css/**",
                        "/js/**","/img/**", "/product","/product/info/{id}", "/product/search","/person_account","/user").permitAll()
                .anyRequest().hasAnyRole("USER","ADMIN") // остальные страницы достиупны всем ролям
                .and() //соединитель
//                .formLogin().loginPage("/authentication") //перенаправление при заходе на защищеные страницы
                .formLogin().loginPage("/product") //перенаправление при заходе на защищеные страницы
                .loginProcessingUrl("/process_login") //указывает на какой адресс отправляются данные из form
                .defaultSuccessUrl("/person account",true) // после успешной аутентификации отправить пользоватьеля на эту страницу
                .failureUrl("/authentication?error")//куда отправляется не авторизованный пользователь
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/authentication");
        return http.build();

    }
    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncode());
    }
}
