package pl.waw.frej.prediction.persistence.database.entity;

import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Transaction;
import pl.waw.frej.prediction.core.boundary.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TransactionEntity implements Transaction, Comparable {

    @Id
    @GeneratedValue
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToOne
    private AnswerEntity answer;
    private Long price;
    private Long quantity;

    @OneToOne
    private UserEntity buyer;
    @OneToOne
    private UserEntity seller;

    private LocalDateTime completionDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void setAuthor(User user) {
        this.user = (UserEntity) user;
    }

    @Override
    public Answer getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(Answer answer) {
        this.answer = (AnswerEntity) answer;
    }

    @Override
    public Long getPrice() {
        return price;
    }

    @Override
    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public Long getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public UserEntity getBuyer() {
        return buyer;
    }

    @Override
    public void setBuyer(User buyer) {
        this.buyer = (UserEntity) buyer;
    }

    @Override
    public UserEntity getSeller() {
        return seller;
    }
    @Override
    public void setSeller(User seller) {
        this.seller = (UserEntity) seller;
    }

    @Override
    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    @Override
    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public int compareTo(Object o) {
        return this.getCompletionDate().compareTo(((TransactionEntity) o).getCompletionDate());
    }
}
