/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author nasrallah
 */
public class SlidingPuzzle extends javax.swing.JFrame {

    /**
     * Creates new form SlidingPuzzle
     */
    Solver1 s = new Solver1();
    JLabel jLabel[] = new JLabel[9];

    public SlidingPuzzle() {

        initComponents();
        s.set_goal();
        s.A_star();
        for (int i = 0; i < 9; i++) {
            jLabel[i] = new JLabel();
        }
        jLabel[0].setBackground(new java.awt.Color(51, 102, 255));
        jLabel[0].setForeground(new java.awt.Color(0, 51, 255));
        jLabel[0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel[0].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/1.png"))); // NOI18N
        jLabel[0].setMaximumSize(new java.awt.Dimension(125, 125));
        jLabel[0].setMinimumSize(new java.awt.Dimension(125, 125));
        jLabel[0].setPreferredSize(new java.awt.Dimension(154, 154));
        jPanel1.add(jLabel[0], new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel[1].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel[1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/2.png"))); // NOI18N
        jLabel[1].setMaximumSize(new java.awt.Dimension(125, 125));
        jLabel[1].setMinimumSize(new java.awt.Dimension(125, 125));
        jLabel[1].setPreferredSize(new java.awt.Dimension(154, 154));
        jPanel1.add(jLabel[1], new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 150, 150));

        jLabel[2].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel[2].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/3.png"))); // NOI18N
        jLabel[2].setMaximumSize(new java.awt.Dimension(125, 125));
        jLabel[2].setMinimumSize(new java.awt.Dimension(125, 125));
        jLabel[2].setPreferredSize(new java.awt.Dimension(154, 154));
        jPanel1.add(jLabel[2], new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 150, -1));

        jLabel[3].setBackground(new java.awt.Color(51, 102, 255));
        jLabel[3].setForeground(new java.awt.Color(0, 51, 255));
        jLabel[3].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel[3].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/4.png"))); // NOI18N
        jLabel[3].setMaximumSize(new java.awt.Dimension(125, 125));
        jLabel[3].setMinimumSize(new java.awt.Dimension(125, 125));
        jLabel[3].setPreferredSize(new java.awt.Dimension(154, 154));
        jPanel1.add(jLabel[3], new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 150, -1));

        jLabel[4].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel[4].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/5.png"))); // NOI18N
        jLabel[4].setMaximumSize(new java.awt.Dimension(125, 125));
        jLabel[4].setMinimumSize(new java.awt.Dimension(125, 125));
        jLabel[4].setPreferredSize(new java.awt.Dimension(154, 154));
        jPanel1.add(jLabel[4], new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 150, -1));

        jLabel[5].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel[5].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/6.png"))); // NOI18N
        jLabel[5].setMaximumSize(new java.awt.Dimension(125, 125));
        jLabel[5].setMinimumSize(new java.awt.Dimension(125, 125));
        jLabel[5].setPreferredSize(new java.awt.Dimension(154, 154));
        jPanel1.add(jLabel[5], new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 150, -1));

        jLabel[6].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/8.png"))); // NOI18N
        jLabel[6].setText("jLabel[1]");
        jLabel[6].setMaximumSize(new java.awt.Dimension(125, 125));
        jLabel[6].setMinimumSize(new java.awt.Dimension(125, 125));
        jLabel[6].setPreferredSize(new java.awt.Dimension(154, 154));
        jPanel1.add(jLabel[6], new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 160, -1));

        jLabel[7].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/7.png"))); // NOI18N
        jLabel[7].setText("jLabel[2]");
        jLabel[7].setMaximumSize(new java.awt.Dimension(125, 125));
        jLabel[7].setMinimumSize(new java.awt.Dimension(125, 125));
        jLabel[7].setPreferredSize(new java.awt.Dimension(154, 154));
        jPanel1.add(jLabel[7], new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 150, -1));

        jLabel[8].setBackground(new java.awt.Color(51, 102, 255));
        jLabel[8].setForeground(new java.awt.Color(0, 51, 255));
        jLabel[8].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/0.png"))); // NOI18N
        jLabel[8].setText("jLabel[0]");
        jLabel[8].setMaximumSize(new java.awt.Dimension(125, 125));
        jLabel[8].setMinimumSize(new java.awt.Dimension(125, 125));
        jLabel[8].setPreferredSize(new java.awt.Dimension(154, 154));
        jPanel1.add(jLabel[8], new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 150, -1));
    }

    void set() {
        ImageIcon iconLogo;
        for (int i = 0, a = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                iconLogo = new ImageIcon("/media/nasrallah/main1/FCI/SECOND TERM/المواد/AI/project/AI-Sliding-Puzzle/src/Main/" + (s.start.n[i][j]) + ".png");

                jLabel[a++].setIcon(iconLogo);

            }
            System.out.println("");
        }
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(375, 375));
        jPanel1.setMinimumSize(new java.awt.Dimension(375, 375));
        jPanel1.setPreferredSize(new java.awt.Dimension(375, 375));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setText("Solve");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ReStart Puzzel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/10.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 99, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        set();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            System.out.println(s.Path.size());
            Thread.sleep(15);
            for (int z = s.Path.size()-1; z >= 0; z++) {
                ImageIcon iconLogo;
                for (int i = 0, a = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        iconLogo = new ImageIcon("/media/nasrallah/main1/FCI/SECOND TERM/المواد/AI/project/AI-Sliding-Puzzle/src/Main/" + (s.Path.get(z).n[i][j]) + ".png");

                        jLabel[a++].setIcon(iconLogo);

                    }
                    System.out.println("");
                }
            }
            // TODO add your handling code here:
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SlidingPuzzle.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SlidingPuzzle.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SlidingPuzzle.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SlidingPuzzle.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SlidingPuzzle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
