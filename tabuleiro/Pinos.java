package tabuleiro;
import util.*;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Pinos implements Serializable{
    //define as configuracoes iniciais dos pinos
    private static final long serialVersionUID = 6;
    JPanel pino_j1 = new JPanel();
    JPanel pino_j2 = new JPanel();
    JPanel pino_j3 = new JPanel();
    JPanel pino_j4 = new JPanel();
    JPanel casas[];

    public void update(Saves save){
        this.casas = save.c.getCasas();
        configPosition();
        layoutPinos();
    }

    public JPanel getPino(int n){
        if(n==1){
            return pino_j1;
        }else if(n==2){
            return pino_j2;
        }else if(n==3){
            return pino_j3;
        }else{
            return pino_j4;
        }
    }

    public void layoutPinos(){
        pino_j1.setBackground(Color.YELLOW);
        pino_j2.setBackground(Color.GREEN);
        pino_j3.setBackground(Color.BLUE);
        pino_j4.setBackground(Color.RED);
        pino_j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pino_j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void configPosition(){
        int x;
        int y;

        x= casas[0].getX();
        y= casas[0].getY();
        pino_j1.setBounds(x+5, y+5, 15, 15);

        x= casas[0].getX();
        y= casas[0].getY();
        pino_j2.setBounds(x+25, y+5, 15, 15);

        x= casas[0].getX();
        y= casas[0].getY();
        pino_j3.setBounds(x+5, y+25, 15, 15);

        x= casas[0].getX();
        y= casas[0].getY();
        pino_j4.setBounds(x+25, y+25, 15, 15);
    }
}
