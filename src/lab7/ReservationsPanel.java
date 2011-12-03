/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReservationsPanel.java
 *
 * Created on Nov 30, 2011, 7:28:44 PM
 */
package lab7;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author spartan
 */
public class ReservationsPanel extends javax.swing.JPanel {

    private Owner owner;
    
    /**
     * Private Class for Listening for Row Selections for 1 Date
     */
    private class ReservationsSelectionListener implements ListSelectionListener {

        private JTable table;
        
        ReservationsSelectionListener(JTable table1) {
           this.table = table1;
        }
        public void valueChanged(ListSelectionEvent e) {
           int row = 0;
           if (table.getRowSelectionAllowed() && !table.getColumnSelectionAllowed() && !e.getValueIsAdjusting()) {
              row = table.getSelectedRow();
          
              int resNum = Integer.parseInt(ReservationsModel.getValueAt(row, 0).toString());
              Object[][] info = owner.ReservationInfo(resNum);
              if(InfoModel.getRowCount() == 0){
                InfoModel.addRow(info[0]);
              }
              else{
                  for(int i = 0; i < InfoModel.getRowCount(); i++){
                      InfoModel.removeRow(i);
                  }
                  InfoModel.addRow(info[0]);
              }
           }
        }
    }
    
    /** Creates new form ReservationsPanel */
    public ReservationsPanel(Owner own) {
        this.owner = own;
        Object[] ReservationsName = {"Reservations"};
        Object[] InfoName = {"Code", "Room", "Checkin", "Checkout", "Rate", "Lastname", "Firstname", "Adults", "Kids"};
        ReservationsModel = new DefaultTableModel(new Object[0][0], ReservationsName);
        InfoModel = new DefaultTableModel(new Object[0][0], InfoName);
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        one = new javax.swing.JTextField();
        two = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        three = new javax.swing.JTextField();
        four = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReservationsTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        InfoTable = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel1.setName("jLabel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(lab7.Lab7App.class).getContext().getResourceMap(ReservationsPanel.class);
        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        one.setText(resourceMap.getString("one.text")); // NOI18N
        one.setName("one"); // NOI18N

        two.setText(resourceMap.getString("two.text")); // NOI18N
        two.setName("two"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        three.setText(resourceMap.getString("three.text")); // NOI18N
        three.setName("three"); // NOI18N

        four.setText(resourceMap.getString("four.text")); // NOI18N
        four.setName("four"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        ReservationsTable.setModel(ReservationsModel);
        ReservationsTable.setName("ReservationsTable"); // NOI18N
        ReservationsTable.setRowHeight(30);
        ReservationsTable.setRowMargin(5);
        ReservationsTable.setSelectionBackground(resourceMap.getColor("ReservationsTable.selectionBackground")); // NOI18N
        jScrollPane1.setViewportView(ReservationsTable);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        InfoTable.setModel(InfoModel);
        InfoTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        InfoTable.setFillsViewportHeight(true);
        InfoTable.setName("InfoTable"); // NOI18N
        InfoTable.setRowHeight(30);
        InfoTable.setRowMargin(5);
        jScrollPane2.setViewportView(InfoTable);

        jToggleButton1.setText(resourceMap.getString("jToggleButton1.text")); // NOI18N
        jToggleButton1.setName("jToggleButton1"); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(375, 375, 375)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(227, Short.MAX_VALUE)
                                .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(two, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)))
                                .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel6)
                                .addGap(6, 6, 6)))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(423, 423, 423)
                        .addComponent(jToggleButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(two, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
// TODO add your handling code here:
    System.out.println("ReservationsPanel button pooshed");
    String stmt = "SELECT DISTINCT Code from rooms, reservations WHERE Checkin > " + 
            "to_date('" + Integer.parseInt(two.getText()) + "-" + one.getText() + 
            "-10','DD-MM-YY') " + 
            "AND Checkin < to_date('" + Integer.parseInt(four.getText()) + "-" + 
            three.getText() + "-10','DD-MM-YY')";
    Integer[][] reservations = owner.findReservation(stmt);

    if(ReservationsModel.getRowCount() != 0){   
       for(int j = 0; j < ReservationsModel.getRowCount(); j++) {
          ReservationsModel.removeRow(j);
       }
       ReservationsModel.fireTableRowsDeleted(ERROR, ERROR);
    }
    
    for(Integer[] i : reservations){
       ReservationsModel.addRow(i);
    }
    ReservationsModel.fireTableChanged(null);
    /*for(Integer[] i : reservations){
        System.out.println("In reservations loop");
        if(ReservationsModel.getRowCount() == 0){
            System.out.println("found empty table");
            ReservationsModel.addRow(i);
        }
        else{
           boolean flag = false;
           for(int j = 0; j < ReservationsModel.getRowCount(); j++) {
              if(i[0].equals(ReservationsModel.getValueAt(j, 0))){
                 flag = true;
              }
           }
           if(!flag){
               ReservationsModel.addRow(i);
           }
        }
    }*/
    
    ReservationsTable.setRowSelectionAllowed(true);
    ReservationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    ReservationsTable.getSelectionModel().addListSelectionListener(new ReservationsSelectionListener(ReservationsTable));
}//GEN-LAST:event_jToggleButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable InfoTable;
    private javax.swing.JTable ReservationsTable;
    private javax.swing.JTextField four;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField one;
    private javax.swing.JTextField three;
    private javax.swing.JTextField two;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel ReservationsModel;
    private DefaultTableModel InfoModel;
}    
