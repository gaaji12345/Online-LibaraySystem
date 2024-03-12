package dao;

import dao.impl.BookDaoImpl;
import dao.impl.BranchesDaoImpl;
import dao.impl.DetailDaoImpl;
import dao.impl.UserDaoImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory=new DAOFactory ();
        }
        return daoFactory;
    }

    public enum DAOTypes{
       BOOK,BRANCHES,DETAIL,USER
    }
    public SuperDao getDao(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDaoImpl();
            case DETAIL:
                return new DetailDaoImpl();
            case BRANCHES:
                return new BranchesDaoImpl();
            case BOOK:
                return new BookDaoImpl();
            default:
                return null;
        }
    }
}
