package com.atrosys.platform;
import com.atrosys.platform.storage.StorageProperties;
import com.atrosys.platform.storage.StorageService;
import com.atrosys.platform.controller.ws.soap.client.SoapTest;
import soap.wsdl.GetBookTitlesResponse;
import soap.wsdl.GetInfoByCityResponse;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import soap.wsdl.GetWhoISResponse;
import soap.wsdl.SendTextToFaxResponse;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)

public class PlatformApplication extends SpringBootServletInitializer implements ApplicationRunner{



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

	@Bean
	CommandLineRunner lookup(SoapTest quoteClient) {
		return args -> {
			String ticker = "www.jsociety.ir";

			if (args.length > 0) {
				ticker = args[0];
			}
			SendTextToFaxResponse response = quoteClient.getQuote(ticker);


			System.err.println(response.getSendTextToFaxResult());
		};

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PlatformApplication.class);
	}
}
