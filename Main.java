import javax.swing.*;
//import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import tabuleiro.*;
import objects.*;
import privacy.Admin;

public class Main{
    Jogo game;
    Tabuleiro tab;
    static Main obj = new Main();
    Nav nav;

    public static void main(String[] args){

        obj.runInicio();   
    }
    
    public void runInicio(){
        //define a tela 1
        JFrame janela = new JFrame("Monopoly");
        janela.setSize(1000, 800);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);

        //Imagem de fundo da tela de início
        ImageIcon img = new ImageIcon("util/tstimg1.jpg");
        JLabel imagem = new JLabel(img);
        imagem.setBounds(0, 0, 1000, 800);

        //painel de inicio
        JPanel inicio = new JPanel();
        inicio.setBounds(100, 100, 900, 500);
        inicio.setOpaque(false);
        inicio.setLayout(null);
        
        //nome do jogo
        JLabel nomejogo = new JLabel("MONOPOLY");
        nomejogo.setSize(800, 100);
        nomejogo.setFont(new Font("Arial", Font.BOLD, 100));
        nomejogo.setHorizontalAlignment(SwingConstants.CENTER);
        nomejogo.setForeground(new Color(255, 255, 0));

        //Menu de opcoes inicial
        JLabel informar = new JLabel("Insira o nome dos jogadores abaixo:");
        informar.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField txt_j1 = new JTextField("Player1");
        txt_j1.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField txt_j2 = new JTextField("Player2");
        txt_j2.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField txt_j3 = new JTextField("Player3");
        txt_j3.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField txt_j4 = new JTextField("Player4");
        txt_j4.setHorizontalAlignment(SwingConstants.CENTER);
        JButton start_new_game = new JButton("Novo Jogo");
        JButton load_game = new JButton("Carregar Jogo");

        informar.setBounds(200, 150, 400, 30);
        informar.setHorizontalAlignment(SwingConstants.CENTER);
        informar.setForeground(Color.WHITE);
        txt_j1.setBounds(250, 200, 300, 30);
        txt_j2.setBounds(250, 240, 300, 30);
        txt_j3.setBounds(250, 280, 300, 30);
        txt_j4.setBounds(250, 320, 300, 30);
        start_new_game.setBounds(300, 380, 200, 30);
        load_game.setBounds(300, 420, 200, 30);
        start_new_game.setBackground(new Color(255,220,73));

