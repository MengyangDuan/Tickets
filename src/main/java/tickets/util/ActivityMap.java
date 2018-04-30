package tickets.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ActivityMap {

    private static Map<Integer, String> map = new HashMap<Integer, String>() {{
        put( 1 , "音乐会" );
        put( 2 , "舞蹈" );
        put( 3 , "话剧" );
        put( 4 , "体育比赛" );
    }};
    public static String getTypeName(int no){
       return map.get(no);
    }
    public static int getTypeNo(String typeName){
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            if(entry.getValue().equals(typeName)){
                return entry.getKey();
            }
        }
        return 0;
    }
}
