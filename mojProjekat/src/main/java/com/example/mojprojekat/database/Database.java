package com.example.mojprojekat.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Connection con;
    public static Database db;

    public static Database getConnection()
    {
        if(db == null)
        {
            db = new Database();
            return db;
        }
        return db;
    }

    private Database()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connect4", "root", "");
        }
        catch (Exception e){}
    }

    public void insertResult(String username)
    {
        String query = "select * from korisnik where username=?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                int id = resultSet.getInt("id");
                query = "insert into rezultati(id,pobede) values(?,0)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,id);
                preparedStatement.execute();
            }

        }
        catch (Exception e){}
    }

    public void updatePobede(int id)
    {
        String query = "Update rezultati set pobede=pobede+1 where id=? ";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        }
        catch (Exception e){}
    }
    public boolean register(String username,String pass)
    {
        if(getUser(username) == false)
            return false;


        String query = "insert into korisnik (username,password,role) values(?,?,?)";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,pass);
            preparedStatement.setString(3,"player");

            if(preparedStatement.executeUpdate() > 0)
            {
                insertResult(username);
                return true;
            }

        }
        catch (Exception e){}
        return false;
    }

    public boolean getUser(String username)
    {
        String query = "select * from korisnik where username=?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,username);

            if(preparedStatement.executeUpdate() > 0)
            {
                return false;
            }

        }
        catch (Exception e){}
        return true;
    }

    public Korisnik login(String username, String pass)
    {
        String query = "select * from korisnik where username=? and password=?";
        try{
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,pass);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                Korisnik korisnik = new Korisnik();
                korisnik.setId(resultSet.getInt("id"));
                korisnik.setPass(resultSet.getString("password"));
                korisnik.setRole(resultSet.getString("role"));
                korisnik.setUsername(resultSet.getString("username"));

                return korisnik;
            }

        }
        catch (Exception e){}
        return null;
    }

    public List<Korisnik> korisnici(int id)
    {
        String query = "select * from korisnik where id!=?";
        try{
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Korisnik> korisnici = new ArrayList<Korisnik>();

            while(resultSet.next())
            {
                Korisnik korisnik = new Korisnik();
                korisnik.setId(resultSet.getInt("id"));
                korisnik.setPass(resultSet.getString("password"));
                korisnik.setRole(resultSet.getString("role"));
                korisnik.setUsername(resultSet.getString("username"));

                korisnici.add(korisnik);
            }
            return korisnici;

        }
        catch (Exception e){}
        return null;
    }

    public void deleteUser(int id)
    {
        String query = "Delete from korisnik where id=?";
        try{
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

        }
        catch (Exception e){}
    }

    public int getPobede(int id)
    {
        String query = "select * from rezultati where id=?";
        try{
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return resultSet.getInt("pobede");
            }

        }
        catch (Exception e){}
        return 0;
    }


}
