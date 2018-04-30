package com.itii.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.itii.planning.gui.task.TaskState;
import com.itii.task.PlanningTask;

/**
 * Contains a list of element referring to the database.
 */
public class DbInfo
{

    public static final String TABLE_NAME = "Tasks";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_TIME = "time";
    public static final String FIELD_DETAILS = "details";
    public static final String FIELD_STATE = "state";

    public static void writeTask(Connection connection, PlanningTask task)
    {
        try
        {
            PreparedStatement stmt = connection.prepareStatement("insert into "
                    + TABLE_NAME + " ( " + FIELD_NAME + "," + FIELD_DATE + ","
                    + FIELD_TIME + "," + FIELD_DETAILS + "," + FIELD_STATE
                    + " ) " + "values ( ?, ?, ?, ?, ?) ");
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDate());
            stmt.setString(3, task.getTime());
            stmt.setString(4, task.getDetails());
            stmt.setInt(5, TaskState.getValueFromTaskState(task.getState()));
            stmt.executeUpdate();
            System.out.println("insertion d'une nouvelle entrée dans la table");
        } catch (SQLException e)
        {
            System.out.println(
                    "problème dans l'insertion d'une nouvelle enrée dans la table.");
        }
    }

    public static void writeTask(Connection connection, PlanningTask formerTask,
            PlanningTask task)
    {
        try
        {
            PreparedStatement stmt = connection.prepareStatement("update "
                    + TABLE_NAME + " ( " + FIELD_NAME + "," + FIELD_DATE + ","
                    + FIELD_TIME + "," + FIELD_DETAILS + "," + FIELD_STATE
                    + " ) " + "values ( ?, ?, ?, ?) ");
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDate());
            stmt.setString(3, task.getTime());
            stmt.setString(4, task.getDetails());
            stmt.setInt(5, TaskState.getValueFromTaskState(task.getState()));
            stmt.executeUpdate();
            System.out.println("insertion d'une nouvelle entrée dans la table");
        } catch (SQLException e)
        {
            System.out.println(
                    "problème dans l'insertion d'une nouvelle enrée dans la table.");
        }
    }
}
