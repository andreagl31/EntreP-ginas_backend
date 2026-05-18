package com.example.Entrepaginas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EntrepaginasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrepaginasApplication.class, args);
	}
    // Demuestra configuración explícita de hilos
    @Bean
    public org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor taskExecutor() {
        var executor = new org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("Entrepaginas-");
        executor.initialize();
        return executor;
    }
}
