package com.jbrigido.dev.view.customer;

import com.jbrigido.dev.components.AButton;
import com.jbrigido.dev.components.ASearcherField;
import com.jbrigido.dev.components.ATable;
import com.jbrigido.dev.components.ATable.CustomerTableListener;
import com.jbrigido.dev.dto.CustomerDTO;
import com.jbrigido.dev.utilities.AdminColor;
import com.jbrigido.dev.utilities.TableConstants;
import org.jdesktop.swingx.JXPanel;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerView extends JXPanel {

    private ASearcherField searcherField;
    private JXPanel bodyContainerPnl, headerContainerPnl, footerContainerPnl;
    private AButton btnAdd;
    private ATable tblCustomers;
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
        this.tblCustomers = new ATable();
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
        tblCustomers.setActionButton("ID", TableConstants.TEXT);
        tblCustomers.setActionButton("Name", TableConstants.TEXT);
        tblCustomers.setActionButton("Phone Number", TableConstants.TEXT);
        tblCustomers.setActionButton("Status", TableConstants.TEXT);
        tblCustomers.setActionButton("Details", TableConstants.SHOW);
        tblCustomers.setActionButton("Remove", TableConstants.DELETE);
        tblCustomers.setCellCustomEditor("Details", TableConstants.SHOW);
        tblCustomers.setCellCustomEditor("Remove", TableConstants.DELETE);
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

    public void resetTable() {
        tblModel.setRowCount(0);
    }

    public void deleteRow(int row) {
        tblModel.removeRow(row);
    }

     public void setTableListener(CustomerTableListener tableListener) {
       tblCustomers.setTableListener(tableListener);
    }
    
    public void setFindAction(ActionListener actionListener) {
        this.searcherField.addActionListener(actionListener);
    }

    public String getTextSearchField() {
        return this.searcherField.getText();
    }
}
