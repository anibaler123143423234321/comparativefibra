package api.codesoft.com.comparativefibra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories("api.codesoft.com.comparativefibra.repository")
@EntityScan("api.codesoft.com.comparativefibra.model")
public class ComparativefibraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComparativefibraApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Configuración de CORS
				registry.addMapping("/**")
						.allowedOrigins(
								"https://ahorraconfibra.es",
								"https://ahorraconfibra.com",
								"http://localhost:4321"
						)
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
						.allowedHeaders("*") // Permitir todos los encabezados
						.allowCredentials(true); // Permitir credenciales si es necesario
			}
		};
	}
}