package xyz.erupt.linq.util;

import xyz.erupt.linq.lambda.LambdaInfo;
import xyz.erupt.linq.lambda.LambdaSee;

public class VirtualColumn {

    public String col() {
        return null;
    }

    public Integer number() {
        return null;
    }

    public String string() {
        return null;
    }

    public static LambdaInfo lambdaColumn() {
        return LambdaSee.info(VirtualColumn::col);
    }

    public static LambdaInfo lambdaStr() {
        return LambdaSee.info(VirtualColumn::string);
    }

    public static LambdaInfo lambdaNumber() {
        return LambdaSee.info(VirtualColumn::number);
    }

}
