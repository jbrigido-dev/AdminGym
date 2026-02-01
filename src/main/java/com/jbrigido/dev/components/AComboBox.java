package com.jbrigido.dev.components;

import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;

public class AComboBox<E> extends JComboBox<E> {

    public AComboBox() {
        setRenderer(new ComboxRender());
    }


    class ComboxRender extends JXLabel implements ListCellRenderer {

        public ComboxRender() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JXPanel container = new JXPanel();
            container.setLayout(new BorderLayout());
            container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            container.setOpaque(true);
            container.setForeground(AdminColor.PRIMARY);
            container.setBackground(AdminColor.WHITE);
            setText(value == null ? "" : value.toString());
            if (isSelected) {
                container.setForeground(AdminColor.PRIMARY);
                container.setBackground(AdminColor.PRIMARY_80);
            }
            container.add(this, BorderLayout.CENTER);
            return container;
        }
    }
}
