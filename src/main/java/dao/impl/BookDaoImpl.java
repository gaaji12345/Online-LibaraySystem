package dao.impl;

import dao.custom.BookDao;
import entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookDaoImpl implements BookDao {

    private Session session;
    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<Book> loadAll() {
        String sqlQuery="FROM Book ";
        Query query = session.createQuery(sqlQuery);
        List list =query.list ();
        session.close();
        return list;
    }

    @Override
    public String save(Book book) {
        return (String) session.save (book);
    }

    @Override
    public void update(Book book) {
        session.update (book);
    }
    @Override
    public void delete(Book book) {
        session.delete (book);
    }

    @Override
    public Book getObject(String id) throws Exception {
        return session.get(Book.class,id);
    }

    @Override
    public List<String> roomIds() {
        String hql = "SELECT id from Book ";
        Query<String> query=session.createQuery (hql);
        List<String> results = query.list();
        session.close();
        return results;
    }
}
