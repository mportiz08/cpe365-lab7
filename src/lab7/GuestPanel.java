/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuestPanel.java
 *
 * Created on Nov 28, 2011, 9:24:01 PM
 */
package lab7;

import java.sql.*;
import javax.swing.JFrame;

/**
 *
 * @author srbecker
 */
public class GuestPanel extends javax.swing.JPanel
{
  private Connection conn;
  private Guest guest;
  private JFrame ContentPane;

  /** Creates new form OwnerPanel */
  public GuestPanel()
  {
    initComponents();
  }

  public void addConnection(Connection c)
  {
    this.conn = c;
    this.guest = new Guest(c);
    this.ContentPane = new JFrame();
    this.ContentPane.setSize(900, 600);
    this.ContentPane.setVisible(false);
  }




@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(700, 400));
        setPreferredSize(new java.awt.Dimension(700, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(lab7.Lab7App.class).getContext().getResourceMap(GuestPanel.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ratesMethod(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Reservations2ButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(39, 39, 39)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(49, 49, 49)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(134, 134, 134)
                        .add(jLabel1)))
                .addContainerGap(343, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(43, 43, 43)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                .addContainerGap(101, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void ratesMethod(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratesMethod
    // TODO add your handling code here:
    System.out.println("Clicked Rooms/Rates Button");
    RoomsRatesPanel rooms_rates = new RoomsRatesPanel(guest, conn);
    ContentPane.setContentPane(rooms_rates);
    ContentPane.setVisible(true);
}//GEN-LAST:event_ratesMethod

private void Reservations2ButtonActionPerformed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Reservations2ButtonActionPerformed
    // TODO add your handling code here:
    System.out.println("Reservations Button");
    Reservations2Panel reservations2 = new Reservations2Panel(guest, conn);
    ContentPane.setContentPane(reservations2);
    ContentPane.setVisible(true);
}//GEN-LAST:event_Reservations2ButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
