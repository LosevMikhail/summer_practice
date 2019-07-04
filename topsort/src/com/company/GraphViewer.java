package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static com.company.PAR_S.*;

/*
    в каждом activeVertex храним координаты и радиус, перегружаем метод paintComponent.
    в GraphViewr для всех его компонентов paintComponent вызывается сам собой
    - основная идея для GraphViewr
 */

public class GraphViewer extends JPanel {
    private final static int DEFAULT_WIDTH = 600;
    private final static int DEFAULT_HEIGHT = 500;

    private JFrame parent;
    private Graph graph;

    GraphViewer(JFrame parent){
        this.parent = parent;
        setBackground(Color.BLUE);
        setBounds(0,0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }


    GraphViewer(JFrame parent, Graph graph){
        this.parent = parent;
        this.graph = graph;
        setBackground(Color.BLUE);
        setBounds(0,0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        for (Integer vertex: graph.getVertexes()) { //
            Random randomizer = new Random();
            // TODO: change VERTEX_D to something that feeds better:
            ActiveVertex activeVertex = new ActiveVertex(this, vertex,
                    randomizer.nextInt(this.getWidth() - VERTEX_D) + VERTEX_R,
                    randomizer.nextInt(this.getHeight() - VERTEX_D) + VERTEX_R);

            // dont have to store vertex components any other way, just add it
            this.add(activeVertex);
        }
        add(new JButton("asdsadsadasd")); // test
    }

    /*
    // paint method should be never overriden, that's the common rule (!!!)
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawRect(5, 50, 900, 450);

        drawGraph(graphics);

    }

    private void drawGraph(Graphics graphics) {
        // paintComponent method of every object of ActiveVertex class must have being invoked by its parent
    }
    */

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        //this.get
        //graphics.drawOval(50, 50, 30, 100);
    }

    @Override
    public JFrame getParent() {
        return parent;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
    public Graph getGraph() {
        return graph;
    }




}
