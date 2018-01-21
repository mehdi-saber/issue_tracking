package com.atrosys.platform;

import com.atrosys.platform.model.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlatformApplication implements ApplicationRunner{

	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}

}
//For creating war file:
/*
@SpringBootApplication
public class PlatformApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PlatformApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PlatformApplication.class, args);
    }

}
*/