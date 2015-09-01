package pl.waw.frej.prediction.persistence.database.entity;

import pl.frej.waw.prediction.core.entity.Transaction;
import pl.frej.waw.prediction.core.entity.User;

import javax.persistence.*;

@Entity
public class TransactionEntity implements Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne @JoinColumn(name = "USER_ID") private UserEntity user;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void setUser(User user) {
//        this.user = (UserEntity)user;
    }
}
