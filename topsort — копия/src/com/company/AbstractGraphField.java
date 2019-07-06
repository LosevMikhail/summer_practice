package com.company;

import javax.swing.*;
import java.awt.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import static com.company.PAR_S.*;

public abstract class AbstractGraphField extends JPanel  {
    private int z = 0;
    protected HashMap<Integer, ActiveVertex> points = new HashMap<>();
    protected Graph graph;

    AbstractGraphField(Graph graph) {
        setLayout(null);
        this.graph = graph;
    }

    protected abstract void drawEdge(Graphics g, Edge edge, Color color,HashMap<Integer, ActiveVertex> points);

    protected void drawGraph(Graphics g, HashMap<Integer, ActiveVertex> points) {
        Edge edge;
        ActiveVertex vertex;
        int av = 0;
        for (int w = 0; w < points.size(); w++) {

            for (; !points.containsKey(av); av++) {}
            vertex = points.get(av);
            av++;

            for (int j = 0; j < graph.V() + 1; j++) {
                if ( ( edge = graph.checkE(vertex.v, j)) != null ) {
                    Color color;
                    color = BASE_EDGE_COLOR;

                    this.add(new DirectEdgeComponent(this, points.get(graph.checkE(vertex.v, j).v1),
                            points.get(graph.checkE(vertex.v, j).v2)));
                    //drawEdge(g, edge, color, points);
                }
            }
        }
    }



    abstract void drawArrow(Graphics g, Point source, Point drain);
/*
    protected void drawVertex(Graphics g, int v, HashMap<Integer, ActiveVertex> points) {

        drawCircle(g, points.get(v).point.x,  points.get(v).point.y, VERTEX_R);
        drawInt(g, points.get(v).point.x, points.get(v).point.y, v);




    }

    private void drawInt(Graphics g, int x, int y, int text) {
        g.setColor(TEXT_COLOR);
        Font font = new Font("Default", Font.PLAIN, TEXT_SIZE);  //Шрифт

        g.setFont(font);

        FontMetrics fm = g.getFontMetrics(font);

        g.drawString(Integer.toString(text),
                x-fm.stringWidth(Integer.toString(text))/2,
                y+fm.getAscent()/2);
    }

    private void drawCircle(Graphics g, int cX, int cY, int rad) {
        g.fillOval(cX-rad, cY-rad, rad*2, rad*2);

        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.setColor( CIRCLE_BORDERLINE_COLOR );
        g.drawOval(cX-rad, cY-rad, rad*2, rad*2);
    }

 */
}
