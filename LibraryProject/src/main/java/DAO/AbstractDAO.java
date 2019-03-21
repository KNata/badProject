package DAO;

import java.util.List;

public interface AbstractDAO <K, T> {

    boolean newItem(T anItem);
    List<T> findAllItems();
    T findItemByID(K anID);
    boolean deleteItem(K itemID);

}
