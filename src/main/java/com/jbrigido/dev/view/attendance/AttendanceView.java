package com.jbrigido.dev.view.attendance;

import com.jbrigido.dev.components.ASearcherField;
import com.jbrigido.dev.dto.CustomerDTO;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceView extends JXPanel {

    private ASearcherField txtSearcher;
    private JTable tblAttendance;
    private JXLabel lblTitle;
    private JXPanel pnlHeader;
    private DefaultTableModel model;

    public AttendanceView() {
        initComponents();
    }

    private void initComponents() {
        this.txtSearcher = new ASearcherField("Input your ID");
        this.tblAttendance = new JTable();
        this.lblTitle = new JXLabel("Attendance List");
        this.pnlHeader = new JXPanel();
        buildWindow();
        buildTable();
    }

    private void buildWindow() {
        this.setBackground(AdminColor.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.pnlHeader.setBackground(AdminColor.WHITE);
        this.pnlHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        this.setLayout(new BorderLayout());
        this.add(this.pnlHeader, BorderLayout.NORTH);
        this.pnlHeader.setLayout(new BorderLayout());
        this.pnlHeader.add(this.lblTitle, BorderLayout.WEST);
        this.pnlHeader.add(this.txtSearcher, BorderLayout.CENTER);
    }

    private void buildHeaders() {
        String[] headers = {"NAME", "CURRENT DATE", "END DATE"};
        this.model = new DefaultTableModel();
        this.model.setColumnIdentifiers(headers);
        this.tblAttendance.setDefaultRenderer(Object.class, new CustomerRender());
    }

    private void buildTable() {
        buildHeaders();
        tblAttendance.getTableHeader().setDefaultRenderer(new DefaultTableRenderer() {
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
        JScrollPane tblPane = new JScrollPane(tblAttendance);
        tblPane.getViewport().setBackground(AdminColor.WHITE);
        tblPane.setBackground(AdminColor.WHITE);
        this.add(tblPane, BorderLayout.CENTER);
        tblAttendance.setShowGrid(false);
        tblAttendance.setRowHeight(40);
        tblAttendance.setIntercellSpacing(new Dimension(0, 0));
        tblAttendance.setRowMargin(0);
        tblAttendance.setModel(model);
    }

    public void resetTableRows() {
        model.setRowCount(0);
    }

    public void addElement(String[] data) {
        model.addRow(new Object[]{data[0], data[1], data[2]});
    }

    public void addSearcherEvent(ActionListener l) {
        txtSearcher.addActionListener(l);
    }

    public String getSearcherText() {
        return this.txtSearcher.getText();
    }

    public void setTxtSearcher(String text) {
        this.txtSearcher.setText(text);
    }

    class CustomerRender extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JXPanel cell = new JXPanel();
            cell.setLayout(new BorderLayout());
            cell.setOpaque(true);
            JXLabel lblIcon = new JXLabel();
            lblIcon.setForeground(AdminColor.PRIMARY);
            lblIcon.setText(value == null ? "" : value.toString());
            lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
            cell.add(lblIcon);
            if (isSelected) {
                cell.setBackground(AdminColor.PRIMARY_60);
            } else {
                cell.setBackground(AdminColor.WHITE);
            }
            return cell;
        }
    }

}
