package com.itii.planning.gui.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.itii.planning.gui.MainWindow;
import com.itii.planning.gui.task.TaskDialog;
import com.itii.planning.gui.view.AbstractInnerViewPanel;
import com.itii.planning.gui.view.ListPanel;
import com.itii.planning.gui.view.MonthPanel;
import com.itii.planning.gui.view.week.WeekPanel;

public class PMenuBar extends JMenuBar
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JMenu menuFile;
    private JMenuItem menuItemQuit;

    private JMenu menuEdit;
    private JMenuItem menuItemCreate;
    private JMenuItem menuItemEdit;
    private JMenuItem menuItemCheck;
    private JMenuItem menuItemDuplicate;
    private JMenuItem menuItemDelete;

    private JMenu menuView;
    private JMenuItem menuItemList;
    private JMenuItem menuItemWeek;
    private JMenuItem menuItemMonth;

    private JMenu menuHelp;

    private MainWindow mainWindow;

    public PMenuBar(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        // FICHIER
        {
            menuFile = new JMenu("Fichier");
            menuItemQuit = new JMenuItem("Quitter");
            menuItemQuit.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    // Save before leaving

                    // Leave the application with error code 0
                    System.exit(0);
                }
            });
            menuFile.add(menuItemQuit);
            add(menuFile);
        }

        // EDITER
        {
            menuEdit = new JMenu("Editer");
            menuItemCreate = new JMenuItem(MenuDetail.CREATE.getDisplayName());
            menuItemCreate.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    TaskDialog td = new TaskDialog(mainWindow);
                }
            });

            menuEdit.add(menuItemCreate);

            menuItemEdit = new JMenuItem(MenuDetail.EDIT.getDisplayName());
            menuItemEdit.setEnabled(false);
            menuEdit.add(menuItemEdit);

            menuItemCheck = new JMenuItem(MenuDetail.DONE.getDisplayName());
            menuItemCheck.setEnabled(false);
            menuEdit.add(menuItemCheck);

            menuItemDuplicate = new JMenuItem(MenuDetail.DUPLICATE.getDisplayName());
            menuItemDuplicate.setEnabled(false);
            menuEdit.add(menuItemDuplicate);

            menuItemDelete = new JMenuItem(MenuDetail.DELETE.getDisplayName());
            menuItemDelete.setEnabled(false);
            menuEdit.add(menuItemDelete);

            add(menuEdit);
        }

        // VUES
        {
            menuView = new JMenu("Vues");
            menuItemList = new JMenuItem("Liste");
            menuItemList.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    // Currently display panel
                    AbstractInnerViewPanel displayedPanel = mainWindow
                            .getMainPanel().getViewPanel().getDisplayedPanel();
                    if (displayedPanel instanceof ListPanel)
                    {
                        System.out.println("list est déjà visible");
                    } else
                    {
                        System.out.println("affichage de list");
                        displayedPanel.setVisible(false);
                        mainWindow.getMainPanel().getViewPanel().getListPanel()
                                .setVisible(true);
                    }

                }
            });
            menuView.add(menuItemList);
            menuItemWeek = new JMenuItem("Semaine");
            menuItemWeek.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {

                    // Currently display panel
                    AbstractInnerViewPanel displayedPanel = mainWindow
                            .getMainPanel().getViewPanel().getDisplayedPanel();
                    if (displayedPanel instanceof WeekPanel)
                    {
                        System.out.println("week est déjà visible");
                    } else
                    {
                        System.out.println("affichage de week");
                        displayedPanel.setVisible(false);
                        mainWindow.getMainPanel().getViewPanel().getWeekPanel()
                                .setVisible(true);
                    }

                }
            });

            menuView.add(menuItemWeek);
            menuItemMonth = new JMenuItem("Mois");
            menuItemMonth.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    // Currently display panel
                    AbstractInnerViewPanel displayedPanel = mainWindow
                            .getMainPanel().getViewPanel().getDisplayedPanel();
                    if (displayedPanel instanceof MonthPanel)
                    {
                        System.out.println("week est déjà visible");
                    } else
                    {
                        System.out.println("affichage de week");
                        displayedPanel.setVisible(false);
                        mainWindow.getMainPanel().getViewPanel().getMonthPanel()
                                .setVisible(true);
                    }
                }
            });

            menuView.add(menuItemMonth);
            add(menuView);
        }

        // AIDE
        {
            menuHelp = new JMenu("Help");
            add(menuHelp);
        }
    }

    public JMenu getMenuFile()
    {
        return menuFile;
    }

    public JMenuItem getMenuItemQuit()
    {
        return menuItemQuit;
    }

    public JMenu getMenuEdit()
    {
        return menuEdit;
    }

    public JMenuItem getMenuItemCreate()
    {
        return menuItemCreate;
    }

    public JMenuItem getMenuItemEdit()
    {
        return menuItemEdit;
    }

    public JMenuItem getMenuItemCheck()
    {
        return menuItemCheck;
    }

    public JMenuItem getMenuItemDuplicate()
    {
        return menuItemDuplicate;
    }

    public JMenuItem getMenuItemDelete()
    {
        return menuItemDelete;
    }

    public JMenu getMenuView()
    {
        return menuView;
    }

    public JMenuItem getMenuItemList()
    {
        return menuItemList;
    }

    public JMenuItem getMenuItemWeek()
    {
        return menuItemWeek;
    }

    public JMenuItem getMenuItemMonth()
    {
        return menuItemMonth;
    }

    public JMenu getMenuHelp()
    {
        return menuHelp;
    }

    public MainWindow getMainWindow()
    {
        return mainWindow;
    }

}
