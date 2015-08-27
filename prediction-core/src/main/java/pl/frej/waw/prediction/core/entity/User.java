package pl.frej.waw.prediction.core.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private List<String> transactionIds;
    private Map<String, Integer> answerQuantities;
    private Integer funds;

    public List<String> getTransactionIds() {
        return transactionIds;
    }

    public void setTransactionIds(List<String> transactionIds) {
        this.transactionIds = transactionIds;
    }

    public Map<String, Integer> getAnswerQuantities() {
        if (answerQuantities == null) {
            answerQuantities = new HashMap<>();
        }
        return answerQuantities;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }
}
