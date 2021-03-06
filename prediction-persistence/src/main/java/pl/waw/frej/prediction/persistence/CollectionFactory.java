package pl.waw.frej.prediction.persistence;


import pl.waw.frej.prediction.core.boundary.collection.*;
import pl.waw.frej.prediction.persistence.collection.*;

public abstract class CollectionFactory {
    public static Answers createAnswers(){
        return new PersistentAnswers();
    }
    public static Offers createOffers(){
        return new PersistentOffers();
    }
    public static Questions createQuestions(){
        return new PersistentQuestions();
    }
    public static Transactions createTransactions(){
        return new PersistentTransactions();
    }
    public static Users createUsers(){
        return new PersistentUsers();
    }
}
