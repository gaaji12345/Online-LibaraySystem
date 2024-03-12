package bo.impl;

import bo.custom.DetailBo;
import dao.DAOFactory;
import dao.custom.BookDao;
import dao.custom.DetailDao;
import dao.custom.UserDao;
import dao.impl.BookDaoImpl;
import dao.impl.DetailDaoImpl;
import dao.impl.UserDaoImpl;
import dto.Bookdto;
import dto.Detaildto;
import dto.Userdto;
import entity.Book;
import entity.Detail;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionFactoryConfig;

import java.util.ArrayList;
import java.util.List;

public class DetailBoImpl  implements DetailBo {


    DetailDao detailDao= (DetailDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.DETAIL);
    //UserDao userDao=new UserDaoImpl();
    UserDao userDao= (UserDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.USER);
  //  BookDao bookDao=new BookDaoImpl();
  BookDao bookDao= (BookDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.BOOK);

    private Session session;
//Student=User
    @Override
    public List<String> getUserIds() {
        try{
            session= SessionFactoryConfig.getInstance ().getSession ();
            userDao.setSession (session);
            return userDao.UserIds();

        }catch (Exception e){
            session.close ();
            return null;
        }
    }

    @Override
    public List<String> getBookIds() {
        try{
            session=SessionFactoryConfig.getInstance ().getSession ();
            bookDao.setSession (session);
            return bookDao.bookIds ();
        }catch (Exception e){
            session.close ();
            return null;
        }
    }

    @Override
    public Userdto getUser(String id) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        userDao.setSession (session);
        try {
            User user = userDao.getObject (id);
            session.close ();
            return new Userdto (
                    user.getUserId(),
                    user.getUserName(),
                    user.getPassword()

            );

        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }
    }

    @Override
    public Bookdto getBook(String id) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        bookDao.setSession (session);
        try {
            Book book=bookDao.getObject (id);
            session.close ();
            return new Bookdto (
                    book.getbId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus()
            );

        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }

    }

    @Override
    public Detaildto getDetail(String detailId) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        detailDao.setSession (session);
        try {
            Detail det = detailDao.getObject (detailId);
            session.close ();
            return new Detaildto (
                    det.getdId(),
                 det.getsDate(),
                    det.geteDate(),
                    new Userdto (
                        det.getUser().getUserId(),
                         det.getUser().getUserName(),
                         det.getUser().getPassword()

                    ),
                    new Bookdto (
                           det.getBook().getbId(),
                           det.getBook().getTitle(),
                           det.getBook().getAuthor(),
                           det.getBook().getGenre(),
                           det.getBook().getStatus()
                    )
            );
        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }
    }

    public boolean updateBook(Bookdto dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            bookDao.setSession (session);
            bookDao.update (new Book (
                    dto.getBId(),
                    dto.getTitle(),
                    dto.getAuthor(),
                    dto.getGenre(),
                    dto.getStatus()
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
    public List<Detaildto> loadAllDetail() {
        return null;
    }

    @Override
    public boolean saveDetail(Detaildto det) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            detailDao.setSession(session);
             detailDao.save(
                     new Detail(
                    det.getDId(),
                    det.getSdate(),
                    det.getEdate(),
                    new User (
                            det.getUser().getUserId(),
                            det.getUser().getUserName(),
                            det.getUser().getPassword()

                    ),
                    new Book (
                            det.getBook().getBId(),
                            det.getBook().getTitle(),
                            det.getBook().getAuthor(),
                            det.getBook().getGenre(),
                            det.getBook().getStatus()

                    )));
            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback ();
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public boolean updateDetail(Detaildto det) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            detailDao.setSession (session);
            detailDao.update (
                    new Detail(
                            det.getDId(),
                            det.getSdate(),
                            det.getEdate(),
                            new User (
                                    det.getUser().getUserId(),
                                    det.getUser().getUserName(),
                                    det.getUser().getPassword()

                            ),
                            new Book (
                                    det.getBook().getBId(),
                                    det.getBook().getTitle(),
                                    det.getBook().getAuthor(),
                                    det.getBook().getGenre(),
                                    det.getBook().getStatus()

                            )));
            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback ();
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public boolean deleteDetail(Detaildto det) {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            detailDao.setSession(session);
            detailDao.delete(
                    new Detail(
                            det.getDId(),
                            det.getSdate(),
                            det.getEdate(),
                            new User (
                                    det.getUser().getUserId(),
                                    det.getUser().getUserName(),
                                    det.getUser().getPassword()

                            ),
                            new Book (
                                    det.getBook().getBId(),
                                    det.getBook().getTitle(),
                                    det.getBook().getAuthor(),
                                    det.getBook().getGenre(),
                                    det.getBook().getStatus()


                            )
                    ));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            transaction.rollback();
        }

        return false;
    }

    @Override
    public List<Detaildto> loadAll() {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        detailDao.setSession (session);
        List<Detail>list= detailDao. loadAll ();
        List<Detaildto>detaildtos= new ArrayList<>();
        System.out.println ("Check1");

        for (Detail det :list) {
            detaildtos.add(new Detaildto (
                    det.getdId(),
                    det.getsDate(),
                    det.geteDate(),
                    new Userdto (
                            det.getUser().getUserId(),
                            det.getUser().getUserName(),
                            det.getUser().getPassword()

                    ),
                    new Bookdto (
                            det.getBook().getbId(),
                            det.getBook().getTitle(),
                            det.getBook().getAuthor(),
                            det.getBook().getGenre(),
                            det.getBook().getStatus()
                    )
            ));
        }

        System.out.println ("Check2");
        return  detaildtos;
    }
}
