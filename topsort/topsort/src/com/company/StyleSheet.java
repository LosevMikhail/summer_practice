package com.company;

import java.awt.*;

public class StyleSheet {
    private Color FontClor;
    private Color BorderColor;
    private Color FillColor;

    StyleSheet(Color FontClor, Color BorderColor, Color FillColor){
        this.FontClor = FontClor;
        this.BorderColor = BorderColor;
        this.FillColor = FillColor;
    }

    public Color getFontClor() {
        return FontClor;
    }

    public void setFontClor(Color fontClor) {
        FontClor = fontClor;
    }

    public Color getBorderColor() {
        return BorderColor;
    }

    public void setBorderColor(Color borderColor) {
        BorderColor = borderColor;
    }

    public Color getFillColor() {
        return FillColor;
    }

    public void setFillColor(Color fillColor) {
        FillColor = fillColor;
    }
}
