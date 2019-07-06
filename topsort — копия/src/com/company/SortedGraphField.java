package com.company;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

import static com.company.PAR_S.*;

public class SortedGraphField extends AbstractGraphField  {
    private HashMap<Integer, ActiveVertex> sort_points = new HashMap<>();
    private TopSort sort;
    private int z = 0;


    public SortedGraphField(Graph graph) {
        super(graph);
        setPreferredSize(new Dimension(900, 150));

        sort = new TopSort(graph);
        sort.alg();
        LinkedList<Integer> sorted = sort.ans;

        for (int i = 0; i < sort.ans.size(); i++) {
            sort_points.put(sorted.get(i), new ActiveVertex(this, i, i * 100,
                    this.getPreferredSize().height / 2, graph));
        }
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        sort.alg();
        LinkedList<Integer> sorted = sort.ans;

        for (int i = 0; i < sort.ans.size(); i++) {
            sort_points.put(sorted.get(i), new ActiveVertex(this, i, i * 100 + VERTEX_D,
                    this.getPreferredSize().height / 2, graph));
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0,255,0));
        g.fillRect(0,0, 900,600);

        drawGraph(g, sort_points);
    }

    @Override
    protected void drawEdge(Graphics g, Edge edge, Color color, HashMap<Integer, ActiveVertex> points) {
        Point v1 = new Point(points.get(edge.v1).point.x, points.get(edge.v1).point.y);
        Point v2 = new Point(points.get(edge.v2).point.x, points.get(edge.v2).point.y);
        ((Graphics2D)g).setStroke( EDGE_LINE_THIKNESS );  // Устанавливаем толщину ребра
        g.setColor( Color.BLACK );

        if(z % 2 == 0) {
            g.drawArc(v1.x, 500, v2.x - v1.x, 100, 0, 180);
        }
        else {
            g.drawArc(v1.x, 500, v2.x - v1.x, 100, 0, -180);
        }
        z++;
        drawArrow(g, v1, v2);
    }

    @Override
    void drawArrow(Graphics g, Point source, Point drain) {
        // TODO: implement
    }
}
