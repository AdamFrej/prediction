package pl.waw.frej.prediction.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.frej.waw.prediction.core.boundary.AnswerController;
import pl.frej.waw.prediction.core.boundary.OfferController;
import pl.frej.waw.prediction.core.boundary.QuestionController;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Users;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
import pl.waw.frej.prediction.persistence.database.repository.OfferRepository;
import pl.waw.frej.prediction.persistence.database.repository.UserRepository;
import pl.waw.frej.prediction.web.model.OfferForm;

import javax.servlet.http.HttpSession;

@Controller
public class MaklerController {
    @Autowired
    private QuestionController questionController;

    @Autowired
    private OfferController offerController;

    @Autowired
    private AnswerController answerController;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OfferRepository offerRepository;


    @RequestMapping(value = "/makler", method = RequestMethod.GET)
    public ModelAndView maklerMain(HttpSession session) {
        UserEntity user = getUserFromSession(session);
        if (user == null) {
            user = new UserEntity();
            user.setFunds(15L);
            userRepository.save(user);
            session.setAttribute("user", user);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/makler");
        modelAndView.addObject("websiteTitle", "Makler");
        modelAndView.addObject("questions", questionController.read());
        modelAndView.addObject("offers", offerController.find(user.getId()));
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/market", method = RequestMethod.GET)
    public ModelAndView market(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/market");
        modelAndView.addObject("websiteTitle", "Notowania");
        modelAndView.addObject("answers", answerController.getPrices());
        return modelAndView;
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
         public ModelAndView questionDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/questionDetails");
        modelAndView.addObject("websiteTitle", "Pytanie nr: " + id.toString());
        modelAndView.addObject("question", questionController.read(id));
        return modelAndView;
    }

    @RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
    public ModelAndView offerDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/offerDetails");
        modelAndView.addObject("websiteTitle", "Oferta nr: " + id.toString());
        modelAndView.addObject("offer", offerRepository.findOne(id).orElse(null));
        return modelAndView;
    }

    @RequestMapping(value = "/addOffer", method = RequestMethod.POST)
    public String addOffer(OfferForm f, HttpSession session) {
        offerController.add(f, getUserFromSession(session).getId());
        return "redirect:/makler";
    }

    private UserEntity getUserFromSession(HttpSession session) {
        return (UserEntity) session.getAttribute("user");
    }
}
