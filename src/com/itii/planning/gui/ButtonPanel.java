package com.itii.planning.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.itii.planning.gui.task.TaskDialog;
import com.itii.planning.gui.task.TaskState;
import com.itii.planning.gui.view.menu.MenuDetail;
import com.itii.task.PlanningTask;
import com.itii.utils.DateFormatUtil;;

public class ButtonPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JButton buttonCreate;
    private JButton buttonEdit;
    private JButton buttonCheck;
    private JButton buttonDuplicate;
    private JButton buttonDelete;

    private int asset = 10;

    private MainWindow mainWindow;

    public ButtonPanel(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        setBackground(Color.WHITE);
        GridBagLayout gbl = new GridBagLayout();

        setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(asset, asset, asset, asset);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(getButtonCreate(), gbc);

        gbc.weightx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(asset, asset, asset, asset);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(getButtonEdit(), gbc);

        gbc.weightx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(asset, asset, asset, asset);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(getButtonCheck(), gbc);

        gbc.weightx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(asset, asset, asset, asset);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(getButtonDuplicate(), gbc);

        gbc.weightx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(asset, asset, asset, asset);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(getButtonDelete(), gbc);

        gbc.weightx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(asset, asset, asset, asset);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.BASELINE;
        add(new JLabel(DateFormatUtil.getDate()), gbc);

    }

    public JButton getButtonCreate()
    {
        if (buttonCreate == null)
        {
            buttonCreate = new JButton(MenuDetail.CREATE.getDisplayName());
            buttonCreate.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    TaskDialog td = new TaskDialog(mainWindow);
                }
            });
        }
        return buttonCreate;
    }

    public JButton getButtonEdit()
    {
        if (buttonEdit == null)
        {
            buttonEdit = new JButton(MenuDetail.EDIT.getDisplayName());
            buttonEdit.setEnabled(false);
            buttonEdit.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    TaskDialog td = new TaskDialog(mainWindow, true);
                    PlanningTask[] pt = mainWindow.getMainPanel().getViewPanel()
                            .getDisplayedPanel().getSelectedPlanningTasks();
                    // in that case, we are sure there is only 1 selected entry
                    // at a time
                    if (pt.length > 0)
                    {
                        td.setPlanningTask(pt[0]);
                    }
                }
            });
        }
        return buttonEdit;
    }

    public JButton getButtonCheck()
    {
        if (buttonCheck == null)
        {
            buttonCheck = new JButton(MenuDetail.DONE.getDisplayName());
            buttonCheck.setEnabled(false);

            buttonCheck.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    MainWindow.getInstance().getMainPanel().getViewPanel()
                    .getDisplayedPanel().markPlanningTasks();
                }
            });
        }
        return buttonCheck;
    }

    public JButton getButtonDuplicate()
    {
        if (buttonDuplicate == null)
        {
            buttonDuplicate = new JButton(MenuDetail.DUPLICATE.getDisplayName());
            buttonDuplicate.setEnabled(false);
            buttonDuplicate.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    TaskDialog td = new TaskDialog(mainWindow);
                    PlanningTask[] pt = mainWindow.getMainPanel().getViewPanel()
                            .getDisplayedPanel().getSelectedPlanningTasks();
                    // in that case, we are sure there is only 1 selected entry
                    // at a time
                    if (pt.length > 0)
                    {
                        td.setPlanningTask(pt[0]);
                    }
                }
            });
        }
        return buttonDuplicate;
    }

    public JButton getButtonDelete()
    {
        if (buttonDelete == null)
        {
            buttonDelete = new JButton(MenuDetail.DELETE.getDisplayName());
            buttonDelete.setEnabled(false);
            buttonDelete.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    MainWindow.getInstance().getMainPanel().getViewPanel()
                            .getDisplayedPanel().deletePlanningTasks();
                }
            });
        }
        return buttonDelete;
    }

}
