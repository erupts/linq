package xyz.erupt.linq.grammar;

import xyz.erupt.linq.Linq;
import xyz.erupt.linq.lambda.SFunction;
import xyz.erupt.linq.schema.Column;

public interface Select {

    Linq distinct();

    Linq select(Column... columns);

    //select *
    <T> Linq select(Class<T> table);

    <T> Linq select(SFunction<T, ?>... columns);

    <T> Linq selectAs(SFunction<T, ?> column, String alias);

    <T, A> Linq selectAs(SFunction<T, ?> column, SFunction<A, ?> alias);

    <T> Linq selectExclude(SFunction<T, ?>... columns);



}
