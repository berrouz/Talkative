import com.github.berrouz.common.Contact;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private static final Logger logger = Logger.getLogger(AppConfig.class);

    public @Bean
    Contact serverContact(String firstName, String lastName, int port){
        return new Contact(firstName, lastName, "127.0.0.1", port);
    }
}
