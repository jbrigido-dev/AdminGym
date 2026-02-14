package com.jbrigido.dev.view.customer.details;

import com.jbrigido.dev.components.AButton;
import com.jbrigido.dev.components.AForm;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.*;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerDetailsView extends JXFrame {

    private JXImagePanel picture;
    private JXPanel customerDataContainer, customerDataPanel, pnlContainer, pnlContainerMain;
    private JXPanel customerHistoryPanel, customerHeaderHistoryPanel, customerFooterHistoryPanel;
    private AButton btnSave;
    private JTable tblCustomers;
    private JXLabel lblHistory;
    private DefaultTableModel tblModel;
    private AForm form;

    public CustomerDetailsView() {
        initComponents();
        buildWindow();
        buildTable();
    }


    private void initComponents() {
        this.picture = new JXImagePanel();
        this.pnlContainer = new JXPanel();
        this.pnlContainerMain = new JXPanel();
        this.customerDataContainer = new JXPanel();
        this.customerDataPanel = new JXPanel();
        this.customerHistoryPanel = new JXPanel();
        this.customerHeaderHistoryPanel = new JXPanel();
        this.customerFooterHistoryPanel = new JXPanel();
        this.btnSave = new AButton("Save", FontIcon.of(MaterialDesignP.PLUS, 32, AdminColor.WHITE));
        this.form = new AForm();
        this.lblHistory = new JXLabel("Jonathan's History");
        this.tblCustomers = new JXTable();
    }

    private void buildWindow() {
        pnlContainer.setLayout(new BorderLayout());
        pnlContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlContainer.add(customerDataContainer, BorderLayout.WEST);
        pnlContainer.add(customerHistoryPanel, BorderLayout.CENTER);
        customerDataContainer.setLayout(new BoxLayout(customerDataContainer, BoxLayout.Y_AXIS));
        customerDataContainer.add(form);
        customerDataPanel.setLayout(new GridBagLayout());
        customerHistoryPanel.setLayout(new BorderLayout());
        customerHistoryPanel.add(customerFooterHistoryPanel, BorderLayout.SOUTH);
        customerHistoryPanel.add(customerHeaderHistoryPanel, BorderLayout.NORTH);
        changeBackground();
        tblCustomers.setDefaultRenderer(Object.class, new CustomerRender());
        buildHeaderHistoryPanel();
        buildFooterHistoryPanel();
        add(pnlContainer);
    }


    private void buildHeaderTable() {
        String[] headers = {"MEMBERSHIP", "START DATE", "END DATE", "STATUS"};

        tblCustomers.getTableHeader().setDefaultRenderer(new DefaultTableRenderer() {
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
        });

        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(headers);
        tblModel.addRow(new Object[]{"1", "30-01-2026", "30-01-2026", "Active"});
        tblModel.addRow(new Object[]{"2", "30-01-2026", "30-01-2026", "Active"});


    }


    private void buildTable() {
        buildHeaderTable();
        JScrollPane scrollPane = new JScrollPane(tblCustomers);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(AdminColor.WHITE);
        scrollPane.setBackground(AdminColor.WHITE);
        tblCustomers.setModel(tblModel);
        tblCustomers.setShowGrid(false);
        tblCustomers.setRowHeight(40);
        tblCustomers.setIntercellSpacing(new Dimension(0, 0));
        tblCustomers.setRowMargin(0);
        customerHistoryPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void changeBackground() {
        Component[] components = {form,pnlContainer, pnlContainerMain, customerDataPanel, customerDataContainer, customerDataPanel, picture, customerHistoryPanel, customerFooterHistoryPanel, customerHeaderHistoryPanel};
        for (Component component : components) {
            component.setBackground(AdminColor.WHITE);
        }
    }

    private void buildHeaderHistoryPanel() {
        customerHeaderHistoryPanel.setLayout(new FlowLayout());
        customerHeaderHistoryPanel.add(lblHistory);
        lblHistory.setForeground(AdminColor.PRIMARY);
        customerHeaderHistoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void buildFooterHistoryPanel() {
        customerFooterHistoryPanel.setLayout(new BorderLayout());
        customerFooterHistoryPanel.add(btnSave, BorderLayout.WEST);
        customerFooterHistoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    class CustomerRender extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JXPanel cell = new JXPanel();
            cell.setLayout(new BorderLayout());
            cell.setOpaque(true);
            JXLabel lblicon = new JXLabel();
            lblicon.setForeground(AdminColor.PRIMARY);
            lblicon.setText(value == null ? "" : value.toString());
            lblicon.setHorizontalAlignment(SwingConstants.CENTER);
            cell.add(lblicon);
            if (isSelected) {
                cell.setBackground(AdminColor.PRIMARY_60);
            } else {
                cell.setBackground(AdminColor.WHITE);
            }
            return cell;
        }
    }
}
