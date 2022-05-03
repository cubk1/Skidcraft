package wtf.kiddo.skidcraft.utils;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;

public class MathUtils {
    public static String solve(String math){

        JexlEngine jexlEngine = new JexlBuilder().create();
        JexlExpression jexlExpression = jexlEngine.createExpression(math);
        Object evaluate = jexlExpression.evaluate(null);
        return String.valueOf(evaluate);
    }
}
