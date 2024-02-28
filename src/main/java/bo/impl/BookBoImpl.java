package bo.impl;

import dao.custom.BookDao;
import dao.impl.BookDaoImpl;
import dto.Bookdto;
import entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionFactoryConfig;

import java.util.ArrayList;
import java.util.List;

public class BookBoImpl {

    private Session session;

    BookDao bookDao=new BookDaoImpl();


    public List<Bookdto> loadAll() {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        bookDao.setSession (session);
        List<Book>list= bookDao.loadAll ();
        List<Bookdto>bookdtoList= new ArrayList<>();

        for (Book book:list) {
            bookdtoList.add(
                    new Bookdto (
                           book.getbId(),
                           book.getTitle(),
                            book.getAuthor(),
                            book.getGenre(),
                            book.getStatus()

                    )
            );
        }

        return bookdtoList;

    }

    public boolean saveBook(Bookdto dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            bookDao.setSession (session);
            bookDao.save (new Book (
                    dto.getBId(),
                    dto.getTitle (),
                    dto.getAuthor (),
                    dto.getGenre (),
                    dto.getStatus()
            ));
            transaction.commit ();
            session.close ();
            return true;

        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
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

    public boolean deleteBook(Bookdto dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            bookDao.setSession (session);
            bookDao.delete (new Book (
                    dto.getBId(),
                    dto.getTitle (),
                    dto.getAuthor(),
                    dto.getGenre (),
                    dto.getStatus()
            ));
            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

}
