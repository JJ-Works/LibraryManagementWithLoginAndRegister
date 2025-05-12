package db;
//Our gateway for accessing mysql database

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import constants.CommonConstants;

import javax.imageio.metadata.IIOInvalidTreeException;
import java.sql.*;
import java.util.ConcurrentModificationException;

public class MyJDBC {
    // Objective
    //Register new user to the database
    //if returns true then success vayo natra vayena

    public static boolean register(String username, String password){
        // We must first check of the username already exits in the database or not
        try{
            //to connect to the database
            if (!checkUser(username)) {
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonConstants.DB_USER_TABLE_NAME + "(username, password)" + "VALUES(?, ?)"
                );

                //TO INSERT PARAMETER IN THE INSERT QUERY
                insertUser.setString(1,username);
                insertUser.setString(2,password);

                // Instead of executeQuery we can use executeUpdate because executeQuery is usually used for select
                insertUser.executeUpdate();
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    // true return garyo vaney cha natra chaina
    public static boolean checkUser(String username){

        try{
            // We use connection class to make a connection with the database
            // And that will only be possible if we had included the .JAR file
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL
                    ,CommonConstants.DB_USERNAME,CommonConstants.DB_PASSWORD);

            PreparedStatement checkUserExists = connection.prepareStatement( // since connecting strings i have to give space before where.
                    "SELECT * FROM " + CommonConstants.DB_USER_TABLE_NAME + " WHERE USERNAME = ?"
            );
            checkUserExists.setString(1,username);

            ResultSet resultset = checkUserExists.executeQuery();

            // Now that the query run huncha and yedi kei return vayo vaney hamle
            // if condition lagayerw check garney kei chaina vaney username chaina vanerw bujey huncha

            if(!resultset.isBeforeFirst()){
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public static boolean validateLogin(String username, String password){
        try{
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,CommonConstants.DB_USERNAME,CommonConstants.DB_PASSWORD);

            // Creating a select query
            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USER_TABLE_NAME + " WHERE USERNAME = ? AND PASSWORD = ?"
            );
            validateUser.setString(1,username);
            validateUser.setString(2,password);

            ResultSet resultSet = validateUser.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }
}
