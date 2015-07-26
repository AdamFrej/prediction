package pl.waw.frej.prediction.web.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    MvcConfig.class
})
public class Config {

}
