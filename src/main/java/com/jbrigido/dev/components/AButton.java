package com.jbrigido.dev.components;

import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AButton extends JXButton {

    private boolean isPressed;

    public AButton() {
        setPreferredSize(new Dimension(120, 40));
    }

    public AButton(String text) {
        super(text);
        isPressed = false;
        setBorderPainted(false);
        setBorder(null);
        setForeground(AdminColor.WHITE);
        setBackground(AdminColor.TRANSPARENT);
        addMouseListener(new MouseButtonEvent());
        setPreferredSize(new Dimension(120, 40));
    }

    public AButton(Icon icon) {
        super(icon);
        isPressed = false;
        setBorderPainted(false);
        setBorder(null);
        setForeground(AdminColor.WHITE);
        setBackground(AdminColor.TRANSPARENT);
        addMouseListener(new MouseButtonEvent());
        setPreferredSize(new Dimension(120, 40));
    }

    public AButton(String text,Icon icon) {
        super(text, icon);
        isPressed = false;
        setBorderPainted(false);
        setBorder(null);
        setForeground(AdminColor.WHITE);
        setBackground(AdminColor.TRANSPARENT);
        addMouseListener(new MouseButtonEvent());
        setPreferredSize(new Dimension(120, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        if (!isPressed) {
            g2.setPaint(AdminColor.PRIMARY);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        } else {
            g2.setPaint(AdminColor.PRIMARY_100);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }
        super.paintComponent(g);
    }

    class MouseButtonEvent implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            isPressed = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            isPressed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

}
