package tickets.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * Created by disinuo on 17/3/10.
 */
public class DateHandler {
    public static int GET_CURRENT_YEAR(){
        return getFieldFromLong(Calendar.YEAR,new Date().getTime());
    }
    public static long fieldToLong(int dateType,int value){
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(0);
        c.set(dateType,value);
        return c.getTimeInMillis();
    }

    /**
     * 比如 输入2016-->先构建2016-1-1 0：0：0再转换成long
     * @param year
     * @return
     */
    public static long yearToLong(int year){
        Calendar helper=Calendar.getInstance();
        helper.set(year,0,1,0,0,0);
        return helper.getTimeInMillis();
    }
    public static long calculateStartOfToday(long date){
        int year=getFieldFromLong(Calendar.YEAR,date);
        int month=getFieldFromLong(Calendar.MONTH,date);
        int day=getFieldFromLong(Calendar.DAY_OF_MONTH,date);
        Calendar helper=Calendar.getInstance();
        helper.set(year,month,day,0,0,0);
        return helper.getTimeInMillis();
    }

    /**
     * DAY_OF_WEEK  周日开始，=1；周一 =2
     */
    public static int getFieldFromLong(int dateType,long date){
        Calendar helper=Calendar.getInstance();
        helper.setTimeInMillis(date);
        if(dateType==Calendar.WEDNESDAY) return helper.get(Calendar.DAY_OF_WEEK);
        return helper.get(dateType);
    }

    public static String monthToShow(int value){
        return (value+1)+"月";
    }

    public static String yearToShow(int year){
        return year+"年";
    }
    public static Date longToDate(long time){
        return new Date(time);
    }

    public static String longToStr_noTime(long time){
        Date date=new Date(time);
        return formatter_noTime.format(date);
    }
    public static String longToStr_withTime(long time){
        Date date=new Date(time);
        return formatter_withTime.format(date);
    }


    /**
     * 将毫秒转化成天数
     * @param milli
     * @return
     */
    public static double milliSecondToDay(long milli){
        double ans=milli/(1000*60*60*24);
        return ans;
    }

    public static long dayToMilliSecond(double day){
        long ans=(long)(day*24*60*60*1000);
        return ans;
    }
    public static long strToLong(String time){
        try {
            Date date=formatter_noTime.parse(time);
            return date.getTime();
        }catch (ParseException e){
            e.printStackTrace();
        }
        return 0;
    }
    public static long add(long originalDate,int type,int num){
        Calendar helper=Calendar.getInstance();
        helper.setTimeInMillis(originalDate);
        helper.add(type,num);
        return helper.getTimeInMillis();
    }
    private static SimpleDateFormat formatter_noTime=new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat formatter_withTime=new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static void main(String[] args){
        int currentYear=DateHandler.GET_CURRENT_YEAR();
        long startOfThisYear=DateHandler.yearToLong(currentYear);
        long startOfSecondYear=DateHandler.add(startOfThisYear,Calendar.YEAR,-1);
        long startOfFirstYear=DateHandler.add(startOfThisYear,Calendar.YEAR,-2);
        System.out.println(longToStr_withTime(startOfThisYear));
        System.out.println(longToStr_withTime(startOfSecondYear));
        System.out.println(longToStr_withTime(startOfFirstYear));
    }
}
