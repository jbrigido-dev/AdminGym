package com.jbrigido.dev.components;

import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXTextField;

import javax.swing.*;
import java.awt.*;

public class ATextField extends JXTextField {

    private Color bgColor;

    public ATextField() {
        setOpaque(false);
        setFont(new Font("Arial", Font.BOLD, 12));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public ATextField(String promptText) {
        super(promptText);
        setOpaque(false);
        setFont(new Font("Arial", Font.BOLD, 12));
        setForeground(AdminColor.PRIMARY);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public ATextField(Color promptForeground, Color bgColor) {
        super("", promptForeground);
        this.bgColor = bgColor;
        setOpaque(false);
        setFont(new Font("Arial", Font.BOLD, 12));
        setForeground(AdminColor.PRIMARY);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public ATextField(String promptText, Color promptForeground, Color bgColor) {
        super(promptText, promptForeground);
        this.bgColor = bgColor;
        setOpaque(false);
        setFont(new Font("Arial",Font.BOLD, 12));
        setForeground(AdminColor.PRIMARY);
        setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (bgColor == null) {
            g2.setPaint(AdminColor.WHITE);
        } else {
            g2.setPaint(bgColor);
        }
        super.paintComponent(g);
    }
}
