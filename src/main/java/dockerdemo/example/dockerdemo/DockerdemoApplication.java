package dockerdemo.example.dockerdemo;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.module.SimpleModule;

import dockerdemo.example.dockerdemo.util.CustomizeAddressDeserializer;
import dockerdemo.example.dockerdemo.util.CustomizeAddressSerializer;

@SpringBootApplication
@EnableJpaRepositories(basePackages="dockerdemo.example.dockerdemo.repository")
@EntityScan(basePackages="dockerdemo.example.dockerdemo.model")
@EnableCaching
public class DockerdemoApplication extends SimpleModule {
	
	@Autowired
	CustomizeAddressSerializer customizeAddressSerializer;
	@Autowired
	CustomizeAddressDeserializer custAddressDeserializer;

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
	
//	@Override
//	public void setupModule(SetupContext context) {
//		
//		
//		SimpleSerializers serializers = new SimpleSerializers();
//	    SimpleDeserializers deserializers = new SimpleDeserializers();
//	    
//		
//		serializers.addSerializer(Address.class, customizeAddressSerializer);
//		deserializers.addDeserializer(Address.class, custAddressDeserializer);
//		
//		context.addSerializers(serializers);
//	    context.addDeserializers(deserializers);
//		
//	}
	
	@Bean
	@Primary
	public EmbeddedCacheManager getManagerConfig() {
		GlobalConfiguration gc = new GlobalConfigurationBuilder().clusteredDefault().transport()
				.clusterName("restfulapidemocluster").addProperty("configurationFile", "jgroup-ec2.xml").build();
		// for non-clustered
		Configuration cfg = new ConfigurationBuilder().clustering().cacheMode(CacheMode.DIST_SYNC).memory().size(150)
				.build();
		return new DefaultCacheManager(gc, cfg);
	}

}
