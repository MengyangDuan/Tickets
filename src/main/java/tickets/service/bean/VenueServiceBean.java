package tickets.service.bean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.AccountDao;
import tickets.dao.ActivityDao;
import tickets.dao.VenueDao;
import tickets.model.*;
import tickets.povo.DataVO;
import tickets.povo.VenueModifyVO;
import tickets.povo.VenueVO;
import tickets.service.VenueService;
import tickets.util.NumberFormatter;

import java.sql.Timestamp;
import java.util.*;

@Transactional
@Service
public class VenueServiceBean implements VenueService {

    @Autowired
    VenueDao venueDao;
    @Autowired
    ActivityDao activityDao;
    @Autowired
    AccountDao accountDao;

    public Boolean register(VenueVO venueVO) {
        Venue venue=new Venue(venueVO);
        return venueDao.saveVenue(venue);
    }

    public Boolean login(int venueId, String password) {
        return venueDao.checkVenue(venueId, password);
    }

    @Override
    public VenueVO getVenueVO(int venueId) {
        return new VenueVO(venueDao.getVenue(venueId));
    }

    @Override
    public Venue getVenue(int venueId) {
        return venueDao.getVenue(venueId);
    }

    public Boolean updateVeunue(VenueVO venueVO) {
        Venue venue=new Venue();
        return venueDao.updateVenue(venue);
    }

    public Boolean updateRegisterState(int venueId, Boolean result) {
        Venue venue=venueDao.getVenue(venueId);
        if(result==true)
            venue.setAuditState(1);
        else
             venue.setAuditState(2);
        return venueDao.updateVenue(venue);
    }

    @Override
    public Boolean updateModifyState(int modifyId, Boolean result) {
        VenueModify venueModify=venueDao.getVenueModify(modifyId);
        Venue venue=venueModify.getVenue();
        if(result==true)
            venueModify.setAuditState(1);
        else
            venueModify.setAuditState(2);
        venueDao.updateVenueModify(venueModify);
        venue.setName(venueModify.getNewName());
        venue.setAuditState(1);
        venue.setSeatMap(venueModify.getNewSeatMap());
        venue.setAddress(venueModify.getNewAddress());
        return venueDao.updateVenue(venue);

    }


    public int releaseActivity(int id, String name, Date date, int type, String description) {
        Venue venue=venueDao.getVenue(id);
        Activity activity=new Activity();
        activity.setName(name);
        activity.setShowType(type);
        activity.setDescription(description);
        activity.setTime(new Timestamp(date.getTime()));
        venue.addActivity(activity);
        int result=venueDao.saveActivity(activity);
        if(result>=0) {
            Boolean updateResult=venueDao.updateVenue(venue);
            if(updateResult==true)
                return result;
            else
                return result;
        }
        else
            return -1;
    }

    public List<VenueVO> getNonCheckedVenues() {
        List<Venue>venues=venueDao.getNonCheckedVenues();
        List<VenueVO>venueVOS=new ArrayList<VenueVO>();
        for(int i=0;i<venues.size();i++){
            venueVOS.add(new VenueVO(venues.get(i)));
        }
        return venueVOS;
    }

    @Override
    public List<VenueModifyVO> getNonCheckedModifies() {
        List<VenueModify>venueModifies=venueDao.getNonCheckedModifies();
        List<VenueModifyVO>venueModifyVOS=new ArrayList<>();
        for(int i=0;i<venueModifies.size();i++){
            VenueModify venueModify=venueModifies.get(i);
            Venue venue=venueModify.getVenue();
            venueModifyVOS.add(new VenueModifyVO(venueModify,venue));
        }
        return venueModifyVOS;
    }


    public Boolean makeTickets(int venueId,int activityId, Map priceMap) {
        Boolean result=true;
        Venue venue=venueDao.getVenue(venueId);
        String seatMap=venue.getSeatMap();
        Activity activity=venueDao.getActivity(activityId);
        char[] array=seatMap.toCharArray();
        int counter=1;
        for(int i=0;i<array.length;i++){
            if(array[i]>='a'&&array[i]<='z'){
                String temp=String.valueOf(array[i]);
                String tempPrice= (String) priceMap.get(temp);
                double price=  Double.parseDouble(tempPrice);
                Ticket ticket=new Ticket(price,String.valueOf(counter));
                activity.addTicket(ticket);
                result=venueDao.saveTicket(ticket);
                counter++;
                if(result==false)
                    break;
            }
        }
       // result=venueDao.updateActivity(activity);
        return result;
    }

