package bo.impl;

import bo.custom.UserBo;
import dao.custom.UserDao;
import dao.impl.UserDaoImpl;
import dto.Userdto;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionFactoryConfig;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    private Session session;
   // UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.USER);
    UserDao userDao=new UserDaoImpl();
    @Override
    public boolean saveUser(Userdto dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            userDao.setSession (session);
            String id=userDao.save (new User(
                    dto.getUserId (),
                    dto.getUserName (),
                    dto.getPassword ()));
            transaction.commit ();
            session.close ();
            if (id!=null){
                return true;
            }
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

    @Override
    public Userdto getUser(String id) throws Exception {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        userDao.setSession (session);
        User u=userDao.getObject (id);
        session.close ();
        return new Userdto (
                u.getUserId (),
                u.getUserName (),
                u.getPassword ()
        );
    }

    @Override
    public boolean updateUser(Userdto dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            userDao.setSession (session);
            userDao.update (new User (
                    dto.getUserId (),
                    dto.getUserName (),
                    dto.getPassword ()
            ));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

    @Override
    public List<Userdto> loadAll() {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        userDao.setSession (session);
        List<User>list= userDao.loadAll ();
        List<Userdto>userList= new ArrayList<>();

        for (User u:list) {
            userList.add(
                    new Userdto (
                            u.getUserId (),
                            u.getUserName (),
                            u.getPassword ()
                    ));
        }

        return userList;

    }

}
