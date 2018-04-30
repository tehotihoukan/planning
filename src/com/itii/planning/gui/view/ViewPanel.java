package com.itii.planning.gui.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.itii.planning.gui.MainWindow;
import com.itii.planning.gui.view.week.WeekPanel;

/**
 * 
 * @author Sebastien
 *
 */
public class ViewPanel extends JPanel implements ItemListener
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JComboBox<ViewMode> viewModeComboBox;

    private ListPanel listPanel;
    private WeekPanel weekPanel;
    private MonthPanel monthPanel;

    private MainWindow mainWindow;

    public ViewPanel(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        setBackground(Color.WHITE);

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.weighty = 0;

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(getviewModeComboBox(), gbc);
        
        {
            gbc.weightx = 1;
            gbc.weighty = 1;

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;
            // Mode par défaut
            getListPanel().setVisible(true);
            add(getListPanel(), gbc);

            getWeekPanel().setVisible(false);
            add(getWeekPanel(), gbc);

            getMonthPanel().setVisible(false);
            add(getMonthPanel(), gbc);
        }

    }

    public AbstractInnerViewPanel getDisplayedPanel()
    {
        AbstractInnerViewPanel panel = null;
        if (listPanel.isVisible())
        {
            panel = listPanel;
        } else if (weekPanel.isVisible())
        {
            panel = weekPanel;
        } else if (monthPanel.isVisible())
        {
            panel = monthPanel;
        }

        return panel;
    }

    public ListPanel getListPanel()
    {
        if (listPanel == null)
        {
            listPanel = new ListPanel(mainWindow);
        }
        return listPanel;
    }

    public WeekPanel getWeekPanel()
    {
        if (weekPanel == null)
        {
            weekPanel = new WeekPanel(mainWindow);
        }
        return weekPanel;
    }

    public MonthPanel getMonthPanel()
    {
        if (monthPanel == null)
        {
            monthPanel = new MonthPanel(mainWindow);
        }
        return monthPanel;
    }

    public JComboBox<ViewMode> getviewModeComboBox()
    {
        if (viewModeComboBox == null)
        {
            viewModeComboBox = new JComboBox<ViewMode>();
            for (final ViewMode viewMode : ViewMode.values())
            {
                viewModeComboBox.addItem(viewMode);
            }

            viewModeComboBox.addItemListener(this);

        }
        return viewModeComboBox;
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        boolean visible = false;
        // Cache le Panneau correspondant à l'élément desélectionné.
        if (e.getStateChange() == ItemEvent.DESELECTED)
        {
            visible = false;
        }
        // Montre le Panneau correspondant à l'élément sélectionné
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            visible = true;
        }
        ViewMode viewMode = (ViewMode) e.getItem();

        switch (viewMode)
        {
        case LIST:
            System.out.println("list est " + (visible ? "visible " : "masqué"));
            getListPanel().setVisible(visible);
            break;

        case WEEK:
            System.out.println("week est " + (visible ? "visible " : "masqué"));
            getWeekPanel().setVisible(visible);
            break;

        case MONTH:
            System.out
                    .println("month est " + (visible ? "visible " : "masqué"));
            getMonthPanel().setVisible(visible);
            break;
        }
        revalidate();
        repaint();
    }

}
