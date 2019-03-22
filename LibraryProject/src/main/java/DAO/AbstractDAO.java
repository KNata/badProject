package DAO;

import java.util.List;

public interface AbstractDAO <T, K> {

   boolean newItem(T anItem);
   List<T> findAllItems();
   boolean deleteItem(K aKey);

}
