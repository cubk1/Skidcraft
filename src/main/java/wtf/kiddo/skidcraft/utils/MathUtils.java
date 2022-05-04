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
    public static String solveFunpixel(String math) {
    	math = math.replace("等于几?(用数字输入)","")
                .replace("一","1")
                .replace("二","2")
                .replace("三","3")
                .replace("四","4")
                .replace("五","5")
                .replace("六","6")
                .replace("七","7")
                .replace("八","8")
                .replace("九","9")
                .replace("十","10")
                .replace("乘","*")
                .replace("加","+")
                .replace("减","-")
                .replace("以","").replaceAll("<.*>", "");;
    	while(math.contains("之")) {
    		math = math.replaceAll("(.*)之.", "($1)");
    	}
    	try {
    		return solve(math);
    	} catch(Throwable t) {
    		System.out.println("数学题解析失败！"+math);
    		t.printStackTrace();
    		return "";
    	}
    }

	public static boolean parsable(String s, byte type) {
		try {
			switch (type) {
				case 0: {
					Short.parseShort(s);
					break;
				}
				case 1: {
					Byte.parseByte(s);
					break;
				}
				case 2: {
					Integer.parseInt(s);
					break;
				}
				case 3: {
					Float.parseFloat(s);
					break;
				}
				case 4: {
					Double.parseDouble(s);
					break;
				}
				case 5: {
					Long.parseLong(s);
				}
				default: {
					break;
				}
			}
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static double square(double in) {
		return in * in;
	}
}
