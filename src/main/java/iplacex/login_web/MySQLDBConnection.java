package iplacex.login_web;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Diego
 */
public class MySQLDBConnection implements Closeable{
    
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_api";
    private static final String USERNAME = "develop";
    private static final String PASSWORD = "admin";
    private static final String MAX_POOL = "250";

    private Connection connection;
    private PreparedStatement statement;
    private Properties properties;
    
    private static MySQLDBConnection instance;

    private MySQLDBConnection(){
        try{
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static MySQLDBConnection getInstance(){
        return instance == null ? new MySQLDBConnection() : instance;
    }
    
    public ResultSet executeQuery(final String query) throws Exception{
        this.statement = connection.prepareStatement(query);
        return this.statement.executeQuery();
    }
    
    @Override
    public void close() throws IOException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
