package pl.waw.frej.prediction.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
import pl.waw.frej.prediction.persistence.database.repository.UserRepository;

import java.util.List;

@Controller
public class SimpleController {

    @Autowired
    private Answers answers;

    @Autowired private UserRepository userRepository;

    @RequestMapping(value = "/makler", method = RequestMethod.GET)
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("makler");
        modelAndView.addObject("websiteTitle", "Moje oferty");
        modelAndView.addObject("content", "tekst");
        return modelAndView;
    }

    @RequestMapping(value = "/answers", method = RequestMethod.POST)
    @ResponseBody
    public List<Answer> getAnswers(User user){
        return answers.findByUser(user);
    }

    @RequestMapping(value = "/add/user", method = RequestMethod.GET) @ResponseBody public UserEntity addUser() {
        return userRepository.save(new UserEntity());
    }

    @RequestMapping(value = "/get/user", method = RequestMethod.GET) @ResponseBody public List<UserEntity> getUser() {
        return userRepository.findAll();
    }
}
