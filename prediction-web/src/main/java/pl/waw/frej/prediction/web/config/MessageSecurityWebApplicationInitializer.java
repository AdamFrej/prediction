package pl.waw.frej.prediction.web.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(value = 2)
public class MessageSecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {
}
