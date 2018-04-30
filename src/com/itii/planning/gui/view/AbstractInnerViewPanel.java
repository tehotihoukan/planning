package com.itii.planning.gui.view;

import javax.swing.JPanel;

import com.itii.planning.gui.MainWindow;
import com.itii.task.PlanningTask;

public abstract class AbstractInnerViewPanel extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = 5900543026101783057L;

    protected MainWindow mainWindow;

    public AbstractInnerViewPanel(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
    }

    /**
     * Delete the list of selected Planning Task(s).
     */
    public abstract void deletePlanningTasks();

    /**
     * Retrieve the list of selected Planning Tasks Can be empty if no
     * selection.
     */
    public abstract PlanningTask[] getSelectedPlanningTasks();

    /**
     * Add Planning Tasks to the view
     */
    public abstract void addPlanningTasks(PlanningTask... planningTasks);

    /**
     * Update an existing Planning Task
     */
    public abstract void updatePlanningTask(PlanningTask planningTask);

    /**
     * Mark (DONE / TO_DO) the given tasks
     */
    public abstract void markPlanningTasks();
}
