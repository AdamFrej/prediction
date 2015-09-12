package pl.waw.frej.prediction.web.controller.makler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Makler;
import pl.waw.frej.prediction.core.boundary.control.Reader;
import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.web.controller.UserProvider;
import pl.waw.frej.prediction.web.model.BundleForm;
import pl.waw.frej.prediction.web.model.Converter;
import pl.waw.frej.prediction.web.model.OfferForm;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MarketController {

    public static final String URI = "/rynek";
    public static final String ANSWER = "/answer";
    public static final String BUNDLE_BUY = "/bundle/buy";
    public static final String OFFER_ADD = "/offer/add";
    @Autowired
    private Makler makler;

    @Autowired
    private Reader reader;

    @Autowired
    private UserProvider userProvider;
    @Autowired
    private Converter converter;

    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView market() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler/market");
        modelAndView.addObject("websiteTitle", "Notowania");
        modelAndView.addObject("quotes", makler.findQuotes());
        return modelAndView;
    }

    @RequestMapping(value = ANSWER + "/{id}", method = RequestMethod.GET)
    public ModelAndView answerDetail(@PathVariable("id") Long id) {
        Optional<Answer> answer = reader.readAnswer(id);
        Long questionId = null;
        if (answer.isPresent())
            questionId = reader.readQuestion(answer.get()).get().getId();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler/answerDetail");
        modelAndView.addObject("websiteTitle", "Szczegóły pytania");

        modelAndView.addObject("answer", answer.orElse(null));
        modelAndView.addObject("quote", makler.findQuote(id).orElse(null));
        modelAndView.addObject("questionId", questionId);

        return modelAndView;
    }

    @RequestMapping(value = BUNDLE_BUY, method = RequestMethod.POST)
    public String buyBundle(BundleForm f, Principal principal) {
        makler.buyBundle(f.getQuestionId(), f.getQuantity(), userProvider.from(principal));
        return "redirect:" + URI;
    }

    @RequestMapping(value = OFFER_ADD, method = RequestMethod.POST)
    public String addOffer(OfferForm f, Principal principal) {
        User user = userProvider.from(principal);
        f.setUserId(user.getId());
        makler.addOffer(converter.toDomain(f), user);
        return "redirect:" + URI;
    }
}
