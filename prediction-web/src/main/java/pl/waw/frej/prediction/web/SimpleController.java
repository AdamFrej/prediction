package pl.waw.frej.prediction.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SimpleController {
    @RequestMapping(value = "/makler", method = RequestMethod.GET)
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("makler");
        modelAndView.addObject("websiteTitle", "Moje oferty");
        modelAndView.addObject("content", "tekst");
        return modelAndView;
    }
}
