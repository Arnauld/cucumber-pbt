package sweet.feature.impl2.generator;

import org.quicktheories.quicktheories.core.Generator;
import org.quicktheories.quicktheories.core.PseudoRandom;
import org.quicktheories.quicktheories.core.Shrink;
import org.quicktheories.quicktheories.core.ShrinkContext;
import org.quicktheories.quicktheories.core.Source;
import org.quicktheories.quicktheories.generators.SourceDSL;
import sweet.feature.impl2.domain.Price;

import java.math.BigDecimal;
import java.util.stream.Stream;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class PriceGenerator implements Generator<Price>, Shrink<Price> {

    private Source<BigDecimal> priceSource = SourceDSL.bigDecimals().ofBytes(30).withScale(10);

    @Override
    public Price next(PseudoRandom prng, int step) {
        return Price.of(priceSource.next(prng, step));
    }

    @Override
    public Stream<Price> shrink(Price original, ShrinkContext context) {
        return priceSource.shrink(original.asBigDecimal(), context).map(Price::of);
    }

    public static Source<Price> source() {
        return Sources.of(new PriceGenerator());
    }

}
