package pl.waw.frej.prediction.web.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.frej.waw.prediction.core.boundary.AnswerController;
import pl.frej.waw.prediction.core.boundary.OfferController;
import pl.frej.waw.prediction.core.boundary.QuestionController;
import pl.frej.waw.prediction.core.boundary.TransactionController;
import pl.frej.waw.prediction.core.entity.EntityFactory;
import pl.frej.waw.prediction.core.persistence.*;
import pl.frej.waw.prediction.core.usecase.SimpleAnswerController;
import pl.frej.waw.prediction.core.usecase.SimpleOfferController;
import pl.frej.waw.prediction.core.usecase.SimpleQuestionController;
import pl.frej.waw.prediction.core.usecase.SimpleTransactionController;
import pl.waw.frej.prediction.persistence.collection.PersistentFactory;

@Configuration
public class PredictionCoreConfig {
    @Autowired
    private Offers offers;
    @Autowired
    private Users users;
    @Autowired
    private Questions questions;
    @Autowired
    private Transactions transactions;
    @Autowired
    private Answers answers;

    @Bean
    EntityFactory entityFactory(){
        return new PersistentFactory();
    }

    @Bean
    TransactionController transactionController(){
        return new SimpleTransactionController(transactions,offers, answers);
    }

    @Bean
    OfferController offerController(){
        return new SimpleOfferController(offers,users, transactionController());
    }

    @Bean
    QuestionController questionController(){
        return new SimpleQuestionController(questions, answers);
    }

    @Bean
    AnswerController answerController(){
        return new SimpleAnswerController(answers, offers, entityFactory());
    }
}
