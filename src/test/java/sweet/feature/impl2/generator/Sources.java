package sweet.feature.impl2.generator;

import org.quicktheories.quicktheories.core.Generator;
import org.quicktheories.quicktheories.core.Shrink;
import org.quicktheories.quicktheories.core.Source;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Sources {

    public static <V, T extends Generator<V> & Shrink<V>> Source<V> of(T generatorAndShrink) {
        return Source.of(generatorAndShrink).withShrinker(generatorAndShrink);
    }
}
