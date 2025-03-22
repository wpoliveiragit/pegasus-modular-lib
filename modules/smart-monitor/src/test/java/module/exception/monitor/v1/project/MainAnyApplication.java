package module.exception.monitor.v1.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MainAnyApplication {

	/**
	 * NOTA: O PACOTE 'ANY.PROJECT' APENAS REPRESENTA A ÁREA DE TESTES INICIAIS DO
	 * MODULO E A BASE DO PACOTE 'SUPORT' NA AREA DE TESTES UNITÁRIOS
	 */
	public static void main(String[] args) {
		SpringApplication.run(MainAnyApplication.class, args);
	}
}
