package pl.waw.frej.prediction.persistence.database.entity;


import org.springframework.data.annotation.CreatedDate;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;
import pl.frej.waw.prediction.core.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class OfferEntity implements Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OfferType offerType;
    private Long price;
    private Long quantity;

    @OneToOne
    private AnswerEntity answer;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @CreatedDate
    LocalDateTime createdDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public OfferType getType() {
        return offerType;
    }

    @Override
    public void setType(OfferType offerType) {
        this.offerType = offerType;
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
    public Answer getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(Answer answer) {
        this.answer = (AnswerEntity) answer;
    }

    @Override
    public void setUser(User user) {
        this.user = (UserEntity) user;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate (LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
