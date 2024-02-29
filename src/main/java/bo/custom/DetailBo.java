package bo.custom;

import dto.Bookdto;
import dto.Detaildto;
import dto.Userdto;
import entity.Book;
import entity.Detail;
import entity.User;
import org.hibernate.Transaction;
import util.SessionFactoryConfig;

import java.util.ArrayList;
import java.util.List;

public interface DetailBo {

    public List<String> getUserIds() ;


    public List<String> getBookIds() ;



    public Userdto getUser(String id) ;


    public Bookdto getBook(String id) ;


    public Detaildto getDetail(String detailId);
    public boolean updateBook(Bookdto dto) ;



    public List<Detaildto> loadAllDetail();


    public boolean saveDetail(Detaildto det);


    public boolean updateDetail(Detaildto det);


    public boolean deleteDetail(Detaildto det) ;


    public List<Detaildto> loadAll();
}
