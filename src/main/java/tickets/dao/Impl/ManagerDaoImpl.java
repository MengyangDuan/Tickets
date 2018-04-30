package tickets.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.BaseDao;
import tickets.dao.ManagerDao;
import tickets.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ManagerDaoImpl implements ManagerDao{

    @Autowired
    BaseDao baseDao;

    public Boolean Login(int id, String password) {
        String hql="SELECT manager FROM Manager as manager WHERE manager.id= '"+id+"' and manager.password= '"+password+"'";
        List<Manager> managers=baseDao.getByHql(Manager.class,hql);
        if(managers.size()==0)
            return false;
        else
            return true;
    }


    public Boolean updateVenue(Venue venue) {
        return baseDao.update(venue);
    }

    public Manager getManager(int id) {
        Manager manager=baseDao.getEntity(Manager.class,id);
        return  manager;
    }


}
