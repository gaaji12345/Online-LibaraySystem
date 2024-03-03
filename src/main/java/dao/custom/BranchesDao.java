package dao.custom;

import entity.Branches;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.SessionFactoryConfig;

import java.util.List;

public interface BranchesDao {

    public boolean save(Branches entity) ;


    public boolean update(Branches entity) ;

    public boolean delete(Branches entity) ;


    public Branches search(String id) ;


    public List<Branches> getAll() ;




    public String findNextBranchId() ;
}
