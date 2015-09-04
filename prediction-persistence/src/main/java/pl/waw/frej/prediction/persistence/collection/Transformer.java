package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.entity.Offer;
import pl.frej.waw.prediction.core.boundary.entity.Question;
import pl.frej.waw.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import pl.waw.frej.prediction.persistence.database.entity.OfferEntity;
import pl.waw.frej.prediction.persistence.database.entity.QuestionEntity;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

@Service
public class Transformer {

    public AnswerEntity getAnswerEntity(Answer answer) {
        if(answer instanceof AnswerEntity){
            return (AnswerEntity) answer;
        }

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setName(answer.getName());
        answerEntity.setDescription(answer.getDescription());
        answerEntity.setCompletionTime(answer.getCompletionTime());
        return answerEntity;
    }

    public QuestionEntity getQuestionEntity(Question question) {
        if(question instanceof QuestionEntity){
            return (QuestionEntity) question;
        }

        QuestionEntity qE = new QuestionEntity();
        qE.setName(question.getName());
        qE.setDescription(question.getDescription());
        qE.setAnswers(Lists.newArrayList(
                Iterables.transform(question.getAnswers(), this::getAnswerEntity)));
        qE.setCompletionTime(question.getCompletionTime());
        qE.setCompletionValue(question.getCompletionValue());
        return qE;
    }

    public UserEntity getUserEntity(User user) {
        if(user instanceof UserEntity){
            return (UserEntity) user;
        }

        UserEntity uE = new UserEntity();
        uE.setFunds(user.getFunds());
        uE.setAnswerQuantities(getAnswerEntityLongMap(user.getAnswerQuantities()));
        return uE;
    }

    private Map<AnswerEntity, Long> getAnswerEntityLongMap(Map<Answer, Long> answerLongMap){
        Map<AnswerEntity, Long> map = new HashMap<>();
        for (Map.Entry<Answer, Long> entry : answerLongMap.entrySet()) {
            map.put(getAnswerEntity(entry.getKey()),entry.getValue());
        }
        return map;
    }

    public OfferEntity getOfferEntity(Offer offer) {
        if(offer instanceof OfferEntity){
            return (OfferEntity) offer;
        }
        OfferEntity oE = new OfferEntity();
        oE.setQuantity(offer.getQuantity());
        oE.setUser(offer.getUser());
        oE.setAnswer(offer.getAnswer());
        oE.setCreatedDate(offer.getCreatedDate());
        oE.setPrice(offer.getPrice());
        oE.setType(offer.getType());
        return oE;
    }
}
