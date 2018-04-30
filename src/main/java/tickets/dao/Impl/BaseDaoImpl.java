package tickets.dao.Impl;

import org.springframework.transaction.annotation.Transactional;
import tickets.dao.BaseDao;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BaseDaoImpl implements BaseDao {

	@Autowired
	protected SessionFactory sessionFactory;
	private Session getNewSession(){

		return sessionFactory.openSession();
	}
	private Session getCurrentSession(){
		Session session=sessionFactory.getCurrentSession();
		return session==null?getNewSession():session;
	}

	public <T> List<T> getByHql(Class<T> c,String hql){
		Session session = getCurrentSession();
		return session.createQuery(hql).list();
	}

	public <T> List<T> getByHql_paging(Class<T> c,String hql,int pageNum,int resultNum){
		Session session = getCurrentSession();
		Query query=session.createQuery(hql);
		query.setFirstResult(pageNum);
		query.setMaxResults(resultNum);
		return query.list();
	}

	//TODO 可能要新加【返回排序结果】的接口
	public int save(Object entity) throws Exception {
		Session session=getNewSession();
		Transaction tr=session.beginTransaction();
		try {
			int id=(Integer) session.save(entity);
			return id;
		}catch (Exception e){
			throw e;
		}finally {
			tr.commit();
			session.clear();
			session.close();
		}
	}

	public Boolean saveNoId(Object entity){
		try {
			save(entity);
		}catch (Exception e){
			return false;
		}
		return true;
	}

	public Boolean saveOrUpdate(Object entity) {
		Session session=getNewSession();
		Transaction tr=session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}finally {
			tr.commit();
			session.clear();
			session.close();
		}
		return true;
	}


	public Boolean update(Object entity) {
		Session session=getNewSession();
		Transaction tr=session.beginTransaction();
		try {
			session.merge(entity);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}finally {
			tr.commit();
			session.clear();
			session.close();
		}
		return true;
	}


	public <T> T getEntity(Class<T> c, int id) {

		Session session = sessionFactory.getCurrentSession();
		if (session != null) {
			session.clear(); // internal cache clear
		}

		Cache cache = sessionFactory.getCache();

		if (cache != null) {
			cache.evictAllRegions(); // Evict data from all query regions.
			cache.evictCollectionRegions();
			cache.evictDefaultQueryRegion();
			cache.evictEntityRegions();
			cache.evictQueryRegions();
			cache.evictNaturalIdRegions();
		}
//		Session session=getNewSession();

		T entity=session.get(c,id);
		return entity;
	}


	public <T> T loadProxy(Class<T> c, int id) {
		Session session=getNewSession();
		T entity=session.load(c,id);
		return entity;
	}


	public <T> List<T> getAll(Class<T> c) {
		Session session=getNewSession();
		Criteria criteria=session.createCriteria(c);
		List list=criteria.list();
		return list;
	}


	public <T> List<T> getAllDESC(Class<T> c, String base) {
		Session session=getNewSession();
		Criteria criteria=session.createCriteria(c);
		criteria.addOrder(Order.desc(base));
		return criteria.list();
	}


	public <T> List<T> getAllASC(Class<T> c, String base) {
		Session session=getNewSession();
		Criteria criteria=session.createCriteria(c);
		criteria.addOrder(Order.asc(base));
		return criteria.list();
	}


}
