// package ibf2022.tfip.simplesecondbrain.Server.configs;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.mongodb.core.MongoTemplate;

// import com.mongodb.client.MongoClient;
// import com.mongodb.client.MongoClients;

// @Configuration
// public class AppConfig {
//     @Value("${spring.data.mongodb.uri}")
//     private String mongoUrl;

//     private MongoClient client = null;

//     @Bean
//     public MongoClient mongoClient() {
//         if(null == client)
//             client = MongoClients.create(mongoUrl);
//         return client;
//     }

//     @Bean
//     public MongoTemplate mongoTemplate() {
//         return new MongoTemplate(mongoClient(), "infoDB");
//     }
// }
