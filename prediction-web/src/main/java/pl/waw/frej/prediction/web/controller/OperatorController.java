package pl.waw.frej.prediction.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Operator;
import pl.waw.frej.prediction.web.model.Converter;
import pl.waw.frej.prediction.web.model.QuestionForm;

@Controller
public class OperatorController {

    @Autowired
    private Operator operator;

    @Autowired
    private Converter converter;

    @RequestMapping(value = "/operator", method = RequestMethod.GET)
    public ModelAndView operatorMain(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/operator");
        modelAndView.addObject("websiteTitle", "Operator");
        return modelAndView;
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public String addQuestion(QuestionForm f) {
        operator.add(converter.getQuestion(f));
        return "redirect:/operator";
    }
}
