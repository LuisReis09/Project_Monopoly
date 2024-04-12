package tabuleiro;
import objects.*;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;
//import java.awt.event.*;
//import javax.swing.border.Border;

public class Tabuleiro implements Serializable{
    private static final long serialVersionUID = 3;
    public Jogo game;
    JPanel tab = new JPanel();
    final int q= 38;
    boolean started = false;
    
    JPanel[] casas = new JPanel[q];
    Player[] owner = new Player[q];
    float[] valor = new float[q];
    int[] ampliacoes = new int[q];
    JLabel[] title = new JLabel[q];  //titulo da casa
    JLabel[] subtitle = new JLabel[q];  //subtitulo da casa
    JLabel[] indices = new JLabel[q]; //indice da casa
    JPanel pino_j1 = new JPanel();
    JPanel pino_j2 = new JPanel();
    JPanel pino_j3 = new JPanel();
    JPanel pino_j4 = new JPanel();

    JPanel card_j1 = new JPanel();
    JPanel card_j2 = new JPanel();
    JPanel card_j3 = new JPanel();
    JPanel card_j4 = new JPanel();
    public JLabel name_j1 = new JLabel();
    public JLabel name_j2 = new JLabel();
    public JLabel name_j3 = new JLabel();
    public JLabel name_j4 = new JLabel();
    JLabel capital_j1 = new JLabel();
    JLabel capital_j2 = new JLabel();
    JLabel capital_j3 = new JLabel();  
    JLabel capital_j4 = new JLabel();
    JLabel falido1 = new JLabel();
    JLabel falido2 = new JLabel();
    JLabel falido3 = new JLabel();
    JLabel falido4 = new JLabel();
    Color desocupada = new Color(212, 203, 203);

    public Tabuleiro(Jogo game){
        this.game = game;

        for(int i=0; i<q; i++){
            casas[i]= new JPanel();
            indices[i] = new JLabel();
            owner[i]= null;
            valor[i]= 0;
            ampliacoes[i]= 0;
        }

    }

    public JPanel getTabuleiro(){
        tab.setBounds(315, 10, 1200, 800);
        tab.setLayout(null);
        tab.setOpaque(false);

        initPinos();
        if(!started)
            configPosition();
        preencheTab();
        preencheFuncoes();
        initCards();
        repaintBackgrounds();
            
        return tab;
    }

    public void verificaFalidos(){
        if(game.getCapital(game.j1) <= 0 || game.j1.falencia){
            game.j1.falencia = true;
            game.setCapital(0, game.j1);
            falido1.setText("FALIU");
            capital_j1.setText("Capital: 0.00");
            pino_j1.setVisible(false);
        }
        if(game.getCapital(game.j2) <= 0 || game.j2.falencia){
            game.j2.falencia = true;
            game.setCapital(0, game.j2);
            falido2.setText("FALIU");
            capital_j2.setText("Capital: 0.00");
            pino_j2.setVisible(false);
        }
        if(game.getCapital(game.j3) <= 0 || game.j3.falencia){
            game.j3.falencia = true;
            game.setCapital(0, game.j3);
            falido3.setText("FALIU");
            capital_j3.setText("Capital: 0.00");
            pino_j3.setVisible(false);
        }
        if(game.getCapital(game.j4) <= 0 || game.j4.falencia){
            game.j4.falencia = true;
            game.setCapital(0, game.j4);
            falido4.setText("FALIU");
            capital_j4.setText("Capital: 0.00");
            pino_j4.setVisible(false);
        }
    }

    public void redefineBorders(){
        pino_j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j1.setBackground(Color.YELLOW);
        pino_j2.setBackground(Color.GREEN);
        pino_j3.setBackground(Color.BLUE);
        pino_j4.setBackground(Color.RED);
    }

    public void configPosition(){
        int n;
        int x;
        int y;

        n = game.j1.getPosition();
        x= casas[n].getX();
        y= casas[n].getY();
        pino_j1.setBounds(x+5, y+5, 15, 15);

        n= game.j2.getPosition();
        x= casas[n].getX();
        y= casas[n].getY();
        pino_j2.setBounds(x+25, y+5, 15, 15);

        n= game.j3.getPosition();
        x= casas[n].getX();
        y= casas[n].getY();
        pino_j3.setBounds(x+5, y+25, 15, 15);

        n= game.j4.getPosition();
        x= casas[n].getX();
        y= casas[n].getY();
        pino_j4.setBounds(x+25, y+25, 15, 15);

        started = true;
    }

    public void preencheTab(){
        int l= 105;
        int h= 75;
        int supx= 0;
        int supy= 0;
        
        for(int i=0; i<11; i++){
            casas[i].setBounds(supx, supy, l, h);
            supx+= l;
        }

        supx-= l;
        for(int i=11; i<20; i++){
            supy+= h;
            casas[i].setBounds(supx, supy, l, h);
        }

        for(int i=20; i<30; i++){
            supx-= l;
            casas[i].setBounds(supx, supy, l, h);
        }

        for(int i=30; i<q; i++){
            supy-= h;
            casas[i].setBounds(supx, supy, l, h);
        }

        for(int i= 0; i<q; i++){
            tab.add(casas[i]);
        }
    }

