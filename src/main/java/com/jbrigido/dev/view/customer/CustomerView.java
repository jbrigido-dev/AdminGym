package com.jbrigido.dev.view.customer;

import com.jbrigido.dev.components.AButton;
import com.jbrigido.dev.components.ASearcherField;
import com.jbrigido.dev.dto.CustomerDTO;
import com.jbrigido.dev.utilities.AdminColor;
import com.jbrigido.dev.utilities.TableConstants;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.materialdesign2.MaterialDesignT;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerView extends JXPanel {
    private ASearcherField searcherField;
    private JXPanel bodyContainerPnl, headerContainerPnl, footerContainerPnl;
    private AButton btnAdd;
    private JTable tblCustomers;
    private DefaultTableModel tblModel;
    private CustomerTableListener listenerTbl;

    public CustomerView() {
        initComponents();
        buildWindow();
        buildTable();
    }

    private void initComponents() {
        this.headerContainerPnl = new JXPanel();
        this.bodyContainerPnl = new JXPanel();
        this.footerContainerPnl = new JXPanel();

        this.searcherField = new ASearcherField("Search a customer");
        this.tblCustomers = new JTable();
        this.btnAdd = new AButton(FontIcon.of(MaterialDesign.MDI_PLUS, 32, AdminColor.WHITE));
        this.btnAdd.setPreferredSize(new Dimension(60, 60));
    }

    private void buildWindow() {
        setLayout(new BorderLayout());
        headerContainerPnl.setLayout(new BoxLayout(this.headerContainerPnl, BoxLayout.X_AXIS));
        headerContainerPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searcherField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        headerContainerPnl.add(searcherField);
        headerContainerPnl.add(btnAdd);
        add(headerContainerPnl, BorderLayout.NORTH);
        add(bodyContainerPnl, BorderLayout.CENTER);
        add(footerContainerPnl, BorderLayout.SOUTH);
        changeBackground();
    }

    private void changeBackground() {
        Component[] components = {this, bodyContainerPnl, headerContainerPnl, footerContainerPnl};
        for (Component component : components) {
            component.setBackground(AdminColor.WHITE);
        }
    }

    private void buildHeaderTable() {
        String[] headers = {"ID", "Name", "Phone Number", "Status", "Details", "Remove"};
        tblCustomers.getTableHeader().setDefaultRenderer(
                new DefaultTableRenderer() {
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
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(headers);
    }

    private void buildTable() {
        buildHeaderTable();
        JScrollPane scrollPane = new JScrollPane(tblCustomers);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(AdminColor.WHITE);
        scrollPane.setBackground(AdminColor.WHITE);
        tblCustomers.setModel(tblModel);
        setRenders();
        tblCustomers.setShowGrid(false);
        tblCustomers.setRowHeight(40);
        tblCustomers.setIntercellSpacing(new Dimension(0, 0));
        tblCustomers.setRowMargin(0);
        add(scrollPane);
    }

    private void setRenders() {
        tblCustomers.getColumn("ID").setCellRenderer(new CustomerButtonRender(TableConstants.TEXT));
        tblCustomers.getColumn("Name").setCellRenderer(new CustomerButtonRender(TableConstants.TEXT));
        tblCustomers.getColumn("Phone Number").setCellRenderer(new CustomerButtonRender(TableConstants.TEXT));
        tblCustomers.getColumn("Status").setCellRenderer(new CustomerButtonRender(TableConstants.TEXT));
        tblCustomers.getColumn("Details").setCellRenderer(new CustomerButtonRender(TableConstants.SHOW));
        tblCustomers.getColumn("Details").setCellEditor(new CustomerButtonEditor(TableConstants.SHOW));
        tblCustomers.getColumn("Remove").setCellRenderer(new CustomerButtonRender(TableConstants.DELETE));
        tblCustomers.getColumn("Remove").setCellEditor(new CustomerButtonEditor(TableConstants.DELETE));
    }

    public void addListenerBtnAdd(ActionListener l) {
        btnAdd.addActionListener(l);
    }

    public void addData(CustomerDTO customer) {
        tblModel.addRow(new Object[]{
                customer.id(),
                customer.name() + " " + customer.lastName() + " " + customer.motherName(),
                customer.phoneNumber(),
                customer.status() ? "Active" : "Inactive",
                "",
                ""
        });
    }

    public long getCustomerIDat(int row) {
        return (long) tblCustomers.getValueAt(row, 0);
    }

    public int getRowNumber() {
        return tblCustomers.getSelectedRow();
    }

    public void deleteRow(int row) {
        tblModel.removeRow(row);
    }

    public void setListenerTbl(CustomerTableListener listenerTbl) {
        this.listenerTbl = listenerTbl;
    }

    public interface CustomerTableListener {
        void onShow(int row);

        void onDelete(int row);
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

            if (action.equals(TableConstants.TEXT)) {
                text.setText((value == null) ? "" : value.toString());
            }

            if (isSelected) {
                text.setForeground(AdminColor.TRANSPARENT_100);
                if (action.equals(TableConstants.DELETE)) {
                    text.setIcon(FontIcon.of(MaterialDesignT.TRASH_CAN, 32, AdminColor.TRANSPARENT_100));
                }
                if (action.equals(TableConstants.SHOW)) {
                    text.setIcon(FontIcon.of(MaterialDesign.MDI_MAGNIFY, 32, AdminColor.TRANSPARENT_100));
                }
            } else {
                text.setForeground(AdminColor.PRIMARY);
                if (action.equals(TableConstants.DELETE)) {
                    text.setIcon(FontIcon.of(MaterialDesignT.TRASH_CAN, 32, AdminColor.PRIMARY));
                }
                if (action.equals(TableConstants.SHOW)) {
                    text.setIcon(FontIcon.of(MaterialDesign.MDI_MAGNIFY, 32, AdminColor.PRIMARY));
                }
            }
            return text;
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
            if (listenerTbl != null) {
                if (TableConstants.SHOW.equals(e.getActionCommand())) {
                    listenerTbl.onShow(currentRow);
                }
                if (TableConstants.DELETE.equals(e.getActionCommand())) {
                    listenerTbl.onDelete(currentRow);
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
}
