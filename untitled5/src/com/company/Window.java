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
    HashMap<Integer, ActiveVertex> points = new HashMap<>();
    HashMap<Integer, ActiveVertex> sort_points = new HashMap<>();
    private Graph graph;
    private TopSort sort;
    private boolean flag = false;

    public Window(Graph graph, TopSort sort) {
        this.sort = sort;
        this.graph = graph;

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy   = 0  ;

        GraphField graphField = new GraphField(graph, sort);
        constraints.gridx = 0;
        contentPanel.add(graphField, constraints);


       /*
        JLabel commentsLabel = new JLabel("Comments", SwingConstants.CENTER);
        commentsLabel.setOpaque(true);
        commentsLabel.setBackground(Color.cyan);
        commentsLabel.setForeground(Color.red);
        */
        LeftControlPanel leftPanel = new LeftControlPanel();

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.weightx   = 0.0;
        constraints.gridwidth = 1;
        constraints.gridx     = 0;
        constraints.gridy     = 1;
        contentPanel.add(leftPanel, constraints);

        RigthControlPanel cp  = new RigthControlPanel();
        constraints.gridx = 1;      // первая ячейка таблицы по горизонтали
        constraints.gridy = 0;
        constraints.gridheight = 2;
        contentPanel.add(cp, constraints);


        add(contentPanel);
    }

    }

