package pl.waw.frej.prediction.web.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

@Order(value = 1)
public class AppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan(AppConfig.class.getPackage().getName());

        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic appServlet =
            servletContext.addServlet("appServlet", new DispatcherServlet(context));

        FilterRegistration charEncodingFilterReg = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
        charEncodingFilterReg.setInitParameter("forceEncoding", "true");
        charEncodingFilterReg.addMappingForUrlPatterns(null, false, "/*");

        appServlet.setLoadOnStartup(1);
        Set<String> mappings = appServlet.addMapping("/");

        if (!mappings.isEmpty()) {
            throw new IllegalStateException("Conflicting mappings found! Terminating. " + mappings);
        }
    }
}
