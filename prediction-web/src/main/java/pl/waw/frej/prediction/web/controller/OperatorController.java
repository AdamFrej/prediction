package pl.waw.frej.prediction.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.usecase.Operator;
import pl.waw.frej.prediction.web.model.QuestionForm;

import java.util.ArrayList;

@Controller
public class OperatorController {

    @Autowired
    private Operator operator;

    @RequestMapping(value = "/operator", method = RequestMethod.GET)
    public ModelAndView operatorMain(){
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
        operator.add(f);
        return "redirect:/operator";
    }
}
