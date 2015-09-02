package pl.waw.frej.prediction.persistence.database.entity;

import com.google.common.collect.Lists;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Transaction;
import pl.frej.waw.prediction.core.entity.User;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class UserEntity implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<TransactionEntity> transactions;

    @ElementCollection
    @CollectionTable(name="ANSWER_QUANTITIES")
    @MapKeyJoinColumn(name="ANSWER_ID")
    @Column(name="QUANTITY")
    private Map<AnswerEntity, Long> answerQuantities = new HashMap<>();
    private Long funds;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public List<Transaction> getTransactions() {
        return Lists.newArrayList(transactions);
    }

    @Override
    public Map<Answer, Long> getAnswerQuantities() {
        Map<Answer, Long> map = new HashMap<>();
        for (Map.Entry<AnswerEntity, Long> entry : answerQuantities.entrySet()) {
            map.put(entry.getKey(),entry.getValue());
        }
        return map;
    }

    public void setAnswerQuantities(Map<AnswerEntity, Long> answerQuantities){
        this.answerQuantities=answerQuantities;
    }

    @Override
    public void addAnswer(Answer answer, Long quantity) {
        modifyAnswerQuantity(answer, quantity);
    }

    private void modifyAnswerQuantity(Answer answer, Long quantity) {
        Long previousQuantity = answerQuantities.get(answer);
        answerQuantities.put((AnswerEntity)answer, previousQuantity == null ? quantity : previousQuantity + quantity);
    }

    @Override
    public void addAnswer(Answer answer) {
        modifyAnswerQuantity(answer, 1L);
    }

    @Override
    public void removeAnswer(Answer answer) {
        modifyAnswerQuantity(answer, -1L);
    }

    @Override
    public void removeAnswer(Answer answer, Long quantity) {
        Long currentQuantity = answerQuantities.get(answer);

        if (currentQuantity == null)
            throw new IllegalArgumentException("Answer doesn't exist");
        else if (currentQuantity < quantity)
            throw new IllegalArgumentException(String.format("Answer quantity to high %d <:%d", currentQuantity, quantity));

        modifyAnswerQuantity(answer, -quantity);

        if(answerQuantities.get(answer) == 0)
            answerQuantities.remove(answer);
    }

    @Override
    public Long getFunds() {
        return funds;
    }

    @Override
    public void setFunds(Long funds) {
        this.funds = funds;
    }
}
