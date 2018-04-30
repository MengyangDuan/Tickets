package tickets.util;

import java.text.DecimalFormat;

/**
 * Created by disinuo on 17/3/11.
 */
public class NumberFormatter {

    /**
     * 将传入的浮点数格式化，保留一位小数
     * @param number
     * @return
     */
    public static double saveOneDecimal(double number){
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(number));
    }
    public static double saveTwoDecimal(double number){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(number));
    }
}
