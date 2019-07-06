package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.PAR_S.*;
import static com.company.PAR_S.VERTEX_R;

public class DirectEdgeComponent extends AbstractEdgeComponent {
    private double angle = Math.PI / 10;
    private double length = 20;
    private int extraSize = (int)(length * Math.sin(angle)) + 1;

    public DirectEdgeComponent(JPanel parent, ActiveVertex sourse, ActiveVertex drain) {
        super(parent, sourse, drain);
        // прибавили к прямоугольнику дополнительное место, чтобы стрелка всегда влезала
        this.setSize(new Dimension( Math.abs(sourse.point.x - drain.point.x) + 2 * extraSize,
                Math.abs(sourse.point.y - drain.point.y) + 2 * extraSize));
        this.setLocation(Math.min(sourse.point.x, drain.point.x) - extraSize,
                Math.min(sourse.point.y, drain.point.y) - extraSize);
    }

    @Override
    protected void draw(Graphics g) {
        // пересчитываем размер и положение на случай движения вершины:
        this.setSize(new Dimension( Math.abs(source.point.x - drain.point.x) + 2 * extraSize,
                Math.abs(source.point.y - drain.point.y) + 2 * extraSize));
        this.setLocation(Math.min(source.point.x, drain.point.x) - extraSize,
                Math.min(source.point.y, drain.point.y) - extraSize);

        Point v1 = new Point(Math.abs(this.getX() + extraSize - source.point.x),
                Math.abs(this.getY() + extraSize - source.point.y));
        Point v2 = new Point(Math.abs(this.getX() + extraSize - drain.point.x),
                Math.abs(this.getY() + extraSize - drain.point.y));

        ((Graphics2D)g).setStroke( EDGE_LINE_THIKNESS );
        g.setColor( EDGE_LINE_COLOR );
        drawArrow(g, v1, v2);
    }

    private void drawArrow(Graphics g, Point source, Point drain){
        Point arrowTop = computeA(source, drain);
        Point arrowEnd1 = computeB(source, drain, angle, length);
        Point arrowEnd2 = computeC(source, drain, angle, length);
        g.drawLine(arrowTop.x, arrowTop.y, arrowEnd1.x, arrowEnd1.y);
        g.drawLine(arrowTop.x, arrowTop.y, arrowEnd2.x, arrowEnd2.y);

        g.drawLine(source.x + extraSize, source.y + extraSize, arrowTop.x, arrowTop.y);
    }

    private double computeEdgeAngle(Point sourse, Point drain){
        return Math.atan2((double)(drain.y - sourse.y), (double)(drain.x - sourse.x));
    }

    private Point computeA(Point sourse, Point drain) {
        double edgeAngle = computeEdgeAngle(sourse, drain);
        return new Point((int)(drain.x + extraSize - VERTEX_R * Math.cos(edgeAngle)),
                (int)(drain.y + extraSize - VERTEX_R * Math.sin(edgeAngle)));
    }

    private Point computeB(Point sourse, Point drain, double angle, double length) {
        Point arrowTop = computeA(sourse, drain);
        double edgeAngle = computeEdgeAngle(sourse, drain);
        return new Point((int)(arrowTop.x - length * Math.cos(edgeAngle + angle)),
                (int)(arrowTop.y - length * Math.sin( edgeAngle + angle)));
    }

    private Point computeC(Point sourse, Point drain, double angle, double length) {
        Point arrowTop = computeA(sourse, drain);
        double edgeAngle = computeEdgeAngle(sourse, drain);
        return new Point((int)(arrowTop.x - length * Math.cos(edgeAngle - angle)),
                (int)(arrowTop.y - length * Math.sin( edgeAngle - angle)));
    }

}
