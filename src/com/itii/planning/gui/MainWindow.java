package com.itii.planning.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.itii.db.DbInfo;
import com.itii.planning.gui.task.TaskState;
import com.itii.planning.gui.view.menu.PMenuBar;
import com.itii.task.PlanningTask;

public class MainWindow extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static MainWindow instance;

    private static Object synchro = new Object();

    private MainPanel mainPanel;

    private PMenuBar menuBar;

    private final ArrayList<PlanningTask> taskList = new ArrayList<PlanningTask>();

    private Connection connection = null;

    private MainWindow(String databaseName)
    {
        {
            Statement statement = null;

            // Connect to database
            try
            {
                // Chargement du Driver. Stockage des données dans le fichier
                // planning.db
                connection = DriverManager
                        .getConnection("jdbc:sqlite:database/" + databaseName);
                // Objet permettant l'exécution des requêtes SQL
                statement = connection.createStatement();
                // Timeout en cas de non-réponse de la base de données.
                statement.setQueryTimeout(15);

//                statement.execute("drop table " + DbInfo.TABLE_NAME);
                // Création de la table
                statement.executeUpdate(
                        "create table " + DbInfo.TABLE_NAME + " ( "
                        // Primary key
                                + DbInfo.FIELD_ID
                                + " integer primary key autoincrement, "
                                // Name
                                + DbInfo.FIELD_NAME + " string, "
                                // date as ISO8601 strings YYYY-MM-DD
                                + DbInfo.FIELD_DATE + " text, "
                                // hh:mm
                                + DbInfo.FIELD_TIME + " text, "
                                // Details
                                + DbInfo.FIELD_DETAILS + " text, "
                                // Marqué
                                + DbInfo.FIELD_STATE + " boolean " + " )");
                System.out
                        .println("table \"" + DbInfo.TABLE_NAME + "\" créée ");
            } catch (SQLException e)
            {
                System.out.println(" Table non créée ou déjà existante");
                e.printStackTrace();
            }

            // Reading data from database
            try
            {
                ResultSet rs = statement
                        .executeQuery("select * from " + DbInfo.TABLE_NAME);
                while (rs.next())
                {
                    PlanningTask pt = new PlanningTask();

                    System.out.print("lecture d'une donnée [");
                    System.out.print(" id = " + rs.getString(DbInfo.FIELD_ID));
                    System.out.print(
                            " ; name = " + rs.getString(DbInfo.FIELD_NAME));
                    System.out.print(
                            " ; date = " + rs.getString(DbInfo.FIELD_DATE));
                    System.out.print(
                            " ; time = " + rs.getString(DbInfo.FIELD_TIME));
                    System.out.print(" ; details = "
                            + rs.getString(DbInfo.FIELD_DETAILS));
                    System.out.println(" ; etat = "
                            + rs.getString(DbInfo.FIELD_STATE) + "]");

                    pt.setDbId(rs.getInt(DbInfo.FIELD_ID));
                    pt.setName(rs.getString(DbInfo.FIELD_NAME));
                    pt.setDate(rs.getString(DbInfo.FIELD_DATE));
                    pt.setTime(rs.getString(DbInfo.FIELD_TIME));
                    pt.setDetails(rs.getString(DbInfo.FIELD_DETAILS));
                    pt.setState(TaskState.getTaskStateFromValue(
                            rs.getInt(DbInfo.FIELD_STATE)));

                    restoreTask(pt);

                }
            } catch (SQLException e)
            {
                System.out.println("erreur à la lecture de la table");
            }
        }

        setVisible(true);
        setSize(800, 600);
        setTitle("Planning");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        getContentPane().add(getMainPanel());

        setJMenuBar(getPMenuBar());

        validate();
        repaint();
    }

    public MainPanel getMainPanel()
    {
        if (mainPanel == null)
        {
            mainPanel = new MainPanel(this);
        }
        return mainPanel;
    }

    public ArrayList<PlanningTask> getTasks()
    {
        return taskList;
    }

    public PMenuBar getPMenuBar()
    {
        if (menuBar == null)
        {
            menuBar = new PMenuBar(this);
        }
        return menuBar;
    }

    public static MainWindow getInstance()
    {
        if (instance == null)
        {
            synchronized (synchro)
            {
                if (instance == null)
                {
                    instance = new MainWindow("planning.db");
                }
            }
        }

        return instance;
    }

    /**
     * Restore a task from the database and display it.
     * 
     * @param task
     *            read from the database.
     */
    public void restoreTask(PlanningTask task)
    {
        taskList.add(task);
        getMainPanel().getViewPanel().getDisplayedPanel()
                .addPlanningTasks(task);
    }

    /**
     * Add a task to the list and store it to the database.
     * 
     * @param formerTask
     *            to update
     */
    public void addTask(PlanningTask currentTask)
    {
        taskList.add(currentTask);
        getMainPanel().getViewPanel().getDisplayedPanel()
                .addPlanningTasks(currentTask);
        DbInfo.writeTask(connection, currentTask);
    }

    /**
     * Add a task to the list and update the database accordingly
     * 
     * @param formerTask
     *            to update
     * @param currentTask
     *            to replace the formerTask
     */
    public void updateTask(PlanningTask formerTask, PlanningTask currentTask)
    {
        taskList.set(taskList.indexOf(formerTask), currentTask);
        getMainPanel().getViewPanel().getDisplayedPanel()
                .updatePlanningTask(currentTask);
        // DbInfo.updateTask(connection, formerTask, currentTask);
    }

}
