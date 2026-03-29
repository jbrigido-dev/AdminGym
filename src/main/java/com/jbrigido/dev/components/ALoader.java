package com.jbrigido.dev.components;

import com.jbrigido.dev.utilities.AdminColor;
import javax.swing.JDialog;

public class ALoader extends JDialog {

    public ALoader(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setSize(100, 100);
        this.setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_container = new javax.swing.JPanel();
        lbl_loader = new javax.swing.JLabel();

        setBackground(AdminColor.WHITE);
        setUndecorated(true);

        pnl_container.setBackground(AdminColor.WHITE);
        pnl_container.setLayout(new java.awt.BorderLayout());

        lbl_loader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_loader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loader.gif"))); // NOI18N
        pnl_container.add(lbl_loader, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_container, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_loader;
    private javax.swing.JPanel pnl_container;
    // End of variables declaration//GEN-END:variables
}
