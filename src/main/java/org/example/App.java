package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SQLiteJDBC database = new SQLiteJDBC();

        System.out.println("|1 - create table\n"
                         + "|2 - insert\n"
                         + "|3 - update\n"
                         + "|4 - delete all\n"
                         + "|5 - print table\n"
                         + "|0 - exit\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("option: ");
        int userOption = scanner.nextInt();
        switch (userOption){
            case 1:
                database.connectToDatabase();
                database.createTable();
                main(args);
                break;
            case 2:
                database.connectToDatabase();
                database.insertOperation();
                main(args);
                break;
            case 3:
                database.connectToDatabase();
                database.updateOperation();
                main(args);
                break;
            case 4:
                database.connectToDatabase();
                database.deleteOperation();
                main(args);
                break;
            case 5:
                database.connectToDatabase();
                database.selectOperation();
                main(args);
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Wrong option");
                main(args);
                break;
        }
    }
}
