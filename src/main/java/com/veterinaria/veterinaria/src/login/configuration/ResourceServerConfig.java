package com.veterinaria.veterinaria.src.login.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "hora/hour").permitAll()
                .antMatchers(HttpMethod.GET,"api/v1/maet/getbycdgrupo").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/maet/getmedicamentos").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.POST,"api/v1/mascotas/orquestador").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/mascotas/byidentificacion").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/mascotas/bycliente").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/peluqueria/orquestador").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/peluqueria/getbymascota").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/formula/orquestador").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/formula/getbycdhistoria").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/mascotas/delete").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.POST,"api/v1/usuario/create").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.POST,"api/v1/usuario/getusuarios").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/usuario/verif").hasAnyRole("Administrador", "Estilista", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/usuario/identificacion").hasAnyRole("Administrador","Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/usuario/change").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.GET,"api/v1/historia/orquestador").hasAnyRole("Administrador", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/historia/getbycdmascota").hasAnyRole("Administrador", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/historia/getbycdhistoria").hasAnyRole("Administrador", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/historiarest/getpdfhistoria").hasAnyRole("Administrador", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/historia/getproduc").hasAnyRole("Administrador", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/historia/getproblema").hasAnyRole("Administrador", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/historia/getmaestra").hasAnyRole("Administrador", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/historia/getdiferencial").hasAnyRole("Administrador", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.GET,"api/v1/historia/getcomplementa").hasAnyRole("Administrador", "Veterinario", "Auxiliar")
                .antMatchers(HttpMethod.POST,"api/v1/planhuellas/create").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.POST,"api/v1/planhuellas/update").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.GET,"api/v1/planhuellas/getbycliente").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.GET,"api/v1/planhuellas/get").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.GET,"api/v1/planhuellas/reporte").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.GET,"api/v1/planhuellas/validate").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.GET,"api/v1/planhuellas/delete").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.POST,"api/v1/planhuellastransas/create").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.POST,"api/v1/planhuellastransas/update").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.GET,"api/v1/planhuellastransas/getcdplanhuellas").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.GET,"api/v1/planhuellastransas/delete").hasAnyRole("Administrador")
                .antMatchers(HttpMethod.GET,"api/v1/planhuellastransas/gettotal").hasAnyRole("Administrador")
                .and().cors().configurationSource(corsConfigurationSource());
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        config.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
