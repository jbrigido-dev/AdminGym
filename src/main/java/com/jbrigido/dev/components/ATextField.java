package com.jbrigido.dev.components;

import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXTextField;

import javax.swing.*;
import java.awt.*;

public class ATextField extends JXTextField {

    private Color bgColor;

    public ATextField() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    }

    public ATextField(String promptText) {
        super(promptText);
        setOpaque(false);
        setForeground(AdminColor.PRIMARY);
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    }

    public ATextField(Color promptForeground, Color bgColor) {
        super("", promptForeground);
        this.bgColor = bgColor;
        setOpaque(false);
        setForeground(AdminColor.PRIMARY);
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    }

    public ATextField(String promptText, Color promptForeground, Color bgColor) {
        super(promptText, promptForeground);
        this.bgColor = bgColor;
        setOpaque(false);
        setForeground(AdminColor.PRIMARY);
        setBorder(BorderFactory.createEmptyBorder());
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (bgColor == null) {
            g2.setPaint(AdminColor.WHITE);
        } else {
            g2.setPaint(bgColor);
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }
}
