package pl.waw.frej.prediction.web.controller.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Operator;
import pl.waw.frej.prediction.web.controller.UserProvider;
import pl.waw.frej.prediction.web.model.*;

import javax.servlet.http.HttpSession;

@Controller
public class LiquidationQuestionsController {
    public static final String URI = "/likwidacja";
    public static final String QUESTION_LIQUIDATE = "/question/liquidate";

    @Autowired
    private Operator operator;
    @Autowired
    private UserProvider userProvider;


    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView liquidationQuestions(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/operator/liquidationQuestions");
        modelAndView.addObject("websiteTitle", "Pytania do likwidacji");
        modelAndView.addObject("questions", operator.findLiquidationQuestions(userProvider.operatorFrom(session)));
        return modelAndView;
    }

    @RequestMapping(value = QUESTION_LIQUIDATE+"/{questionId}/{answerId}", method = RequestMethod.GET)
    public String addQuestion(@PathVariable("questionId") Long questionId, @PathVariable("answerId") Long answerId) {
        operator.liquidate(new QuestionStub(questionId), new AnswerStub(answerId));
        return "redirect:" + URI;
    }
}
