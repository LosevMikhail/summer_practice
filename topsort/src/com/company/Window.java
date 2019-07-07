package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


import static com.company.PAR_S.*;

public class Window extends JPanel{
    private Graph graph;
    private TopSort sort;
    private boolean flag = false;
    private SourceGraphField graphField;
    private SortedGraphField sortedGraphField;


    public Window(Graph graph, TopSort sort) {
        this.sort = sort;
        this.graph = graph;
        graphField = new SourceGraphField(graph);
        //new SourceGraphField(graph);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty   = 0.3;
        constraints.gridheight = 1;
        constraints.gridy   = 0  ;
        constraints.gridx = 0;
        contentPanel.add(graphField, constraints);

        LeftControlPanel leftPanel = new LeftControlPanel();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty   = 0.3;
        constraints.gridx     = 0;
        constraints.gridy     = 1;
        sortedGraphField = new SortedGraphField(graph);
        contentPanel.add(sortedGraphField, constraints);

        constraints.weighty   = 0.0;
        constraints.gridx     = 0;
        constraints.gridy     = 2;
        contentPanel.add(leftPanel, constraints);

        RigthControlPanel cp  = new RigthControlPanel(this.graph, contentPanel, graphField, sortedGraphField);
        constraints.gridx = 1;      // первая ячейка таблицы по горизонтали
        constraints.gridy = 0;
        constraints.gridheight = 3;
        contentPanel.add(cp, constraints);

        this.add(contentPanel);
    }


}