package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import static com.company.PAR_S.*;

public class Window extends JPanel{
    public String input = new String();
    HashMap<Integer, ActiveVertex> points = new HashMap<>();
    HashMap<Integer, ActiveVertex> sort_points = new HashMap<>();
    Graph graph;
    TopSort sort;
    private boolean flag = false;
    public AbstractGraphField graphField;
    ///////////////////////////////

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
        contentPanel.add(new SortedGraphField(graph), constraints);

        constraints.weighty   = 0.0;
        constraints.gridx     = 0;
        constraints.gridy     = 2;
        contentPanel.add(leftPanel, constraints);

        RightControlPanel cp  = new RightControlPanel(this.graph, contentPanel, graphField);
        constraints.gridx = 1;      // первая ячейка таблицы по горизонтали
        constraints.gridy = 0;
        constraints.gridheight = 3;
        contentPanel.add(cp, constraints);

        this.add(contentPanel);
    }


}

