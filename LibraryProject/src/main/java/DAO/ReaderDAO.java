package DAO;

import DB_Connection.ConnectionPool;
import Model.Book;
import Model.Reader;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO implements AbstractDAO<Reader, Integer> {

    private static final String INSERT_SQL = "INSERT INTO Reader VALUES(?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_SQL = "SELECT * FROM Reader";
    private static final String SELECT_BY_READER_CARD_SQL = "SELECT * FROM Reader WHERE reader_card = ?";
    private static final String SELECT_BY_EMAIL_SQL = "SELECT * FROM Reader WHERE email = ?";
    private static final String SELECT_BY_NAME_SQL = "SELECT * FROM Reader WHERE name = ?";

    private static final String UPDATE_EMAIL_SQL = "UPDATE Reader SET email = ? WHERE reader_card = ?";
    private static final String UPDATE_PASSWORD_SQL = "UPDATE Reader SET password = ?" +
            "WHERE reader_card = ?";
    private static final String UPDATE_USER_CITY_SQL = "UPDATE Reader SET city = ? WHERE reader_card = ?";
    private static final String UPDATE_USER_ROLE_SQL = "UPDATE Reader SET role = ? WHERE reader_card = ?";

    private static final String UPDATE_READER_SQL = "UPDATE Reader SET email = ?, phone_number = ?,  city = ?, role = ?" +
            "WHERE reader_card = ?";

    private static final String DELETE_READER_SQL = "DELETE FROM Reader WHERE reader_card = ?";

    private static Logger theLogger;

    static {
        theLogger = Logger.getLogger(CatalogDAO.class);
    }

    @Override
    public boolean newItem(Reader anItem) {
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
            preparedStatement.setInt(1, anItem.getReaderCard());
            preparedStatement.setString(2, anItem.getReaderName());
            preparedStatement.setString(3, anItem.getEmail());
            preparedStatement.setString(4, anItem.getPassword());
            preparedStatement.setInt(5, anItem.getPhoneNumber());
            preparedStatement.setString(6, anItem.getCity());
            preparedStatement.setString(7, anItem.getRole());
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
            preparedStatement = conn.prepareStatement(DELETE_READER_SQL);
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

    @Override
    public List<Reader> findAllItems() {
        List<Reader> readerList = new ArrayList<Reader>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = ConnectionPool.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int readerCard = resultSet.getInt("reader_card");
                String readerName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int phone_number = resultSet.getInt("phone_number");
                String city = resultSet.getString("city");
                String role = resultSet.getString("role");
                Reader theReader = Reader.newBuilder().setReaderCard(readerCard).setReaderName(readerName).setEmail(email)
                        .setCity(city).setPassword(password).setRole(role).setPhoneNumber(phone_number).build();
                readerList.add(theReader);
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
        return readerList;
    }

    public Reader findItemByEmail(String anEmail) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Reader theReader = null;
        try {
            conn = ConnectionPool.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_BY_EMAIL_SQL);
            preparedStatement.setString(1, anEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int readerCard = resultSet.getInt("reader_card");
                String readerName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int phone_number = resultSet.getInt("phone_number");
                String city = resultSet.getString("city");
                String role = resultSet.getString("role");
                theReader = Reader.newBuilder().setReaderCard(readerCard).setReaderName(readerName).setEmail(email)
                        .setCity(city).setPassword(password).setRole(role).setPhoneNumber(phone_number).build();
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
        return theReader;
    }

    public Reader findItemByReaderCard(Integer aReaderCard) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Reader theReader = null;
        try {
            conn = ConnectionPool.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_BY_READER_CARD_SQL);
            preparedStatement.setInt(1, aReaderCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int readerCard = resultSet.getInt("reader_card");
                String readerName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int phone_number = resultSet.getInt("phone_number");
                String city = resultSet.getString("city");
                String role = resultSet.getString("role");
                theReader = Reader.newBuilder().setReaderCard(readerCard).setReaderName(readerName).setEmail(email)
                        .setCity(city).setPassword(password).setRole(role).setPhoneNumber(phone_number).build();
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
        return theReader;
    }

    public Reader findItemByReaderName(Integer aReaderName) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Reader theReader = null;
        try {
            conn = ConnectionPool.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_BY_NAME_SQL);
            preparedStatement.setInt(1, aReaderName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int readerCard = resultSet.getInt("reader_card");
                String readerName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int phone_number = resultSet.getInt("phone_number");
                String city = resultSet.getString("city");
                String role = resultSet.getString("role");
                theReader = Reader.newBuilder().setReaderCard(readerCard).setReaderName(readerName).setEmail(email)
                        .setCity(city).setPassword(password).setRole(role).setPhoneNumber(phone_number).build();
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
        return theReader;
    }

    public boolean updatePassword(String newPassword) {
        boolean wasUpdated = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(UPDATE_PASSWORD_SQL);
            preparedStatement.setString(1, newPassword);
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

    public boolean updateEmail(String newEmail) {
        boolean wasUpdated = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(UPDATE_EMAIL_SQL);
            preparedStatement.setString(1, newEmail);
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

    public boolean updateCity(String newCity) {
        boolean wasUpdated = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(UPDATE_USER_CITY_SQL);
            preparedStatement.setString(1, newCity);
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

    public boolean updateUserRole(String newRole) {
        boolean wasUpdated = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(UPDATE_USER_ROLE_SQL);
            preparedStatement.setString(1, newRole);
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

    public boolean updateEmailAndCityAndRoleAndPhoneNumber(String newPassword, int newPhoneNumber,
                                                            String newCity, String newRole) {
        boolean wasUpdated = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(UPDATE_READER_SQL);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, newPhoneNumber);
            preparedStatement.setString(3, newCity);
            preparedStatement.setString(4, newRole);
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
