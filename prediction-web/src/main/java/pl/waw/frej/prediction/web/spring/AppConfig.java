package pl.waw.frej.prediction.web.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.waw.frej.prediction.persistence.configuration.PersistenceConfig;

@Configuration
@Import({
        MvcConfig.class,
        PersistenceConfig.class,
        PredictionCoreConfig.class
})
public class AppConfig {

}
