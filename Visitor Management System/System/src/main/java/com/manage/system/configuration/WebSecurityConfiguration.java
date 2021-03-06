package com.manage.system.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨, 스프링 시큐리티 필터가 SecurityConfig 이것을 말한다. 
//지금부터 등록할 필터가 기본 필터에 등록이 된다. 
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService userDetailsService;
	
	@Bean // spring-security에서 제공하는 암호화방식
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("resources/css/**", "resources/js/**", "resources/img/**", "resources/lib/**");
    }
    
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable() // csrf 토큰 비활성화 (테스트 시 걸어두는 게 좋음)
				.authorizeRequests().antMatchers("/signin/**", "/signup/**").permitAll()
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/signin")
					.defaultSuccessUrl("/") // 로그인 성공 시 이동할 경로.
					.failureUrl("/signin?error=true"). // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸
					and()
				.logout()
					.permitAll()
					.logoutUrl("/signout")
					.logoutSuccessUrl("/signin")
					.and()
				.exceptionHandling().accessDeniedPage("/accessDenied_page"); // 권한이 없는 대상이 접속을시도했을 때
	}
	
	@Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
