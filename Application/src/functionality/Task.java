package functionality;
import DatabaseHelper.Helper;

import java.sql.Date;
import java.util.Scanner;

public class Task {
    private Scanner sc = new Scanner(System.in);
    Helper database = new Helper();;


    public void ViewTasks(){
        int year;
        int month;
        int day;


        System.out.println("Please input year (2021 - ):");
        year = sc.nextInt();

        System.out.println("Please input month (1-12):");
        month = sc.nextInt();
        System.out.println("Please input day (1 - 31):");
        day = sc.nextInt();

        Date date = new Date(year-1900, month-1, day);

        database.printOutAllTasks(date);




    }




}
