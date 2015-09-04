package pl.waw.frej.prediction.web.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.frej.waw.prediction.core.persistence.Users;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import pl.waw.frej.prediction.persistence.database.entity.OfferEntity;
import pl.waw.frej.prediction.persistence.database.entity.QuestionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Converter {

    @Autowired
    private Users users;
    @Autowired
    private Answers answers;

    public OfferEntity getOfferEntity(OfferForm offerForm) {
        OfferEntity offerEntity = new OfferEntity();

        offerEntity.setType(offerForm.getType());
        offerEntity.setPrice(offerForm.getPrice());
        offerEntity.setQuantity(offerForm.getQuantity());

        Optional<Answer> answer = answers.find(offerForm.getAnswerId());
        Optional<User> user = users.find(offerForm.getUserId());

        if (answer.isPresent())
            offerEntity.setAnswer(answer.get());
        if (user.isPresent())
            offerEntity.setUser(user.get());

        return offerEntity;
    }

    public QuestionEntity getQuestionEntity(QuestionForm questionForm){
        QuestionEntity questionEntity= new QuestionEntity();

        questionEntity.setCompletionTime(questionForm.getCompletionTime());
        questionEntity.setCompletionValue(questionForm.getCompletionValue());
        questionEntity.setDescription(questionForm.getDescription());
        questionEntity.setName(questionForm.getName());

        List<Answer> answers = new ArrayList<>();
        answers.add(fromDescription(questionForm.getAnswerOne()));
        answers.add(fromDescription(questionForm.getAnswerTwo()));
        questionEntity.setAnswers(answers);

        return questionEntity;
    }

    private AnswerEntity fromDescription(String description) {
        AnswerEntity a = new AnswerEntity();
        a.setDescription(description);
        return a;
    }
}
