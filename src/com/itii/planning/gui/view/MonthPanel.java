package com.itii.planning.gui.view;

import java.awt.Color;

import com.itii.planning.gui.MainWindow;
import com.itii.task.PlanningTask;

public final class MonthPanel extends AbstractInnerViewPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = 2156261695968424790L;

    public MonthPanel(MainWindow mainWindow)
    {
        super ( mainWindow );
        setBackground(Color.YELLOW);
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
