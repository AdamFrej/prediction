package pl.waw.frej.prediction.web.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.frej.waw.prediction.core.boundary.OfferController;
import pl.frej.waw.prediction.core.boundary.QuestionController;
import pl.frej.waw.prediction.core.boundary.TransactionController;
import pl.frej.waw.prediction.core.persistence.*;
import pl.frej.waw.prediction.core.usecase.SimpleOfferController;
import pl.frej.waw.prediction.core.usecase.SimpleQuestionController;
import pl.frej.waw.prediction.core.usecase.SimpleTransactionController;

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
    TransactionController transactionController(){
        return new SimpleTransactionController(transactions,offers, answers);
    }

    @Bean
    OfferController offerController(){
        return new SimpleOfferController(offers,users, transactionController());
    }

    @Bean
    QuestionController quetionController(){
        return new SimpleQuestionController(questions);
    }
}
