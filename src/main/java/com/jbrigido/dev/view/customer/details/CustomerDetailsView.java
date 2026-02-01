package com.jbrigido.dev.view.customer.details;

import com.jbrigido.dev.components.AButton;
import com.jbrigido.dev.components.ATextField;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXImagePanel;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerDetailsView extends JXPanel {

    private JXImagePanel picture;
    private JXPanel customerDataContainer, customerDataPanel;
    private JXPanel customerHistoryPanel, customerHeaderHistoryPanel, customerFooterHistoryPanel;
    private ATextField txtCustomerID;
    private ATextField txtCustomerName;
    private ATextField txtCustomerLastName;
    private ATextField txtCustomerMotherLastName;
    private ATextField txtCustomerPhone;
    private ATextField txtCustomerEmail;
    private ATextField txtCustomerAddress;
    private ATextField txtCustomerBirthday;
    private AButton btnSave;
    private JTable tblCustomers;
    private JXLabel lblHistory;
    private DefaultTableModel tblModel;

    public CustomerDetailsView() {
        initComponents();
        buildWindow();
        buildCustomerDataPanel();
        buildTable();
    }


    private void initComponents() {
        this.picture = new JXImagePanel();
        this.customerDataContainer = new JXPanel();
        this.customerDataPanel = new JXPanel();
        this.customerHistoryPanel = new JXPanel();
        this.customerHeaderHistoryPanel = new JXPanel();
        this.customerFooterHistoryPanel = new JXPanel();
        this.txtCustomerID = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtCustomerName = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtCustomerLastName = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtCustomerMotherLastName = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtCustomerPhone = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtCustomerEmail = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtCustomerAddress = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtCustomerBirthday = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.btnSave = new AButton("Renew", FontIcon.of(MaterialDesignP.PLUS,32, AdminColor.WHITE));
        this.lblHistory = new JXLabel("Jonathan's History");
        this.tblCustomers = new JXTable();
    }

    private void buildWindow() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(customerDataContainer, BorderLayout.WEST);
        add(customerHistoryPanel, BorderLayout.CENTER);
        customerDataContainer.setLayout(new BoxLayout(customerDataContainer, BoxLayout.Y_AXIS));
        customerDataContainer.add(customerDataPanel);
        customerDataPanel.setLayout(new GridBagLayout());
        customerHistoryPanel.setLayout(new BorderLayout());
        customerHistoryPanel.add(customerFooterHistoryPanel, BorderLayout.SOUTH);
        customerHistoryPanel.add(customerHeaderHistoryPanel, BorderLayout.NORTH);
        changeBackground();
        tblCustomers.setDefaultRenderer(Object.class, new CustomerRender());
        buildHeaderHistoryPanel();
        buildFooterHistoryPanel();
    }

    private void buildCustomerDataPanel() {
        picture.setImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage().getScaledInstance(200, 200, Image.SCALE_FAST));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        customerDataPanel.add(picture, gbc);
        txtCustomerID.setEditable(false);
        txtCustomerID.setFocusable(false);
        txtCustomerID.setForeground(AdminColor.PRIMARY_100);
        gbc.gridx--;
        gbc.gridy++;
        customerDataPanel.add(txtCustomerID, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        customerDataPanel.add(txtCustomerName, gbc);
        gbc.gridx++;
        customerDataPanel.add(txtCustomerLastName, gbc);
        gbc.gridx++;
        customerDataPanel.add(txtCustomerMotherLastName, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        customerDataPanel.add(txtCustomerBirthday, gbc);
        gbc.gridy++;
        customerDataPanel.add(txtCustomerEmail, gbc);
        gbc.gridy++;
        customerDataPanel.add(txtCustomerAddress, gbc);
        gbc.gridy++;
        customerDataPanel.add(txtCustomerPhone, gbc);
        gbc.gridy++;

        Dimension fieldSize = new Dimension(140, 45);

        txtCustomerID.setPreferredSize(fieldSize);
        txtCustomerName.setPreferredSize(fieldSize);
        txtCustomerLastName.setPreferredSize(fieldSize);
        txtCustomerMotherLastName.setPreferredSize(fieldSize);
        txtCustomerBirthday.setPreferredSize(fieldSize);
        txtCustomerEmail.setPreferredSize(fieldSize);
        txtCustomerAddress.setPreferredSize(fieldSize);
        txtCustomerPhone.setPreferredSize(fieldSize);
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
        Component[] components = {this, customerDataPanel, customerDataContainer, customerDataPanel, picture, customerHistoryPanel, customerFooterHistoryPanel, customerHeaderHistoryPanel};
        for (Component component : components) {
            component.setBackground(AdminColor.WHITE);
        }
    }

    private void buildHeaderHistoryPanel() {
        customerHeaderHistoryPanel.setLayout(new FlowLayout());
        customerHeaderHistoryPanel.add(lblHistory);
        lblHistory.setForeground(AdminColor.PRIMARY);
        customerHeaderHistoryPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    private void buildFooterHistoryPanel() {
        customerFooterHistoryPanel.setLayout(new BorderLayout());
        customerFooterHistoryPanel.add(btnSave, BorderLayout.WEST);
        customerFooterHistoryPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
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
