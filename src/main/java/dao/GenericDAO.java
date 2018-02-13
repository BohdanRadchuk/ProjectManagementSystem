package dao;

import java.util.List;

public interface GenericDAO <T, ID> {
    void save (T t);
    T getById (ID id);
    void remove (ID id);
    void update (T t);
    List<T> getAll();
}
