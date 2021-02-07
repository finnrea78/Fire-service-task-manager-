package DatabaseHelper;
import com.mysql.cj.exceptions.CJConnectionFeatureNotAvailableException;

import java.sql.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Helper {
    private Connection conn;
    private Statement stmt;
    private Statement stmt2;
    private Statement stmt3;
    private PreparedStatement prepStmt;
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String USER = "user";
    private String PASS = "password";
    private String DB_URL = "jdbc:mysql://localhost:33333/fireService";


    public void printOutAllTasks(LocalDate date) {


        try {
            System.out.println();
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            stmt3 = conn.createStatement();
            String query = "select Dates.DateTask,  Tasks.Description, FireWorkers.Name from ((Dates inner join " +
                    "FireWorkers on Dates.WorkerID = FireWorkers.Id) inner join Tasks on  Dates.TaskId = Tasks.Id)" +
                    " where Dates.DateTask = '" + java.sql.Date.valueOf(date) + "'";
            ResultSet rsTasks = stmt.executeQuery("select Tasks.Id, Tasks.Description, Tasks.Frequency, Tasks.DayOfMonth, Tasks.StartDate from Tasks WHERE Id NOT IN " +
                    "(SELECT TaskId FROM Dates WHERE DateTask = '" + java.sql.Date.valueOf(date) +"')");
            ResultSet rsCrew = stmt2.executeQuery("select * from FireCrew");
            ResultSet rsAssignedTasks = stmt3.executeQuery(query);

            int crew = -1;
            boolean serviceEngine1 = false;
            boolean serviceEngine2 = false;

            if (14 <= date.getDayOfMonth() && date.getDayOfMonth() < 22) {
                serviceEngine1 = true;
            }
            if (1 <= date.getDayOfMonth() && date.getDayOfMonth() < 9) {
                serviceEngine2 = true;
            }



            while(rsAssignedTasks.next()){
                System.out.println(rsAssignedTasks.getString(2) + " - " + rsAssignedTasks.getString(3));
            }


            while (rsCrew.next()) {
                long daysbetween = DAYS.between(rsCrew.getDate(2).toLocalDate(), date);
                if (daysbetween % 14 < 7) {
                    crew = rsCrew.getInt(1);
                }
            }


            while (rsTasks.next()) {

                if (rsTasks.getInt(4) != 0) {
                    if (rsTasks.getInt(4) == date.getDayOfMonth()) {
                        System.out.println(rsTasks.getString(2) + " - Crew: " + crew + " - not assigned to an individual");
                    }
                } else {
                    long daysBetween = DAYS.between(rsTasks.getDate(5).toLocalDate(), date);
                    if ((rsTasks.getInt(1) == 3 && serviceEngine1 && daysBetween %
                            rsTasks.getInt(3) == 0) || (rsTasks.getInt(1) == 4 &&
                            serviceEngine2 && daysBetween % rsTasks.getInt(3) == 0)) {
                            System.out.println("Sorry " + rsTasks.getString(2) + " can't occur because it is being serviced this week");
                    } else {

                        if (daysBetween % rsTasks.getInt(3) == 0) {
                            System.out.println(rsTasks.getString(2) + " - Crew: " + crew + " - not assigned to an individual");
                        }
                    }

                }
            }

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


    public void printOutAllTasks(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> totalDates = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            totalDates.add(startDate);
            startDate = startDate.plusDays(1);
        }


        try {
            Class.forName(JDBC_DRIVER);

            for (LocalDate dateToFind : totalDates) {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                stmt2 = conn.createStatement();
                stmt3 = conn.createStatement();
                String query = "select Dates.DateTask,  Tasks.Description, FireWorkers.Name from ((Dates inner join " +
                        "FireWorkers on Dates.WorkerID = FireWorkers.Id) inner join Tasks on  Dates.TaskId = Tasks.Id)" +
                        " where Dates.DateTask = '" + Date.valueOf(dateToFind) + "'";
                ResultSet rsTasks = stmt.executeQuery("select Tasks.Id, Tasks.Description, Tasks.Frequency, Tasks.DayOfMonth, Tasks.StartDate from Tasks WHERE Id NOT IN " +
                        "(SELECT TaskId FROM Dates WHERE DateTask = '" + Date.valueOf(dateToFind) +"')");
                ResultSet rsCrew = stmt2.executeQuery("select * from FireCrew");
                ResultSet rsAssignedTasks = stmt3.executeQuery(query);


                System.out.println(dateToFind + "\n");


                while(rsAssignedTasks.next()){
                    System.out.println(rsAssignedTasks.getString(2) + " - " + rsAssignedTasks.getString(3));
                }


                int crew = -1;
                boolean serviceEngine1 = false;
                boolean serviceEngine2 = false;

                if (14 <= dateToFind.getDayOfMonth() && dateToFind.getDayOfMonth() < 21) {
                    serviceEngine1 = true;
                }
                if (1 <= dateToFind.getDayOfMonth() && dateToFind.getDayOfMonth() < 8) {
                    serviceEngine2 = true;
                }


                while (rsCrew.next()) {
                    long daysbetween = DAYS.between(rsCrew.getDate(2).toLocalDate(), dateToFind);
                    if (daysbetween % 14 < 7) {
                        crew = rsCrew.getInt(1);
                    }
                }



                while (rsTasks.next()) {

                    if (rsTasks.getInt(4) != 0) {
                        if (rsTasks.getInt(4) == dateToFind.getDayOfMonth()) {
                            System.out.println(rsTasks.getString(2) + " - Crew: " + crew + " - not assigned to an individual");
                        }
                    } else {
                        long daysBetween = DAYS.between(rsTasks.getDate(5).toLocalDate(), dateToFind);

                        if ((rsTasks.getInt(1) == 3 && serviceEngine1 && daysBetween %
                                rsTasks.getInt(3) == 0) || (rsTasks.getInt(1) == 4 &&
                                serviceEngine2 && daysBetween % rsTasks.getInt(3) == 0)) {
                            System.out.println("Sorry " + rsTasks.getString(2) + " can't occur because it is being serviced this week");
                        } else {
                            if (daysBetween % rsTasks.getInt(3) == 0) {
                                System.out.println(rsTasks.getString(2) + " - Crew: " + crew + " - not assigned to an individual");
                            }
                        }

                    }
                }

                conn.close();
                System.out.println();

            }
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


    public void ViewAllTasks() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Tasks");
            while (rs.next()) {
                if (rs.getInt(8) != 0) {
                    System.out.println("ID: " + rs.getInt(1) + " |Description: " + rs.getString(2) + " |Frequency (days): " + rs.getInt(8));
                } else {
                    System.out.println("ID: " + rs.getInt(1) + " |Description: " + rs.getString(2) + " |Day of the month: " + rs.getInt(9));
                }
            }
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

    public void ViewAllWorkers() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from FireWorkers");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1) + "| Name: " + rs.getString(3) + "| Crew: " + rs.getInt(2));
            }
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


    public boolean AddIndividual(LocalDate date, int workerID, int taskID) {

        boolean validAdd = true;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            ResultSet rsWorker = stmt.executeQuery("SELECT FireCrew.WeekStart from FireCrew INNER JOIN FireWorkers" +
                    " ON FireWorkers.IdCrew = FireCrew.Id WHERE FireWorkers.Id =" + workerID);
            ResultSet rsTasks = stmt2.executeQuery("SELECT StartDate, Frequency, DayOfMonth FROM Tasks WHERE Id = " + taskID);


            boolean taskExists = false;
            boolean working = false;
            boolean workerExists = true;


            if(rsWorker == null){
                workerExists = false;
            }
            while (rsWorker.next()) {
                long daysbetween = DAYS.between(rsWorker.getDate(1).toLocalDate(), date);
                if (daysbetween % 14 < 7) {
                    working = true;
                }
            }
            while (rsTasks.next()) {

                if (rsTasks.getInt(2) == 0) {
                    if(rsTasks.getInt(3) == date.getDayOfMonth()){
                    taskExists = true;
                    }
                } else {
                    long daysbetween = DAYS.between(rsTasks.getDate(1).toLocalDate(), date);
                    if (daysbetween % rsTasks.getInt(2) == 0) {
                        taskExists = true;
                    }
                }
            }


            if (working && taskExists && workerExists) {
                prepStmt = conn.prepareStatement("INSERT INTO Dates(DateTask, WorkerID, TaskID) VALUES (?,?,?)");
                prepStmt.setDate(1, java.sql.Date.valueOf(date));
                prepStmt.setInt(2, workerID);
                prepStmt.setInt(3, taskID);
                prepStmt.execute();
            } else {
                validAdd = false;
                if(workerExists){
                    if(!working){
                        System.out.println("The worker isn't working today");
                    }
                }
                else{
                    System.out.println("This worker doesn't exist");
                }
                if(!taskExists){
                    System.out.println("This task doesn't exist or isn't meant to be done today");
                }


            }
            conn.close();

        } catch (Exception se) {
            validAdd = false;
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
        return validAdd;
    }
}