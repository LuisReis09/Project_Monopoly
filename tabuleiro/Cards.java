package tabuleiro;

import javax.swing.*;
import java.awt.*;

import objects.Jogo;

public class Cards {
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
    Jogo game;
    JPanel tab;

    public Cards(Jogo game, JPanel tab){
        this.game = game;
        this.tab = tab;
        initCards();
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
}
