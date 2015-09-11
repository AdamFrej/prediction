package pl.waw.frej.prediction.web.controller.makler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Makler;
import pl.waw.frej.prediction.web.controller.UserProvider;

import javax.servlet.http.HttpSession;

@Controller
public class WalletController {
    public static final String URI = "/portfel";
    @Autowired
    private Makler makler;
    @Autowired
    private UserProvider userProvider;

    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView wallet(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler/wallet");
        modelAndView.addObject("websiteTitle", "Mój Portfel");
        modelAndView.addObject("offers", makler.findOffers(userProvider.from(session)));
        return modelAndView;
    }
}
