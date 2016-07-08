package sweet.util;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public interface Function4<A, B, C, D, E> {
    E apply(A a, B b, C c, D d);
}
