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
    private static final String SELECT_BY_READER_CARD_SQL = "SELECT * FROM Book_Order WHERE reader_card = ?";
    private static final String SELECT_BY_BOOK_ISBN_SQL = "SELECT * FROM Book_Order WHERE book_isbn = ?";

    private static Logger theLogger;

    static {
        theLogger = Logger.getLogger(CatalogDAO.class);
    }


    @Override
    public boolean newItem(BookOrder anItem) {
        boolean wasAdded = false;
        if (anItem == null) {
            wasAdded = false;
        }
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(INSERT_SQL);
            preparedStatement.setInt(1, anItem.getOrderID());
            preparedStatement.setString(2, anItem.getBook());
            preparedStatement.setString(3, anItem.getReader());
            preparedStatement.setDate(4, anItem.getDateOfLoan());
            preparedStatement.setDate(5, anItem.getReturnDate());
            preparedStatement.setInt(6, anItem.getHowMuchDays());
            preparedStatement.executeUpdate();
            wasAdded = true;
            savePoint = conn.setSavepoint();
            conn.commit();
        } catch (SQLException e) {
            try {
                if (savePoint == null) {
                    conn.rollback();
                } else {
                    conn.rollback(savePoint);
                }
            } catch (SQLException ee) {}
            theLogger.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException e) {}
        }
        return wasAdded;
    }

    @Override
    public List<BookOrder> findAllItems() {
        List<BookOrder> orderList = new ArrayList<BookOrder>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                String bookISBN = resultSet.getString("book_isbn");
                String readerCard = resultSet.getString("reader_card");
                Date dateOfLoan = resultSet.getDate("date_of_loan");
                Date returnDate = resultSet.getDate("date_of_return");
                int daysOnLoan = resultSet.getInt("how_much_days_was_on_loan");
                BookOrder theOrder = BookOrder.newBuilder().setOrderID(orderID).setBook(bookISBN)
                        .setReader(readerCard).setDateOfLoan(dateOfLoan).setDateOfReturn(returnDate)
                        .setDaysOfLoan(daysOnLoan).build();
                orderList.add(theOrder);
            }
        } catch (SQLException e) {
            theLogger.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException e) {}
        }
        return orderList;
    }

    @Override
    public boolean deleteItem(Integer aKey) {
        throw new UnsupportedOperationException();
    }

    public List<BookOrder> findOrdersByReaderCard(String aReaderCard) {
        List<BookOrder> ordersByReaderList = new ArrayList<BookOrder>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectionPool.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_BY_READER_CARD_SQL);
            preparedStatement.setString(1, aReaderCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                String bookISBN = resultSet.getString("book_isbn");
                String readerCard = resultSet.getString("reader_card");
                Date dateOfLoan = resultSet.getDate("date_of_loan");
                Date dateOfReturn = resultSet.getDate("date_of_return");
                int daysWasOnLoan = resultSet.getInt("how_much_days_was_on_loan");
                BookOrder theOrder = BookOrder.newBuilder().setOrderID(orderID).setReader(readerCard).setBook(bookISBN)
                        .setDateOfReturn(dateOfLoan).setDateOfReturn(dateOfReturn).setDaysOfLoan(daysWasOnLoan).build();
                ordersByReaderList.add(theOrder);
            }
        } catch (SQLException e) {
            theLogger.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException e) {}
        }
        return ordersByReaderList;
    }

    public List<BookOrder> findOrdersByBookISBN(String aBookISBN) {
        List<BookOrder> ordersByReaderList = new ArrayList<BookOrder>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectionPool.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_BY_BOOK_ISBN_SQL);
            preparedStatement.setString(1, aBookISBN);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                String bookISBN = resultSet.getString("book_isbn");
                String readerCard = resultSet.getString("reader_card");
                Date dateOfLoan = resultSet.getDate("date_of_loan");
                Date dateOfReturn = resultSet.getDate("date_of_return");
                int daysWasOnLoan = resultSet.getInt("how_much_days_was_on_loan");
                BookOrder theOrder = BookOrder.newBuilder().setOrderID(orderID).setReader(readerCard).setBook(bookISBN)
                        .setDateOfReturn(dateOfLoan).setDateOfReturn(dateOfReturn).setDaysOfLoan(daysWasOnLoan).build();
                ordersByReaderList.add(theOrder);
            }
        } catch (SQLException e) {
            theLogger.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException e) {}
        }
        return ordersByReaderList;
    }
}
