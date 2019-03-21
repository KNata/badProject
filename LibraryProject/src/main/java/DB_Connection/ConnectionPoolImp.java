package DB_Connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoolImp implements ConnectionPool {

    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;
    private static int MAX_CONNECTION_SIZE = 20;

    private static Logger logger;

    static {
        logger = Logger.getLogger(ConnectionPoolImp.class);
    }

    public ConnectionPoolImp(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static ConnectionPoolImp create(String url, String user, String password) throws SQLException {

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPoolImp(url, user, password);
    }

    // standard constructors

    @Override
    public Connection getConnection() {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_CONNECTION_SIZE) {
                try {
                    connectionPool.add(createConnection(url, user, password));
                } catch (SQLException e) {
                    logger.error("");
                }
            } else {
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
