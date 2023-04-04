package ibf2022.batch2.paf.serverstub.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class AppConfig {
    @Value("${spring.datasource.mongodb.uri}")
    private String mongoUrl;

    @Bean
    public MongoTemplate createMongoTemplate(){
        // create the mongo client 
        MongoClient client = MongoClients.create(mongoUrl);

        return new MongoTemplate(client, "bank");
    }
}
