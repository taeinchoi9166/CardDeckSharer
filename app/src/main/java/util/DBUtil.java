package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBUtil {
    Connection conn = null;
    PreparedStatement pstmt = null;

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
    public int insertUser(String id,String passwd){
        try {
            conn = DBUtil.getConnection();

            pstmt = conn.prepareStatement("insert into cds_users(id,password) values(?,?)");
            pstmt.setString(1,id);
            pstmt.setString(2,passwd);

            int n = pstmt.executeUpdate();

            return n;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
