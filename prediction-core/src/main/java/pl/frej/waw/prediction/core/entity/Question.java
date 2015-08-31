package pl.frej.waw.prediction.core.entity;

import java.util.Date;
import java.util.List;

 public interface Question {

     Long getId();

     String getName();

     void setName(String name);

     String getDescription();

     void setDescription(String description);

     List<Answer> getAnswers();

     void setAnswers(List<Answer> answers);

     Date getCompletionTime();
     void setCompletionTime(Date completionTime);
}
