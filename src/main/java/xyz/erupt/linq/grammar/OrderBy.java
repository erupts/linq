package xyz.erupt.linq.grammar;

import xyz.erupt.linq.Linq;
import xyz.erupt.linq.lambda.SFunction;

public interface OrderBy {

    <R> Linq orderBy(SFunction<R, ?> column, Direction direction);


    default <R> Linq orderBy(SFunction<R, ?> column) {
        return orderBy(column, Direction.ASC);
    }

    default <R> Linq orderByDesc(SFunction<R, ?> column) {
        return orderBy(column, Direction.DESC);
    }

    enum Direction {

        ASC,
        DESC

    }

}
