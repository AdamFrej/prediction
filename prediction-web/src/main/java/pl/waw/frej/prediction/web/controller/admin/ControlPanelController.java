package pl.waw.frej.prediction.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlPanelController {
    public static final String URI = "/admin";

    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/admin/controlPanel");
        modelAndView.addObject("websiteTitle", "Panel administracyjny");
        return modelAndView;
    }
}
