import DatabaseHelper.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {


        Helper database = new Helper();

        database.printOut();

        Date date = new Date(120, 1, 8);

        database.printOutAllTasks(date);


    }
}
