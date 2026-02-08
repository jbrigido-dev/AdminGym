package com.jbrigido.dev.view.customer;

import com.jbrigido.dev.components.AButton;
import com.jbrigido.dev.components.ASearcherField;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.materialdesign2.MaterialDesignM;
import org.kordamp.ikonli.materialdesign2.MaterialDesignT;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class CustomerView extends JXPanel {
    private ASearcherField searcherField;
    private JXPanel bodyContainerPnl, headerContainerPnl, footerContainerPnl;
    private AButton btnOK;
    private JTable tblCustomers;
    private DefaultTableModel tblModel;

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
        this.btnOK = new AButton(FontIcon.of(MaterialDesign.MDI_PLUS, 32, AdminColor.WHITE));
        this.btnOK.setPreferredSize(new Dimension(60,60));
    }

    private void buildWindow() {
        setLayout(new BorderLayout());
        headerContainerPnl.setLayout(new BoxLayout(this.headerContainerPnl, BoxLayout.X_AXIS));
        headerContainerPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searcherField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        headerContainerPnl.add(searcherField);
        headerContainerPnl.add(btnOK);
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
        String[] headers = {"ID", "Name", "Number Phone", "Status", "Details", "Remove"};
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
        tblModel.addRow(new Object[]{"1", "Jonathan Brigido Perez", "7732606656", "Active"});
        tblModel.addRow(new Object[]{"2", "Jonathan Brigido Perez", "7732606656", "Active"});
    }

    private void buildTable() {
        buildHeaderTable();
        JScrollPane scrollPane = new JScrollPane(tblCustomers);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(AdminColor.WHITE);
        scrollPane.setBackground(AdminColor.WHITE);
        tblCustomers.setModel(tblModel);
        tblCustomers.getColumn("ID").setCellRenderer(new CustomerButtonRender(null, "text"));
        tblCustomers.getColumn("Name").setCellRenderer(new CustomerButtonRender(null, "text"));
        tblCustomers.getColumn("Number Phone").setCellRenderer(new CustomerButtonRender(null, "text"));
        tblCustomers.getColumn("Status").setCellRenderer(new CustomerButtonRender(null, "text"));
        tblCustomers.getColumn("Details").setCellRenderer(new CustomerButtonRender(FontIcon.of(MaterialDesignM.MAGNIFY, 32, AdminColor.PRIMARY), "show"));
        tblCustomers.getColumn("Remove").setCellRenderer(new CustomerButtonRender(FontIcon.of(MaterialDesignT.TRASH_CAN, 32, AdminColor.PRIMARY), "delete"));
        tblCustomers.setShowGrid(false);
        tblCustomers.setRowHeight(40);
        tblCustomers.setIntercellSpacing(new Dimension(0, 0));
        tblCustomers.setRowMargin(0);
        add(scrollPane);
    }

    class CustomerButtonRender extends DefaultTableCellRenderer {

        private Icon icon;
        private String action;

        public CustomerButtonRender(Icon icon, String action) {
            this.icon = icon;
            this.action = action;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JXPanel cell = new JXPanel();
            cell.setLayout(new BorderLayout());
            cell.setOpaque(true);
            JXLabel lblicon = new JXLabel(icon);
            lblicon.setForeground(AdminColor.PRIMARY);
            lblicon.setText(value == null ? "" : value.toString());
            cell.add(lblicon);
            if (isSelected) {
                cell.setBackground(AdminColor.PRIMARY_60);
                if (action.equals("show")) {
                    lblicon.setIcon(FontIcon.of(MaterialDesignM.MAGNIFY, 32, AdminColor.PRIMARY));
                }
                if (action.equals("delete")) {
                    lblicon.setIcon(FontIcon.of(MaterialDesignT.TRASH_CAN, 32, AdminColor.PRIMARY));
                }
            }else {
                cell.setBackground(AdminColor.WHITE);
            }
            return cell;
        }
    }

}
