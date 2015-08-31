package pl.frej.waw.prediction.core.entity;

import java.util.List;
import java.util.Map;

 public interface User {

     List<Transaction> getTransactions();

     void setTransactions(List<Transaction> transactions);

     Map<Long, Long> getAnswerQuantities();

     Long getFunds();

     void setFunds(Long funds);

     Long getId();
 }
