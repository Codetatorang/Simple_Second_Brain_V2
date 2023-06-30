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
                .allowedOrigins("https://simple-second-brain-v2.vercel.app", "http://localhost:4200" )
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