    @Override
    public double getOccupancyRate(int venueId) {
        List<Ticket>tickets=venueDao.getTicketsByVenue(venueId);
        int size=tickets.size();
        int counter=0;
        for(int i=0;i<size;i++){
            Ticket ticket=tickets.get(i);
            if(ticket.getIsSeated()==1)
                counter++;
        }
        return counter*1.0/size;
    }

    @Override
    public List<Ticket> getTicketByVenue(int venueId) {
        return venueDao.getTicketsByVenue(venueId);
    }

    @Override
    public Boolean saveVenueModify(VenueModify venueModify) {
        return venueDao.saveVenueModify(venueModify);
    }

    /**
     * 年度大盘点
     * 返回所有有效酒店的以下信息
     * 本年度：总收入、总住店人数、房间总数，酒店名称-ID
     */
    @Override
    public JSONObject getSummaryNumOfAllVenues(){
        JSONArray jsonArray=new JSONArray();
        List<Venue>venues=venueDao.getCheckedVenues();
        for(Venue venue:venues){
            int venueId=venue.getId();
            String venueName=venue.getName();
            List<Activity>activities=activityDao.getActivityByVenue(venueId);
            //总收入
            double income=getAllIncomeByVenue(venueId);
            //总住店人数
            int sellNum=getAllSellTicketNumByVenue(activities);
            jsonArray.add(new Object[]{NumberFormatter.saveOneDecimal(income),sellNum,activities.size(),venueName,venueId});
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("year",2018);
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }
    private int getAllSellTicketNumByVenue(List<Activity>activities){
        int sellNum=0;
        for(Activity activity:activities) {
            List<Ticket>tickets=activityDao.getUnavailableTickets(activity.getId());
            sellNum+=tickets.size();
        }
        return sellNum;
    }
    private double getAllIncomeByVenue(int venueId){
        double income=0;
        List<VenueAccount> venueAccounts=accountDao.getVenueAccount(venueId);
        for(VenueAccount venueAccount:venueAccounts) income+=venueAccount.getMoney();
        return income;
    }

    @Override
    public List<DataVO> getSeatedBookRate(int id) {
        return getLiveInBookRateByDate_Helper(id);
    }

    private List<DataVO>  getLiveInBookRateByDate_Helper(int id) {
        Map<String,Double> map= createMonthMap();
        List<Ticket> tickets=venueDao.getTicketsByVenue(id);
        for(Ticket ticket:tickets){
            //状态的【未入住】 且 现在已经晚于预订的入住日期
            Date ticketTime=ticket.getActivity().getTime();
            if(ticket.getStatus()==1&&ticket.getIsSeated()==0&&ticketTime.before(new Date())){
                String dateFieldStr=String.valueOf(ticketTime.getMonth());
                double num=map.get(dateFieldStr);
                map.put(dateFieldStr,++num);
            }
        }
        Map<String,Double> notCanceled=getNotCanceledBookNum(id);
        for(String key:notCanceled.keySet()){
            double ans=0;
            if(map.containsKey(key)){
                ans= DO_DIVIDE(map.get(key),notCanceled.get(key));
            }
            map.put(key,NumberFormatter.saveTwoDecimal(1-ans));
        }
        return DataVO.mapToVO(map);

    }

    //获得未取消的订单数
    private Map<String,Double> getNotCanceledBookNum(int venueId){
        Map<String,Double> map= createMonthMap();
        List<Activity>activities=activityDao.getActivityByVenue(venueId);
        for(Activity activity:activities) {
            List<Ticket> tickets = activityDao.getUnavailableTickets(activity.getId());
            String dateFieldStr=String.valueOf(activity.getTime().getMonth());
            double num=map.get(dateFieldStr);
            map.put(dateFieldStr,num+tickets.size());
        }
        return map;

    }
    private Map<String,Double> createMonthMap(){
        Map<String,Double> map=new HashMap<>();
        for(int i=0;i<12;i++){
            map.put(String.valueOf(i),0.0);
        }
        return map;
    }


    public static double DO_DIVIDE(double x, double y){
        if(y==0) return 0;
        return x/y;
    }

    @Override
    public List<DataVO> getAllBookNum(int id) {
        return DataVO.mapToVO(getNotCanceledBookNum(id));
    }

    @Override
    public List<DataVO>  getNotCancelledBookNumByActivityType(int venueId){
        List<Activity>activities=venueDao.getActivityByVenue(venueId);
        Map<String,Double> map=new LinkedHashMap();
        for(Activity activity:activities){
            String description=typeToDescription(activity.getShowType());
            if(!map.containsKey(description))
                map.put(description,activityDao.getUnavailableTickets(activity.getId()).size()+0.0);
            else{
                double num=map.get(description);
                map.put(description,activityDao.getUnavailableTickets(activity.getId()).size()+num);
            }
        }
        return DataVO.mapToVO(map);
    }
    private String typeToDescription(int id){
        String str;
        switch (id){
            case 1:
                str= "音乐会";
                break;
            case 2:
                str="舞蹈";
                break;
            case 3:
                str="话剧";
                break;
            default:
                str="体育比赛";
                break;
        }
        return str;
    }

    public List<Object[]> getMoneyVipRate(int venueId){
        List<Object[]> res=new ArrayList<>();
        List<PlatformAccount>platformAccounts=accountDao.getPlatformAccount(venueId);
        List<PlatformAccount>bills_all=new ArrayList<>();
        List<PlatformAccount> bills_vip=new ArrayList<>();
        for(PlatformAccount platformAccount:platformAccounts) {
            if(platformAccount.getUserInfo()!=null)
                bills_vip.add(platformAccount);
            if(platformAccount.getMoney()>0||platformAccount.getUserInfo()!=null)
                bills_all.add(platformAccount);
        }
        Map<String,Double> map_all=getMoneyByDate_Helper(bills_all);
        Map<String,Double> map_vip=getMoneyByDate_Helper(bills_vip);
        for(String key:map_all.keySet()){
            res.add(new Object[]{
                    key,
                    NumberFormatter.saveOneDecimal(map_vip.get(key)),
                    NumberFormatter.saveOneDecimal(map_all.get(key))
            });
        }
        return res;

    }
    private Map<String,Double> getMoneyByDate_Helper(List<PlatformAccount> platformAccounts){
        Map<String,Double> map=createMonthMap();
        for(PlatformAccount platformAccount:platformAccounts){
            String dateFieldStr=String.valueOf(platformAccount.getTimestamp().getMonth());
            if(map.containsKey(dateFieldStr)){
                double money=map.get(dateFieldStr);
                map.put(dateFieldStr,money+platformAccount.getMoney());
            }else {
                map.put(dateFieldStr,platformAccount.getMoney());
            }
        }
        return map;
    }

    public List<DataVO> getIncomeAvg(int venueId){
        List<PlatformAccount>platformAccounts=accountDao.getPlatformAccount(venueId);
        List<PlatformAccount>bills=new ArrayList<>();
        for(PlatformAccount platformAccount:platformAccounts) {
            if(platformAccount.getMoney()>0||platformAccount.getUserInfo()!=null)
                bills.add(platformAccount);
        }
        Map<String,Double> map_money=getMoneyByDate_Helper(bills);
        Map<String,Double> map_peopleNum=getPeopleNumByDate_Helper(bills);
        List<DataVO> vos=new ArrayList<>(map_money.size());
        for(String key:map_money.keySet()){
            double ans=DO_DIVIDE(map_money.get(key),map_peopleNum.get(key));
            vos.add(new DataVO(key,NumberFormatter.saveTwoDecimal(ans)));
        }
        return vos;
    }
    private Map<String,Double> getPeopleNumByDate_Helper(List<PlatformAccount> bills){
        Map<String,Double> map=createMonthMap();
        for(PlatformAccount bill:bills){
            String dateFieldStr=String.valueOf(bill.getTimestamp().getMonth());
            double people=map.get(dateFieldStr);
            map.put(dateFieldStr,people+1);
        }
        return map;
    }

}

