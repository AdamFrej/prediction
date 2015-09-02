package pl.frej.waw.prediction.core.entity;

public enum OfferType {
    BUY, SELL;

    public OfferType flip() {
        return BUY.equals(this) ? SELL : BUY;
    }
}
