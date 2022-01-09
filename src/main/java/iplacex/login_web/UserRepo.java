package iplacex.login_web;

import java.sql.ResultSet;

/**
 *
 * @author Diego
 */
public class UserRepo {
    
    private static final String QUERY_LOGIN = new String("SELECT id_user, user_name, password FROM user WHERE user_name = '%s' AND password = '%s'");
    
    public UserEntity getUserByLogin(String user, String pass) throws Exception {
        UserEntity userEntity = new UserEntity();
        
        MySQLDBConnection db = MySQLDBConnection.getInstance();
        ResultSet result = db.executeQuery(String.format(QUERY_LOGIN, user, pass));
        if(result.next()){
            userEntity.setId(result.getInt("id_user"));
            userEntity.setUserName(result.getString("user_name"));
            userEntity.setPassword(result.getString("password"));
        }
        return userEntity;
    }
    
}
