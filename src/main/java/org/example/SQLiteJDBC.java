package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteJDBC {
    private Connection connection;

    public void connectToDatabase(){
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        this.connection = c;
    }

    public void createTable(){
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
                String sql = "CREATE TABLE SCHOOL " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " SURNAME        TEXT    NOT NULL, " +
                    " AVERAGE        REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
            System.out.println("Table created successfully");
        } catch ( Exception e ) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void insertOperation(){
        Statement stmt = null;

        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            String sql = "INSERT INTO SCHOOL (ID,NAME,SURNAME,AVERAGE) " +
                    "VALUES (1, 'Paul', 'Clark', 4.32);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO SCHOOL (ID,NAME,SURNAME,AVERAGE) " +
                    "VALUES (2, 'Allen', 'Blake', 3.55);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO SCHOOL (ID,NAME,SURNAME,AVERAGE) " +
                    "VALUES (3, 'Teddy', 'Smith', 4.87 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO SCHOOL (ID,NAME,SURNAME,AVERAGE) " +
                    "VALUES (4, 'Mark', 'Rich', 5.05 );";
            stmt.executeUpdate(sql);

            stmt.close();
            connection.commit();
            connection.close();
            System.out.println("Insert operation done successfully");
        } catch ( Exception e ) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void selectOperation(){
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SCHOOL;" );

            System.out.println("~".repeat(60));
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                String surname  = rs.getString("surname");
                float average = rs.getFloat("average");

                System.out.print( "| ID = " + id + " | ");
                System.out.print( "NAME = " + name + " ".repeat(5-name.length()) + " | ");
                System.out.print( "SURNAME = " + surname + " ".repeat(5-surname.length()) + " | ");
                System.out.println( "AVERAGE + " + average + " |");
            }
            System.out.println("~".repeat(60));
            rs.close();
            stmt.close();
            connection.close();
            System.out.println("select operation done successfully");
        } catch ( Exception e ) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void updateOperation(){
        Statement stmt = null;

        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            String sql = "UPDATE SCHOOL set AVERAGE = 5.12 where ID=1;";
            stmt.executeUpdate(sql);
            connection.commit();

            stmt.close();
            connection.close();
            System.out.println("Update operation done successfully");
        } catch ( Exception e ) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void deleteOperation(){
        Statement stmt = null;

        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            String sql = "DELETE from SCHOOL;";
            stmt.executeUpdate(sql);
            connection.commit();

            stmt.close();
            connection.close();
            System.out.println("delete operation done successfully");
        } catch ( Exception e ) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

}
