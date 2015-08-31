package pl.waw.frej.prediction.persistence.database.entity;

import pl.frej.waw.prediction.core.entity.Transaction;
import pl.frej.waw.prediction.core.entity.User;

import javax.persistence.*;
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


//    private Map<Long, Long> answerQuantities;
    private Long funds;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    @Override
    public void setTransactions(List<Transaction> transactions) {
//        this.transactions = transactions;
    }

    @Override
    public Map<Long, Long> getAnswerQuantities() {
        return null;
    }

    public void setAnswerQuantities(Map<Long, Long> answerQuantities) {
//        this.answerQuantities = answerQuantities;
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
