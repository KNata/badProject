package DB_Connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionPool {

        private static BasicDataSource ds = new BasicDataSource();
        private static Properties property = new Properties();
        private static Logger theLogger;


        static {
            theLogger = Logger.getLogger(ConnectionPool.class);

            FileInputStream fis = null;
            try {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream input = classLoader.getResourceAsStream("databaseProperties.properties");
                property.load(input);
                ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                ds.setUrl(property.getProperty("url"));
                ds.setUsername(property.getProperty("user"));
                ds.setPassword(property.getProperty("password"));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        theLogger.error(e.getMessage());
                    }
                }
            }
        }


        public static Connection getConnection() throws SQLException {
            Connection connection = ds.getConnection();
            return connection;
        }
    }
}
