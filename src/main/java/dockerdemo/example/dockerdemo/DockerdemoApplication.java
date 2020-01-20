package dockerdemo.example.dockerdemo;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="dockerdemo.example.dockerdemo.repository")
@EntityScan(basePackages="dockerdemo.example.dockerdemo.model")
@EnableCaching
public class DockerdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerdemoApplication.class, args);
	} 
	
//	@Bean
//	public CacheManager expiringConfiguration() {
//		
//		Configuration config = new ConfigurationBuilder().expiration()
//	      .lifespan(1, TimeUnit.SECONDS)
//	      .build();
//		
//		return new DefaultCacheManager(config);
//	}

}
