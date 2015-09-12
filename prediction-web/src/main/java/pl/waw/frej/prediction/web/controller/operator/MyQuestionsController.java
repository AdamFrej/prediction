package pl.waw.frej.prediction.web.controller.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Operator;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.web.controller.UserProvider;
import pl.waw.frej.prediction.web.model.Converter;
import pl.waw.frej.prediction.web.model.QuestionForm;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class MyQuestionsController {
    public static final String URI = "/pytania";
    public static final String QUESTION_ADD = "/question/add";

    @Autowired
    private Operator operator;
    @Autowired
    private Converter converter;
    @Autowired
    private UserProvider userProvider;


    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView myQuestions(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/operator/myQuestions");
        modelAndView.addObject("websiteTitle", "Moje pytania");
        modelAndView.addObject("questions", operator.findQuestions(userProvider.from(principal)));
        return modelAndView;
    }

    @RequestMapping(value = QUESTION_ADD, method = RequestMethod.POST)
    public String addQuestion(Principal principal, QuestionForm questionForm) {
        Question question = converter.toDomain(questionForm);
        question.setOperator(userProvider.from(principal));
        operator.add(question);
        return "redirect:" + URI;
    }
}
