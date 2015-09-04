package pl.waw.frej.prediction.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.frej.waw.prediction.core.boundary.control.Makler;
import pl.frej.waw.prediction.core.boundary.control.QuestionReader;
import pl.frej.waw.prediction.core.boundary.entity.Offer;
import pl.frej.waw.prediction.core.boundary.entity.Question;
import pl.frej.waw.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.web.model.Converter;
import pl.waw.frej.prediction.web.model.OfferForm;
import pl.waw.frej.prediction.web.spring.ResourceNotFoundException;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MaklerController {

    @Autowired
    private QuestionReader questionReader;
    @Autowired
    private Makler makler;

    @Autowired
    private Converter converter;
    @Autowired
    private UserProvider userProvider;


    @RequestMapping(value = "/makler", method = RequestMethod.GET)
    public ModelAndView maklerMain(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler");
        modelAndView.addObject("websiteTitle", "Makler");
        modelAndView.addObject("questions", questionReader.read());
        modelAndView.addObject("offers", makler.findOffers(userProvider.from(session)));
        return modelAndView;
    }

    @RequestMapping(value = "/market", method = RequestMethod.GET)
    public ModelAndView market(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/market");
        modelAndView.addObject("websiteTitle", "Notowania");
        modelAndView.addObject("answers", makler.findQuotes());
        return modelAndView;
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public ModelAndView questionDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/questionDetails");
        modelAndView.addObject("websiteTitle", "Pytanie nr: " + id.toString());
        Optional<Question> question = questionReader.read(id);
        if(question.isPresent())
            modelAndView.addObject("question", question.get());
        else
            throw new ResourceNotFoundException();
        return modelAndView;
    }

    @RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
    public ModelAndView offerDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/offerDetails");
        modelAndView.addObject("websiteTitle", "Oferta nr: " + id.toString());
        Optional<Offer> offer = makler.findOffer(id);
        if(offer.isPresent())
            modelAndView.addObject("offer", offer.get());
        else
            throw new ResourceNotFoundException();
        return modelAndView;
    }

    @RequestMapping(value = "/addOffer", method = RequestMethod.POST)
    public String addOffer(OfferForm f, HttpSession session) {
        User user = userProvider.from(session);
        f.setUserId(user.getId());
        makler.addOffer(converter.getOffer(f), user);
        return "redirect:/makler";
    }


}
