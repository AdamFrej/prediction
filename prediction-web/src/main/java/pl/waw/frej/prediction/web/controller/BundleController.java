package pl.waw.frej.prediction.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Makler;
import pl.waw.frej.prediction.web.model.BundleForm;

import javax.servlet.http.HttpSession;

@Controller
public class BundleController {

    @Autowired
    private Makler makler;
    @Autowired
    private UserProvider userProvider;

    @RequestMapping(value = "/bundle", method = RequestMethod.GET)
    public ModelAndView bundleMain(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/bundle");
        modelAndView.addObject("websiteTitle", "Pakiet");
        return modelAndView;
    }

    @RequestMapping(value = "/bundle/buy", method = RequestMethod.POST)
    public String buyBundle(BundleForm f, HttpSession session) {
        makler.buyBundle(f.getQuestionId(),f.getQuantity(), userProvider.from(session));
        return "redirect:/bundle";
    }
}
