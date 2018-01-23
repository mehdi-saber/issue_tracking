package com.atrosys.platform;

import com.atrosys.platform.model.service.EmailService;

import com.atrosys.platform.storage.StorageProperties;
import com.atrosys.platform.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PlatformApplication implements ApplicationRunner{

	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
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