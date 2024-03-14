package bo;

import bo.impl.BookBoImpl;
import bo.impl.BranchesBOImpl;
import bo.impl.DetailBoImpl;
import bo.impl.UserBoImpl;

public class BoFactory {
    public static BoFactory boFactory;
    public BoFactory() {
    }

    public BoFactory  getBoFactory(){
        if (boFactory==null){
            boFactory=new BoFactory ();
        }
        return boFactory;
    }

    public enum BOTypes{
       USER,DETAIL,BOOK,BRANCHES
    }

    public static SuperBo getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBoImpl();
            case DETAIL:
                return new DetailBoImpl();
            case BOOK:
                return new BookBoImpl();
            case BRANCHES:
                return new BranchesBOImpl();
            default:
                return null;
        }
    }
}
