package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;
import java.util.Vector;

import static com.company.PAR_S.*;

public class ActiveVertex extends JComponent implements MouseListener, MouseMotionListener {
    Graph graph;
    private JPanel parent;
    Point point;
    final int v;
    private static Stack<ActiveVertex> stack = new Stack<>();
    private Point mouse = new Point();
    private boolean flagCanMove = false;
    private Vector<AbstractEdgeComponent> edges = new Vector<>();

    public void addEdge(AbstractEdgeComponent edgeComponent) {
        edges.add(edgeComponent);
    }


    ActiveVertex(JPanel parent, int v, int x, int y, Graph graph) {
        this.graph = graph;
        this.parent = parent;
        this.v = v;
        this.point = new Point(x, y);

        this.setSize(new Dimension( VERTEX_D + 2, VERTEX_D + 2)); // круг слегка не влезает в размеры, видимо из-за округления
        this.setLocation(point.x - VERTEX_R, point.y - VERTEX_R);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        drawVertex(g);
        parent.repaint();
    }

    private void drawVertex(Graphics g) {
        g.setColor(BASE_VERTEX_COLOR);
        g.fillOval(0, 0, VERTEX_D, VERTEX_D);
        g.setColor( CIRCLE_BORDERLINE_COLOR );
        g.drawOval(0, 0, VERTEX_D, VERTEX_D);
        g.setColor(TEXT_COLOR);
        Font font = new Font("Default", Font.PLAIN, TEXT_SIZE);  //Шрифт
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics(font);
        g.drawString(Integer.toString(v),
                VERTEX_R - fm.stringWidth(Integer.toString(v))/2,
                VERTEX_R + fm.getAscent()/2);
    }


    // Движение вершины
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(mouse.x+" "+mouse.y);
        if (Math.pow(VERTEX_R - e.getX(), 2) + Math.pow(VERTEX_R - e.getY(), 2) < Math.pow(VERTEX_R, 2))
        flagCanMove = true;
        mouse = e.getPoint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        flagCanMove = false;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (flagCanMove) {
            int dx = e.getX() - mouse.x;
            int dy = e.getY() - mouse.y; 

            int x = point.x + dx;
            if (x- VERTEX_R < 0) point.x = VERTEX_R;
            else if (x+ VERTEX_R>900) point.x = 900- VERTEX_R;
            else point.x = x;

            int y = point.y + dy;
            if (y- VERTEX_R < 0) point.y = VERTEX_R;
            else if (y+ VERTEX_R>500) point.y = 500- VERTEX_R;
            else point.y = y;

            setLocation(point.x- VERTEX_R, point.y- VERTEX_R);
            for (AbstractEdgeComponent edgeComponent: edges){
                edgeComponent.repaint();
            }
            this.repaint();
            //parent.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked");
        stack.push(this);
        if(stack.size()==2) {
            int k = stack.peek().v;
            System.out.println(k);
            stack.pop();
            System.out.println(stack.peek());
            /*
            if (k == stack.peek().v) {
                graph.removeV(stack.peek().v);
                stack.pop();
                parent.repaint();
            } else {

             */
                graph.addE(stack.peek().v, k);
                stack.pop();
                parent.repaint();
            //}
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
