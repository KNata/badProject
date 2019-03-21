package DAO;

import java.util.List;

public interface AbstractDAO <T, K> {

    boolean newItem(T anItem);
    List<T> findAllItems();
    T findItemByParametr(K aParam);
    boolean deleteItem(K itemID);

}
