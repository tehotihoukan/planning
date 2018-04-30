package com.itii.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Test de connection / ex�cution de requ�tes SQL / d�conncetion de SQLite
 * @author Sebastien
 */
public class SQLiteTest
{
    private static final String TABLE_NAME = "Tasks";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DATE = "date";
    private static final String FIELD_DETAILS = "details";
    private static final String FIELD_STATE = "state";

    public static void main(String[] args)
    {
        Connection connection = null;
        Statement statement = null;
       
        // Cr�ation de la table
        try
        {
            // Chargement du Driver. Stockage des donn�es dans le fichier planning.db
            connection = DriverManager
                    .getConnection("jdbc:sqlite:database/planning.db");
            // Objet permettant l'ex�cution des requ�tes SQL
            statement = connection.createStatement();
            // Timeout en cas de non-r�ponse de la base de donn�es.
            statement.setQueryTimeout(15);

            statement.execute("drop table " + TABLE_NAME);
            // Cr�ation de la table
            statement.executeUpdate("create table " + TABLE_NAME + " ( "
                    + FIELD_ID + " integer primary key autoincrement, " // Primary key
                    + FIELD_NAME + " string, " // Name
                    + FIELD_DATE + " text, " // Details
                    + FIELD_DETAILS + " text, " // date as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
                    + FIELD_STATE + " boolean " + " )"); // marqu�e
            System.out.println("table \"" + TABLE_NAME + "\" cr��e ");
        } catch (SQLException e)
        {
            System.out.println(" Table non cr��e ou d�j� existante");
            e.printStackTrace();
        }

        // Test d'�criture dans la table
        try
        {
            PreparedStatement stmt = connection.prepareStatement(
                    "insert into " + TABLE_NAME + " ( " + FIELD_NAME + ","
                            + FIELD_DATE + "," + FIELD_DETAILS + ","
                            + FIELD_STATE + " ) " + "values ( ?, ?, ?, ?) ");
            stmt.setString(1, "TP #1");
            stmt.setString(2, "2018-04-20 12:00");
            stmt.setString(3, "penser � rendre le tp");
            stmt.setString(4, "0");
            stmt.executeUpdate();
            System.out.println("insertion d'une nouvelle entr�e dans la table");
        } catch (SQLException e)
        {
            System.out.println("probl�me dans l'insertion d'une nouvelle enr�e dans la table.");
        }

        // Test de lecture depuis la table
        try
        {
            ResultSet rs = statement.executeQuery("select * from " + TABLE_NAME);
            while (rs.next())
            {
                System.out.print("lecture d'une donn�e [");
                System.out.print(" id = " + rs.getString(FIELD_ID));
                System.out.print(" ; name = " + rs.getString(FIELD_NAME));
                System.out.print(" ; date = " + rs.getString(FIELD_DATE));
                System.out.print(" ; details = " + rs.getString(FIELD_DETAILS));
                System.out.println( " ; etat = " + rs.getString(FIELD_STATE) + "]");

            }
        } catch (SQLException e)
        {
            System.out.println("erreur � la lecture de la table");
        } finally
        {
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
                System.out.println("fermeture de la connection � la base de donn�es");
            } catch (SQLException e)
            {
                System.out.println("erreur lors de la fermeture de la connection");
            }
        }
    }
}
