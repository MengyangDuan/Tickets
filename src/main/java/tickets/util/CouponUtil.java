package tickets.util;

import java.util.ArrayList;
import java.util.List;

public class CouponUtil {
    static final int[] points={10,20,30,50,100};
    static final int[] coupons={5,15,25,45,100};

    public static int[] getPoints(){
        return points;
    }
    public static int[] getCoupons(){
        return coupons;
    }

    public static List getAvailableCoupons(int point){
       List availableCoupons=new ArrayList();
       for(int i=0;i<points.length;i++){
           if(point>=points[i])
               availableCoupons.add(coupons[i]);
           else
               break;
       }
       return availableCoupons;
    }

    public static int getActualPoints(double totalAcount){
        int result=(int)totalAcount/10;
        return result;
    }
}
