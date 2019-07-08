package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import static com.company.PAR_S.*;

public class ActiveVertex extends JPanel implements MouseListener, MouseMotionListener {
    Graph graph;
    JPanel parent;
    Point point;
    final int v;
    static Stack<ActiveVertex> stack = new Stack<>();

    private Point mouse = new Point();

    private boolean flagCanMove = false;


    ActiveVertex( JPanel parent, int v, int x, int y, Graph graph ) {
        this.graph = graph;
        this.parent = parent;
        this.v = v;

       // Random random = new Random();
        point = new Point(x, y);

        setSize(new Dimension( VERTEX_D, VERTEX_D));
        setLocation(point.x- VERTEX_R, point.y- VERTEX_R);

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    // Движение вершины
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(mouse.x+" "+mouse.y);
        flagCanMove = true;
        mouse.x = e.getX();
        mouse.y = e.getY();
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
            parent.repaint();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        drawVertex(g);
        parent.repaint(this.getBounds());

    }

    protected void drawVertex(Graphics g) {
        drawCircle(g, VERTEX_R, VERTEX_R, VERTEX_R);
        drawInt(g, VERTEX_R, VERTEX_R, v);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        stack.push(this);
        if(stack.size()==2) {
            int k = stack.peek().v;
            stack.pop();

            if (k == stack.peek().v) {
                graph.removeV(stack.peek().v);
                stack.pop();
                parent.repaint();
            } else {

                graph.addE(stack.peek().v, k);
                stack.pop();
                parent.repaint();
            }
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
