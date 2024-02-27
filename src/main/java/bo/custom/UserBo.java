package bo.custom;

import dao.custom.UserDao;
import dto.Userdto;

import java.util.List;

public interface UserBo {
    boolean saveUser(Userdto dto);
    Userdto getUser(String id) throws Exception;
    boolean updateUser(Userdto dto);
    List<Userdto> loadAll();
}
