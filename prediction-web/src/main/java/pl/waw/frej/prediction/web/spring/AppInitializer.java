package pl.waw.frej.prediction.web.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class AppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan(AppConfig.class.getPackage().getName());

        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic appServlet =
            servletContext.addServlet("appServlet", new DispatcherServlet(context));

        appServlet.setLoadOnStartup(1);
        Set<String> mappings = appServlet.addMapping("/");

        if (!mappings.isEmpty()) {
            throw new IllegalStateException("Conflicting mappings found! Terminating. " + mappings);
        }
    }
}
