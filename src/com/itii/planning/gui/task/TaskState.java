package com.itii.planning.gui.task;

public enum TaskState
{
    TO_DO, // 0
    DONE; // 1

    public static TaskState getTaskStateFromValue(int task)
    {
        return (task == 0) ? TO_DO : DONE;
    }

    public static int getValueFromTaskState( TaskState taskState )
    {
        return (taskState.equals(TO_DO) ? 0: 1);
    }

}
