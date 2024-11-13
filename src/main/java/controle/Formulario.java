package controle;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import conexao.Conexao;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class Formulario extends JFrame{
    
    Conexao con_pet;
    
    JLabel lblNumRegistro, lblNome, lblData, lblEspecie, lblRaca, lblCor, lblSexo, lblPesquisa;
    JTextField txtNumRegistro, txtNome,txtRaca, txtEspecie, txtCor, txtSexo, txtPesquisa;
    JButton primeiro, anterior, proximo, ultimo, limpar, gravar, alterar, excluir, sair, pesquisar;
    JFormattedTextField txtData;
    MaskFormatter mData, mTelefone;
    
    JTable tablePet;
    JScrollPane scrollPet;
    
    public Formulario(){
        con_pet = new Conexao();
        con_pet.conecta();
        
        setTitle("CrudPet");
        Container tela = getContentPane();
        tela.setLayout(null);
        ImageIcon icone = new ImageIcon("sql.png");
        setIconImage(icone.getImage());
        setResizable(false);
        
        try{
            mData = new MaskFormatter("##/##/####");
            mData.setPlaceholderCharacter('_');
        }
        catch(ParseException excp){}
        
        lblNumRegistro = new JLabel("numRegistro:");
        lblNome = new JLabel("Nome:");
        lblData = new JLabel("Data:");
        lblEspecie = new JLabel("Especie:");
        lblRaca = new JLabel("Raca:");
        lblPesquisa = new JLabel("Pesquisar espécie:");
        lblSexo = new JLabel("Sexo:");
        lblCor = new JLabel("Cor:");
        
        txtNumRegistro = new JTextField("0");
        txtNome = new JTextField("");
        txtRaca = new JTextField("");
        txtPesquisa = new JTextField("");
        txtData = new JFormattedTextField(mData);
        txtEspecie = new JTextField("");
        txtSexo = new JTextField("");
        txtCor = new JTextField("");
        
        primeiro = new JButton("Primeiro");
        anterior = new JButton("Anterior");
        proximo = new JButton("Próximo");
        ultimo = new JButton("Último");
        limpar = new JButton("Novo Registro");
        gravar = new JButton("Gravar");
        alterar = new JButton("Alterar");
        excluir = new JButton("Excluir");
        pesquisar = new JButton("Pesquisar");
        sair = new JButton("Sair");
                
        tablePet = new javax.swing.JTable();
        scrollPet = new javax.swing.JScrollPane();
        
        lblNumRegistro.setBounds(75,20,100,25);
        lblNome.setBounds(75,50,100,25);
        lblRaca.setBounds(75,80,100,25);
        lblData.setBounds(75,110,100,25);
        lblEspecie.setBounds(75,140,100,25);
        lblSexo.setBounds(75,170,100,25);
        lblCor.setBounds(75,200,100,25);
        lblPesquisa.setBounds(75,480,200,25);
        txtNumRegistro.setBounds(225,20,200,25);
        txtNome.setBounds(225,50,200,25);
        txtRaca.setBounds(225,80,200,25);
        txtData.setBounds(225,110,200,25);
        txtSexo.setBounds(225,170,200,25);
        txtCor.setBounds(225,200,200,25);
        txtEspecie.setBounds(225,140,200,25);
        txtPesquisa.setBounds(225,480,200,25);
        tablePet.setBounds(50, 290, 550, 170);
        scrollPet.setBounds(50,290,550, 170);
        
        primeiro.setBounds(75, 250, 125, 28);
        anterior.setBounds(200, 250, 125, 28);
        proximo.setBounds(325, 250, 125, 28);
        ultimo.setBounds(450, 250, 125, 28);
        limpar.setBounds(450,20,125,28);
        gravar.setBounds(450,50,125,28);
        alterar.setBounds(450,80,125,28);
        excluir.setBounds(450,110,125,28);
        sair.setBounds(450,140,125,28);
        pesquisar.setBounds(450, 480, 125, 28);
        
        primeiro.setToolTipText("ALT + ⬇ para executar a ação");
        anterior.setToolTipText("ALT + ⬅ para executar a ação");
        proximo.setToolTipText("ALT + ⮕ para executar a ação");
        ultimo.setToolTipText("ALT + ⬆ para executar a ação");
        limpar.setToolTipText("ALT + N para executar a ação");
        gravar.setToolTipText("ALT + G para executar a ação");
        alterar.setToolTipText("ALT + A para executar a ação");
        excluir.setToolTipText("ALT + E para executar a ação");
        sair.setToolTipText("ALT + BACKSPACE para executar a ação");
        
        pesquisar.setToolTipText("ENTER para executar a ação");
   
        getRootPane().setDefaultButton(pesquisar);
        primeiro.setMnemonic(KeyEvent.VK_DOWN);
        anterior.setMnemonic(KeyEvent.VK_LEFT);
        proximo.setMnemonic(KeyEvent.VK_RIGHT);
        ultimo.setMnemonic(KeyEvent.VK_UP);
        limpar.setMnemonic(KeyEvent.VK_N);
        gravar.setMnemonic(KeyEvent.VK_G);
        alterar.setMnemonic(KeyEvent.VK_A);
        excluir.setMnemonic(KeyEvent.VK_E);
        sair.setMnemonic(KeyEvent.VK_BACK_SPACE);
        
        
        tela.add(lblNumRegistro);
        tela.add(lblNome);
        tela.add(lblData);
        tela.add(lblEspecie);
        tela.add(lblRaca);
        tela.add(lblCor);
        tela.add(lblSexo);
        tela.add(txtNumRegistro);
        tela.add(txtNome);
        tela.add(txtData);
        tela.add(txtEspecie);
        tela.add(txtRaca);
        tela.add(txtCor);
        tela.add(txtSexo);
        tela.add(tablePet);
        tela.add(scrollPet);
        tela.add(primeiro);
        tela.add(anterior);
        tela.add(proximo);
        tela.add(ultimo);
        tela.add(limpar);
        tela.add(gravar);
        tela.add(alterar);
        tela.add(excluir);
        tela.add(pesquisar);
        tela.add(sair);
        tela.add(lblPesquisa);
        tela.add(txtPesquisa);
        
        primeiro.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                con_pet.resultset.first();
                mostrar_Dados();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro: "+erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        anterior.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                con_pet.resultset.previous();
                mostrar_Dados();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro: "+erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        proximo.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                con_pet.resultset.next();
                mostrar_Dados();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro: "+erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        ultimo.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                con_pet.resultset.last();
                mostrar_Dados();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro: "+erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        limpar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            txtNumRegistro.setText("0");
            txtNome.setText("");
            txtData.setText("");
            txtEspecie.setText("");
            txtRaca.setText("");
            txtCor.setText("");
            txtSexo.setText("");
            txtNumRegistro.requestFocus();
        }
    });
        gravar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String nome = txtNome.getText();
            String especie = txtEspecie.getText();
            String raca = txtRaca.getText();
            String cor = txtCor.getText();
            String nasc = txtData.getText();
            String sexo = txtSexo.getText();
            
            try{
                String insert_sql="insert into pet (nome,especie, raca, cor, nasc ,sexo) values ('" + nome + "','" + especie+ "','" + raca + "','" + cor + "','" + nasc + "','" + sexo + "')";
                con_pet.statement.executeUpdate(insert_sql);
                JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                
                con_pet.executaSQL("select * from pet order by numRegistro");
                preencherTabela();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "\n Erro na gravação:\n " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        alterar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String nome = txtNome.getText();
            String especie = txtEspecie.getText();
            String raca = txtRaca.getText();
            String cor = txtCor.getText();
            String nasc = txtData.getText();
            String sexo = txtSexo.getText();
            String sql;
            String msg ="";
            
            try{
                if(txtNumRegistro.getText().equals("")){
                    sql="insert into pet (nome,especie, raca, cor, nasc ,sexo) values ('" + nome + "','" + especie+ "','" + raca + "','" + cor + "','" + nasc + "','" + sexo + "')";
                    msg="Gravação de um novo registro";
                }else{
                    sql="update pet set nome='" + nome + "',especie='" + especie + "', raca='" + raca + "',cor='" + cor + "', nasc='" + nasc + "', sexo='" + sexo + "' where numRegistro = " + txtNumRegistro.getText();
                    msg="Alteração de registro";
                }
                
                con_pet.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                
                con_pet.executaSQL("select * from pet order by numRegistro");
                preencherTabela();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "\n Erro na gravação:\n " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        excluir.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String sql="";
            String codigo = txtNumRegistro.getText();
            try{
                int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o registro: \n" +codigo,"Confirmar Exclusão", JOptionPane.YES_NO_OPTION, 3);
                if(resposta==0){
                    sql="delete from pet where numRegistro = " + txtNumRegistro.getText();
                    int excluir = con_pet.statement.executeUpdate(sql);
                    if (excluir==1){
                        JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                        con_pet.executaSQL("select * from pet order by numRegistro");
                        con_pet.resultset.first();
                        preencherTabela();
                        posicionarRegistro();
                    }else{
                        JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "\n Erro na exclusão:\n " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        pesquisar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                String pesquisa = "select * from pet where especie like'" + txtPesquisa.getText() + "%'";
                con_pet.executaSQL(pesquisa);
                
                if(con_pet.resultset.first()){
                    preencherTabela();
                }
                else{
                    JOptionPane.showMessageDialog(null, "\n Não existe dados com este paramêtro!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "\n Os dados digitados não foram localizados:\n " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        sair.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            int opcao;
            Object[] botoes = {"Sim","Não"};
            opcao = JOptionPane.showOptionDialog(null,"Deseja mesmo fechar a janela?","Fechar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,botoes,botoes[0]);
            if (opcao==JOptionPane.YES_OPTION)
            System.exit(0); 
        }
    });
        tablePet.setFocusable(false);
        tablePet.setIntercellSpacing(new Dimension(0, 0));
        tablePet.setRowHeight(25);
        tablePet.setGridColor(new Color(240, 240, 240));
        tablePet.setSelectionBackground(new Color(217, 235, 249));
        tablePet.getTableHeader().setFont(new Font("", Font.BOLD, 12));
        tablePet.setSelectionForeground(Color.BLACK);
        
        tablePet.setModel(new javax.swing.table.DefaultTableModel(
        new Object [] [] {
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
        },
        new String [] {"numRegistro", "Nome", "Especie", "Raça", "Cor", "Data", "Sexo"})
        {
        boolean[] canEdit = new boolean []{
        false, false, false, false, false, false, false};
        
        public boolean isCellEditable(int rowIndex, int columnIndex){
        return canEdit [columnIndex];}
        });
        scrollPet.setViewportView(tablePet);
        tablePet.setAutoCreateRowSorter(true);
        
        setSize(675, 560);
        setVisible(true);
        setLocationRelativeTo(null);
        
        con_pet.executaSQL("select * from pet order by numRegistro");
        
        preencherTabela();
        posicionarRegistro();
    }   
    
    public void preencherTabela() {
        tablePet.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablePet.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablePet.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablePet.getColumnModel().getColumn(3).setPreferredWidth(90);
        tablePet.getColumnModel().getColumn(4).setPreferredWidth(130);
        tablePet.getColumnModel().getColumn(5).setPreferredWidth(90);
        tablePet.getColumnModel().getColumn(6).setPreferredWidth(130);

        DefaultTableModel modelo = (DefaultTableModel) tablePet.getModel();
        modelo.setNumRows(0);

        try {
            con_pet.resultset.beforeFirst();
            while(con_pet.resultset.next()) {
                modelo.addRow(new Object[] {
                    con_pet.resultset.getString("numRegistro"),
                    con_pet.resultset.getString("nome"),
                    con_pet.resultset.getString("especie"),
                    con_pet.resultset.getString("raca"),
                    con_pet.resultset.getString("cor"),
                    con_pet.resultset.getString("nasc"),
                    con_pet.resultset.getString("sexo")
                });
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "\n Erro ao listar dados da tabela!! :\n " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void posicionarRegistro(){
        try{
            con_pet.resultset.first();
            mostrar_Dados();
        }catch(SQLException erro){
        JOptionPane.showMessageDialog(null,"Não foi possível posicionar no primeiro registro: "+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void mostrar_Dados(){
        try{
            txtNumRegistro.setText(con_pet.resultset.getString("numRegistro"));
            txtNome.setText(con_pet.resultset.getString("nome"));
            txtData.setText(con_pet.resultset.getString("nasc"));
            txtEspecie.setText(con_pet.resultset.getString("especie"));
            txtRaca.setText(con_pet.resultset.getString("raca"));
            txtCor.setText(con_pet.resultset.getString("cor"));
            txtSexo.setText(con_pet.resultset.getString("sexo"));
        }catch(SQLException erro){
        JOptionPane.showMessageDialog(null,"Não localizou dados: "+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
