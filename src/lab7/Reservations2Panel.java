/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Reservations2Panel.java
 *
 * Created on Dec 2, 2011, 3:01:03 PM
 */

package lab7;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
/**
 *
 * @author srbecker
 */
public class Reservations2Panel extends javax.swing.JPanel {

    /** Creates new form Reservations2Panel */
    public Reservations2Panel(Guest guest, Connection conn) {
        this.guest = guest;
        this.conn = conn;
        Object[] columnName = {"Rooms", "Nightly_rate"};
        RoomModel = new DefaultTableModel(guest.getAllRooms(), columnName);
        r = guest.getAllRooms();

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
        m1 = new javax.swing.JTextField();
        d1 = new javax.swing.JTextField();
        m2 = new javax.swing.JTextField();
        d2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RoomTable = new javax.swing.JTable();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(lab7.Lab7App.class).getContext().getResourceMap(Reservations2Panel.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        m1.setText(resourceMap.getString("m1.text")); // NOI18N
        m1.setName("m1"); // NOI18N

        d1.setText(resourceMap.getString("d1.text")); // NOI18N
        d1.setName("d1"); // NOI18N

        m2.setText(resourceMap.getString("m2.text")); // NOI18N
        m2.setName("m2"); // NOI18N

        d2.setText(resourceMap.getString("d2.text")); // NOI18N
        d2.setName("d2"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkAvail(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        RoomTable.setModel(RoomModel);
        RoomTable.setName("RoomTable"); // NOI18N
        jScrollPane1.setViewportView(RoomTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(129, 129, 129)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkAvail(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkAvail
        // TODO add your handling code here:
        System.out.println("Clicked check availibily button");
        ArrayList<ArrayList<String>> results = null;
        String monthString1 = this.m1.getText();
        String dayString1 = this.d1.getText();
        String monthString2 = this.m2.getText();
        String dayString2 = this.d2.getText();

        if(verifyDates(dayString1, monthString1) && verifyDates(dayString2, monthString2)){
            //Call Model method
            results = this.guest.getAvailableRooms2(monthString1, Integer.parseInt(dayString1), monthString2, Integer.parseInt(dayString2));
            for(int i = 0; i < RoomModel.getRowCount(); i++){
                if(results.get(0).contains(RoomModel.getValueAt(i, 0).toString())){
                    //RoomModel.setValueAt("Fully Occupied", i, 0);
                    RoomModel.setValueAt("", i, 0);
                }
                else if(results.get(1).contains(RoomModel.getValueAt(i, 0).toString())){
                    //RoomModel.setValueAt("Partially Occupied", i, 0);
                    //r.set(i, (String)RoomModel.getValueAt(i, 0));
                    RoomModel.setValueAt("", i, 0);                }
                else{
                    //RoomModel.setValueAt("Empty", i, 0);
                    RoomModel.setValueAt((r[i][0]), i, 0);

                    RoomModel.setValueAt(
                    guest.getBaseRate((String)r[i][0],monthString1,dayString1,monthString2,dayString2), i, 1);

                }
            }

            /*for(int j=0; j<RoomModel.getRowCount(); j++) {
                if (RoomModel.getValueAt(j, 0).equals((Object)""))
                {
                    System.out.println("here");
                    continue;
                }
                else
                {
                    RoomModel.setValueAt(r.get(j), j, 0);
                }
            }*/
        }
        else{
            System.out.println("Invalid date");
            System.exit(1);
        }

        //RoomTable.setRowSelectionAllowed(true);
        //RoomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //RoomTable.getSelectionModel().addListSelectionListener(new RoomsSelectionListener2(RoomsTable));

    }//GEN-LAST:event_checkAvail

    /*private double findRate(String d1, String m1, String d2, String m2, String rm) {
        /*select to_char(to_date('28.08.1970','dd.mm.yyyy'), 'day') from dual;*/

        /*The price of one night of stay is determined as follows.
        On a weeknight, the price of the stay is the base rate of the room. On a
        weekend, the price of the stay is 110% of the base for the room. For any
        reservation or putative stay that covers the following nights: January 1, July
        4, September 6, October 30, the nightly rate for each night shall be 125%
        of the base for the room.
        These rates can get a 10% AAA or a 15% AARP discount adjustment
        during the reservation time.*/

        /*int stayLen;
        double rate = 0;
        String[] wd = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        if (m1.equals(m2)) {
            stayLen = Integer.parseInt(d2) - Integer.parseInt(d1);
        }
        else {

        }

        for (int k=0; k<stayLen; k++) {

            for (int i=0; i<6; i++) {
                if (guest.getWeekday(d1,m1).equals(wd[i])) {
                    //weekday rate
                    //rate += guest.getBasePrice(rm);
                    break;
                }
                else {
                   //weekend rate
                   // rate += guest.getBasePrice(rm) * 1.10;
                    break;
                }
            }

            Integer tempDay = Integer.parseInt(d1) + 1;
            d1 = tempDay.toString();

        }






        return 10;
    }*/


   private boolean verifyDates(String dayString, String monthString){
    String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    int day = 0;
    boolean monthFound = false;

    //Checking both for nulls + valid month
    if(monthString != null && dayString != null && !monthString.isEmpty() && !dayString.isEmpty()) {
        try{
           day = Integer.parseInt(dayString);
        }
        catch(NumberFormatException ex){
            System.out.println("Day entered is not an integer!!");
            System.exit(1);
        }
        for(String i : months){
           if(i.equals(monthString)) {
              monthFound = true;
           }
        }
    }

    //Error Checking Dates/Calling model method
    if(day > 0 && day <= 31 && monthFound == true){
        return true;
    }

    return false;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable RoomTable;
    private javax.swing.JTextField d1;
    private javax.swing.JTextField d2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField m1;
    private javax.swing.JTextField m2;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel RoomModel;
    private Guest guest;
    private Connection conn;
    private Object[][] r;

}
