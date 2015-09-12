package pl.waw.frej.prediction.web.controller.makler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Makler;
import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Quote;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.web.controller.UserProvider;
import pl.waw.frej.prediction.web.model.AnswerQuantityForm;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WalletController {
    public static final String URI = "/portfel";
    @Autowired
    private Makler makler;
    @Autowired
    private UserProvider userProvider;

    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView wallet(Principal principal) {
        User user = userProvider.from(principal);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler/wallet");
        modelAndView.addObject("websiteTitle", "Mój Portfel");
        modelAndView.addObject("funds", makler.findFunds(user));
        modelAndView.addObject("answerQuantities", getAnswerQuantities(user));
        modelAndView.addObject("offers", makler.findOffers(user));
        return modelAndView;
    }

    private List<AnswerQuantityForm> getAnswerQuantities(User user) {
        List<Quote> quotes = makler.findQuotes();
        Map<Answer, Long> answerQuantities = makler.getAnswerQuantities(user);
        List<AnswerQuantityForm> forms = new ArrayList<>();
        for (Map.Entry<Answer, Long> entry : answerQuantities.entrySet()) {
            AnswerQuantityForm form = new AnswerQuantityForm();
            Quote quote = quotes.stream().filter(q -> q.getAnswerId().equals(entry.getKey().getId())).findFirst().get();

            form.setAnswerName(entry.getKey().getName());
            form.setQuantity(entry.getValue());
            form.setBuyPrice(quote.getBuyPrice());
            form.setSellPrice(quote.getSellPrice());
            form.setAveragePrice(quote.getLastTransactionPrice());
            forms.add(form);
        }

        return forms;
    }

}
