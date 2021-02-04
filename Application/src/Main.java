import DatabaseHelper.Helper;
import functionality.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        boolean manageTasks = true;
        Scanner sc = new Scanner(System.in);
        int choice;

        Task taskManagment = new Task();


        //Helper database = new Helper();
        //database.printOut();
        //Date date = new Date(120, 1, 8);
        //database.printOutAllTasks(date);


        System.out.println("------ Welcome to fire service manager ------");
        while (manageTasks){
            System.out.println("Options \n (1) View tasks for a date \n (2) add new task " +
                    "\n (3) finish manager");
            choice = sc.nextInt();

            switch (choice){
                case 1:
                    taskManagment.ViewTasks();
                    break;
                case 2:

                    break;
                case 3:
                    manageTasks = false;
                    break;
            }


            Thread.sleep(1500);

        }

        System.out.println("Thank you for using fire service manager");





    }
}
