package sweet.feature.impl2.generator;

import org.quicktheories.quicktheories.core.Generator;
import org.quicktheories.quicktheories.core.PseudoRandom;
import org.quicktheories.quicktheories.core.Shrink;
import org.quicktheories.quicktheories.core.ShrinkContext;
import org.quicktheories.quicktheories.core.Source;
import org.quicktheories.quicktheories.generators.SourceDSL;
import sweet.feature.impl2.domain.Sweet;

import java.util.stream.Stream;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class SweetGenerator implements Generator<Sweet>, Shrink<Sweet> {

    private Source<String> nameSource = SourceDSL.strings().basicLatinAlphabet().ofLengthBetween(0, 500);

    @Override
    public Sweet next(PseudoRandom prng, int step) {
        return new Sweet(nameSource.next(prng, step));
    }

    @Override
    public Stream<Sweet> shrink(Sweet original, ShrinkContext context) {
        return nameSource.shrink(original.name(), context).map(Sweet::new);
    }

    public static Source<Sweet> source() {
        return Sources.of(new SweetGenerator());
    }
}
