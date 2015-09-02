package pl.frej.waw.prediction.core.entity;

import java.util.List;
import java.util.Map;

 public interface User {

     List<Transaction> getTransactions();

     Map<Answer, Long> getAnswerQuantities();

     void addAnswer(Answer answer, Long quantity);
     void addAnswer(Answer answer);
     void removeAnswer(Answer answer, Long quantity);
     void removeAnswer(Answer answer);

     Long getFunds();

     void setFunds(Long funds);

     Long getId();
 }
