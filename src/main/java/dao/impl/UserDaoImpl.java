package dao.impl;

import dao.custom.UserDao;
import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<User> loadAll() {
        String sqlQuery="FROM User ";
        Query query = session.createQuery(sqlQuery);
        List list =query.list ();
        session.close();
        return list;
    }

    @Override
    public String save(User user) {
        return (String) session.save (user);
    }

    @Override
    public void update(User user) {
        session.update (user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User getObject(String id) throws Exception {
        return session.get (User.class,id);
    }
}
