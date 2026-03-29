package com.jbrigido.dev.components;

import com.jbrigido.dev.utilities.AdminColor;
import com.jbrigido.dev.utilities.TableConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.materialdesign2.MaterialDesignT;
import org.kordamp.ikonli.swing.FontIcon;

public class ATable extends JTable {

    private CustomerTableListener tableListener;

    public ATable() {
        setHeader();
        setDefaultRenderer(String.class, new CustomerRender());
    }

    private void setHeader() {
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JXPanel identifier = new JXPanel();
                JXLabel lbl = new JXLabel(value.toString());
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setForeground(AdminColor.WHITE);
                identifier.setBackground(AdminColor.PRIMARY);
                identifier.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                identifier.add(lbl);
                return identifier;
            }

        }
        );
    }

    public void setTableListener(CustomerTableListener tableListener) {
        this.tableListener = tableListener;
    }

    public void setActionButton(String column, String action) {
        getColumn(column).setCellRenderer(new CustomerButtonRender(action));
    }

    public void setCellCustomEditor(String column, String action) {
        getColumn(column).setCellEditor(new CustomerButtonEditor(action));
    }

    class CustomerRender extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JXPanel cell = new JXPanel();
            cell.setLayout(new BorderLayout());
            JXLabel lblicon = new JXLabel();
            lblicon.setText(value == null ? "" : value.toString());
            lblicon.setHorizontalAlignment(SwingConstants.CENTER);
            if (isSelected) {
                cell.setBackground(AdminColor.PRIMARY_60);
            } else {
                cell.setBackground(AdminColor.WHITE);
                lblicon.setForeground(AdminColor.PRIMARY);
            }
            cell.add(lblicon);
            return cell;
        }
    }

    class CustomerButtonRender extends DefaultTableCellRenderer {

        private String action;

        public CustomerButtonRender(String action) {
            this.action = action;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JXLabel text = new JXLabel();
            text.setHorizontalAlignment(SwingConstants.CENTER);
            JXPanel cell = new JXPanel(new BorderLayout());
            cell.setOpaque(true);
            if (action.equals(TableConstants.TEXT)) {
                text.setText((value == null) ? "" : value.toString());
            }

            if (isSelected) {
                text.setForeground(AdminColor.WHITE);
                cell.setBackground(AdminColor.PRIMARY_90);
                if (action.equals(TableConstants.DELETE)) {
                    text.setIcon(FontIcon.of(MaterialDesignT.TRASH_CAN, 32, AdminColor.WHITE));
                }
                if (action.equals(TableConstants.SHOW)) {
                    text.setIcon(FontIcon.of(MaterialDesign.MDI_MAGNIFY, 32, AdminColor.WHITE));
                }
            } else {
                text.setForeground(AdminColor.PRIMARY);
                cell.setBackground(AdminColor.WHITE);
                if (action.equals(TableConstants.DELETE)) {
                    text.setIcon(FontIcon.of(MaterialDesignT.TRASH_CAN, 32, AdminColor.PRIMARY));
                }
                if (action.equals(TableConstants.SHOW)) {
                    text.setIcon(FontIcon.of(MaterialDesign.MDI_MAGNIFY, 32, AdminColor.PRIMARY));
                }
            }
            cell.add(text, BorderLayout.CENTER);
            return cell;
        }
    }

    class CustomerButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

        private String action;
        private JXButton command;
        private int currentRow;

        public CustomerButtonEditor(String action) {
            this.action = action;
            command = new JXButton();
            command.setActionCommand(action);
            command.addActionListener(this);
            command.setBorderPainted(false);
            command.setContentAreaFilled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            fireEditingStopped();
            if (tableListener != null) {
                if (TableConstants.SHOW.equals(e.getActionCommand())) {
                    tableListener.onShow(currentRow);
                }
                if (TableConstants.DELETE.equals(e.getActionCommand())) {
                    tableListener.onDelete(currentRow);
                }
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentRow = row;
            if (action.equals(TableConstants.SHOW)) {
                command.setIcon(FontIcon.of(MaterialDesign.MDI_MAGNIFY, 32, AdminColor.PRIMARY));
            }
            if (action.equals(TableConstants.DELETE)) {
                command.setIcon(FontIcon.of(MaterialDesignT.TRASH_CAN, 32, AdminColor.PRIMARY));
            }
            return command;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }

    public interface CustomerTableListener {

        void onShow(int row);

        void onDelete(int row);
    }

}
