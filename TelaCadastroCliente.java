package br.com.varejo.telas;

//IMPORTANDO
import java.sql.*;
import br.com.varejo.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCadastroCliente extends javax.swing.JFrame {
//UTILIZANDO FRAMEWORKS DO PACOTE MODULO DE CONEXÃO
   Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null; //exibe o resultado da conexão
    
    public TelaCadastroCliente() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        conexao = ModuloConexao.conector(); //CHAMANDO O MÉTODO CONECTOR
    }

     //   tratativa para conferir se são somente números
    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*\\.?[0-9]+");}
    
   
    
    //MÉTODO ADICIONAR CLIENTE
     private void adicionarCliente() {            
        String sql = "insert into tbclientes(idcli,nomecli,cpf," 
                +"fonecli,email,data_nasc,endcli,bairro,municipio,cep) values(?,?,?,?,?,?,?,?,?,?)";
       if (isInteger(txtCliCpf.getText()) && isInteger(txtCliTelefone.getText()) && isInteger(txtCliCep.getText())){
           try {
               pst = conexao.prepareStatement(sql);
               pst.setString(1, null);
               pst.setString(2, txtCliNome.getText());
               pst.setString(3, txtCliCpf.getText());
               pst.setString(4, txtCliTelefone.getText());
               pst.setString(5, txtCliEmail.getText());
               pst.setString(6, txtCliData.getText());
               pst.setString(7, txtCliEndereco.getText());
               pst.setString(8, txtCliBairro.getText());
               pst.setString(9, txtCliMunicipio.getText());
               pst.setString(10, txtCliCep.getText());
               int adicionado = pst.executeUpdate();

               if (adicionado > 0) {
                   JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");

                   txtCliId.setText(null);
                   txtCliNome.setText(null);
                   txtCliCpf.setText(null);
                   txtCliTelefone.setText(null);
                   txtCliEmail.setText(null);
                   txtCliData.setText(null);
                   txtCliEndereco.setText(null);
                   txtCliBairro.setText(null);
                   txtCliMunicipio.setText(null);
                    txtCliCep.setText(null);
               }
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
        }
       
    }else{JOptionPane.showMessageDialog(null, "Valores de CPF e Telefone somente números!");}// linha da tratativa
}
     
     //MÉTODO PESQUISAR CLIENTE
  private void pesquisarCliente(){
  
        String sql ="select * from tbclientes where idcli=?";
         try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtCliId.getText());
            rs=pst.executeQuery();
             if (rs.next()) {
                
                 txtCliNome.setText(rs.getString(2));
                 txtCliCpf.setText(rs.getString(3));
                 txtCliTelefone.setText(rs.getString(4));
                txtCliEmail.setText(rs.getString(5));
                txtCliData.setText(rs.getString(6));
                 txtCliEndereco.setText(rs.getString(7));
                 txtCliBairro.setText(rs.getString(8));
                 txtCliMunicipio.setText(rs.getString(9));
                 txtCliCep.setText(rs.getString(10));
                 
                 btnCliAdicionar.setEnabled(false);
                
             } else {
                JOptionPane.showMessageDialog(null,"Cliente não cadastrado");
                
                txtCliNome.setText(null);
                txtCliCpf.setText(null);
                 txtCliTelefone.setText(null);
                txtCliEmail.setText(null);
                txtCliData.setText(null);
                 txtCliEndereco.setText(null);
                 txtCliBairro.setText(null);
                 txtCliMunicipio.setText(null);
                 txtCliCep.setText(null);
                                           
             }
            
        } catch (Exception e) {
                 JOptionPane.showMessageDialog(null,e);
        }
         
        
               
    }   
     
     //MÉTODO DE PESQUISA PELA TABELA
        private void pesquisar_cliente(){
               String  sql= "select * from tbclientes where nomecli like ?";
               
               try {
                pst = conexao.prepareStatement(sql);
                
                pst.setString(1, txtCliPesquisa.getText() + "%");
                
                rs=pst.executeQuery();
                
                tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
                
                btnCliAdicionar.setEnabled(false);
               
                
                                         
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }  
        }
        
        
        // MÉTODO LIMPAR CAMPO DE PESQUISA E TABELA 
      private void limpar() {
           txtCliPesquisa.setText(null);
           String  sql= "select * from tbclientes where nomecli like ?";
               
               try {
                pst = conexao.prepareStatement(sql);
                
                pst.setString(1, txtCliPesquisa.getText() + "");
                
                rs=pst.executeQuery();
                
                tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
                
                                         
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }  
       } 
      
      //MÉTODO LIMPAR TODOS OS CAMPOS
      private void limparcampos(){
      
                txtCliId.setText(null); 
                txtCliNome.setText(null);
                 txtCliCpf.setText(null);
                 txtCliTelefone.setText(null);
                txtCliEmail.setText(null);
                txtCliData.setText(null);
                 txtCliEndereco.setText(null);
                 txtCliBairro.setText(null);
                 txtCliMunicipio.setText(null);
                 txtCliCep.setText(null);
                 btnCliAdicionar.setEnabled(true);
                txtCliPesquisa.setEnabled(true);
                tblClientes.setVisible(true);
      }
      
      //MÉTODO ALTERAR DADOS DO CLIENTE
       private void alterarCliente(){
        String sql = "update tbclientes set nomecli =?,cpf=?, fonecli=?, email=?, data_nasc=?, endcli=?, bairro=?, municipio=?, cep=? where idcli=?";
        
        try {
          pst = conexao.prepareStatement(sql);
               
            
               
               pst.setString(1, txtCliNome.getText());
               pst.setString(2, txtCliCpf.getText());
               pst.setString(3, txtCliTelefone.getText());
               pst.setString(4, txtCliEmail.getText());
               pst.setString(5, txtCliData.getText());
               pst.setString(6, txtCliEndereco.getText());
               pst.setString(7, txtCliBairro.getText());
               pst.setString(8, txtCliMunicipio.getText());
               pst.setString(9,txtCliCep.getText());
               pst.setString(10, txtCliId.getText());
               int adicionado = pst.executeUpdate();
          
                
                if(adicionado >0){
                    JOptionPane.showMessageDialog(null,"Cliente alterado com sucesso");
                   txtCliId.setText(null);
                   txtCliNome.setText(null);
                   txtCliCpf.setText(null);
                   txtCliTelefone.setText(null);
                   txtCliEmail.setText(null);
                   txtCliData.setText(null);
                   txtCliEndereco.setText(null);
                   txtCliBairro.setText(null);
                   txtCliMunicipio.setText(null);
                   txtCliCep.setText(null);
                }         
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }  
      // MÉTODO DELETE (REMOÇÃO DE CLIENTES)
       private void removerCliente(){
        
        int confirma=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente?","Atenção", JOptionPane.YES_NO_OPTION);
        
        
        if (confirma==JOptionPane.YES_OPTION) {
            String sql="delete from tbclientes where idcli=?"; 
            
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1,txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado>0) {
                    JOptionPane.showMessageDialog(null,"Cliente removido com sucesso");
                }
                            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                
            }   
        }
        
        
        }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCliCpf = new javax.swing.JTextField();
        txtCliData = new javax.swing.JTextField();
        txtCliTelefone = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        txtCliNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCliAdicionar = new javax.swing.JButton();
        btnCliLimparTudo = new javax.swing.JButton();
        btnCliAlterar = new javax.swing.JButton();
        txtCliDeletar = new javax.swing.JButton();
        btnCliPesquisar1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliCep = new javax.swing.JTextField();
        txtCliBairro = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCliMunicipio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtCliPesquisa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnCliLimpar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente");
        setBackground(new java.awt.Color(255, 204, 204));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(1150, 636));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Data de Nascimento");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 240, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("CPF");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 140, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Telefone");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 60, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Email");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));
        jPanel2.add(txtCliCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 320, -1));
        jPanel2.add(txtCliData, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 60, 260, -1));
        jPanel2.add(txtCliTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 270, -1));
        jPanel2.add(txtCliEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 1090, -1));

        txtCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomeActionPerformed(evt);
            }
        });
        jPanel2.add(txtCliNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 950, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Endereço");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Cód");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        btnCliAdicionar.setText("Adicionar");
        btnCliAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAdicionarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCliAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, -1, -1));

        btnCliLimparTudo.setText("Limpar");
        btnCliLimparTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliLimparTudoActionPerformed(evt);
            }
        });
        jPanel2.add(btnCliLimparTudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, -1, -1));

        btnCliAlterar.setText("Alterar");
        btnCliAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAlterarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCliAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, -1, -1));

        txtCliDeletar.setText("Deletar");
        txtCliDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliDeletarActionPerformed(evt);
            }
        });
        jPanel2.add(txtCliDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, -1, -1));

        btnCliPesquisar1.setText("Pesquisar");
        btnCliPesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliPesquisar1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnCliPesquisar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Nome");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));
        jPanel2.add(txtCliId, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 50, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("*Campos obrigatórios para cadastro");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 200, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 51));
        jLabel10.setText("*");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 10, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 51));
        jLabel11.setText("*");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 10, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("CEP");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 180, -1, -1));
        jPanel2.add(txtCliEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 1090, -1));
        jPanel2.add(txtCliCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, 210, -1));
        jPanel2.add(txtCliBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 360, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Bairro");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Município");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, -1, -1));
        jPanel2.add(txtCliMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 390, -1));

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PESQUISA RÁPIDA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 13))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCliPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisaKeyReleased(evt);
            }
        });
        jPanel3.add(txtCliPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 990, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nome");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        btnCliLimpar.setText("Limpar");
        btnCliLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliLimparActionPerformed(evt);
            }
        });
        jPanel3.add(btnCliLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 30, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, -170, -1, 110));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Telefone", "CPF"
            }
        ));
        jScrollPane4.setViewportView(tblClientes);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1150, 120));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        setBounds(0, 0, 1290, 676);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisaKeyReleased
