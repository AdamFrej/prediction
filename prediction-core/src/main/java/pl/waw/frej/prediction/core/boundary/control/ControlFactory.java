package pl.waw.frej.prediction.core.boundary.control;

import pl.waw.frej.prediction.core.usecase.AdminImpl;
import pl.waw.frej.prediction.core.usecase.MaklerImpl;
import pl.waw.frej.prediction.core.usecase.OperatorImpl;
import pl.waw.frej.prediction.core.usecase.QuestionReaderImpl;
import pl.waw.frej.prediction.core.boundary.collection.*;

public abstract class ControlFactory {
    public static Admin createAdmin(Users users) {
        return new AdminImpl(users);
    }

    public static Makler createMakler(Answers answers, Questions questions, Offers offers, Users users, Transactions transactions) {
        return new MaklerImpl(answers, questions, offers, users, transactions);
    }

    public static Operator createOperator(Questions questions, Answers answers, Users users) {
        return new OperatorImpl(questions, answers, users);
    }

    public static QuestionReader createQuestionReader(Questions questions){
        return new QuestionReaderImpl(questions);
    }
}
