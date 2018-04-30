package tickets.util;

import tickets.model.Orders;

import java.sql.Timestamp;
import java.util.Date;

public class OrderUtil {
    static final int[] dayPoint={336,48,24,0};
    static final double[] percentage={1.0,0.9,0.7,0.5};
    public static Boolean match(Orders orders){
        return true;
    }

    public static double cancelPercentage(Timestamp timestamp){
        Date dateBefore=new Date(timestamp.getTime());
        Date dateAfter=new Date();
        long diffHour=(dateAfter.getTime()-dateBefore.getTime())/(60*60*1000);
        double percent=0;
        for(int i=0;i<dayPoint.length;i++){
            if(diffHour>=dayPoint[i]){
               percent=percentage[i];
               break;
            }
        }
        return percent;
    }
}
