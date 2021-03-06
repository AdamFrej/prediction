package pl.waw.frej.prediction.web.controller.makler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Makler;
import pl.waw.frej.prediction.web.controller.UserProvider;

import java.security.Principal;

@Controller
public class HistoryController {
    public static final String URI = "/historia";
    @Autowired
    private Makler makler;
    @Autowired
    private UserProvider userProvider;

    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView history(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler/history");
        modelAndView.addObject("websiteTitle", "Historia transakcji");
        modelAndView.addObject("transactions", makler.findTransactions(userProvider.from(principal)));
        return modelAndView;
    }
}
