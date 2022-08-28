/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.varejo.telas;

import java.sql.*;
import br.com.varejo.dal.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JFrame {
//UTILIZANDO FRAMEWORKS DO PACOTE MODULO DE CONEXÃO
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null; //exibe o resultado da conexão
  

private static boolean isInteger(String str) {//   tratativa para conferir se são somente números
        return str != null && str.matches("[0-9]*\\.?[0-9]+");
    }

    public TelaUsuario() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        conexao = ModuloConexao.conector(); //CHAMANDO O MÉTODO CONECTOR
    }

    //CRIANDO MÉTODO DE CONSULTA
    private void consultar() {
        String sql = "select * from tbusuarios where iduser=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuFone.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4));
                txtUsuSenha.setText(rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                txtUsuNome.setText(null);
                txtUsuFone.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
   
    }
    //MÉTODO ADICIONAR USUÁRIO
    private void adicionar() {            //com tratativa para conferir se são somente números
        String sql = "insert into tbusuarios(iduser, usuario,fone,login,senha) values(?,"
                + "?,?,?,?)";
        if (isInteger(txtUsuFone.getText()) && isInteger(txtUsuId.getText())){// linha da tratativa
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuFone.getText());
            pst.setString(4, txtUsuLogin.getText());
            pst.setString(5, txtUsuSenha.getText());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                txtUsuId.setText(null);
                txtUsuNome.setText(null);
                txtUsuFone.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }else{JOptionPane.showMessageDialog(null, "ID e CONTATO somente números!");}// linha da tratativa
    }

// MÉTODO DELETE (REMOÇÃO DE USUÁRIOS)
    private void remover() {
        int confirma = JOptionPane.
        showConfirmDialog(null, "Tem certeza que deseja remover este usuário ? ",
                        "  Atenção", JOptionPane.YES_NO_OPTION);
if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbusuarios where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
   
    //MÉTODO ALTERAR DADOS DE USUÁRIO
    private void alterar() { //com tratativa para conferir se são somente números
        String sql = "update tbusuarios set usuario=?, fone=?, login=?, senha=? where iduser=?";
        if (isInteger(txtUsuFone.getText()) && isInteger(txtUsuId.getText())){// linha da tratativa
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuFone.getText());
            pst.setString(3, txtUsuLogin.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, txtUsuId.getText());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso");
                txtUsuId.setText(null);
                txtUsuNome.setText(null);
                txtUsuFone.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }else{JOptionPane.showMessageDialog(null, "ID e CONTATO somente números!");}// linha da tratativa
    }
    
    
  //  private static boolean verificaAcesso(String num_Admin) {
       // String Admn = "Admin";
      //  String num_Adm =JOptionPane.showInputDialog("Senha de Administrador");
       //if (Admn = "Admin"){ Return True;
    //}else{JOptionPane.showMessageDialog(null, "Senha Incorreta");}
   // } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuFone = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        txtUsuId = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        btnUsuAdicionar = new javax.swing.JButton();
        btnUsuPesquisar = new javax.swing.JButton();
        btnUsuAtualizar = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuário");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 900));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("ID");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 51, -1, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Nome");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 112, -1, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Login");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 175, -1, -1));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Contato");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 51, -1, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Senha");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 175, -1, -1));

        txtUsuNome.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtUsuNome.setForeground(new java.awt.Color(102, 153, 255));
        jPanel1.add(txtUsuNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 113, 458, -1));

        txtUsuFone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtUsuFone.setForeground(new java.awt.Color(102, 153, 255));
        jPanel1.add(txtUsuFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(399, 52, 171, -1));

        txtUsuSenha.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtUsuSenha.setForeground(new java.awt.Color(102, 153, 255));
        jPanel1.add(txtUsuSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 176, 168, -1));

        txtUsuId.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtUsuId.setForeground(new java.awt.Color(102, 153, 255));
        jPanel1.add(txtUsuId, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 52, 178, -1));

        txtUsuLogin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtUsuLogin.setForeground(new java.awt.Color(102, 153, 255));
        jPanel1.add(txtUsuLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 176, 178, -1));

        btnUsuAdicionar.setText("Adicionar");
        btnUsuAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuAdicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btnUsuAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 364, -1, -1));

        btnUsuPesquisar.setText("Pesquisar");
        btnUsuPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(btnUsuPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 364, -1, -1));

        btnUsuAtualizar.setText("Atualizar");
        btnUsuAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuAtualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnUsuAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 364, -1, -1));

        btnUsuDelete.setText("Deletar");
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnUsuDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 364, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/tiger_icon_126625 (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(618, 618, 618)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(563, 563, 563))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, -1));

        setSize(new java.awt.Dimension(1317, 831));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuAdicionarActionPerformed
//CHAMANDO MÉTODO ADICIONAR
adicionar();
        
    }//GEN-LAST:event_btnUsuAdicionarActionPerformed

    private void btnUsuPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuPesquisarActionPerformed
//CHAMANDO O MÉTODO PESQUISAR
consultar();
        
    }//GEN-LAST:event_btnUsuPesquisarActionPerformed

    private void btnUsuAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuAtualizarActionPerformed
//MÉTODO ALTERAR
alterar();

        
    }//GEN-LAST:event_btnUsuAtualizarActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
//MÉTODO DELETE
remover();

    }//GEN-LAST:event_btnUsuDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuAdicionar;
    private javax.swing.JButton btnUsuAtualizar;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
