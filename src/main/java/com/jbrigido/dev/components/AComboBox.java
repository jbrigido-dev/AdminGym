package com.jbrigido.dev.components;

import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;

public class AComboBox<T> extends JComboBox<T> {

    public AComboBox() {
        setRenderer(new ComboxRender());
    }


    class ComboxRender extends JXLabel implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

            JXLabel lbl = new JXLabel();
            lbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            lbl.setForeground(AdminColor.PRIMARY);
            if (value != null) {
                lbl.setText(value.toString());
            }
            return lbl;
        }
    }
}
