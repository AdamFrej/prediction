package pl.waw.frej.prediction.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.waw.frej.prediction.core.boundary.collection.*;
import pl.waw.frej.prediction.core.boundary.control.*;
import pl.waw.frej.prediction.persistence.CollectionFactory;

@Configuration
public class PredictionCoreConfig {

    @Bean
    public Answers answers(){
        return CollectionFactory.createAnswers();
    }
    @Bean
    public Offers offers(){
        return CollectionFactory.createOffers();
    }
    @Bean
    public Questions questions(){
        return CollectionFactory.createQuestions();
    }

    @Bean
    public Transactions transactions(){
        return CollectionFactory.createTransactions();
    }
    @Bean
    public Users users(){
        return CollectionFactory.createUsers();
    }

    @Bean
    public Operator operator(){
        return ControlFactory.createOperator(questions(), answers(), users());
    }

    @Bean
    public Makler makler(){
        return ControlFactory.createMakler(answers(), questions(), offers(), users(), transactions());
    }

    @Bean
    public Admin admin(){
        return ControlFactory.createAdmin(users());
    }

    @Bean
    public Reader questionReader(){
        return ControlFactory.createQuestionReader(questions(), answers(), offers());
    }
}
