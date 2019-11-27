package cn.faceland.springbootmydbdbquery.webDev;


import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author c
 * 仅仅本地测试用,用于main方法中测试,需要将名字改为DbConnection
 */
public class DbConnection {

  private String DBDriver; // driver

  private String DBUrl; // db url

  private String DBUser; // db login

  private String DBPw; // db pw

  public DbConnection(String DBDriver, String DBUrl, String DBUser,
                      String DBPw) {
    this.DBDriver = DBDriver;
    this.DBUrl = DBUrl;
    this.DBUser = DBUser;
    this.DBPw = DBPw;
  }

  public DbConnection(){
    DBDriver = "com.mysql.cj.jdbc.Driver";//"org.gjt.mm.mysql.Driver";
//    DBDriver = "com.mysql.jdbc.Driver";//"org.gjt.mm.mysql.Driver";
    // DBUrl = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";  121.196.212.231
    DBUrl = "jdbc:mysql://123.206.129.53:3306/taskpartner?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";//"jdbc:mysql://localhost:3306/JinyiTMS32?characterEncoding=utf-8";
    DBUser = "root";//"root";
    DBPw = "123_xy";
  }

  public Connection getConnection() {
    Connection conn = null;
    try {
      if(StringUtils.isNotEmpty(DBUrl)){
        Class.forName(DBDriver);
        conn = DriverManager.getConnection(DBUrl, DBUser, DBPw);
      }else{
//        conn = DriverManager.getConnection("proxool.db");
      }
    }
    catch (Exception e) {
      Error.print(e);
    }
    return conn;
  }

  public void closeConnetion(Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    }
    catch (SQLException e) {
      Error.print(e);
    }
  }

}
