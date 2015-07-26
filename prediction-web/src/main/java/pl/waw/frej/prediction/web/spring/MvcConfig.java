package pl.waw.frej.prediction.web.spring;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.spring.PebbleViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;

@Configuration @EnableWebMvc @ComponentScan(
    basePackages = {"pl.waw.frej.prediction.web"},
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class))
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired ServletContext servletContext;

    @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources");
    }

    @Bean public ServletLoader servletLoader() {
        return new ServletLoader(servletContext);
    }

    @Bean public PebbleEngine pebbleEngine() {
        PebbleEngine pebbleEngine = new PebbleEngine();
        pebbleEngine.setLoader(servletLoader());
        return pebbleEngine;
    }

    @Bean public ViewResolver viewResolver() {
        PebbleViewResolver pebbleViewResolver = new PebbleViewResolver();
        pebbleViewResolver.setPrefix("/WEB-INF/resources/templates");
        pebbleViewResolver.setSuffix(".html");
        pebbleViewResolver.setPebbleEngine(pebbleEngine());
        return pebbleViewResolver;
    }
}
