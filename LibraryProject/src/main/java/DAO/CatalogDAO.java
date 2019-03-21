package DAO;

import Model.BookOrder;

import java.util.List;

public class CatalogDAO implements AbstractDAO<BookOrder, Integer>{


    @Override
    public boolean newItem(Integer anItem) {
        return false;
    }

    @Override
    public List<Integer> findAllItems() {
        return null;
    }

    @Override
    public Integer findItemByID(BookOrder anID) {
        return null;
    }

    @Override
    public boolean deleteItem(BookOrder itemID) {
        return false;
    }
}
