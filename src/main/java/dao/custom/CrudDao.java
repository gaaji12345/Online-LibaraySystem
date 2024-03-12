package dao.custom;

import java.util.List;

public interface CrudDao <T>{
    List<T> loadAll();
    String save(T t);
    void update(T t);
    void delete(T t);
    T getObject(String id) throws Exception;
}
