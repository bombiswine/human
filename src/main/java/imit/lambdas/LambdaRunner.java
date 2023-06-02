package imit.lambdas;

import java.util.Objects;
import java.util.function.*;

public class LambdaRunner {
    public static <T, R> R applyFunction(
        final Function<T, R> function,
        final T arg
    ) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(arg);

        return function.apply(arg);
    }
    public static <T> T applyUnaryOperator(
        final UnaryOperator<T> unaryOperator,
        final T arg
    ) {
        Objects.requireNonNull(unaryOperator);
        Objects.requireNonNull(arg);

        return unaryOperator.apply(arg);
    }

    public static <T, U, R> R applyBiFunction(
        final BiFunction<T, U, R> biFunction,
        final T argT,
        final U argU
    ) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(argT);
        Objects.requireNonNull(argU);

        return biFunction.apply(argT, argU);
    }

    public static <T> boolean testPredicate(
        final Predicate<T> predicate,
        final T arg
    ) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(arg);

        return predicate.test(arg);
    }

    public static <T, U> boolean testBiPredicate(
      final BiPredicate<T, U> biPredicate,
      final T argT,
      final U argU
    ) {
        Objects.requireNonNull(biPredicate);
        Objects.requireNonNull(argT);
        Objects.requireNonNull(argU);

        return biPredicate.test(argT, argU);
    }
}
