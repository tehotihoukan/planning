package com.itii.planning.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.itii.planning.gui.view.ViewPanel;

public class MainPanel extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ViewPanel viewPanel;

    private ButtonPanel buttonPanel;

    private MainWindow mainWindow;

    public MainPanel(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;

        setBackground(Color.orange);

        GridBagLayout gbl = new GridBagLayout();

        setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 0.8;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(getViewPanel(), gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(getButtonPanel(), gbc);
    }

    public ViewPanel getViewPanel()
    {
        if (viewPanel == null)
        {
            viewPanel = new ViewPanel( mainWindow );
        }
        return viewPanel;
    }

    public ButtonPanel getButtonPanel()
    {
        if (buttonPanel == null)
        {
            buttonPanel = new ButtonPanel(mainWindow);
        }
        return buttonPanel;
    }
}
