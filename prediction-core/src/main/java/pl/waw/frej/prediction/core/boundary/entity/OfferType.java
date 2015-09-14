package pl.waw.frej.prediction.core.boundary.entity;

public enum OfferType {
    BUY("kupno"), SELL("sprzedaż");

    private final String name;

    OfferType(String name) {
        this.name = name;
    }

    public OfferType flip() {
        return BUY.equals(this) ? SELL : BUY;
    }

    public String getName() {
        return name;
    }
}
