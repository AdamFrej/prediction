package pl.waw.frej.prediction.web.controller.operator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LiquidationQuestionsController {
    public static final String URI = "/likwidacja";


    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView help() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/operator/liquidationQuestions");
        modelAndView.addObject("websiteTitle", "Pytania do likwidacji");
        return modelAndView;
    }
}
