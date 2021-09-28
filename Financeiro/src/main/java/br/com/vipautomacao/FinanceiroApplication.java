package br.com.vipautomacao;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.vipautomacao.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class FinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceiroApplication.class, args);

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		var app = new SpringApplication(FinanceiroApplication.class);
//		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
		
		System.out.println("");
	}

}
