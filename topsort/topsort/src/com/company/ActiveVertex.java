package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class ActiveVertex extends JPanel implements MouseListener, MouseMotionListener {
    private Container parent;
    Point point;
    private final int v;
    private StyleSheet style;
    final static int VERTEX_R = 25;
    final static int VERTEX_D = VERTEX_R * 2;
    private Point mouse = new Point();

    private boolean flagCanMove = false;

    ActiveVertex( Container parent, int v, int x, int y ) {
        this.setPreferredSize(new Dimension(VERTEX_D, VERTEX_D));
        this.style = new StyleSheet(Color.BLACK, Color.BLACK, Color.RED);

        this.parent = parent;
        this.v = v;
        this.setBounds(x, y, VERTEX_D, VERTEX_D);
        point = new Point(x, y);

        parent.addMouseMotionListener(this);
        parent.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed (" + e.getX() + ", " + e.getY() + ")");
        System.out.println(point.x + ", " +  point.y + ", " + this.getBounds().height);

        if ((getBounds().x + VERTEX_R - e.getX()) * (getBounds().x + VERTEX_R - e.getX()) +
                (getBounds().y + VERTEX_R - e.getY()) * (getBounds().y + VERTEX_R - e.getY()) < VERTEX_R * VERTEX_R) {
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
            int dx = e.getX() - mouse.x;
            mouse.x = e.getX();
            int dy = e.getY() - mouse.y;
            mouse.y = e.getY();

            int x = point.x + dx;
            if (x < 0) point.x = 0;
            else if (x > 600 - VERTEX_D) point.x = 600 - VERTEX_D;
            else point.x = x;

            int y = point.y + dy;
            if (y < 0) point.y = 0;
            else if (y > 500 - VERTEX_D) point.y = 500 - VERTEX_D;
            else point.y = y;

            setLocation(point.x, point.y);
            parent.repaint(point.x, point.y, VERTEX_D, VERTEX_D);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(style.getFillColor());
        graphics.fillOval(0, 0, VERTEX_D, VERTEX_D);
        graphics.setColor(style.getBorderColor());
        graphics.drawOval(0,0, VERTEX_D, VERTEX_D);
        graphics.setColor(style.getFontClor());
        String vertexLabel = Integer.toString(v);

        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        graphics.drawString(vertexLabel, VERTEX_R - metrics.stringWidth(vertexLabel) / 2,
                VERTEX_R - metrics.getHeight() / 2 + metrics.getAscent()); // center the label
    }
}