    public void initCards(){
        card_j1.setBounds(280, 180, 300, 200);
        card_j1.setLayout(null);
        card_j1.setBackground(game.j1.getColor());
        card_j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //System.out.println(game.Str_Players());
        name_j1.setText(game.j1.getName());
        name_j1.setBounds(0, 50, 300, 50);
        name_j1.setHorizontalAlignment(SwingConstants.CENTER);
        name_j1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        capital_j1.setText("Capital: " + game.getCapital(game.j1));
        capital_j1.setBounds(0, 100, 300, 50);
        capital_j1.setHorizontalAlignment(SwingConstants.CENTER);
        capital_j1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        falido1.setText("Não falido");	
        falido1.setBounds(0, 140, 300, 50);
        falido1.setHorizontalAlignment(SwingConstants.CENTER);
        falido1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        card_j1.add(name_j1);
        card_j1.add(capital_j1);
        card_j1.add(falido1);
        tab.add(card_j1);

        card_j2.setBounds(580, 180, 300, 200);
        card_j2.setLayout(null);
        card_j2.setBackground(game.j2.getColor());
        card_j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tab.add(card_j2);

        name_j2.setText(game.j2.getName());
        name_j2.setBounds(0, 50, 300, 50);
        name_j2.setHorizontalAlignment(SwingConstants.CENTER);
        name_j2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        capital_j2.setText("Capital: " + game.getCapital(game.j2));
        capital_j2.setBounds(0, 100, 300, 50);
        capital_j2.setHorizontalAlignment(SwingConstants.CENTER);
        capital_j2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        falido2.setText("Não falido");
        falido2.setBounds(0, 140, 300, 50);
        falido2.setHorizontalAlignment(SwingConstants.CENTER);
        falido2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        card_j2.add(name_j2);
        card_j2.add(capital_j2);
        card_j2.add(falido2);

        card_j3.setBounds(280, 380, 300, 200);
        card_j3.setLayout(null);
        card_j3.setBackground(game.j3.getColor());
        card_j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tab.add(card_j3);

        name_j3.setText(game.j3.getName());
        name_j3.setBounds(0, 50, 300, 50);
        name_j3.setHorizontalAlignment(SwingConstants.CENTER);
        name_j3.setFont(new Font("Times New Roman", Font.BOLD, 40));
        capital_j3.setText("Capital: " + game.getCapital(game.j3));
        capital_j3.setBounds(0, 100, 300, 50);
        capital_j3.setHorizontalAlignment(SwingConstants.CENTER);
        capital_j3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        falido3.setText("Não falido");
        falido3.setBounds(0, 140, 300, 50);
        falido3.setHorizontalAlignment(SwingConstants.CENTER);
        falido3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        card_j3.add(name_j3);
        card_j3.add(capital_j3);
        card_j3.add(falido3);
        
        card_j4.setBounds(580, 380, 300, 200);
        card_j4.setLayout(null);
        card_j4.setBackground(game.j4.getColor());
        card_j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tab.add(card_j4);

        name_j4.setText(game.j4.getName());
        name_j4.setBounds(0, 50, 300, 50);
        name_j4.setHorizontalAlignment(SwingConstants.CENTER);
        name_j4.setFont(new Font("Times New Roman", Font.BOLD, 40));
        capital_j4.setText("Capital: " + game.getCapital(game.j4));
        capital_j4.setBounds(0, 100, 300, 50);
        capital_j4.setHorizontalAlignment(SwingConstants.CENTER);
        capital_j4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        falido4.setText("Não falido");
        falido4.setBounds(0, 140, 300, 50);
        falido4.setHorizontalAlignment(SwingConstants.CENTER);
        falido4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        card_j4.add(name_j4);
        card_j4.add(capital_j4);
        card_j4.add(falido4);
    }

    public void updateCapital(Player player){

        if(player == game.j1){
            String format = String.format("%.2f", game.getCapital(game.j1));
            capital_j1.setText("Capital: " + format);
            verificaFalidos();
            updatePropriedade();
            return;
        }
        
        if(player == game.j2){
            String format = String.format("%.2f", game.getCapital(game.j2));
            capital_j2.setText("Capital: " + format);
            verificaFalidos();
            updatePropriedade();
            return;
        }
        
        if(player == game.j3){
            String format = String.format("%.2f", game.getCapital(game.j3));
            capital_j3.setText("Capital: " + format);
            verificaFalidos();
            updatePropriedade();
            return;
        }
        
        if(player == game.j4){
            String format = String.format("%.2f", game.getCapital(game.j4));
            capital_j4.setText("Capital: " + format);
            verificaFalidos();
            updatePropriedade();
            return;
        }
    }

