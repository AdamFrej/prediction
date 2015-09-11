package pl.waw.frej.prediction.web.controller.makler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Makler;

import javax.servlet.http.HttpSession;

@Controller
public class MarketController {

    public static final String URI = "/rynek";
    @Autowired
    private Makler makler;

    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView market(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler/market");
        modelAndView.addObject("websiteTitle", "Notowania");
        modelAndView.addObject("answers", makler.findQuotes());
        return modelAndView;
    }
}
