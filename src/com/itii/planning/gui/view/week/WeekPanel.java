package com.itii.planning.gui.view.week;

import java.awt.Color;

import com.itii.planning.gui.MainWindow;
import com.itii.planning.gui.view.AbstractInnerViewPanel;
import com.itii.task.PlanningTask;

public final class WeekPanel extends AbstractInnerViewPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = 8046603648970009355L;

    public WeekPanel(MainWindow mainWindow)
    {
        super(mainWindow);

        setBackground(Color.RED);
    }

    @Override
    public void deletePlanningTasks()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public PlanningTask[] getSelectedPlanningTasks()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addPlanningTasks(PlanningTask... planingTasks)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updatePlanningTask(PlanningTask planningTask)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void markPlanningTasks()
    {
        // TODO Auto-generated method stub
        
    }
}
