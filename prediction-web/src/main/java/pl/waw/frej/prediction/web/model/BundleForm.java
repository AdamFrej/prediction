package pl.waw.frej.prediction.web.model;


import java.io.Serializable;

public class BundleForm implements Serializable{
    private Long questionId;
    private Long quantity;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
