package com.atrosys.platform.configuration;
import com.atrosys.platform.converter.UserListConverter;
import com.atrosys.platform.model.bl.UserManager;
import com.atrosys.platform.model.service.*;
import com.atrosys.platform.controller.ws.soap.client.SoapTest;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration


public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Bean
    UserService userService() {
        return new UserServiceImpl();
    }
    @Bean public UserManager userManager(){
        return new UserManager(userService(),daysService(),roleService(),taskService());
    }
    @Bean
    DaysService daysService(){return new DaysServiceImpl();}
@Bean RoleService roleService(){return new RoleServiceImpl();}
@Bean TaskService taskService(){return new TaskServiceImpl();}
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag("fa"));
        return  localeResolver;
    }
    @Bean
    public Scheduler scheduler(){
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = null;
        try {
            sched = schedFact.getScheduler();
            sched.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


        return sched;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**",
                "/font/**",
                "/semantic/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/semantic/",
                        "classpath:/static/font/",
                        "classpath:/static/js/");
    }
    @Override
    public void addFormatters (FormatterRegistry registry) {
        registry.addConverter(new UserListConverter());
    }
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("soap.wsdl");
        return marshaller;
    }

    @Bean
    public SoapTest soapTest(Jaxb2Marshaller marshaller) {
        SoapTest client = new SoapTest();
        client.setDefaultUri("http://www.webservicex.com/stockquote.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
