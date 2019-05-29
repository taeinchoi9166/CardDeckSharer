package util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBUtil {
    private static Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://www.sproutseed.ga:3306/project?serverTimezone=Asia/Seoul&useUnicode=true","tkfrn8448","zkdlf3451");
    }

//    public void uploadDeck(Deck deck){
//        Connection conn = null;
//        try{
//            conn = getConnection();
//
//            String sql = "insert into "
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}
