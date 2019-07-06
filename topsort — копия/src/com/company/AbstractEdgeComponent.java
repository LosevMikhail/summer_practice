package com.company;

import javax.swing.*;
import java.awt.*;


public abstract class AbstractEdgeComponent extends JComponent {
    protected JPanel parent;
    protected ActiveVertex source;
    protected ActiveVertex drain;

    public AbstractEdgeComponent(JPanel parent, ActiveVertex source, ActiveVertex drain) {
        this.parent = parent;
        this.source = source;
        this.drain = drain;
        source.addEdge(this);
        drain.addEdge(this);
    }

    @Override
    public void paintComponent(Graphics g){
        this.draw(g);
    }
    abstract protected void draw(Graphics g);
}
