package com.jbrigido.dev.components;

import com.jbrigido.dev.utilities.AdminColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class APasswordField extends JPasswordField {

    private Color bgColor;
    private String promptText;
    private Color promptColor;


    public APasswordField(String promptText) {
        super(promptText);
        this.promptText = promptText;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addFocusListener(new focusPasswordListener());
    }

    public APasswordField(String promptText, Color promptForeground, Color bgColor) {
        super(promptText);
        this.promptText = promptText;
        this.promptColor = promptForeground;
        this.bgColor = bgColor;
        setOpaque(false);
        setForeground(promptForeground);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addFocusListener(new focusPasswordListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (bgColor == null) {
            g2.setPaint(AdminColor.WHITE);
        } else {
            g2.setPaint(bgColor);
        }
    }

    class focusPasswordListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            String current = String.copyValueOf(getPassword());
            if (current.equals(promptText)) {
                setText("");
            } else {
                setText(current);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (getPassword().length == 0) {
                setText(promptText);
            }
        }
    }

}
