import javax.swing.*;
import java.sql.*;

/**
 * Created by kutkovetskiy on 27.04.2017.
 */
public class SQL {
    public Connection con =null;
    Statement stmt;
    ResultSet rs;

    String namebd1,url,user,password;

    SQL(String ip,String port1,String user1, String password1,String namebd){
        url="jdbc:mysql://"+ip+":"+port1+"/"+namebd;
        namebd1=namebd;
        user=user1;
        password=password1;
    }

    public void mySQLConect(){
        try {
            con = DriverManager.getConnection(url, user, password);

        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(null, "Wrong");
        }
    }

    public void closedb() throws SQLException {
        con.close();
        stmt.close();
        rs.close();
    }

}

