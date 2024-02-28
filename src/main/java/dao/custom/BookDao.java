package dao.custom;

import entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public interface BookDao {

    public void setSession(Session session);


    public List<Book> loadAll() ;


    public String save(Book book);


    public void update(Book book);

    public void delete(Book book);


    public Book getObject(String id) throws Exception ;


    public List<String> roomIds() ;
}
