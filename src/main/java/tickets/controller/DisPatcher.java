package tickets.controller;

import org.springframework.web.servlet.ModelAndView;
import tickets.model.UserInfo;

public class DisPatcher {

    /**
     * 未登录则返回登录界面的ModelAndView
     * 已登录则返回null
     * @param user
     * @return
     */
    public static ModelAndView checkLoggedIn(UserInfo user){
        if(user==null) return new ModelAndView("redirect:/login");
        else return null;
    }
}
