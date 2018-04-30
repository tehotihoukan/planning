package com.itii.planning.gui.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.itii.planning.gui.ButtonPanel;
import com.itii.planning.gui.MainWindow;
import com.itii.planning.gui.task.TaskState;
import com.itii.planning.gui.view.menu.PMenuBar;
import com.itii.task.PlanningTask;
import com.itii.utils.DateFormatUtil;

public final class ListPanel extends AbstractInnerViewPanel
        implements ListSelectionListener
{

    /**
     * 
     */
    private static final long serialVersionUID = -5761368540539739651L;

    public enum Table
    {
        TITLE_TASK_ID(0, "Id"), TITLE_TASK_NAME(1,
                "Nom de la tâche"), TITLE_DUE_TIME(2,
                        "Date due"), TITLE_DETAILS(3,
                                "Détails"), TITLE_TASK_DONE(4, "Done"),;

        private int columnIndex;
        private String displayTitle;

        Table(int columnIndex, String display)
        {
            this.columnIndex = columnIndex;
            this.displayTitle = display;

        }

        public int getColumnIndex()
        {
            return columnIndex;
        }

        public String getDisplayTitle()
        {
            return displayTitle;
        }
    }

    private JTable planningListTable;

    public ListPanel(MainWindow mainWindow)
    {
        super(mainWindow);
        setBackground(Color.blue);

        String title[] = { Table.TITLE_TASK_ID.getDisplayTitle(),
                Table.TITLE_TASK_NAME.getDisplayTitle(),
                Table.TITLE_DUE_TIME.getDisplayTitle(),
                Table.TITLE_DETAILS.getDisplayTitle(),
                Table.TITLE_TASK_DONE.getDisplayTitle(), };

        // Data model with Cell Non-Editable
        DefaultTableModel table_model = new DefaultTableModel(title, 0)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        planningListTable = new JTable(table_model)
        {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                    int row, int column)
            {
                int rowIdx = convertRowIndexToModel(row);
                int colIdx= convertColumnIndexToModel(column);
                DefaultTableModel thisModel = (DefaultTableModel) getModel();
                Component c = super.prepareRenderer(renderer, row, column);

                Color bgColor = c.getBackground();
                Color fgColor = c.getForeground();
                
                // If "DONE" > Display row in red
                if ((boolean) thisModel.getValueAt(row,
                        Table.TITLE_TASK_DONE.getColumnIndex()))
                {
                    c.setBackground(Color.BLACK);
                    c.setForeground(Color.white);
                }
                else
                {
                    // Nothing
                }

                return c;
            }
        };
        planningListTable.setFillsViewportHeight(true);

        // Hide the ID Column
        TableColumnModel tcm = planningListTable.getColumnModel();
        tcm.removeColumn(tcm.getColumn(Table.TITLE_TASK_ID.getColumnIndex()));

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(new JScrollPane(planningListTable), gbc);

        planningListTable.getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void paint(Graphics g)
    {

        TableColumn column = null;

        // Ensure all columns are displayed evenly
        for (int i = 0; i < planningListTable.getColumnCount(); i++)
        {
            column = planningListTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(
                    getWidth() / planningListTable.getColumnCount());
        }

        // int index = 0;
        // for (PlanningTask pt : MainWindow.getInstance().getTasks())
        // {
        // DefaultTableModel model = (DefaultTableModel) planningList
        // .getModel();
        //
        // model.addRow(new String[] { pt.getName(),
        // pt.getDate() + "[" + pt.getTime() + "]", pt.getDetails() });
        // index++;
        //
        // }

        super.paint(g);
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        System.out.println("selection (first) : " + e.getFirstIndex());
        System.out.println("selection  (last) : " + e.getLastIndex());

        int min = 0;
        int max = 0;

        ButtonPanel buttonPanel = mainWindow.getMainPanel().getButtonPanel();
        PMenuBar menuBar = mainWindow.getPMenuBar();
        if (planningListTable.getSelectedRows().length > 0)
        {
            if (planningListTable.getSelectedRows().length == 1)
            {
                // selection
                min = planningListTable.getSelectedRow();
            }
            if (planningListTable.getSelectedRows().length > 1)
            {

                // selection
                min = planningListTable.getSelectedRows()[0];
                max = planningListTable
                        .getSelectedRows()[planningListTable.getSelectedRowCount()
                                - 1];
            }

            buttonPanel.getButtonDelete().setEnabled(true);
            menuBar.getMenuItemDelete().setEnabled(true);
            buttonPanel.getButtonCheck().setEnabled(true);
            menuBar.getMenuItemCheck().setEnabled(true);
            // Not allowed in multi-selection
            buttonPanel.getButtonDuplicate()
                    .setEnabled(max != 0 ? false : true);
            menuBar.getMenuItemDuplicate().setEnabled(max != 0 ? false : true);
            buttonPanel.getButtonEdit().setEnabled(max != 0 ? false : true);
            menuBar.getMenuItemEdit().setEnabled(max != 0 ? false : true);

        } else
        {
            buttonPanel.getButtonDelete().setEnabled(false);
            menuBar.getMenuItemDelete().setEnabled(false);
            buttonPanel.getButtonCheck().setEnabled(false);
            menuBar.getMenuItemCheck().setEnabled(false);
            buttonPanel.getButtonDuplicate().setEnabled(false);
            menuBar.getMenuItemDuplicate().setEnabled(false);
            buttonPanel.getButtonEdit().setEnabled(false);
            menuBar.getMenuItemEdit().setEnabled(false);
        }

        System.out.println("min : " + min);
        System.out.println("max : " + max);

        if (e.getFirstIndex() >= 0 && e.getLastIndex() > e.getFirstIndex())
        {
            // MultiSelection
            System.out.println("multi selection");
        }
        if (e.getFirstIndex() >= 0 && e.getFirstIndex() == e.getLastIndex())
        {
            System.out.println("selection unique");
        }

    }

    @Override
    public void deletePlanningTasks()
    {
        // Delete selected entry by default
        if (planningListTable.getSelectedRows().length >= 0)
        {
            int[] indexes = planningListTable.getSelectedRows();
            Arrays.sort(indexes);

            for (int i = (indexes.length - 1); i >= 0; i--)
            {
                System.out.println("deleting entry : #" + indexes[i]);
                ((DefaultTableModel) planningListTable.getModel())
                        .removeRow(indexes[i]);
            }
        }

    }

    @Override
    public PlanningTask[] getSelectedPlanningTasks()
    {
        // We know there won't be more selected rows that the total number of
        // rows, so we create a table of maximum size
        PlanningTask[] tasks = new PlanningTask[planningListTable.getRowCount()];

        DefaultTableModel tableModel = ((DefaultTableModel) planningListTable
                .getModel());
        int index = 0;
        for (int row : planningListTable.getSelectedRows())
        {

            String[] dateAndTime = DateFormatUtil
                    .unformatDate((String) tableModel.getValueAt(row,
                            Table.TITLE_DUE_TIME.getColumnIndex()));

            PlanningTask pt = new PlanningTask();
            pt.setName((String) tableModel.getValueAt(row,
                    Table.TITLE_TASK_NAME.getColumnIndex()));
            pt.setDate(dateAndTime[0]);
            pt.setTime(dateAndTime[1]);
            pt.setDetails((String) tableModel.getValueAt(row,
                    Table.TITLE_DETAILS.getColumnIndex()));

            tasks[index++] = pt;
            // planningList.getModel().get
        }

        return tasks;
    }

    @Override
    public void addPlanningTasks(PlanningTask... planingTasks)
    {
        for (PlanningTask pt : planingTasks)
        {
            ((DefaultTableModel) planningListTable.getModel())
                    .addRow(new Object[] { pt.getDbId(), pt.getName(),
                            DateFormatUtil.formatDate(pt.getDate(),
                                    pt.getTime()),
                            pt.getDetails(), pt.getState() == TaskState.DONE });
        }
        validate();
        repaint();
    }

    @Override
    public void updatePlanningTask(PlanningTask planningTask)
    {
        int row = planningListTable.getSelectedRow();
        if (row >= 0)
        {

            // Update currently selected row

            // ID
            ((DefaultTableModel) planningListTable.getModel()).setValueAt(
                    planningTask.getDbId(), row,
                    Table.TITLE_TASK_NAME.getColumnIndex());

            // NAME
            ((DefaultTableModel) planningListTable.getModel()).setValueAt(
                    planningTask.getName(), row,
                    Table.TITLE_TASK_NAME.getColumnIndex());
            // DATE
            ((DefaultTableModel) planningListTable.getModel()).setValueAt(
                    DateFormatUtil.formatDate(planningTask.getDate(),
                            planningTask.getTime()),
                    row, Table.TITLE_DUE_TIME.getColumnIndex());
            // DETAILS
            ((DefaultTableModel) planningListTable.getModel()).setValueAt(
                    planningTask.getDetails(), row,
                    Table.TITLE_DETAILS.getColumnIndex());
            // DONE / TO_DO
            ((DefaultTableModel) planningListTable.getModel()).setValueAt(
                    planningTask.getState() == TaskState.DONE, row,
                    Table.TITLE_DETAILS.getColumnIndex());

        }
        validate();
        repaint();
    }

    @Override
    public void markPlanningTasks()
    {
        PlanningTask[] tasks = new PlanningTask[planningListTable.getRowCount()];

        DefaultTableModel tableModel = ((DefaultTableModel) planningListTable
                .getModel());
        int index = 0;
        for (int row : planningListTable.getSelectedRows())
        {

            boolean currentState = (boolean) ((DefaultTableModel) planningListTable
                    .getModel()).getValueAt(row,
                            Table.TITLE_TASK_DONE.getColumnIndex());

            // Invert the current state
            boolean newState = !currentState;
            ((DefaultTableModel) planningListTable.getModel())
                    .setValueAt(newState, row, Table.TITLE_TASK_DONE.getColumnIndex());
        }

    }

}
