package tickets.service;

import net.sf.json.JSONObject;
import tickets.model.Manager;
import tickets.model.PlatformAccount;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    Boolean Login(int id,String password);

    void sendEmail(Boolean result, String email, int id);

    void sendModifyEmail(Boolean result, String email);

    Manager getManager(int id);




}
