package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
        setLayout(null);
        setBackground(Color.BLUE);
        setBounds(0,0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        for (Integer vertex: graph.getVertexes()) {
            Random random = new Random();
            ActiveVertex activeVertex = new ActiveVertex(this, vertex,
                    random.nextInt(this.getWidth() - ActiveVertex.VERTEX_D),
                    random.nextInt(this.getHeight() - ActiveVertex.VERTEX_D));
            this.add(activeVertex);
            System.out.println("ActiveVertex added: " + activeVertex.toString());
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
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