        txt_j1.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "nextfield");
        txt_j1.getActionMap().put("nextfield", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                txt_j2.requestFocus();
            }
        });
        txt_j2.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "nextfield");
        txt_j2.getActionMap().put("nextfield", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                txt_j3.requestFocus();
            }
        });
        txt_j3.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "nextfield");
        txt_j3.getActionMap().put("nextfield", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                txt_j4.requestFocus();
            }
        });
        txt_j4.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "nextfield");
        txt_j4.getActionMap().put("nextfield", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                start_new_game.doClick();
            }
        });

        start_new_game.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                game = new Jogo();
                tab = new Tabuleiro(game);
                nav = new Nav(tab);

                game.j1.setName(txt_j1.getText());
                game.j2.setName(txt_j2.getText());
                game.j3.setName(txt_j3.getText());
                game.j4.setName(txt_j4.getText());
    
                janela.dispose();
                runApresentacaoJogo();
            }
        });

        load_game.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    FileInputStream fis_game = new FileInputStream("util/saveJogo.txt");
                    FileInputStream fis_tab = new FileInputStream("util/saveTabuleiro.txt");
                    FileInputStream fis_nav = new FileInputStream("util/saveNav.txt");
                    ObjectInputStream ois_game = new ObjectInputStream(fis_game);
                    ObjectInputStream ois_tab = new ObjectInputStream(fis_tab);
                    ObjectInputStream ois_nav = new ObjectInputStream(fis_nav);
                    game = (Jogo) ois_game.readObject();
                    tab = (Tabuleiro) ois_tab.readObject();
                    nav = (Nav) ois_nav.readObject();

                    fis_game.close();
                    fis_tab.close();
                    fis_nav.close();
                    ois_game.close();
                    ois_tab.close();
                    ois_nav.close();
                }catch(Exception ex){
                    System.out.println("\nHouve um ERRO: " + ex.getMessage());
                }

                janela.dispose();
                runJogo();
            }
        });


        janela.add(inicio);
        janela.add(imagem);
        inicio.add(nomejogo);
        inicio.add(informar);
        inicio.add(txt_j1);
        inicio.add(txt_j2);
        inicio.add(txt_j3);
        inicio.add(txt_j4);
        inicio.add(start_new_game);
        inicio.add(load_game);
        janela.repaint();
    }

    public void runApresentacaoJogo(){
        //define a tela 2
        JFrame janela = new JFrame("Monopoly");
        janela.setSize(1000, 800);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);

        //Imagem de fundo da tela
        ImageIcon img = new ImageIcon("util/tstimg1.jpg");
        JLabel imagem = new JLabel(img);
        imagem.setBounds(0, 0, 1000, 800);

        //criacao do painel de apresentacao
        JPanel apresentacao = new JPanel();
        apresentacao.setBounds(50, 100, 900, 600);
        apresentacao.setLayout(null);
        apresentacao.setOpaque(false);

        //label titulo
        JLabel strapresentacao = new JLabel("APRESENTAÇÃO");
        strapresentacao.setBounds(50, 50, 800, 100);
        strapresentacao.setFont(new Font("Arial", Font.BOLD, 80));
        strapresentacao.setHorizontalAlignment(SwingConstants.CENTER);
        strapresentacao.setForeground(new Color(255, 255, 0));

        //label complementar
        JLabel bvs = new JLabel("Bem-vindos jogadores " + game.Str_Players() + ".");
        bvs.setBounds(100, 140, 700, 20);
        bvs.setHorizontalAlignment(SwingConstants.CENTER);
        bvs.setForeground(Color.WHITE);
        
        //Caixa de texto de explicacao do jogo
        JTextArea rules = new JTextArea();
        rules.setLineWrap(true);
        rules.setWrapStyleWord(true);
        rules.setEditable(false);
        rules.setFont(new Font("Arial", Font.PLAIN, 18));
        rules.setOpaque(false);
        rules.setAutoscrolls(true);
        rules.setForeground(Color.WHITE);
        rules.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        JScrollPane rules_scroll = new JScrollPane(rules);
        rules_scroll.setBounds(100, 200, 700, 280);

        JViewport vp = rules_scroll.getViewport();
        vp.setBackground(new Color(67, 88, 146));
        apresentacao.add(rules_scroll);

        //importacao do arquivo de regras
        String banco = "";
        try{
            File arq = new File("util/Rules.txt");
            Scanner scan = new Scanner(arq);
            while(scan.hasNextLine()){
                banco += scan.nextLine() + "\n";
            }
            scan.close();
        }catch(Exception e){
            System.out.println("\nErro de L/E: " + e.getMessage());
        }
        rules.setText(banco);

        JButton st = new JButton("Start");
        st.setBounds(400, 500, 150, 30);

        st.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                janela.dispose();
                runJogo();
            }
        });

        janela.add(apresentacao);
        janela.add(imagem);
        apresentacao.add(strapresentacao);
        apresentacao.add(bvs);
        apresentacao.add(st);
        janela.repaint();
    }

    public void runJogo(){
        //configuracao da janela
        JFrame janela = new JFrame("Monopoly");
        janela.setSize(1500, 800);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);

        ImageIcon img = new ImageIcon("util/tstimg1.jpg");
        JLabel imagem = new JLabel(img);
        imagem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imagem.setBounds(300, 0, 1250, 800);

        //divisoes principais da tela
        JPanel mmenu = new JPanel();
        mmenu.setLayout(null);
        JPanel tabuleiro = new JPanel();
        mmenu.setBounds(0, 0, 300, 800);
        tabuleiro.setLayout(null);
        tabuleiro.setBounds(300, 0, 1200, 800);

        //Criacao do mmenu de opcoes
        JPanel options = new JPanel();
        options.setBounds(0, 650, 300, 150);
        options.setLayout(null);
        JButton endgame = new JButton("Encerrar Jogo");
        endgame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                janela.dispose();
            }
        });
        JButton restartgame = new JButton("Reiniciar Jogo");
        restartgame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                janela.dispose();
                runInicio();
            }
        });
        JButton savegame = new JButton("Salvar Jogo");
        savegame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                    FileOutputStream fos_game = new FileOutputStream("util/saveJogo.txt");
                    FileOutputStream fos_tab = new FileOutputStream("util/saveTabuleiro.txt");
                    FileOutputStream fos_nav = new FileOutputStream("util/saveNav.txt");
                    ObjectOutputStream oos_game = new ObjectOutputStream(fos_game);
                    ObjectOutputStream oos_tab = new ObjectOutputStream(fos_tab);
                    ObjectOutputStream oos_nav = new ObjectOutputStream(fos_nav);
                    oos_game.writeObject(game);
                    oos_tab.writeObject(tab);
                    oos_nav.writeObject(nav);

                    fos_game.close();
                    fos_tab.close();
                    fos_nav.close();
                    oos_game.close();
                    oos_tab.close();
                    oos_nav.close();
                }catch(Exception exc){
                    System.out.println("Houve um ERRO: " + exc.getMessage());
                }
           }
        });
        savegame.setBounds(0, 0, 300, 37);
        restartgame.setBounds(0, 37, 300, 37);
        endgame.setBounds(0, 74, 300, 38);

        options.add(endgame);
        options.add(restartgame);
        options.add(savegame);
        mmenu.add(options);

        //Tabuleiro:
        JPanel fmesa = new JPanel();
        fmesa = tab.getTabuleiro(); 
        tabuleiro.add(fmesa);

        JPanel fmmenu = new JPanel();
        fmmenu = nav.getPanel();
        mmenu.add(fmmenu);

        Admin admin = new Admin(tab);
        JPanel adm = new JPanel();
        adm = admin.getJPanel();
        adm.setBounds(0, 613, 300, 37);
        tabuleiro.add(adm);



        tabuleiro.add(imagem);
        janela.add(mmenu);
        janela.add(tabuleiro);
        tabuleiro.repaint();
        janela.repaint();
    }
}