package nl.codebase.faceter.common;

/**
 * Created by ruben on 10/29/15.
 */
public interface Transformer<S,D> {
    D transform(S source);
}
