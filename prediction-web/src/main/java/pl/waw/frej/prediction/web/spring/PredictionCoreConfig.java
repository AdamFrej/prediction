package pl.waw.frej.prediction.web.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.frej.waw.prediction.core.persistence.*;
import pl.frej.waw.prediction.core.usecase.Admin;
import pl.frej.waw.prediction.core.usecase.Makler;
import pl.frej.waw.prediction.core.usecase.Operator;

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
    public Operator operator(){
        return new Operator(questions, answers);
    }

    @Bean
    public Makler makler(){
        return new Makler(answers, questions, offers, users, transactions);
    }

    @Bean
    public Admin admin(){
        return new Admin(users);
    }
}
