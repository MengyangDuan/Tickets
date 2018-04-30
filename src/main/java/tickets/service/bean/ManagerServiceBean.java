package tickets.service.bean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.ManagerDao;
import tickets.model.Manager;
import tickets.model.PlatformAccount;
import tickets.model.Venue;
import tickets.model.VenueAccount;
import tickets.service.ManagerService;
import tickets.util.MailUtil;
import tickets.util.SettleAccountUtil;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class ManagerServiceBean implements ManagerService{

    @Autowired
    ManagerDao managerDao;

    public Boolean Login(int id, String password) {
        return managerDao.Login(id, password);
    }

    public void sendEmail(Boolean result, String email, int id) {
        DecimalFormat df=new DecimalFormat("0000000");
        String sevenId=df.format(id);
        String message;
        if(result==true) {
            message="审核成功！您的编码是： "+sevenId+"，请使用此编码作为账号登录~";
        }
        else {
            message="审核失败！抱歉~~";
        }
        new Thread(new MailUtil(email, message)).start();
    }

    public void sendModifyEmail(Boolean result, String email) {
        String message;
        if(result==true) {
            message="修改场馆信息成功！";
        }
        else {
            message="修改场馆信息失败！抱歉~~";
        }
        new Thread(new MailUtil(email, message)).start();
    }


    public Manager getManager(int id) {
        return managerDao.getManager(id);
    }

}
