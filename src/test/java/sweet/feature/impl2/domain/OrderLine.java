package sweet.feature.impl2.domain;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class OrderLine {

    private final int quantity;
    private final Sweet sweet;

    public OrderLine(int quantity, Sweet sweet) {
        this.quantity = quantity;
        this.sweet = sweet;
    }

    public Price total(Prices prices) {
        Price unitPrice = prices.priceOf(sweet);
        return unitPrice.mult(quantity);
    }

    public int quantity() {
        return quantity;
    }

    public Sweet sweet() {
        return sweet;
    }
}
