package functionality;
import DatabaseHelper.Helper;
import com.sun.net.httpserver.Authenticator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Task {
    private Scanner sc = new Scanner(System.in);
    Helper database = new Helper();
    ;


    public void ViewTasks() {
        int year;
        int month;
        int day;
        boolean validDate = false;
        LocalDate dateLocal = null;


        while (!validDate) {
            validDate = true;
            System.out.println("Please input year (2021 - ):");
            year = sc.nextInt();
            System.out.println("Please input month (1-12):");
            month = sc.nextInt();
            System.out.println("Please input day (1 - 31):");
            day = sc.nextInt();

            try {
                dateLocal = LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("That is not a valid date please input a valid one");
                validDate = false;
            }
            if (validDate) {
                if (dateLocal.isBefore(LocalDate.of(2021, 02, 1))) {
                    System.out.println("Sorry, no records before 2021-02-01 please input dates after this");
                    validDate = false;
                }
            }


        }
        database.printOutAllTasks(dateLocal);
    }


    public void ViewTasksBetween() {
        int year;
        int month;
        int day;
        int year2;
        int month2;
        int day2;
        boolean validDates = false;
        LocalDate startDate = null;
        LocalDate endDate = null;


        while (!validDates) {

            validDates = true;
            System.out.println("Input date from:");
            System.out.println("Please input year (2021 - ):");
            year = sc.nextInt();
            System.out.println("Please input month (1-12):");
            month = sc.nextInt();
            System.out.println("Please input day (1 - 31):");
            day = sc.nextInt();

            System.out.println("Input date till:");
            System.out.println("Please input year (2021 - ):");
            year2 = sc.nextInt();
            System.out.println("Please input month (1-12):");
            month2 = sc.nextInt();
            System.out.println("Please input day (1 - 31):");
            day2 = sc.nextInt();

            try {
                startDate = LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("The first date you input was not a valid date. Please input a valid one");
                validDates = false;
            }
            try {
                endDate = LocalDate.of(year2, month2, day2);
            } catch (Exception e) {
                System.out.println("The second date you input was not a valid date. Please input a valid one");
                validDates = false;
            }

            if (validDates) {
                if (startDate.isAfter(endDate)) {
                    validDates = false;
                    System.out.println("The second date you input must be after the first one. Please input valid date");
                }
                if (startDate.isBefore(LocalDate.of(2021, 02, 1))) {
                    System.out.println("Sorry, no records before 2021-02-01 please input dates after this");
                    validDates = false;
                }


            }

        }

        database.printOutAllTasks(startDate, endDate);

    }


    public void AssignIndividual() {
        int year, month, day;
        int workerId;
        int taskId;
        LocalDate date = null;
        boolean validAdd = false;

        while (!validAdd) {
            validAdd = true;
            System.out.println("What date is this task occurring:");
            System.out.println("Year: ");
            year = sc.nextInt();
            System.out.println("Month: ");
            month = sc.nextInt();
            System.out.println("Day: ");
            day = sc.nextInt();
            System.out.println("Please input the ID of the worker: ");
            workerId = sc.nextInt();
            System.out.println("Please input the ID of the task: ");
            taskId = sc.nextInt();

            try {
                date = LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("That is not a valid date please input a valid one");
                validAdd = false;
            }
            if (validAdd) {
                if (date.isBefore(LocalDate.of(2021, 02, 1))) {
                    System.out.println("Sorry, no records before 2021-02-01 please input dates after this");
                    validAdd = false;
                }
            }
            if(validAdd){
                validAdd = database.AddIndividual(date, workerId, taskId);
            }
        }

        System.out.println("Successfully added individual");
    }
}
