package DAO;

import DB_Connection.ConnectionPool;
import Model.Book;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class BookDAO implements AbstractDAO<Book, Integer> {

    private static final String INSERT_SQL = "INSERT INTO Book VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)";

    private static final String SELECT_ALL_SQL = "SELECT * FROM Book";
    private static final String SELECT_BY_ISBN_SQL = "SELECT * FROM Book WHERE isbn = ?";
    private static final String SELECT_BY_AUTHOR_SQL = "SELECT * FROM Book WHERE author = ?";
    private static final String SELECT_BY_TITLE_SQL = "SELECT * FROM Book WHERE title = ?";

    private static final String UPDATE_LOCATION_SQL = "UPDATE Book SET location = ? WHERE isbn = ?";
    private static final String UPDATE_LOCATION_AND_BOOK_COUNT_SQL = "UPDATE Book SET location = ?, book_count = ? " +
            "WHERE isbn = ?";
    private static final String UPDATE_BOOK_COUNT_SQL = "UPDATE Book SET book_count = ? WHERE isbn = ?";

    private static final String DELETE_SQL = "DELETE FROM Book WHERE isbn = ?";

    private static Logger theLogger;

    static {
        theLogger = Logger.getLogger(BookDAO.class);
    }

    @Override
    public boolean newItem(Book anItem) {
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
            preparedStatement.setString(1, anItem.getIsbn());
            preparedStatement.setString(2, anItem.getAuthor());
            preparedStatement.setString(3, anItem.getTitle());
            preparedStatement.setString(4, anItem.getAnnotation());
            preparedStatement.setString(5, anItem.getOwnLocation());
            preparedStatement.setDate(6, anItem.getYearOfPublishing());
            preparedStatement.setInt(7, anItem.getBookCount());
           // preparedStatement.setArray(8, anItem.getKeyWordsList());
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
    public List<Book> findAllItems() {
        List<Book> bookList = new ArrayList<Book>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String annotation = resultSet.getString("annotation");
                String location = resultSet.getString("location");
                Date yearOfPublishing = resultSet.getDate("year_of_publishing");
                int bookCount = resultSet.getInt("book_count");
                //String keyWords = resultSet.getString("key_words");
                Book theBook = Book.newBuilder().setIsbn(isbn).setAuthor(author).setTitle(title)
                        .setAnnotation(annotation).setBookCount(bookCount).setYearOfPublishing(yearOfPublishing)
                        .setKeyWordsList(null).build();
                bookList.add(theBook);
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
        return bookList;
    }

    @Override
    public boolean deleteItem(Integer itemID) {
        boolean wasDeleted = false;
        if (itemID == null) {
            wasDeleted = false;
        }
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, itemID);
            preparedStatement.executeUpdate();
            wasDeleted = true;
            savePoint = conn.setSavepoint();
            conn.commit();
        } catch (SQLException e) {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
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
        return wasDeleted;
    }

    public List<Book> findBookByTitle(String aTitle) {
        List<Book> bookList = new ArrayList<Book>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(SELECT_BY_TITLE_SQL);
            preparedStatement.setString(1, aTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String isbn = resultSet.getString("isbn");
                String author = resultSet.getString("author");
                String annotation = resultSet.getString("annotation");
                String location = resultSet.getString("location");
                Date yearOfPublishing = resultSet.getDate("year_of_publishing");
                int bookCount = resultSet.getInt("book_count");
                //String keyWords = resultSet.getString("key_words");
                Book theBook = Book.newBuilder().setIsbn(isbn).setAuthor(author).setTitle(title)
                        .setAnnotation(annotation).setBookCount(bookCount).setYearOfPublishing(yearOfPublishing)
                            .setBookLocation(location).setKeyWordsList(null).build();
                bookList.add(theBook);

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
        return bookList;
    }

    public Book findBookByAuthor(String anAuthor) {
        Book theBook = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(SELECT_BY_AUTHOR_SQL);
            preparedStatement.setString(1, anAuthor);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                String annotation = resultSet.getString("annotation");
                String location = resultSet.getString("location");
                Date yearOfPublishing = resultSet.getDate("year_of_publishing");
                int bookCount = resultSet.getInt("book_count");
                //String keyWords = resultSet.getString("key_words");
                theBook = Book.newBuilder().setIsbn(isbn).setAuthor(author).setTitle(title)
                            .setAnnotation(annotation).setBookCount(bookCount).setYearOfPublishing(yearOfPublishing)
                            .setBookLocation(location).setKeyWordsList(null).build();
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
        return theBook;
    }

    public Book findBookByBookISBN(String anISBN) {
        Book theBook = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(SELECT_BY_ISBN_SQL);
            preparedStatement.setString(1, anISBN);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                String annotation = resultSet.getString("annotation");
                String location = resultSet.getString("location");
                Date yearOfPublishing = resultSet.getDate("year_of_publishing");
                int bookCount = resultSet.getInt("book_count");
                //String keyWords = resultSet.getString("key_words");
                theBook = Book.newBuilder().setIsbn(isbn).setAuthor(author).setTitle(title)
                        .setAnnotation(annotation).setBookCount(bookCount).setYearOfPublishing(yearOfPublishing)
                        .setBookLocation(location).setKeyWordsList(null).build();
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
        return theBook;
    }

    private boolean updateBookChangingItsCount(int updatedBookCount) {
        boolean wasUpdated = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(UPDATE_BOOK_COUNT_SQL);
            preparedStatement.setInt(1, updatedBookCount);
            preparedStatement.executeUpdate();
            conn.commit();
            savePoint = conn.setSavepoint();
            wasUpdated = true;
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
        return wasUpdated;
    }

    private boolean updateBookLocation(String newBookLocation) {
        boolean wasUpdated = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(UPDATE_LOCATION_SQL);
            preparedStatement.setString(1, newBookLocation);
            preparedStatement.executeUpdate();
            conn.commit();
            savePoint = conn.setSavepoint();
            wasUpdated = true;
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
        return wasUpdated;
    }

    private boolean updateBookLocationAndBookCount(String newBookLocation, int newBookCount) {
        boolean wasUpdated = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(UPDATE_LOCATION_AND_BOOK_COUNT_SQL);
            preparedStatement.setString(1, newBookLocation);
            preparedStatement.setInt(2, newBookCount);
            preparedStatement.executeUpdate();
            conn.commit();
            savePoint = conn.setSavepoint();
            wasUpdated = true;
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
        return wasUpdated;
    }

}
