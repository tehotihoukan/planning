package com.itii.task;

import com.itii.planning.gui.task.TaskState;

public class PlanningTask
{

    // Primary key from database entry
    public int dbId;

    // Store the date in format yyyy/mm/dd
    public String date;

    // Store the time in format hh:mm
    public String time;

    // Store the name of the current task
    public String name;

    // Store details about the current task
    public String details;

    // Store the state of the current task TO_DO / DONE
    public TaskState state = TaskState.TO_DO;

    public PlanningTask()
    {

    }

    public int getDbId()
    {
        return dbId;
    }

    public void setDbId(int dbId)
    {
        this.dbId = dbId;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public TaskState getState()
    {
        return this.state;
    }

    public void setState(TaskState state)
    {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj)
    {
        PlanningTask pt = (PlanningTask) obj;

        return getName().equals(pt.getName())
                && getDetails().equals(pt.getDetails())
                && getTime().equals(pt.getTime())
                && getDate().equals(pt.getDate())
                && getState().equals(pt.getState());
    }

    @Override
    public int hashCode()
    {
        return this.name.length() << 10 + Math.max(this.details.length(), 200)
                + this.time.length();
    }
}
