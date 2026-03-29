package com.jbrigido.dev.view.transaction;

import com.jbrigido.dev.components.ATable;
import com.jbrigido.dev.utilities.AdminColor;
import javax.swing.BorderFactory;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXPanel;
import org.kordamp.ikonli.materialdesign2.MaterialDesignM;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

public class TransactionView extends JXPanel {

    public TransactionView() {
        initComponetsCustom();
    }

    private void initComponetsCustom() {

        pnlHeader = new JXPanel();
        btnSearch = new com.jbrigido.dev.components.AButton(FontIcon.of(MaterialDesignM.MAGNIFY, 32, AdminColor.WHITE));
        pnlContainer = new javax.swing.JPanel();
        tblscrollpane = new javax.swing.JScrollPane();
        tblTransaction = new ATable();
        pnlFooter = new JXPanel();
        startDatePicker = new JXDatePicker();
        endDatePicker = new JXDatePicker();
        pnlHeader.add(startDatePicker);
        pnlHeader.add(endDatePicker);
        btnIncome = new com.jbrigido.dev.components.AButton(FontIcon.of(MaterialDesignP.PLUS, 32, AdminColor.WHITE));
        btnExpenditure = new com.jbrigido.dev.components.AButton(FontIcon.of(MaterialDesignM.MINUS, 32, AdminColor.WHITE));

        setBackground(AdminColor.WHITE);
        setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(AdminColor.WHITE);
        pnlHeader.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        btnSearch.addActionListener(this::btnSearchActionPerformed);
        pnlHeader.add(btnSearch);

        add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlContainer.setBackground(AdminColor.WHITE);
        pnlContainer.setLayout(new java.awt.BorderLayout());
        pnlContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tblscrollpane.setViewportView(tblTransaction);

        pnlContainer.add(tblscrollpane, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);

        pnlFooter.setBackground(AdminColor.WHITE);
        pnlFooter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        btnIncome.setText("Income");
        pnlFooter.add(btnIncome);

        btnExpenditure.setText("Expenditure");
        pnlFooter.add(btnExpenditure);

        add(pnlFooter, java.awt.BorderLayout.SOUTH);
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new JXPanel();
        btnSearch = new com.jbrigido.dev.components.AButton(FontIcon.of(MaterialDesignM.MAGNIFY, 32, AdminColor.WHITE));
        pnlContainer = new javax.swing.JPanel();
        tblscrollpane = new javax.swing.JScrollPane();
        tblTransaction = new ATable()
        ;
        pnlFooter = new JXPanel();
        btnIncome = new com.jbrigido.dev.components.AButton(FontIcon.of(MaterialDesignP.PLUS, 32, AdminColor.WHITE));
        btnExpenditure = new com.jbrigido.dev.components.AButton(FontIcon.of(MaterialDesignM.MINUS,32, AdminColor.WHITE));

        setBackground(AdminColor.WHITE);
        setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(AdminColor.WHITE);
        pnlHeader.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        btnSearch.addActionListener(this::btnSearchActionPerformed);
        pnlHeader.add(btnSearch);

        add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlContainer.setBackground(AdminColor.WHITE);
        pnlContainer.setLayout(new java.awt.BorderLayout());

        tblscrollpane.setViewportView(tblTransaction);

        pnlContainer.add(tblscrollpane, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);

        pnlFooter.setBackground(AdminColor.WHITE);
        pnlFooter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        btnIncome.setText("Income");
        pnlFooter.add(btnIncome);

        btnExpenditure.setText("Expenditure");
        pnlFooter.add(btnExpenditure);

        add(pnlFooter, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private JXDatePicker startDatePicker, endDatePicker;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jbrigido.dev.components.AButton btnExpenditure;
    private com.jbrigido.dev.components.AButton btnIncome;
    private com.jbrigido.dev.components.AButton btnSearch;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTable tblTransaction;
    private javax.swing.JScrollPane tblscrollpane;
    // End of variables declaration//GEN-END:variables
}
