package pl.waw.frej.prediction.web.controller.makler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TipsController {
    public static final String URI = "/tips";


    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView tips() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler/tips");
        modelAndView.addObject("websiteTitle", "Pomoc");
        return modelAndView;
    }
}
