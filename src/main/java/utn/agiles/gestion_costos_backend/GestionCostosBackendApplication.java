package utn.agiles.gestion_costos_backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionCostosBackendApplication {

    public static void main(String[] args) {
        // Carga las variables de entorno desde el archivo .env
        Dotenv dotenv = Dotenv.configure().load();

        // Establece las variables de entorno en el sistema
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(GestionCostosBackendApplication.class, args);
    }
}