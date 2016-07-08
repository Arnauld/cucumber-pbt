package sweet.feature.impl2.generator;

import org.quicktheories.quicktheories.core.Generator;
import org.quicktheories.quicktheories.core.PseudoRandom;
import org.quicktheories.quicktheories.core.Shrink;
import org.quicktheories.quicktheories.core.ShrinkContext;
import org.quicktheories.quicktheories.core.Source;
import org.quicktheories.quicktheories.generators.SourceDSL;
import sweet.feature.impl2.domain.OrderLine;
import sweet.feature.impl2.domain.Sweet;
import sweet.util.Streams;

import java.util.stream.Stream;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class OrderLineGenerator implements Generator<OrderLine>, Shrink<OrderLine> {

    private Source<Integer> quantitySource = SourceDSL.integers().all();
    private Source<Sweet> sweetSource = SweetGenerator.source();

    @Override
    public OrderLine next(PseudoRandom prng, int step) {
        return new OrderLine(quantitySource.next(prng, step), sweetSource.next(prng, step));
    }

    @Override
    public Stream<OrderLine> shrink(OrderLine original, ShrinkContext context) {
        return Streams.zip(
                quantitySource.shrink(original.quantity(), context),
                sweetSource.shrink(original.sweet(), context),
                OrderLine::new);
    }

    public static Source<OrderLine> source() {
        return Sources.of(new OrderLineGenerator());
    }
}
