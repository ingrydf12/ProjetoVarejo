//Ideia da tela (Inicial)
// Lucro é automatico ou funcionar quando clicar no botão de adicionar venda (Valor Custo - Valor Venda = Lucro) 
//*obs: funciona se apertar enter
// Estoque atual deve ser automático pelo sistema e deve funcionar junto ao tela produto ou tela de estoque (se existir)
// Quando uma venda for adicionada, o estoque do produto deve alterar (Ideia)

package br.com.varejo.telas;

import java.sql.*;
import br.com.varejo.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;



public class TelaVendas extends javax.swing.JFrame {
//UTILIZANDO FRAMEWORKS DO PACOTE MODULO DE CONEXÃO

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null; //exibe o resultado da conexão
    
    public TelaVendas() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
        conexao = ModuloConexao.conector(); //CHAMANDO O MÉTODO CONECTOR
    }

    // Apenas números
    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*\\.?[0-9]+");}
    
    
    
    
    // Método de adicionarVenda (Funcionando)
    private void adicionarVenda(){
        String sql = "insert into tbvendas (idvenda,categoria,descricao_item,"
              +"vendedor,valor_custo, valor_venda, lucro, estoque) values(?,?,?,?,?,?,?,?)";
       if (isInteger(txtVendaCusto.getText()) && isInteger(txtVendaValor.getText())){
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtVendaId.getText());
            pst.setString(1,null);
            pst.setString(2, jComboBox1.getSelectedItem().toString());
            pst.setString(3, txtVendaItem.getText());
           pst.setString(4,txtVendaVendedor.getText());
            pst.setString(5, txtVendaCusto.getText());
            pst.setString(6, txtVendaValor.getText());
            pst.setString(7, txtVendaLucro.getText());
            pst.setString(8, txtVendaLucro.getText());
            int adicionado = pst.executeUpdate();
            
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso");
               
                txtVendaId.setText(null);
                txtVendaVendedor.setText(null);
                txtVendaItem.setText(null);
                txtVendaValor.setText(null);
                txtVendaCusto.setText(null);
                txtVendaLucro.setText(null);
                txtVendaEstoque.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }else{JOptionPane.showMessageDialog(null, "Valores de custo e venda somente números (Obrigatório)");}// linha da tratativa
    }
    
    
    
       // Método de pesquisa de vendas (Funcionando - Apenas ID)
    private void pesquisar(){
     String sql = "select * from tbvendas where idvenda =?";
 try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtVendaPesquisar.getText() + "%");
                rs=pst.executeQuery();
                tabVendas.setModel(DbUtils.resultSetToTableModel(rs));
                
                                         
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }  
    }
    
    
    //Método de alterarVenda (Verificar)
    private void alterarVenda(){
        String sql = "update tbvendas set idvenda =? vendedor=?, categoria=?, valor_custo=?, valor_venda=?" + 
                "descricao_item=?, estoque =?" + "where idvenda=?";
        
        try {
pst=conexao.prepareStatement(sql);
pst.setString(1,txtVendaId.getText());
pst.setString(2,txtVendaVendedor.getText());
pst.setString(3,jComboBox1.getSelectedItem().toString());
pst.setString(4,txtVendaCusto.getText());
pst.setString(5,txtVendaValor.getText());
pst.setString(6,txtVendaLucro.getText());
pst.setString(7,txtVendaItem.getText());
pst.setString(8,txtVendaEstoque.getText());
int adicionado = pst.executeUpdate();
if(adicionado > 0){
JOptionPane.showMessageDialog(null,"Usuário alterado com sucesso");
   txtVendaId.setText(null);
   txtVendaVendedor.setText(null);
   txtVendaItem.setText(null);
   txtVendaValor.setText(null);
   txtVendaCusto.setText(null);
   txtVendaLucro.setText(null);
   txtVendaEstoque.setText(null);
}
} catch (Exception e) {
JOptionPane.showMessageDialog(null, e);
}
    }
    

    
    // Método removerVenda (Verificar)
    // Mudar o get.Text para o txtVendaPesquisar ou no próprio painel (ID de VENDA)
    // String num_ID =JOptionPane.showInputDialog("Número da ID da Venda);
    private void removerVenda(){
                int confirma = JOptionPane.
        showConfirmDialog(null, "Tem certeza que deseja excluir a venda?",
                        "Atenção", JOptionPane.YES_NO_OPTION);
if (confirma == JOptionPane.YES_OPTION) {
     // String num_ID =JOptionPane.showInputDialog("Número da ID da Venda);
     String sql = "delete from tbvendas where idvenda = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtVendaId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Venda deletada com sucesso");
                    
                txtVendaId.setText(null);
                txtVendaItem.setText(null);
                jComboBox1.setSelectedItem(null);
                txtVendaVendedor.setText(null);
                txtVendaCusto.setText(null);
                txtVendaValor.setText(null);
                txtVendaLucro.setText(null);
                txtVendaEstoque.setText(null);
                txtVendaPesquisar.setText(null);
                btnVendaAdicionar.setEnabled(true);
                txtVendaPesquisar.setEnabled(true);
                tabVendas.setVisible(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    
    // Método do botão pesquisar (Completo) [VERIFICAR]
    private void pesquisarCompleto(){

 String num_ID =JOptionPane.showInputDialog("Número da ID da Venda");
 String sql = "select * from tbvendas where idvenda =?";

 try{
 pst=conexao.prepareStatement(sql);
 pst.setString(1, num_ID);
 rs=pst.executeQuery();

  if (rs.next()) {
            txtVendaId.setText(rs.getString(1));
            jComboBox1.setSelectedItem((rs.getString(2)));
            txtVendaVendedor.setText(rs.getString(3));
            txtVendaCusto.setText(rs.getString(4));
            txtVendaValor.setText(rs.getString(5));
            txtVendaLucro.setText(rs.getString(6));
            txtVendaItem.setText(rs.getString(7));
            txtVendaEstoque.setText(rs.getString(8));

                //EVITANDO PROBLEMAS
                btnVendaAdicionar.setEnabled(false);
                txtVendaPesquisar.setEnabled(false);
                tabVendas.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Venda não cadastrada");
                txtVendaId.setText(null);
                txtVendaItem.setText(null);
                jComboBox1.setSelectedItem(null);
                txtVendaVendedor.setText(null);
                txtVendaCusto.setText(null);
                txtVendaValor.setText(null);
                txtVendaLucro.setText(null);
                txtVendaEstoque.setText(null);
                txtVendaPesquisar.setText(null);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
    
    
    // Método lucro (Subtração)
    private void lucro(){
        double n1, n2, total;
        
        n1 = Double.parseDouble(txtVendaCusto.getText());
        n2 = Double.parseDouble(txtVendaValor.getText());
        
        total = n2 - n1;
        
        txtVendaLucro.setText(String.valueOf(total));
    }
    
    // Método de limpar os campos
    private void limpar(){
        txtVendaId.setText(null);
                txtVendaItem.setText(null);
                jComboBox1.setSelectedItem(null);
                txtVendaVendedor.setText(null);
                txtVendaCusto.setText(null);
                txtVendaValor.setText(null);
                txtVendaLucro.setText(null);
                txtVendaEstoque.setText(null);
                txtVendaPesquisar.setText(null);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtVendaCusto = new javax.swing.JTextField();
        txtVendaVendedor = new javax.swing.JTextField();
        txtVendaId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtVendaValor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtVendaItem = new javax.swing.JTextField();
        txtVendaLucro = new javax.swing.JTextField();
        txtVendaEstoque = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtVendaPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabVendas = new javax.swing.JTable();
        btnVendaLimpar = new javax.swing.JButton();
        btnVendaAdicionar = new javax.swing.JButton();
        btnVendaPesquisar = new javax.swing.JButton();
        btnVendaEditar = new javax.swing.JButton();
        btnVendaRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vendas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setText("ID");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setText("Categoria");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setText("Valor de Custo");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setText("Vendedor");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setText("Valor de Venda");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setText("Lucro:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roupa", "Calçado", "Acessório" }));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setText("Descrição de Item");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setText("Estoque atual:");

        txtVendaLucro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVendaLucroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtVendaId, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVendaEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtVendaCusto)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtVendaValor, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtVendaLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtVendaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtVendaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 111, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtVendaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtVendaEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtVendaItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtVendaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtVendaCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtVendaValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtVendaLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/tiger_icon_126625 (1).png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel10.setText("Busca");

        txtVendaPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVendaPesquisarKeyReleased(evt);
            }
        });

        tabVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "idvenda", "Categoria", "Item", "Vendedor", "Lucro"
            }
        ));
        jScrollPane1.setViewportView(tabVendas);

        btnVendaLimpar.setText("Limpar campos");
        btnVendaLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaLimparActionPerformed(evt);
            }
        });

        btnVendaAdicionar.setText("Adicionar venda");
        btnVendaAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaAdicionarActionPerformed(evt);
            }
        });

        btnVendaPesquisar.setText("Pesquisar");
        btnVendaPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaPesquisarActionPerformed(evt);
            }
        });

        btnVendaEditar.setText("Editar venda");
        btnVendaEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaEditarActionPerformed(evt);
            }
        });

        btnVendaRemover.setText("Remover venda");
        btnVendaRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addGap(32, 32, 32)
                .addComponent(txtVendaPesquisar)
                .addGap(163, 163, 163))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(btnVendaAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnVendaPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnVendaEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnVendaRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnVendaLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtVendaPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVendaLimpar)
                    .addComponent(btnVendaPesquisar)
                    .addComponent(btnVendaEditar)
                    .addComponent(btnVendaRemover)
                    .addComponent(btnVendaAdicionar))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(81, 81, 81))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(525, 525, 525)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtVendaLucroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVendaLucroActionPerformed
        lucro();
    }//GEN-LAST:event_txtVendaLucroActionPerformed

    private void txtVendaPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendaPesquisarKeyReleased
        pesquisar();
    }//GEN-LAST:event_txtVendaPesquisarKeyReleased

    private void btnVendaLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnVendaLimparActionPerformed

    private void btnVendaPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaPesquisarActionPerformed
        pesquisarCompleto();
    }//GEN-LAST:event_btnVendaPesquisarActionPerformed

    private void btnVendaAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaAdicionarActionPerformed
        adicionarVenda();
    }//GEN-LAST:event_btnVendaAdicionarActionPerformed

    private void btnVendaRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaRemoverActionPerformed
        removerVenda();
    }//GEN-LAST:event_btnVendaRemoverActionPerformed

    private void btnVendaEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaEditarActionPerformed
        alterarVenda();
    }//GEN-LAST:event_btnVendaEditarActionPerformed

    
    
    
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
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVendaAdicionar;
    private javax.swing.JButton btnVendaEditar;
    private javax.swing.JButton btnVendaLimpar;
    private javax.swing.JButton btnVendaPesquisar;
    private javax.swing.JButton btnVendaRemover;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabVendas;
    private javax.swing.JTextField txtVendaCusto;
    private javax.swing.JTextField txtVendaEstoque;
    private javax.swing.JTextField txtVendaId;
    private javax.swing.JTextField txtVendaItem;
    private javax.swing.JTextField txtVendaLucro;
    private javax.swing.JTextField txtVendaPesquisar;
    private javax.swing.JTextField txtVendaValor;
    private javax.swing.JTextField txtVendaVendedor;
    // End of variables declaration//GEN-END:variables
}
