package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        //return session.get(UsersDataSet.class, id); // new hibernate 5.2.10.Final
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException {


        Criteria criteriaOld = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteriaOld.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name));
    }
}