pesquisar_cliente();        // PESQUISAR CLIENTE NA BARRA DE PESQUISA
    }//GEN-LAST:event_txtCliPesquisaKeyReleased

    private void btnCliAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAdicionarActionPerformed
       adicionarCliente(); //BOTÃO ADICIONAR
    }//GEN-LAST:event_btnCliAdicionarActionPerformed

    private void btnCliLimparTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliLimparTudoActionPerformed
limparcampos();      //BOTÃO LIMPAR TUDO
    }//GEN-LAST:event_btnCliLimparTudoActionPerformed

    private void btnCliLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliLimparActionPerformed
limpar();        // LIMPAR CAIXA DE PESQUISA
    }//GEN-LAST:event_btnCliLimparActionPerformed

    private void txtCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomeActionPerformed

    private void btnCliPesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliPesquisar1ActionPerformed
pesquisarCliente ();        // PESQUISAR CLIENTE
    }//GEN-LAST:event_btnCliPesquisar1ActionPerformed

    private void btnCliAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAlterarActionPerformed
alterarCliente();        // BOTÃO ALTERAR DADOS DO CLIENTE
    }//GEN-LAST:event_btnCliAlterarActionPerformed

    private void txtCliDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliDeletarActionPerformed
removerCliente();        // BOTÃO DELETAR CLIENTE
    }//GEN-LAST:event_txtCliDeletarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliAdicionar;
    private javax.swing.JButton btnCliAlterar;
    private javax.swing.JButton btnCliLimpar;
    private javax.swing.JButton btnCliLimparTudo;
    private javax.swing.JButton btnCliPesquisar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliBairro;
    private javax.swing.JTextField txtCliCep;
    private javax.swing.JTextField txtCliCpf;
    private javax.swing.JTextField txtCliData;
    private javax.swing.JButton txtCliDeletar;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliMunicipio;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisa;
    private javax.swing.JTextField txtCliTelefone;
    // End of variables declaration//GEN-END:variables
}
