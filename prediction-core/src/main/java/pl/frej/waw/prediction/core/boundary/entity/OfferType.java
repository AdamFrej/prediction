package pl.frej.waw.prediction.core.boundary.entity;

public enum OfferType {
    BUY, SELL;

    public OfferType flip() {
        return BUY.equals(this) ? SELL : BUY;
    }
}