    public void preencheFuncoes(){
        Font titulos = new Font("Times New Roman", Font.BOLD, 12);

        
        title[0] = new JLabel("INÍCIO");
        title[0].setBounds(50, 50, 55, 25);
        title[0].setHorizontalAlignment(SwingConstants.LEFT);
        title[0].setFont(new Font("Times New Roman", Font.BOLD, 15));
        indices[0].setText("0");
        indices[0].setBounds(90,5, 15, 10);
        casas[0].setLayout(null);
        casas[0].add(title[0]);
        casas[0].add(indices[0]);
        casas[0].setBackground(Color.LIGHT_GRAY);
        casas[0].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[1] = new JLabel("Propriedade");
        subtitle[1] = new JLabel();
        valor[1]= (float) 250.00;
        subtitle[1].setText("Aluguel: " + valor[1]);
        title[1].setBounds(0, 45, 105, 15);
        subtitle[1].setBounds(0, 60, 105, 15);
        title[1].setFont(titulos);
        subtitle[1].setFont(titulos);
        indices[1].setText("1");
        indices[1].setBounds(90, 5, 15, 10);
        title[1].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[1].setHorizontalAlignment(SwingConstants.CENTER);
        casas[1].setLayout(null);
        casas[1].add(title[1]);
        casas[1].add(subtitle[1]); 
        casas[1].add(indices[1]);
        casas[1].setBackground(desocupada);
        casas[1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[2] = new JLabel("Propriedade");   
        subtitle[2] = new JLabel();
        valor[2]= (float) 250.00;
        subtitle[2].setText("Aluguel: " + valor[2]);
        title[2].setBounds(0, 45, 105, 15);
        subtitle[2].setBounds(0, 60, 105, 15);
        title[2].setFont(titulos);
        subtitle[2].setFont(titulos);
        indices[2].setText("2");
        indices[2].setBounds(90, 5, 15, 10);
        title[2].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[2].setHorizontalAlignment(SwingConstants.CENTER);
        casas[2].setLayout(null);
        casas[2].add(title[2]);
        casas[2].add(subtitle[2]);
        casas[2].add(indices[2]);
        casas[2].setBackground(desocupada);
        casas[2].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[3] = new JLabel("Praça Pública");
        title[3].setBounds(0, 45, 105, 15);
        title[3].setHorizontalAlignment(SwingConstants.CENTER);
        title[3].setFont(titulos);
        indices[3].setText("3");
        indices[3].setBounds(90, 5, 15, 10);
        casas[3].setLayout(null);
        casas[3].add(title[3]);
        casas[3].add(indices[3]);
        casas[3].setBackground(desocupada);
        casas[3].setBorder(BorderFactory.createLineBorder(Color.BLACK));


        title[4] = new JLabel("Propriedade");
        subtitle[4] = new JLabel();
        valor[4]= (float) 250.00;
        subtitle[4].setText("Aluguel: " + valor[4]);
        title[4].setBounds(0, 45, 105, 15);
        subtitle[4].setBounds(0, 60, 105, 15);
        title[4].setFont(titulos);
        subtitle[4].setFont(titulos);
        indices[4].setText("4");
        indices[4].setBounds(90, 5, 15, 10);
        title[4].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[4].setHorizontalAlignment(SwingConstants.CENTER);
        casas[4].setLayout(null);
        casas[4].add(title[4]);
        casas[4].add(subtitle[4]);
        casas[4].add(indices[4]);
        casas[4].setBackground(desocupada);
        casas[4].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[5] = new JLabel("Redemoinho");
        title[5].setBounds(0, 45, 105, 15);
        title[5].setFont(titulos);
        indices[5].setText("5");
        indices[5].setBounds(90, 5, 15, 10);
        title[5].setHorizontalAlignment(SwingConstants.CENTER);
        casas[5].setLayout(null);
        casas[5].add(title[5]);
        casas[5].add(indices[5]);
        casas[5].setBackground(desocupada);
        casas[5].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[6] = new JLabel("Propriedade");
        subtitle[6] = new JLabel();
        valor[6]= (float) 250.00;
        subtitle[6].setText("Aluguel: " + valor[6]);
        title[6].setBounds(0, 45, 105, 15);
        subtitle[6].setBounds(0, 60, 105, 15);
        title[6].setFont(titulos);
        subtitle[6].setFont(titulos);
        indices[6].setText("6");
        indices[6].setBounds(90, 5, 15, 10);
        title[6].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[6].setHorizontalAlignment(SwingConstants.CENTER);
        casas[6].setLayout(null);
        casas[6].add(title[6]);
        casas[6].add(subtitle[6]);
        casas[6].add(indices[6]);
        casas[6].setBackground(desocupada);
        casas[6].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[7]= new JLabel("Sorte ou Azar?");
        valor[7]= (float) 0.00;
        title[7].setBounds(0, 45, 105, 15);
        title[7].setHorizontalAlignment(SwingConstants.CENTER);
        title[7].setFont(titulos);
        indices[7].setText("7");
        indices[7].setBounds(90, 5, 15, 10);
        casas[7].setLayout(null);
        casas[7].add(title[7]);
        casas[7].add(indices[7]);
        casas[7].setBackground(desocupada);
        casas[7].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        title[8] = new JLabel("Propriedade");
        subtitle[8] = new JLabel();
        valor[8]= (float) 250.00;
        subtitle[8].setText("Aluguel: " + valor[8]);
        title[8].setBounds(0, 45, 105, 15);
        subtitle[8].setBounds(0, 60, 105, 15);
        title[8].setFont(titulos);
        subtitle[8].setFont(titulos);
        indices[8].setText("8");
        indices[8].setBounds(90, 5, 15, 10);
        title[8].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[8].setHorizontalAlignment(SwingConstants.CENTER);
        casas[8].setLayout(null);
        casas[8].add(title[8]);
        casas[8].add(subtitle[8]);
        casas[8].add(indices[8]);
        casas[8].setBackground(desocupada);
        casas[8].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        title[9] = new JLabel("Propriedade");
        subtitle[9] = new JLabel();
        valor[9]= (float) 250.00;
        subtitle[9].setText("Aluguel: " + valor[9]);
        title[9].setBounds(0, 45, 105, 15);
        subtitle[9].setBounds(0, 60, 105, 15);
        title[9].setFont(titulos);
        subtitle[9].setFont(titulos);
        indices[9].setText("9");
        indices[9].setBounds(90, 5, 15, 10);
        title[9].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[9].setHorizontalAlignment(SwingConstants.CENTER);
        casas[9].setLayout(null);
        casas[9].add(title[9]);
        casas[9].add(subtitle[9]);
        casas[9].add(indices[9]);
        casas[9].setBackground(desocupada);
        casas[9].setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        
        title[10] = new JLabel("BANCO");
        subtitle[10]= new JLabel("Valor: " + valor[7]);
        title[10].setBounds(0, 45, 105, 15);
        subtitle[10].setBounds(0, 60, 105, 15);
        title[10].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[10].setHorizontalAlignment(SwingConstants.CENTER);
        title[10].setFont(new Font("Times New Roman", Font.BOLD, 15));
        subtitle[10].setFont(titulos);
        indices[10].setText("10");
        indices[10].setBounds(90, 5, 15, 10);
        casas[10].setLayout(null);
        casas[10].add(subtitle[10]);
        casas[10].add(title[10]);
        casas[10].add(indices[10]);
        casas[10].setBackground(Color.LIGHT_GRAY);
        casas[10].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        title[11] = new JLabel("Propriedade");
        subtitle[11] = new JLabel();
        valor[11]= (float) 250.00;
        subtitle[11].setText("Aluguel: " + valor[11]);
        title[11].setBounds(0, 45, 105, 15);
        subtitle[11].setBounds(0, 60, 105, 15);
        title[11].setFont(titulos);
        subtitle[11].setFont(titulos);
        indices[11].setText("11");
        indices[11].setBounds(90, 5, 15, 10);
        title[11].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[11].setHorizontalAlignment(SwingConstants.CENTER);
        casas[11].setLayout(null);
        casas[11].add(title[11]);
        casas[11].add(subtitle[11]);
        casas[11].add(indices[11]);
        casas[11].setBackground(desocupada);
        casas[11].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[12] = new JLabel("Propriedade");
        subtitle[12] = new JLabel();
        valor[12]= (float) 250.00;
        subtitle[12].setText("Aluguel: " + valor[12]);
        title[12].setBounds(0, 45, 105, 15);
        subtitle[12].setBounds(0, 60, 105, 15);
        title[12].setFont(titulos);
        subtitle[12].setFont(titulos);
        indices[12].setText("12");
        indices[12].setBounds(90, 5, 15, 10);
        title[12].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[12].setHorizontalAlignment(SwingConstants.CENTER);
        casas[12].setLayout(null);
        casas[12].add(title[12]);
        casas[12].add(subtitle[12]);
        casas[12].add(indices[12]);
        casas[12].setBackground(desocupada);
        casas[12].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[13] = new JLabel("Imposto de Renda");
        subtitle[13] = new JLabel("Valor: 10%");
        title[13].setBounds(0, 45, 105, 15);
        subtitle[13].setBounds(0, 60, 105, 15);
        title[13].setFont(titulos);
        subtitle[13].setFont(titulos);
        indices[13].setText("13");
        indices[13].setBounds(90, 5, 15, 10);
        title[13].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[13].setHorizontalAlignment(SwingConstants.CENTER);
        casas[13].setLayout(null);
        casas[13].add(title[13]);
        casas[13].add(subtitle[13]);
        casas[13].add(indices[13]);
        casas[13].setBackground(desocupada);
        casas[13].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[14] = new JLabel("Propriedade");
        subtitle[14] = new JLabel();
        valor[14]= (float) 250.00;
        subtitle[14].setText("Aluguel: " + valor[14]);
        title[14].setBounds(0, 45, 105, 15);
        subtitle[14].setBounds(0, 60, 105, 15);
        title[14].setFont(titulos);
        subtitle[14].setFont(titulos);
        indices[14].setText("14");
        indices[14].setBounds(90, 5, 15, 10);
        casas[14].add(indices[14]);
        title[14].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[14].setHorizontalAlignment(SwingConstants.CENTER);
        casas[14].setLayout(null);
        casas[14].add(title[14]);
        casas[14].add(subtitle[14]);
        casas[14].setBackground(desocupada);
        casas[14].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[15] = new JLabel("Propriedade");
        subtitle[15] = new JLabel();
        valor[15]= (float) 250.00;
        subtitle[15].setText("Aluguel: " + valor[15]);
        title[15].setBounds(0, 45, 105, 15);
        subtitle[15].setBounds(0, 60, 105, 15);
        title[15].setFont(titulos);
        subtitle[15].setFont(titulos);
        indices[15].setText("15");
        indices[15].setBounds(90, 5, 15, 10);
        casas[15].add(indices[15]);
        title[15].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[15].setHorizontalAlignment(SwingConstants.CENTER);
        casas[15].setLayout(null);
        casas[15].add(title[15]);
        casas[15].add(subtitle[15]);
        casas[15].setBackground(desocupada);
        casas[15].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[16] = new JLabel("Imposto Geral");
        valor[16] = (float) 250.00;
        subtitle[16] = new JLabel("Valor: $" + valor[16]);
        title[16].setBounds(0, 45, 105, 15);
        subtitle[16].setBounds(0, 60, 105, 15);
        title[16].setFont(titulos);
        subtitle[16].setFont(titulos);
        indices[16].setText("16");
        indices[16].setBounds(90, 5, 15, 10);
        casas[16].add(indices[16]);
        title[16].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[16].setHorizontalAlignment(SwingConstants.CENTER);
        casas[16].setLayout(null);
        casas[16].add(title[16]);
        casas[16].add(subtitle[16]);
        casas[16].setBackground(desocupada);
        casas[16].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[17] = new JLabel("Propriedade");
        subtitle[17] = new JLabel();
        valor[17]= (float) 250.00;
        subtitle[17].setText("Aluguel: " + valor[17]);
        title[17].setBounds(0, 45, 105, 15);
        subtitle[17].setBounds(0, 60, 105, 15);
        title[17].setFont(titulos);
        subtitle[17].setFont(titulos);
        indices[17].setText("17");
        indices[17].setBounds(90, 5, 15, 10);
        casas[17].add(indices[17]);
        title[17].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[17].setHorizontalAlignment(SwingConstants.CENTER);
        casas[17].setLayout(null);
        casas[17].add(title[17]);
        casas[17].add(subtitle[17]);
        casas[17].setBackground(desocupada);
        casas[17].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[18] = new JLabel("Propriedade");
        subtitle[18] = new JLabel();
        valor[18]= (float) 250.00;
        subtitle[18].setText("Aluguel: " + valor[18]);
        title[18].setBounds(0, 45, 105, 15);
        subtitle[18].setBounds(0, 60, 105, 15);
        title[18].setFont(titulos);
        subtitle[18].setFont(titulos);
        indices[18].setText("18");
        indices[18].setBounds(90, 5, 15, 10);
        casas[18].add(indices[18]);
        title[18].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[18].setHorizontalAlignment(SwingConstants.CENTER);
        casas[18].setLayout(null);
        casas[18].add(title[18]);
        casas[18].add(subtitle[18]);
        casas[18].setBackground(desocupada);
        casas[18].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[19] = new JLabel("PRISÃO");
        title[19].setBounds(0, 45, 105, 15);
        title[19].setHorizontalAlignment(SwingConstants.CENTER);
        title[19].setFont(new Font("Times New Roman", Font.BOLD, 15));
        indices[19].setText("19");
        indices[19].setBounds(90, 5, 15, 10);
        casas[19].add(indices[19]);
        casas[19].setLayout(null);
        casas[19].add(title[19]);
        casas[19].setBackground(Color.LIGHT_GRAY);
        casas[19].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[20] = new JLabel("Propriedade");
        subtitle[20] = new JLabel();
        valor[20]= (float) 250.00;
        subtitle[20].setText("Aluguel: " + valor[20]);
        title[20].setBounds(0, 45, 105, 15);
        subtitle[20].setBounds(0, 60, 105, 15);
        title[20].setFont(titulos);
        subtitle[20].setFont(titulos);
        indices[20].setText("20");
        indices[20].setBounds(90, 5, 15, 10);
        casas[20].add(indices[20]);
        title[20].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[20].setHorizontalAlignment(SwingConstants.CENTER);
        casas[20].setLayout(null);
        casas[20].add(title[20]);
        casas[20].add(subtitle[20]);
        casas[20].setBackground(desocupada);
        casas[20].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[21] = new JLabel("Redemoinho");
        title[21].setBounds(0, 45, 105, 15);
        title[21].setHorizontalAlignment(SwingConstants.CENTER);
        title[21].setFont(titulos);
        indices[21].setText("21");
        indices[21].setBounds(90, 5, 15, 10);
        casas[21].add(indices[21]);
        casas[21].setLayout(null);
        casas[21].add(title[21]);
        casas[21].setBackground(desocupada);
        casas[21].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        title[22] = new JLabel("Propriedade");
        subtitle[22] = new JLabel();
        valor[22]= (float) 250.00;
        subtitle[22].setText("Aluguel: " + valor[22]);
        title[22].setBounds(0, 45, 105, 15);
        subtitle[22].setBounds(0, 60, 105, 15);
        title[22].setFont(titulos);
        subtitle[22].setFont(titulos);
        indices[22].setText("22");
        indices[22].setBounds(90, 5, 15, 10);
        casas[22].add(indices[22]);
        title[22].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[22].setHorizontalAlignment(SwingConstants.CENTER);
        casas[22].setLayout(null);
        casas[22].add(title[22]);
        casas[22].add(subtitle[22]);
        casas[22].setBackground(desocupada);
        casas[22].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        title[23] = new JLabel("Sorte ou Azar?");
        title[23].setBounds(0, 45, 105, 15);
        title[23].setHorizontalAlignment(SwingConstants.CENTER);
        title[23].setFont(titulos);
        indices[23].setText("23");
        indices[23].setBounds(90, 5, 15, 10);
        casas[23].add(indices[23]);
        casas[23].setLayout(null);
        casas[23].add(title[23]);
        casas[23].setBackground(desocupada);
        casas[23].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[24] = new JLabel("Pedágio");
        valor[24]= (float) 300.00;
        subtitle[24] = new JLabel("Valor: " + valor[24]);
        title[24].setBounds(0, 45, 105, 15);
        subtitle[24].setBounds(0, 60, 105, 15);
        title[24].setFont(titulos);
        subtitle[24].setFont(titulos);
        indices[24].setText("24");
        indices[24].setBounds(90, 5, 15, 10);
        casas[24].add(indices[24]);
        title[24].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[24].setHorizontalAlignment(SwingConstants.CENTER);
        casas[24].setLayout(null);
        casas[24].add(title[24]);
        casas[24].add(subtitle[24]);
        casas[24].setBackground(desocupada);
        casas[24].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[25] = new JLabel("Cobrança");
        valor[25]= (float) 250.00;
        subtitle[25] = new JLabel("Valor: " + valor[25]);
        title[25].setBounds(0, 45, 105, 15);
        subtitle[25].setBounds(0, 60, 105, 15);
        title[25].setFont(titulos);
        subtitle[25].setFont(titulos);
        indices[25].setText("25");
        indices[25].setBounds(90, 5, 15, 10);
        casas[25].add(indices[25]);
        title[25].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[25].setHorizontalAlignment(SwingConstants.CENTER);
        casas[25].setLayout(null);
        casas[25].add(title[25]);
        casas[25].add(subtitle[25]);
        casas[25].setBackground(desocupada);
        casas[25].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[26] = new JLabel("Propriedade");
        subtitle[26] = new JLabel();
        valor[26]= (float) 250.00;
        subtitle[26].setText("Aluguel: " + valor[26]);
        title[26].setBounds(0, 45, 105, 15);
        subtitle[26].setBounds(0, 60, 105, 15);
        title[26].setFont(titulos);
        subtitle[26].setFont(titulos);
        indices[26].setText("26");
        indices[26].setBounds(90, 5, 15, 10);
        casas[26].add(indices[26]);
        title[26].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[26].setHorizontalAlignment(SwingConstants.CENTER);
        casas[26].setLayout(null);
        casas[26].add(title[26]);
        casas[26].add(subtitle[26]);
        casas[26].setBackground(desocupada);
        casas[26].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[27] = new JLabel("Propriedade");
        subtitle[27] = new JLabel();
        valor[27]= (float) 250.00;
        subtitle[27].setText("Aluguel: " + valor[27]);
        title[27].setBounds(0, 45, 105, 15);
        subtitle[27].setBounds(0, 60, 105, 15);
        title[27].setFont(titulos);
        subtitle[27].setFont(titulos);
        indices[27].setText("27");
        indices[27].setBounds(90, 5, 15, 10);
        casas[27].add(indices[27]);
        title[27].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[27].setHorizontalAlignment(SwingConstants.CENTER);
        casas[27].setLayout(null);
        casas[27].add(title[27]);
        casas[27].add(subtitle[27]);
        casas[27].setBackground(desocupada);
        casas[27].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[28] = new JLabel("Propriedade");
        subtitle[28] = new JLabel();
        valor[28]= (float) 250.00;
        subtitle[28].setText("Aluguel: " + valor[28]);
        title[28].setBounds(0, 45, 105, 15);
        subtitle[28].setBounds(0, 60, 105, 15);
        title[28].setFont(titulos);
        subtitle[28].setFont(titulos);
        indices[28].setText("28");
        indices[28].setBounds(90, 5, 15, 10);
        casas[28].add(indices[28]);
        title[28].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[28].setHorizontalAlignment(SwingConstants.CENTER);
        casas[28].setLayout(null);
        casas[28].add(title[28]);
        casas[28].add(subtitle[28]);
        casas[28].setBackground(desocupada);
        casas[28].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[29]= new JLabel("Lotérica");
        title[29].setBounds(0, 45, 105, 15);
        title[29].setHorizontalAlignment(SwingConstants.CENTER);
        title[29].setFont(new Font("Times New Roman", Font.BOLD, 15));
        indices[29].setText("29");
        indices[29].setBounds(90, 5, 15, 10);
        casas[29].add(indices[29]);
        casas[29].setLayout(null);
        casas[29].add(title[29]);
        casas[29].setBackground(Color.LIGHT_GRAY);
        casas[29].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[30] = new JLabel("Propriedade");
        subtitle[30] = new JLabel();
        valor[30]= (float) 250.00;
        subtitle[30].setText("Aluguel: " + valor[30]);
        title[30].setBounds(0, 45, 105, 15);
        subtitle[30].setBounds(0, 60, 105, 15);
        title[30].setFont(titulos);
        subtitle[30].setFont(titulos);
        indices[30].setText("30");
        indices[30].setBounds(90, 5, 15, 10);
        casas[30].add(indices[30]);
        title[30].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[30].setHorizontalAlignment(SwingConstants.CENTER);
        casas[30].setLayout(null);
        casas[30].add(title[30]);
        casas[30].add(subtitle[30]);
        casas[30].setBackground(desocupada);
        casas[30].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[31] = new JLabel("Assalto");
        title[31].setBounds(0, 45, 105, 15);
        title[31].setHorizontalAlignment(SwingConstants.CENTER);
        title[31].setFont(titulos);
        indices[31].setText("31");
        indices[31].setBounds(90, 5, 15, 10);
        casas[31].add(indices[31]);
        casas[31].setLayout(null);
        casas[31].add(title[31]);
        casas[31].setBackground(desocupada);
        casas[31].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[32] = new JLabel("Propriedade");
        subtitle[32] = new JLabel();
        valor[32]= (float) 250.00;
        subtitle[32].setText("Aluguel: " + valor[32]);
        title[32].setBounds(0, 45, 105, 15);
        subtitle[32].setBounds(0, 60, 105, 15);
        title[32].setFont(titulos);
        subtitle[32].setFont(titulos);
        indices[32].setText("32");
        indices[32].setBounds(90, 5, 15, 10);
        casas[32].add(indices[32]);
        title[32].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[32].setHorizontalAlignment(SwingConstants.CENTER);
        casas[32].setLayout(null);
        casas[32].add(title[32]);
        casas[32].add(subtitle[32]);
        casas[32].setBackground(desocupada);
        casas[32].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[33] = new JLabel("Propriedade");
        subtitle[33] = new JLabel();
        valor[33]= (float) 250.00;
        subtitle[33].setText("Aluguel: " + valor[33]);
        title[33].setBounds(0, 45, 105, 15);
        subtitle[33].setBounds(0, 60, 105, 15);
        title[33].setFont(titulos);
        subtitle[33].setFont(titulos);
        indices[33].setText("33");
        indices[33].setBounds(90, 5, 15, 10);
        casas[33].add(indices[33]);
        title[33].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[33].setHorizontalAlignment(SwingConstants.CENTER);
        casas[33].setLayout(null);
        casas[33].add(title[33]);
        casas[33].add(subtitle[33]);
        casas[33].setBackground(desocupada);
        casas[33].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[34] = new JLabel("Imposto Veicular");
        subtitle[34] = new JLabel();
        valor[34]= (float) 400.00;
        subtitle[34].setText("Valor: 5%");
        title[34].setBounds(0, 45, 105, 15);
        subtitle[34].setBounds(0, 60, 105, 15);
        title[34].setFont(titulos);
        subtitle[34].setFont(titulos);
        indices[34].setText("34");
        indices[34].setBounds(90, 5, 15, 10);
        casas[34].add(indices[34]);
        title[34].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[34].setHorizontalAlignment(SwingConstants.CENTER);
        casas[34].setLayout(null);
        casas[34].add(title[34]);
        casas[34].add(subtitle[34]);
        casas[34].setBackground(desocupada);
        casas[34].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[35] = new JLabel("Imposto Geral");
        valor[35] = (float) 500.00;
        subtitle[35] = new JLabel("Valor: " + valor[35]);
        title[35].setBounds(0, 45, 105, 15);
        subtitle[35].setBounds(0, 60, 105, 15);
        title[35].setFont(titulos);
        subtitle[35].setFont(titulos);
        indices[35].setText("35");
        indices[35].setBounds(90, 5, 15, 10);
        casas[35].add(indices[35]);
        title[35].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[35].setHorizontalAlignment(SwingConstants.CENTER);
        casas[35].setLayout(null);
        casas[35].add(title[35]);
        casas[35].add(subtitle[35]);
        casas[35].setBackground(desocupada);
        casas[35].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[36] = new JLabel("Propriedade");
        subtitle[36] = new JLabel();
        valor[36]= (float) 250.00;
        subtitle[36].setText("Aluguel: " + valor[36]);
        title[36].setBounds(0, 45, 105, 15);
        subtitle[36].setBounds(0, 60, 105, 15);
        title[36].setFont(titulos);
        subtitle[36].setFont(titulos);
        indices[36].setText("36");
        indices[36].setBounds(90, 5, 15, 10);
        casas[36].add(indices[36]);
        title[36].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[36].setHorizontalAlignment(SwingConstants.CENTER);
        casas[36].setLayout(null);
        casas[36].add(title[36]);
        casas[36].add(subtitle[36]);
        casas[36].setBackground(desocupada);
        casas[36].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title[37] = new JLabel("Propriedade");
        subtitle[37] = new JLabel();
        valor[37]= (float) 250.00;
        subtitle[37].setText("Aluguel: " + valor[37]);
        title[37].setBounds(0, 45, 105, 15);
        subtitle[37].setBounds(0, 60, 105, 15);
        title[37].setFont(titulos);
        subtitle[37].setFont(titulos);
        indices[37].setText("37");
        indices[37].setBounds(90, 5, 15, 10);
        casas[37].add(indices[37]);
        title[37].setHorizontalAlignment(SwingConstants.CENTER);
        subtitle[37].setHorizontalAlignment(SwingConstants.CENTER);
        casas[37].setLayout(null);
        casas[37].add(title[37]);
        casas[37].add(subtitle[37]);
        casas[37].setBackground(desocupada);
        casas[37].setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void initPinos(){
        pino_j1.setBackground(Color.YELLOW);
        pino_j2.setBackground(Color.GREEN);
        pino_j3.setBackground(Color.BLUE);
        pino_j4.setBackground(Color.RED);
        pino_j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tab.add(pino_j1);
        tab.add(pino_j2);
        tab.add(pino_j3);
        tab.add(pino_j4);
    }

    public void attPosition(Player player, int n){
        if(n>=1&&n<=6){
            player.posicao += n;
            if(player.posicao >= 38){
               player.posicao -= 38;
            }

            if(player==game.j1){
                pino_j1.setLocation(casas[player.posicao].getX()+5, casas[player.posicao].getY()+5);
            }else if(player==game.j2){
                pino_j2.setLocation(casas[player.posicao].getX()+25, casas[player.posicao].getY()+5);
            }else if(player==game.j3){
                pino_j3.setLocation(casas[player.posicao].getX()+5, casas[player.posicao].getY()+25);
            }else if(player==game.j4){
                pino_j4.setLocation(casas[player.posicao].getX()+25, casas[player.posicao].getY()+25);
            }
            return;
        }

        player.posicao = n;
        int m = player.getPosition();
        int x= casas[m].getX();
        int y= casas[m].getY();
        if(player==game.j1){
            pino_j1.setLocation(x+5, y+5);
        }else if(player==game.j2){
            pino_j2.setLocation(x+25, y+5);
        }else if(player==game.j3){
            pino_j3.setLocation(x+5, y+25);
        }else if(player==game.j4){
            pino_j4.setLocation(x+25, y+25);
        }

    }

    public void attPositionbySwirl(Player player, int n){
        player.posicao = n;
        int x = casas[n].getX();
        int y = casas[n].getY();
        if(player==game.j1){
            pino_j1.setLocation(x+5, y+5);
        }else if(player==game.j2){
            pino_j2.setLocation(x+25, y+5);
        }else if(player==game.j3){
            pino_j3.setLocation(x+5, y+25);
        }else{
            pino_j4.setLocation(x+25, y+25);
        }
    }

    public void updateBanco(float valor){
        this.valor[10] += valor;
        String format = String.format("%.2f", this.valor[10]);
        subtitle[10].setText("Valor: " + format);
    }

    public void updatePropriedade(){
        int p;

        if(game.j1.getFalido()){
            for(int i= 0; i<game.j1.propriedades.size(); i++){
                p = game.j1.propriedades.get(i);
                casas[p].setBackground(desocupada);
                owner[p] = null;
                valor[p] = (float) 250;
            }
            game.j1.propriedades.clear();
        }

        if(game.j2.getFalido()){
            for(int i= 0; i<game.j2.propriedades.size(); i++){
                p = game.j2.propriedades.get(i);
                casas[p].setBackground(desocupada);
                owner[p] = null;
                valor[p] = (float) 250;
            }
            game.j2.propriedades.clear();
        }

        if(game.j3.getFalido()){
            for(int i= 0; i<game.j3.propriedades.size(); i++){
                p = game.j3.propriedades.get(i);
                casas[p].setBackground(desocupada);
                owner[p] = null;
                valor[p] = (float) 250;
            }
            game.j3.propriedades.clear();
        }

        if(game.j4.getFalido()){
            for(int i= 0; i<game.j4.propriedades.size(); i++){
                p = game.j4.propriedades.get(i);
                casas[p].setBackground(desocupada);
                owner[p] = null;
                valor[p] = (float) 250;
            }
            game.j4.propriedades.clear();
        }
    }	

    public void repaintBackgrounds(){
        for(int i=0; i<38; i++){
            if(owner[i] != null){
                casas[i].setBackground(owner[i].getColor());
            }
        }
    }

    public int randomNumber(){
        int number = (int) (Math.random()*6) + 1;
        return number;
    }

    public int randomSwirl(){
        int x = (int) (Math.random()*38);
        return x;
    }
}