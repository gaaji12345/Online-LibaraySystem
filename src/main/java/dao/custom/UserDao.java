package dao.custom;

import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public interface UserDao {

    public void setSession(Session session);


    public List<User> loadAll() ;


    public String save(User user);


    public void update(User user);


    public void delete(User user) ;




    public User getObject(String id) throws Exception;
}
