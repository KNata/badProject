package DAO;

import DB_Connection.ConnectionPool;
import Model.BookOrder;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogDAO implements AbstractDAO<BookOrder, Integer>{

    private static final String INSERT_SQL = "INSERT INTO Book_Order VALUES(?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_SQL = "SELECT * FROM Book_Order";
    private static final String SELECT_BY_ISBN_SQL = "SELECT * FROM Book_Order WHERE book_isbn = ?";
    private static final String SELECT_BY_READER_CARD_SQL = "SELECT * FROM Book_Order WHERE reader_card = ?";
    private static final String SELECT_BY_BOOK_ISBN_SQL = "SELECT * FROM Book_Order WHERE title = ?";

    private static final String UPDATE_LOCATION_AND_BOOK_COUNT_SQL = "UPDATE Book_Order SET location = ?, book_count = ? " +
            "WHERE isbn = ?";

    private static Logger theLogger;

    static {
        theLogger = Logger.getLogger(CatalogDAO.class);
    }


    @Override
    public boolean newItem(BookOrder anItem) {
        return false;
    }

    @Override
    public List<BookOrder> findAllItems() {
        return null;
    }

    @Override
    public boolean deleteItem(Integer aKey) {
        return false;
    }
}
