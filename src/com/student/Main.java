package com.student;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to the Student Database");
        System.out.println();
        System.out.println("Please Login to proceed ");
        System.out.print("username : ");
        String user = in.next();
        Properties login = new Properties();
        try(FileReader fr = new FileReader("./login.properties")){
            login.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (user.equals("ritik")){
            System.out.print("Enter your password : ");
            String password = in.next();
            if (Objects.equals(password, login.getProperty("supervisorpassword"))){
                System.out.println("Authentication Successful");
                System.out.println();
                // Our Admin menu
                int ch = 0;
                while(ch!=6) {
                    System.out.println("Our Program Menu");
                    System.out.println();
                    System.out.println("1. View Database");
                    System.out.println("2. Insert in Database ");
                    System.out.println("3. Update Database");
                    System.out.println("4. Delete Database");
                    System.out.println("5. Track Fees Status");
                    System.out.println("6. Exit the Database");
                    System.out.println();
                    System.out.print("Enter your Choice :");
                    ch = in.nextInt();


                    switch (ch) {
                        case 1:
                            viewadmindatabase();
                            break;
                        case 2:
                            insertdatabase();
                            break;
                        case 3:
                            updatedatabase();
                            break;
                        case 4:
                            deletedatabse();
                            break;
                        case 5:
                            feestracker();
                            break;
                        case 6:
                            System.exit(1);
                    }
                }
            }else{
                System.exit(1);
            }
        }else{
            // Our user menu
            int ch = 0;
            while(ch!=3) {
                System.out.println("Our Program Menu");
                System.out.println();
                System.out.println("1. View Database");
                System.out.println("2. Search Name in Database");
                System.out.println("3. Exit the Database");
                System.out.println();
                System.out.print("Enter your Choice :");
                ch = in.nextInt();


                switch (ch) {
                    case 1:
                        viewdatabase();
                        break;
                    case 2:
                        searchname();
                        break;

                    case 3:
                        System.exit(1);
                }
            }
        }
    }

    private static void feestracker() {
        try{
            Connection conn = getconnection();
            Statement statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery("select * from student"+" where FEES='unpaid'");
            while(resultset.next()){
                System.out.println("Name : " + resultset.getString("NAME") );
                System.out.println("Class : " + resultset.getString("CLASS"));
                System.out.println("Sec : " + resultset.getString("SECTION"));
                System.out.println("Address : " + resultset.getString("ADDRESS"));
                System.out.println("Mobile : " + resultset.getString("MOBILE"));
                System.out.println("Fees : " + resultset.getString("FEES"));
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private static void searchname() {
        System.out.print("Enter Name :");
        String oldname = in.next();
        in.nextLine();
        try{
            Connection conn = getconnection();
            Statement statement = conn.createStatement();
            StringBuilder sql = new StringBuilder(in.nextLine());
            System.out.println(sql);
            ResultSet resultset = statement.executeQuery(sql.toString());
            System.out.println("Match Found");
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    private static void deletedatabse() {
        System.out.print("Enter Name :");
        String oldname = in.next();

        try {
            Connection conn = getconnection();
            String sql = "DELETE FROM student where NAME= ? ";
//            System.out.println(sql);
            Statement myst = conn.prepareStatement(sql);
            ((PreparedStatement) myst).setString(1,oldname);
            ((PreparedStatement) myst).executeUpdate();
            System.out.println("Data deleted");
            System.out.println();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    private static void updatedatabase(){

        System.out.println("Enter the Type of updates");
        System.out.println();
        System.out.println("1, Name Update");
        System.out.println("2. Class and Section Update");
        System.out.println("3. Address Update");
        System.out.println("4. Mobile Update");
        System.out.println("5. Fees Update");
        int ch =0;
        ch=in.nextInt();
        switch(ch){
            case 1:
                nameupdate();
                break;
            case 2:
                csupdate();
                break;
            case 3:
                addressupdate();
                break;
            case 4:
                mobileupdate();
                break;
            case 5:
                feesupdate();
                break;
        }
    }

    private static void feesupdate() {

        System.out.print("Enter Name :");
        String oldname = in.next();
        System.out.print("Enter new Fees:");
        String newaddress=in.next();

        try {
            Connection conn = getconnection();
            String sql = "UPDATE student set FEES= ? where NAME= ? ";
//            System.out.println(sql);
            Statement myst = conn.prepareStatement(sql);
            ((PreparedStatement) myst).setString(1,newaddress);
            ((PreparedStatement) myst).setString(2,oldname);
            ((PreparedStatement) myst).executeUpdate();
            System.out.println("Data updated");
            System.out.println();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private static void mobileupdate() {

        System.out.print("Enter Name :");
        String oldname = in.next();
        System.out.print("Enter new Mobile no. ::");
        String newaddress=in.next();

        try {
            Connection conn = getconnection();
            String sql = "UPDATE student set MOBILE= ? where NAME= ? ";
//            System.out.println(sql);
            Statement myst = conn.prepareStatement(sql);
            ((PreparedStatement) myst).setString(1,newaddress);
            ((PreparedStatement) myst).setString(2,oldname);
            ((PreparedStatement) myst).executeUpdate();
            System.out.println("Data updated");
            System.out.println();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private static void addressupdate() {

        System.out.print("Enter Name :");
        String oldname = in.next();
        System.out.print("Enter new Address:");
        String newaddress=in.next();

        try {
            Connection conn = getconnection();
            String sql = "UPDATE student set ADDRESS= ? where NAME= ? ";
//            System.out.println(sql);
            Statement myst = conn.prepareStatement(sql);
            ((PreparedStatement) myst).setString(1,newaddress);
            ((PreparedStatement) myst).setString(2,oldname);
            ((PreparedStatement) myst).executeUpdate();
            System.out.println("Data updated");
            System.out.println();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private static void csupdate() {

        System.out.print("Enter Name :");
        String oldname = in.next();
        System.out.print("Enter new class:");
        String newclass=in.next();
        System.out.print("Enter new Section:");
        String newsection=in.next();
        try {
            Connection conn = getconnection();
            String sql = "UPDATE student set CLASS= ? , SECTION = ?  where NAME= ? ";
//            System.out.println(sql);
            Statement myst = conn.prepareStatement(sql);
            ((PreparedStatement) myst).setString(1,newclass);
            ((PreparedStatement) myst).setString(2,newsection);
            ((PreparedStatement) myst).setString(3,oldname);
            ((PreparedStatement) myst).executeUpdate();
            System.out.println("Data updated");
            System.out.println();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private static void nameupdate() {
        System.out.print("Enter old Name :");
        String oldname = in.next();
        System.out.print("Enter new name :");
        String newname=in.next();
        try {
            Connection conn = getconnection();
            String sql = "UPDATE student set NAME= ? where NAME= ? ";
//            System.out.println(sql);
            Statement myst = conn.prepareStatement(sql);
            ((PreparedStatement) myst).setString(1,newname);
            ((PreparedStatement) myst).setString(2,oldname);
            ((PreparedStatement) myst).executeUpdate();
            System.out.println("Data updated");
            System.out.println();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    private static void insertdatabase(){
        System.out.println("Insert the information of the student");
        System.out.println();
        System.out.print("NAME : ");
        String name = in.next();
        System.out.print("CLASS : ");
        String clas = in.next();
        System.out.print("SECTION : ");
        String section = in.next();
        System.out.print("MOBILE : ");
        String mobile = in.next();
        System.out.print("FEES : ");
        String fees = in.next();
        System.out.print("ADDRESS : ");
        String address = in.next();

        try {
            Connection conn = getconnection();
            String sql = "insert into student" + "(NAME,CLASS,SECTION,ADDRESS,MOBILE,FEES)" + "values(?,?,?,?,?,?)";
            Statement myst = conn.prepareStatement(sql);
            ((PreparedStatement) myst).setString(1,name);
            ((PreparedStatement) myst).setString(2,clas);
            ((PreparedStatement) myst).setString(3,section);
            ((PreparedStatement) myst).setString(4,address);
            ((PreparedStatement) myst).setString(5,mobile);
            ((PreparedStatement) myst).setString(6,fees);
           ((PreparedStatement) myst).execute();
            System.out.println("Data inserted");
            System.out.println();
        }
        catch (Exception e){
            System.out.println(e);
        }



    }
    private static void viewadmindatabase(){
        try{
            Connection conn = getconnection();
            Statement statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery("select * from student");
            while(resultset.next()){
                System.out.println("Name : " + resultset.getString("NAME") );
                System.out.println("Class : " + resultset.getString("CLASS"));
                System.out.println("Sec : " + resultset.getString("SECTION"));
                System.out.println("Address : " + resultset.getString("ADDRESS"));
                System.out.println("Mobile : " + resultset.getString("MOBILE"));
                System.out.println("Fees : " + resultset.getString("FEES"));
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }


    }

    private static void viewdatabase(){
        try{
            Connection conn = getconnection();
            Statement statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery("select NAME ,CLASS,SECTION,ADDRESS from student");
            while(resultset.next()){
                System.out.println("Name : " + resultset.getString("NAME") );
                System.out.println("Class : " + resultset.getString("CLASS"));
                System.out.println("Sec : " + resultset.getString("SECTION"));
                System.out.println("Address : " + resultset.getString("ADDRESS"));
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }


    }
    
    private static Connection getconnection(){
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseurl = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6472192";
            Properties login = new Properties();
            try(FileReader fr = new FileReader("./login.properties")){
                login.load(fr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String user = login.getProperty("username");
            String password = login.getProperty("password");
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl,user,password);
            System.out.println();
            return conn;

        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
