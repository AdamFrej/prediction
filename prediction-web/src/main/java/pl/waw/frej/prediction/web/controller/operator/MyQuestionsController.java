package pl.waw.frej.prediction.web.controller.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Makler;
import pl.waw.frej.prediction.core.boundary.control.Operator;
import pl.waw.frej.prediction.core.boundary.control.Reader;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.web.controller.UserProvider;
import pl.waw.frej.prediction.web.model.Converter;
import pl.waw.frej.prediction.web.model.QuestionDetailsForm;
import pl.waw.frej.prediction.web.model.QuestionForm;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MyQuestionsController {
    public static final String URI = "/pytania";
    public static final String ADD = "/add";
    public static final String QUESTION = "/question";

    @Autowired
    private Operator operator;
    @Autowired
    private Makler makler;
    @Autowired
    private Reader reader;
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

    @RequestMapping(value = QUESTION + ADD, method = RequestMethod.POST)
    public String addQuestion(Principal principal, QuestionForm questionForm) {
        Question question = converter.toDomain(questionForm);
        question.setOperator(userProvider.from(principal));
        operator.add(question);
        return "redirect:" + URI;
    }

    @RequestMapping(value = QUESTION + "/{id}", method = RequestMethod.GET)
    public ModelAndView answerDetail(@PathVariable("id") Long id) {
        Optional<Question> question = reader.readQuestion(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/operator/questionDetails");
        modelAndView.addObject("websiteTitle", "Szczegóły pytania");

        modelAndView.addObject("question", getQuestionDetails(question.get()));

        return modelAndView;
    }

    private QuestionDetailsForm getQuestionDetails(Question question) {
        QuestionDetailsForm form = new QuestionDetailsForm();

        form.setName(question.getName());
        form.setDescription(question.getDescription());
        form.setLiquidationDate(question.getLiquidationDate());
        form.setLiquidationValue(question.getLiquidationValue());

        form.setAnswerOneName(question.getAnswers().get(0).getName());
        form.setAnswerTwoName(question.getAnswers().get(1).getName());

        Long priceOne = makler.findQuote(question.getAnswers().get(0).getId()).get().getLastTransactionPrice();
        if (priceOne !=null) {
            double answerOnePercentage = priceOne.doubleValue() / question.getLiquidationValue().doubleValue();
            form.setAnswerOnePercentage(answerOnePercentage*100);
        }

        Long priceTwo = makler.findQuote(question.getAnswers().get(1).getId()).get().getLastTransactionPrice();
        if(priceTwo !=null) {
            double answerTwoPercentage = priceTwo.doubleValue() / question.getLiquidationValue().doubleValue();
            form.setAnswerTwoPercentage(answerTwoPercentage*100);
        }

        return form;
    }
}
