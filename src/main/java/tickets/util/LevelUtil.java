package tickets.util;

import java.util.HashMap;
import java.util.Map;

public class LevelUtil {
    static final int[] moneyPoints={0,300,1000,2000,5000,10000};
    static final double[] discounts={1,0.95,0.92,0.88,0.85,0.8};

    public static int getLevel(double money){
        int level=0;
        for(int i=0;i<moneyPoints.length;i++){
            if(money>=moneyPoints[i])
                level++;
            else
                break;
        }
        return level;
    }

    public static Map<String,Integer> createVipLevelMap(){
        int length=moneyPoints.length;
        Map<String,Integer>map=new HashMap<>();
        for(int i=1;i<=length;i++){
            map.put(String.valueOf(i),0);
        }
        return map;
    }

    public static double getDiscount(int level){
        return discounts[level];
    }

    public static int[] getMoneyPoints(){
        return moneyPoints;
    }

    public static double[] getDiscounts(){
        return discounts;
    }
}
