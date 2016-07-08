package sweet.util;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Streams {

    public static <A, B, C> Stream<C> zip(Stream<A> streamA, Stream<B> streamB, BiFunction<A, B, C> zipper) {
        final Iterator<A> iteratorA = streamA.iterator();
        final Iterator<B> iteratorB = streamB.iterator();
        final Iterator<C> iteratorC = new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return iteratorA.hasNext() && iteratorB.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(iteratorA.next(), iteratorB.next());
            }
        };
        final boolean parallel = streamA.isParallel() || streamB.isParallel();
        return iteratorToFiniteStream(iteratorC, parallel);
    }

    public static <A, B, C, D> Stream<D> zip(Stream<A> streamA, Stream<B> streamB, Stream<C> streamC, Function3<A, B, C, D> zipper) {
        final Iterator<A> iteratorA = streamA.iterator();
        final Iterator<B> iteratorB = streamB.iterator();
        final Iterator<C> iteratorC = streamC.iterator();
        final Iterator<D> iteratorD = new Iterator<D>() {
            @Override
            public boolean hasNext() {
                return iteratorA.hasNext() && iteratorB.hasNext() && iteratorC.hasNext();
            }

            @Override
            public D next() {
                return zipper.apply(iteratorA.next(), iteratorB.next(), iteratorC.next());
            }
        };
        final boolean parallel = streamA.isParallel() || streamB.isParallel() || streamC.isParallel();
        return iteratorToFiniteStream(iteratorD, parallel);
    }

    public static <A, B, C, D, E> Stream<E> zip(Stream<A> streamA, Stream<B> streamB, Stream<C> streamC, Stream<D> streamD, Function4<A, B, C, D, E> zipper) {
        final Iterator<A> iteratorA = streamA.iterator();
        final Iterator<B> iteratorB = streamB.iterator();
        final Iterator<C> iteratorC = streamC.iterator();
        final Iterator<D> iteratorD = streamD.iterator();
        final Iterator<E> iteratorE = new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return iteratorA.hasNext() && iteratorB.hasNext() && iteratorC.hasNext() && iteratorD.hasNext();
            }

            @Override
            public E next() {
                return zipper.apply(iteratorA.next(), iteratorB.next(), iteratorC.next(), iteratorD.next());
            }
        };
        final boolean parallel = streamA.isParallel() || streamB.isParallel() || streamC.isParallel() || streamD.isParallel();
        return iteratorToFiniteStream(iteratorE, parallel);
    }

    public static <T> Stream<T> iteratorToFiniteStream(Iterator<T> iterator, boolean parallel) {
        final Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }

}
