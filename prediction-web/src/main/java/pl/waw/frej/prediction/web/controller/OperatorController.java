package pl.waw.frej.prediction.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.frej.waw.prediction.core.boundary.QuestionController;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.waw.frej.prediction.persistence.database.entity.QuestionEntity;
import pl.waw.frej.prediction.persistence.database.repository.QuestionRepository;
import pl.waw.frej.prediction.web.model.AnswerForm;
import pl.waw.frej.prediction.web.model.QuestionForm;

import java.util.ArrayList;

@Controller
public class OperatorController {

    @Autowired
    private QuestionController questionController;

    @RequestMapping(value = "/operator", method = RequestMethod.GET)
    public ModelAndView maklerMain(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/operator");
        modelAndView.addObject("websiteTitle", "Operator");
        return modelAndView;
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public String addQuestion(QuestionForm f) {
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(f.getAnswerOne());
        answers.add(f.getAnswerTwo());
        f.setAnswers(answers);
        questionController.add(f);
        return "redirect:/operator";
    }
}
