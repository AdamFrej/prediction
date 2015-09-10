package pl.waw.frej.prediction.core.boundary.entity;

public enum OfferType {
    BUY, SELL;

    public OfferType flip() {
        return BUY.equals(this) ? SELL : BUY;
    }
}
