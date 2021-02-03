package DatabaseHelper;
import java.sql.*;
import java.io.IOException;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Helper {
    private Connection conn;
    private Statement stmt;
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String USER = "user";
    String PASS = "password";
    String DB_URL = "jdbc:mysql://localhost:33333/fireService";

    public void printOut() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Tasks");
            while (rs.next())
                System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            conn.close();


            conn.close();

            // display account.jsp page with given message if successful

        } catch (Exception se) {
            se.printStackTrace();
            // display error.jsp page with given message if unsuccessful
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public void printOutAllTasks(Date date) {

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Tasks");


            LocalDate dateTofind = date.toLocalDate();

            while (rs.next()) {



                long daysBetween = DAYS.between(rs.getDate(6).toLocalDate(), dateTofind);

                if (daysBetween%rs.getInt(7) == 0) {
                    System.out.println(rs.getString(2));
                }
            }


            // while(rs.next())
            //      System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            conn.close();


        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}





