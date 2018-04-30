package com.itii.planning.gui.task;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import com.itii.planning.gui.MainWindow;
import com.itii.task.PlanningTask;
import com.itii.utils.DateFormatUtil;

public class TaskDialog extends JDialog implements ActionListener
{

    // TASK NAME
    private JLabel taskNameLabel = new JLabel("Nom de la tâche : ");
    private JTextField taskNameTextField = new JTextField();

    // DATE
    private JLabel dueDateLabel = new JLabel("Date dûe : ");
    private JTextField dueDateTextField = new JTextField();

    // CALENDAR
    private JDatePicker calendar;

    // DETAILS
    private JLabel detailLabel = new JLabel("Détails : ");
    private JTextArea detailTextArea = new JTextArea();

    public static final int INSET_VALUE = 10;
    public static final Insets INSET = new Insets(INSET_VALUE, INSET_VALUE,
            INSET_VALUE, INSET_VALUE);

    // Button for validating / saving / closing the dialog
    JButton buttonOk;

    // Main Application Window
    MainWindow mainWindow;

    // If current Dialog is used for editing an already existing entry
    private boolean edit;
    private PlanningTask formerTask = null;

    public TaskDialog(MainWindow mainWindow)
    {
        this(mainWindow, false);
    }
    
    /**
     * Constructor
     * @param mainWindow is the main window (parent one)
     * @param edit true if current dialog is editing an existing entry.
     * False if new one (create / duplicate)
     */
    public TaskDialog(MainWindow mainWindow, boolean edit)
    {
        this.mainWindow = mainWindow;
        this.edit = edit;

        setSize(500, 400);
        setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());

        gbc.insets = INSET;

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(taskNameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(taskNameTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(dueDateLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(dueDateTextField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(getCalendar(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(detailLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(detailTextArea, gbc);

        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.fill = GridBagConstraints.NONE;
        add(getButtonOk(), gbc);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        validate();
        repaint();
    }

    public void setDate(String date)
    {
        getCalendar().getFormattedTextField().setText(date);
    }

    public String getDate()
    {
        return getCalendar().getFormattedTextField().getText();
    }

    public void setTime(String time)
    {
        dueDateTextField.setText(time);
    }

    public String getTime()
    {
        return dueDateTextField.getText();
    }

    public void setName(String name)
    {
        taskNameTextField.setText(name);
    }

    public String getName()
    {
        return taskNameTextField.getText();
    }

    public void setDetails(String details)
    {
        detailTextArea.setText(details);
    }

    public String getDetails()
    {
        return detailTextArea.getText();
    }

    public void setPlanningTask(PlanningTask planningTask)
    {
        formerTask = planningTask;

        setName(planningTask.getName());
        setTime(planningTask.getTime());
        setDate(planningTask.getDate());
        setDetails(planningTask.getDetails());

    }

    public JButton getButtonOk()
    {
        if (buttonOk == null)
        {
            buttonOk = new JButton("OK");
            buttonOk.addActionListener(this);

        }
        return buttonOk;
    }

    public JDatePicker getCalendar()
    {
        if (calendar == null)
        {
            UtilDateModel model = new UtilDateModel();
            Calendar cal = Calendar.getInstance();
            model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_WEEK));
            calendar = new JDatePicker(model, DateFormatUtil.DATE_FORMAT);

        }
        return calendar;
    }

    public static void main(String[] args)
    {
        TaskDialog td = new TaskDialog(null);

        td.setName("Première tâche");
        td.setDate("2018/04/28");
        td.setTime("10:30");
        td.setDetails("Des commmentaires ....");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        e.getSource();

        if (e.getSource() instanceof JButton)
        {
            JButton button = (JButton) e.getSource();
            this.setVisible(false);

            PlanningTask task = new PlanningTask();
            task.setTime(getTime());
            task.setDate(getDate());
            task.setName(getName());
            task.setDetails(getDetails());

            // Nom de la tâche :
            System.out.println(
                    "Nom de la tâche : " + taskNameTextField.getText());

            // Date dûe :
            System.out.println("Date due : "
                    + getCalendar().getFormattedTextField().getText() + " ["
                    + dueDateTextField.getText() + "]");

            // Commentaire :
            System.out.println("Détails : " + detailTextArea.getText());

            if (edit)
            {
                if (formerTask == null)
                {
                    System.out.println("erreur !");
                }
                // update an existing entry
                mainWindow.updateTask(formerTask, task);

            } else
            {
                // create a new entry
                mainWindow.addTask(task);
            }
        }
    }

}
