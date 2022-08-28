package br.com.varejo.telas;

import java.sql.*;
import br.com.varejo.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaProduto extends javax.swing.JFrame {
//UTILIZANDO FRAMEWORKS DO PACOTE MODULO DE CONEXÃO

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null; //exibe o resultado da conexão

    
    
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TelaProduto() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        conexao = ModuloConexao.conector(); //CHAMANDO O MÉTODO CONECTOR
    }
    
    //   tratativa para conferir se são somente números
    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*\\.?[0-9]+");}
    
    
private void adicionarProduto() {            
        String sql = "insert into tbprodutos( idproduto,categoria,descricao,"
                + "fornecedor,tamanho,cor,custo,venda) values(?,?,?,?,?,?,?,?)";
       if (isInteger(txtVenda.getText()) && isInteger(txtCusto.getText())){
        try {
            pst = conexao.prepareStatement(sql);
            //pst.setString(1, txtId.getText());
            pst.setString(1,null);
            pst.setString(2, jComboBox1.getSelectedItem().toString());
            pst.setString(3, txtDescricao.getText());
            pst.setString(4, txtFornecedor.getText());
            pst.setString(5, txtTamanho.getText());
            pst.setString(6, txtCor.getText());
            pst.setString(7, txtCusto.getText());
            pst.setString(8, txtVenda.getText());
            int adicionado = pst.executeUpdate();
            
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
               
                txtDescricao.setText(null);
                txtFornecedor.setText(null);
                txtTamanho.setText(null);
                txtCor.setText(null);
                txtCusto.setText(null);
                txtVenda.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }else{JOptionPane.showMessageDialog(null, "Valores de custo e venda somente números!");}// linha da tratativa
}
    
private void remover() {
        int confirma = JOptionPane.
        showConfirmDialog(null, "Tem certeza que deseja remover este produto ? ",
                        "  Atenção", JOptionPane.YES_NO_OPTION);
if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbprodutos where idproduto=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
                    
                    jComboBox1.setSelectedItem(null);
                    txtId.setText(null);
                    txtDescricao.setText(null);
                    txtFornecedor.setText(null);
                    txtTamanho.setText(null);
                    txtCor.setText(null);
                    txtCusto.setText(null);
                    txtVenda.setText(null);
                    txtBuscar.setText(null);
                    btnAdicionar.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

private void consultar() { //ATUALMENTE ESTÁ SEM USO, SUBSTITUIDO 
        String sql = "select * from tbprodutos where idproduto=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtDescricao.setText(rs.getString(3));
                jComboBox1.setSelectedItem((rs.getString(2)));
                txtFornecedor.setText(rs.getString(4));
                txtTamanho.setText(rs.getString(5));
                txtCor.setText(rs.getString(6));
                txtCusto.setText(rs.getString(7));
                txtVenda.setText(rs.getString(8));
            } else {
                JOptionPane.showMessageDialog(null, "Produto não cadastrado");
                txtFornecedor.setText(null);
                txtTamanho.setText(null);
                txtVenda.setText(null);
                txtCor.setText(null);
                txtCusto.setText(null);
                txtDescricao.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
   
    }

 private void alterar() { //com tratativa para conferir se são somente números
        String sql = "update tbprodutos set categoria=?, descricao=?, fornecedor=?, tamanho=?, cor=?, custo=?, venda=? where idproduto=?";
        
        
        if (isInteger(txtVenda.getText()) && isInteger(txtCusto.getText())){// linha da tratativa
        try {
            
            pst = conexao.prepareStatement(sql);
            pst.setString(8,txtId.getText());
            pst.setString(1, jComboBox1.getSelectedItem().toString());
            pst.setString(2, txtDescricao.getText());
            pst.setString(3, txtFornecedor.getText());
            pst.setString(4, txtTamanho.getText());
            pst.setString(5, txtCor.getText());
            pst.setString(6, txtCusto.getText());
            pst.setString(7, txtVenda.getText());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
                
                txtDescricao.setText(null);
                txtFornecedor.setText(null);
                txtTamanho.setText(null);
                txtCor.setText(null);
                txtCusto.setText(null);
                txtVenda.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }else{JOptionPane.showMessageDialog(null, "Valores de custo e venda somente números!");}// linha da tratativa
    }
 
 private void pesquisarProdutoTabela(){
               String  sql= "select * from tbprodutos where descricao like ?";
               
               try {
                pst = conexao.prepareStatement(sql);
                
                pst.setString(1, txtBuscar.getText() + "%");
                
                rs=pst.executeQuery();
                
                tblBuscaProdutos.setModel(DbUtils.resultSetToTableModel(rs));
                
                                         
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }  
        }
 
 
 //*************** 14- MÉTODO PESQUISA COMPLETA ********************

 private void pesquisarProdutosCompleto(){

 String num_ID =JOptionPane.showInputDialog("Número da ID do produto");
 String sql = "select * from tbprodutos where idproduto =?";

 try{
 pst=conexao.prepareStatement(sql);
 pst.setString(1, num_ID);
 rs=pst.executeQuery();

  if (rs.next()) {
                txtId.setText(rs.getString(1));
                txtDescricao.setText(rs.getString(3));
                jComboBox1.setSelectedItem((rs.getString(2)));
                txtFornecedor.setText(rs.getString(4));
                txtTamanho.setText(rs.getString(5));
                txtCor.setText(rs.getString(6));
                txtCusto.setText(rs.getString(7));
                txtVenda.setText(rs.getString(8));

                //EVITANDO PROBLEMAS
                btnAdicionar.setEnabled(false);
                txtBuscar.setEnabled(false);
                tblBuscaProdutos.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Produto não cadastrado");
                txtFornecedor.setText(null);
                txtTamanho.setText(null);
                txtVenda.setText(null);
                txtCor.setText(null);
                txtCusto.setText(null);
                txtDescricao.setText(null);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
 
 
private void limpaCampos(){

    txtFornecedor.setText(null);
    txtTamanho.setText(null);
    txtVenda.setText(null);
    txtCor.setText(null);
    txtCusto.setText(null);
    txtDescricao.setText(null);
    txtId.setText(null);
    jComboBox1.setSelectedItem(null);
    txtBuscar.setText(null);
    btnAdicionar.setEnabled(true);
    txtBuscar.setEnabled(true);
    tblBuscaProdutos.setVisible(true);



}
 
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTamanho = new javax.swing.JTextField();
        txtCusto = new javax.swing.JTextField();
        txtFornecedor = new javax.swing.JTextField();
        txtVenda = new javax.swing.JTextField();
        txtCor = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBuscaProdutos = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        BtnPesquisar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtDescricao = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        btnLimpar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos Tigger");
        setPreferredSize(new java.awt.Dimension(1200, 900));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Categoria");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Fornecedor");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Descrição");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tamanho");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Cor");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Valor Custo");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Valor de Venda");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Buscar Cadastro de Produto");

        txtTamanho.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCusto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtFornecedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtVenda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        tblBuscaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Categoria", "Descrição", "Valor de Venda", "ID"
            }
        ));
        jScrollPane1.setViewportView(tblBuscaProdutos);

        btnAdicionar.setText("Adicionar Produto");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        BtnPesquisar.setText("Pesquisa Completa");
        BtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPesquisarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar Produto");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDeletar.setText("Deletar Produto");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("ID");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roupa", "Calçado", "Acessório" }));

        txtDescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar ");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscar))
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTamanho)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(68, 68, 68)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCor, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                        .addComponent(txtVenda))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtFornecedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(btnAdicionar)
                        .addGap(50, 50, 50)
                        .addComponent(BtnPesquisar)
                        .addGap(59, 59, 59)
                        .addComponent(btnEditar)
                        .addGap(56, 56, 56)
                        .addComponent(btnDeletar)
                        .addGap(59, 59, 59)
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(BtnPesquisar)
                    .addComponent(btnEditar)
                    .addComponent(btnDeletar)
                    .addComponent(btnLimpar))
                .addGap(43, 43, 43))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 950, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/tiger_icon_126625 (1).png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1473, 739);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
adicionarProduto();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void BtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPesquisarActionPerformed
pesquisarProdutosCompleto();

        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPesquisarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
remover();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
alterar();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
pesquisarProdutoTabela();
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
limpaCampos();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparActionPerformed

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("Convert2Lambda")
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                new TelaProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnPesquisar;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBuscaProdutos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtCusto;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtFornecedor;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTamanho;
    private javax.swing.JTextField txtVenda;
    // End of variables declaration//GEN-END:variables
}
