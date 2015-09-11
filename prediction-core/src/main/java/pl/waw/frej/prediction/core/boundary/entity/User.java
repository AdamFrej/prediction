package pl.waw.frej.prediction.core.boundary.entity;

import java.util.List;
import java.util.Map;

 public interface User {

     List<Transaction> getTransactions();

     Map<Answer, Long> getAnswerQuantities();

     void addAnswer(Answer answer, Long quantity);
     void addAnswer(Answer answer);
     void removeAnswer(Answer answer, Long quantity);
     void removeOneAnswer(Answer answer);
     void removeAllAnswers(Answer answer);

     Long getFunds();

     void setFunds(Long funds);
     void modifyFunds(Long funds);

     Long getId();

     void setName(String name);
     String getName();
 }
