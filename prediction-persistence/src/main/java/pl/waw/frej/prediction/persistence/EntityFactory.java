package pl.waw.frej.prediction.persistence;


import pl.frej.waw.prediction.core.boundary.entity.*;
import pl.waw.frej.prediction.persistence.database.entity.*;

public abstract class EntityFactory {
    public static Answer createAnswer(){
        return new AnswerEntity();
    }
    public static Offer createOffer(){
        return new OfferEntity();
    }
    public static Question createQuestion(){
        return new QuestionEntity();
    }
    public static Transaction createTransaction(){
        return new TransactionEntity();
    }
    public static User createUser(){
        return new UserEntity();
    }
}
