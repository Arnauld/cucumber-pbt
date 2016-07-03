package sweet.feature.step;

import cucumber.api.java.After;
import org.quicktheories.quicktheories.api.QuadConsumer;
import org.quicktheories.quicktheories.api.TriConsumer;
import org.quicktheories.quicktheories.core.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.quicktheories.quicktheories.QuickTheory.qt;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Pbt {
    private List<KeyedSource> sources = new ArrayList<>();
    private List<Consumer<Context>> consumers = new ArrayList<>();

    public void addChecker(Consumer<Context> consumer) {
        consumers.add(consumer);
    }

    @SuppressWarnings("unchecked")
    public void setSource(Object key, Source source) {
        if (this.sources.size() == 4)
            throw new IllegalStateException("No more than 4 sources can be declared");
        this.sources.add(new KeyedSource(key, source));
    }

    @After
    public void checkProperties() {
        switch (sources.size()) {
            case 1:
                qt().forAll(
                        sources.get(0).source).checkAssert(checker());
                break;
            case 2:
                qt().forAll(
                        sources.get(0).source,
                        sources.get(1).source).checkAssert(checker());
                break;
            case 3:
                qt().forAll(
                        sources.get(0).source,
                        sources.get(1).source,
                        sources.get(2).source).checkAssert(checker());
                break;
            case 4:
                qt().forAll(
                        sources.get(0).source,
                        sources.get(1).source,
                        sources.get(2).source,
                        sources.get(3).source).checkAssert(checker());
                break;
        }
    }

    private Checker checker() {
        return new Checker(sources, consumers);
    }

    public static class Checker implements Consumer<Object>,
            BiConsumer<Object, Object>,
            TriConsumer<Object, Object, Object>,
            QuadConsumer<Object, Object, Object, Object> {

        private final List<KeyedSource> sources;
        private List<Consumer<Context>> consumers;

        public Checker(List<KeyedSource> sources, List<Consumer<Context>> consumers) {
            this.sources = sources;
            this.consumers = consumers;
        }

        private void invokeConsumers(Context ctx) {
            consumers.forEach(c -> c.accept(ctx));
        }

        @Override
        public void accept(Object o1) {
            Context ctx = new Context();
            ctx.set(sources.get(0).key, o1);
            invokeConsumers(ctx);
        }

        @Override
        public void accept(Object o1, Object o2) {
            Context ctx = new Context();
            ctx.set(sources.get(0).key, o1);
            ctx.set(sources.get(1).key, o2);
            invokeConsumers(ctx);
        }

        @Override
        public void accept(Object o1, Object o2, Object o3) {
            Context ctx = new Context();
            ctx.set(sources.get(0).key, o1);
            ctx.set(sources.get(1).key, o2);
            ctx.set(sources.get(2).key, o3);
            invokeConsumers(ctx);
        }

        @Override
        public void accept(Object o1, Object o2, Object o3, Object o4) {
            Context ctx = new Context();
            ctx.set(sources.get(0).key, o1);
            ctx.set(sources.get(1).key, o2);
            ctx.set(sources.get(2).key, o3);
            ctx.set(sources.get(3).key, o4);
            invokeConsumers(ctx);
        }
    }

    public static class Context {
        private final Map<Object, Object> values = new HashMap<>();

        void set(Object key, Object value) {
            values.put(key, value);
        }

        @SuppressWarnings("unchecked")
        public <T> T get(Object key) {
            return (T) values.get(key);
        }
    }

    private static class KeyedSource {
        public final Object key;
        public final Source<Object> source;

        private KeyedSource(Object key, Source<Object> source) {
            this.key = key;
            this.source = source;
        }
    }
}
