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
    protected HashMap<Integer, ActiveVertex> sort_points = new HashMap<>();
    protected Graph graph;

    AbstractGraphField(Graph graph) {
        setLayout(null);
        setPreferredSize(new Dimension(900, 600));

        this.graph = graph;



    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(255,255,255));
        g.fillRect(0,0, 900,600);
        if (points.size() > graph.VertexList().size()) {
            int k = points.size();
            for (int i = 0; i < k; i++) {
                if (!graph.VertexList().contains(i)) {
                    points.remove(i);
                }
            }
        }
        for (int i = 0; i < graph.VertexList().size(); i++) {
            Random r = new Random();
            if (!points.containsKey(i)) {
                points.put(i, new ActiveVertex(this, i, r.nextInt(600 - VERTEX_D) + VERTEX_R, r.nextInt(500 - VERTEX_D) + VERTEX_R, graph));
                add(points.get(i));
            }

        }
        drawGraph(g, points);
    }

    // Отрисовка графа


    //Отрисовка ребра
    protected void drawGraph(Graphics g,HashMap<Integer, ActiveVertex> points) {
        Edge edge;

        ActiveVertex i;
        int av = 0;
        for (int w=0; w < points.size(); w++) {

            for ( ; !points.containsKey(av); av++) {};
            i = points.get(av);
            av++;

            // boolean inRes = graph.checkV(i.v)!=null ? true : false;
            for (int j = 0; j < graph.V()+1; j++) {
                if ( ( edge = graph.checkE(i.v,j)) != null ) {
                    Color color;
                    //if (inRes && (graph.checkE(i.v,j)!= null) ) color = RESULT_EDGE_COLOR;
                    //else
                    color = BASE_EDGE_COLOR;
                    drawEdge(g, edge, color, points);
                }
                g.setColor(/*inRes ? RESULT_VERTEX_COLOR :*/ BASE_VERTEX_COLOR);
                drawVertex(g, i.v, points);
            }
        }
    }
    protected abstract void drawEdge(Graphics g, Edge edge, Color color,HashMap<Integer, ActiveVertex> points);

        /*
        if(!flag) {
            g.drawLine(v1.x, v1.y, v2.x, v2.y);
            drawArrow(g, v1, v2);
        } else {
            if(z%2==0) {
                g.drawArc(v1.x, 500, v2.x - v1.x, 100, 0, 180);
                z++;
            }
            else{
                g.drawArc(v1.x, 500, v2.x - v1.x, 100, 0, -180);
                z++;
            }

        }

         */



    abstract void drawArrow(Graphics g, Point source, Point drain);

    // Отрисовка вершины



    private void drawVertex(Graphics g, int v, HashMap<Integer, ActiveVertex> points) {
        drawCircle(g, points.get(v).point.x,  points.get(v).point.y, VERTEX_R);
        drawInt(g, points.get(v).point.x, points.get(v).point.y, v);
    }

    // Пишет text в точку (x,y)
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
}
