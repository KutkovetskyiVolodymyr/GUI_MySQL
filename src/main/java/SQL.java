import javax.swing.*;
import java.sql.*;
import java.util.Vector;

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
    public Boolean qery(String query) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        if(rs==null)
                return false;
        return true;
    }
    public JTable mySQLqery(String query) throws SQLException {  //запросы
        stmt = con.createStatement();

        rs = stmt.executeQuery(query);
        ResultSetMetaData rsmt = rs.getMetaData();
        int c = rsmt.getColumnCount();
        Vector column = new Vector(c);
        for(int i = 1; i <= c; i++)
        {
            column.add(rsmt.getColumnName(i));
        }
        Vector data = new Vector();
        Vector row = new Vector();
        while(rs.next())
        {
            row = new Vector(c);
            for(int i = 1; i <= c; i++){
                row.add(rs.getString(i));
            }
            data.add(row);
        }
        JTable table = new JTable(data,column);
        return table;

        //return jsp;
    }
    public String[] showtables() {
        int i = 0;
        //rs=null;
        String show = "show tables in "+namebd1;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(show);
            while (rs.next()) {
                i++;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        String[] table = new String[i];
        try {
            i = 0;
            rs = stmt.executeQuery(show);
            while (rs.next()) {
                table[i] = new String();
                table[i] = rs.getString(1);
                i++;
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }



        return table;
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