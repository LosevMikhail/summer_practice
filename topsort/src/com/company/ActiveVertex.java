package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.util.Random;

import static com.company.PAR_S.VERTEX_R;

public class ActiveVertex extends JPanel implements MouseListener, MouseMotionListener {
    static int i = 0;
    Container parent;
    Point point;
    final int v;
    private final static int VERTEX_R = 25;
    private final static int VERTEX_D = VERTEX_R*2;
    private Point mouse = new Point();

    private boolean flagCanMove = false;


    ActiveVertex( Container parent, int v, int x, int y ) {
        this.setPreferredSize(new Dimension(VERTEX_D, VERTEX_D));

        this.parent = parent;
        this.v = v;

        point = new Point(x, y);

        setSize(new Dimension( VERTEX_D, VERTEX_D));
        setLocation(point.x- VERTEX_R, point.y- VERTEX_R);
        parent.addMouseMotionListener(this);
        parent.addMouseListener(this);
    }

    // Движение вершины
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
        if ((point.x - e.getX()) * (point.x - e.getX()) + (point.y - e.getY()) * (point.y - e.getY()) < VERTEX_R * VERTEX_R) {
            flagCanMove = true;
            mouse.x = e.getX();
            mouse.y = e.getY();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        flagCanMove = false;
        System.out.println("mouse released");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (flagCanMove) {
            int dx = e.getX() - point.x;
            int dy = e.getY() - point.y;

            int x = point.x + dx;
            if (x- VERTEX_R < 0) point.x = VERTEX_R;
            else if (x+ VERTEX_R>600) point.x = 600- VERTEX_R;
            else point.x = x;

            int y = point.y + dy;
            if (y- VERTEX_R < 0) point.y = VERTEX_R;
            else if (y+ VERTEX_R>500) point.y = 500- VERTEX_R;
            else point.y = y;

            setLocation(point.x- VERTEX_R, point.y- VERTEX_R);
            parent.repaint(point.x - VERTEX_R, point.y - VERTEX_R, VERTEX_D, VERTEX_D);
        }
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println(i);
        i++;
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics graphics) {
        //super.paintComponent(graphics);
        graphics.drawOval(50, 50, 30, 100);
    }
}
