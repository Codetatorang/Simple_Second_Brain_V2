package ibf2022.tfip.simplesecondbrain.Server.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCORSConfiguration implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedOrigins("simplesecondbrain.up.railway.app") //needs to change after serve to railway
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
