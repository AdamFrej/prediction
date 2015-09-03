package pl.waw.frej.prediction.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.frej.waw.prediction.core.persistence.Offers;
import pl.frej.waw.prediction.core.persistence.Questions;
import pl.frej.waw.prediction.core.usecase.Admin;
import pl.frej.waw.prediction.core.usecase.Makler;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
import pl.waw.frej.prediction.web.model.OfferForm;

import javax.servlet.http.HttpSession;

@Controller
public class MaklerController {

    @Autowired
    private Questions questions;
    @Autowired
    private Admin admin;
    @Autowired
    private Offers offers;
    @Autowired
    private Makler makler;


    @RequestMapping(value = "/makler", method = RequestMethod.GET)
    public ModelAndView maklerMain(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler");
        modelAndView.addObject("websiteTitle", "Makler");
        modelAndView.addObject("questions", questions.find());
        modelAndView.addObject("offers", makler.findOffers(getUserFromSession(session)));
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
        modelAndView.addObject("question", questions.find(id));
        return modelAndView;
    }

    @RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
    public ModelAndView offerDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/offerDetails");
        modelAndView.addObject("websiteTitle", "Oferta nr: " + id.toString());
        modelAndView.addObject("offer", offers.find(id));
        return modelAndView;
    }

    @RequestMapping(value = "/addOffer", method = RequestMethod.POST)
    public String addOffer(OfferForm f, HttpSession session) {
        makler.addOffer(f, getUserFromSession(session));
        return "redirect:/makler";
    }

    private UserEntity getUserFromSession(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            user = new UserEntity();
            user.setFunds(15L);
            admin.addUser(user);
            session.setAttribute("user", user);
        }
        return user;
    }
}
