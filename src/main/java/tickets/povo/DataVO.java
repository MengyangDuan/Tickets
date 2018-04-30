package tickets.povo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/5/31.
 */
public class DataVO {
    String name;
    Object value;

    public static List<DataVO> mapToVO(Map map){

        List<DataVO> list=new ArrayList<DataVO>(map.size());

        for(Object key:map.keySet()){
            list.add(new DataVO(key.toString(),map.get(key)));
        }
        return list;
    }

    public DataVO(String name, Object value){
        this.name = name;
        this.value=value;
    }
    public DataVO(){}
    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
