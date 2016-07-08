package sweet.pbt;

import java.util.function.Consumer;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public interface Context {
    <T> void set(Object key, T value);

    <T> T get(Object key);

    void invokeIfAbsent(Object order, Consumer<Context> supplier);
}
