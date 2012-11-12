import com.github.berrouz.common.Contact;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class AppConfig {

    private static final Logger logger = Logger.getLogger(AppConfig.class);

    public @Bean
    Contact serverContact(){
        Properties properties = new Properties();
        try {
            properties.load(Class.class.getResourceAsStream("/server.properties"));
        } catch (IOException e) {
            logger.error("Cannot open resource while reading server properties", e);
        }
        return new Contact(properties.getProperty("server"),
                properties.getProperty("server"),
                properties.getProperty("ip.address"),
                Integer.parseInt(properties.getProperty("port")));
    }
}
