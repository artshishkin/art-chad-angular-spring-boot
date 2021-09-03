package net.shyshkin.study.fullstack.ecommerce.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // protect endpoint `/api/orders`
        http.authorizeRequests()
                .antMatchers("/api/orders/**")
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        // add CORS filters
        http.cors();

        http.csrf().disable();

        // Send a 401 message to the browser (w/o this, you'll see a blank page)
//        Okta.configureResourceServer401ResponseBody(http);
    }
}
