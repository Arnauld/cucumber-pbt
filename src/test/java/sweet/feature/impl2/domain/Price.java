package sweet.feature.impl2.domain;

import java.math.BigDecimal;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Price {
    public static final Price NONE = new Price(null);

    public static Price of(double value) {
        return of(new BigDecimal(value));
    }

    public static Price of(BigDecimal value) {
        return new Price(value);
    }

    private final BigDecimal value;

    private Price(BigDecimal value) {
        this.value = value == null ? null : value.setScale(3, BigDecimal.ROUND_FLOOR);
    }

    public Price add(Price other) {
        if (this == NONE)
            return other;
        if (other == NONE)
            return this;
        return new Price(value.add(other.value));
    }

    @Override
    public String toString() {
        return "Price{" + value + '}';
    }

    public Price mult(int factor) {
        if (this == NONE)
            return this;
        return new Price(value.multiply(BigDecimal.valueOf(factor)));
    }

    public BigDecimal asBigDecimal() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        return value.equals(price.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
