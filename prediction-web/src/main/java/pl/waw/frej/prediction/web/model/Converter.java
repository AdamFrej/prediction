package pl.waw.frej.prediction.web.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.boundary.collection.Answers;
import pl.frej.waw.prediction.core.boundary.collection.Users;
import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.entity.Offer;
import pl.frej.waw.prediction.core.boundary.entity.Question;
import pl.frej.waw.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.persistence.EntityFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Converter {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Autowired
    private Users users;
    @Autowired
    private Answers answers;

    public Offer getOffer(OfferForm offerForm) {
        Offer offer = EntityFactory.createOffer();

        offer.setType(offerForm.getType());
        offer.setPrice(offerForm.getPrice());
        offer.setQuantity(offerForm.getQuantity());

        Optional<Answer> answer = answers.find(offerForm.getAnswerId());
        Optional<User> user = users.find(offerForm.getUserId());

        if (answer.isPresent())
            offer.setAnswer(answer.get());
        if (user.isPresent())
            offer.setUser(user.get());

        return offer;
    }

    public Question getQuestion(QuestionForm questionForm){
        Question question= EntityFactory.createQuestion();


        question.setCompletionTime(LocalDateTime.parse(questionForm.getCompletionTime(), DATE_TIME_FORMATTER));
        question.setCompletionValue(questionForm.getCompletionValue());
        question.setDescription(questionForm.getDescription());
        question.setName(questionForm.getName());

        List<Answer> answers = new ArrayList<>();
        answers.add(fromDescription(questionForm.getAnswerOne()));
        answers.add(fromDescription(questionForm.getAnswerTwo()));
        question.setAnswers(answers);

        return question;
    }

    private Answer fromDescription(String description) {
        Answer a = EntityFactory.createAnswer();
        a.setDescription(description);
        return a;
    }
}
