package tabuleiro;
import objects.*;
import javax.swing.*;
//import java.awt.*;
//import util.*;

public class Tabuleiro{
    //fornece metodos que realizam a manutencao das casas, dos pinos e dos cards
    public Jogo game;
    JPanel tab = new JPanel();
    final int q= 38;

    Casas casas;
    Pinos pinos;
    JPanel[] paineisCasa;
    Player[] owner = new Player[q];
    int[] ampliacoes = new int[q];

    public Cards cards;
    

    public Tabuleiro(Casas c, Jogo game){
        this.game = game;
        this.paineisCasa = c.getCasas();
        this.casas = c;
        cards = new Cards(this.game, tab);
        
        for(int i=0; i<q; i++){
            owner[i]= null;
            ampliacoes[i]= 0;
        }
    }

    public void setPinos(Pinos p) {
        this.pinos = p;
    }

    public JPanel getTabuleiro(){
        tab.setBounds(315, 10, 1200, 800);
        tab.setLayout(null);
        tab.setOpaque(false);

        initPinos();
        preencheTab();
        cards.initCards();
        repaintBackgrounds();
            
        return tab;
    }

    public void reloadPositions(){
        int x = game.j1.getPosition();
        pinos.pino_j1.setLocation(paineisCasa[x].getX()+5, paineisCasa[x].getY()+5);
        x = game.j2.getPosition();
        pinos.pino_j2.setLocation(paineisCasa[x].getX()+25, paineisCasa[x].getY()+5);
        x = game.j3.getPosition();
        pinos.pino_j3.setLocation(paineisCasa[x].getX()+5, paineisCasa[x].getY()+25);
        x = game.j4.getPosition();
        pinos.pino_j4.setLocation(paineisCasa[x].getX()+25, paineisCasa[x].getY()+25);
    }


    public void verificaFalidos(){
        if(game.getCapital(game.j1) <= 0 || game.j1.falencia){
            game.j1.falencia = true;
            game.setCapital(0, game.j1);
            cards.falido1.setText("FALIU");
            cards.capital_j1.setText("Capital: 0.00");
            pinos.pino_j1.setVisible(false);
        }
        if(game.getCapital(game.j2) <= 0 || game.j2.falencia){
            game.j2.falencia = true;
            game.setCapital(0, game.j2);
            cards.falido2.setText("FALIU");
            cards.capital_j2.setText("Capital: 0.00");
            pinos.pino_j2.setVisible(false);
        }
        if(game.getCapital(game.j3) <= 0 || game.j3.falencia){
            game.j3.falencia = true;
            game.setCapital(0, game.j3);
            cards.falido3.setText("FALIU");
            cards.capital_j3.setText("Capital: 0.00");
            pinos.pino_j3.setVisible(false);
        }
        if(game.getCapital(game.j4) <= 0 || game.j4.falencia){
            game.j4.falencia = true;
            game.setCapital(0, game.j4);
            cards.falido4.setText("FALIU");
            cards.capital_j4.setText("Capital: 0.00");
            pinos.pino_j4.setVisible(false);
        }

        updatePropriedade();
    }

    public void preencheTab(){
        for(int i= 0; i<q; i++){
            tab.add(paineisCasa[i]);
        }
    }

    public void updateCapital(Player player){

        if(player == game.j1){
            String format = String.format("%.2f", game.getCapital(game.j1));
            cards.capital_j1.setText("Capital: " + format);
            verificaFalidos();
            return;
        }
        
        if(player == game.j2){
            String format = String.format("%.2f", game.getCapital(game.j2));
            cards.capital_j2.setText("Capital: " + format);
            verificaFalidos();
            return;
        }
        
        if(player == game.j3){
            String format = String.format("%.2f", game.getCapital(game.j3));
            cards.capital_j3.setText("Capital: " + format);
            verificaFalidos();
            return;
        }
        
        if(player == game.j4){
            String format = String.format("%.2f", game.getCapital(game.j4));
            cards.capital_j4.setText("Capital: " + format);
            verificaFalidos();
            return;
        }
    }

    public void initPinos(){
        tab.add(pinos.pino_j1);
        tab.add(pinos.pino_j2);
        tab.add(pinos.pino_j3);
        tab.add(pinos.pino_j4);
    }

    public void attPosition(Player player, int n){
        if(n>=1&&n<=6){
            player.posicao += n;
            if(player.posicao >= 38){
               player.posicao -= 38;
            }

            if(player==game.j1){
                pinos.pino_j1.setLocation(paineisCasa[player.posicao].getX()+5, paineisCasa[player.posicao].getY()+5);
            }else if(player==game.j2){
                pinos.pino_j2.setLocation(paineisCasa[player.posicao].getX()+25, paineisCasa[player.posicao].getY()+5);
            }else if(player==game.j3){
                pinos.pino_j3.setLocation(paineisCasa[player.posicao].getX()+5, paineisCasa[player.posicao].getY()+25);
            }else if(player==game.j4){
                pinos.pino_j4.setLocation(paineisCasa[player.posicao].getX()+25, paineisCasa[player.posicao].getY()+25);
            }
            return;
        }

        player.posicao = n;
        int m = player.getPosition();
        int x= paineisCasa[m].getX();
        int y= paineisCasa[m].getY();
        if(player==game.j1){
            pinos.pino_j1.setLocation(x+5, y+5);
        }else if(player==game.j2){
            pinos.pino_j2.setLocation(x+25, y+5);
        }else if(player==game.j3){
            pinos.pino_j3.setLocation(x+5, y+25);
        }else if(player==game.j4){
            pinos.pino_j4.setLocation(x+25, y+25);
        }

    }

    public void attPositionbySwirl(Player player, int n){
        player.posicao = n;
        int x = paineisCasa[n].getX();
        int y = paineisCasa[n].getY();
        if(player==game.j1){
            pinos.pino_j1.setLocation(x+5, y+5);
        }else if(player==game.j2){
            pinos.pino_j2.setLocation(x+25, y+5);
        }else if(player==game.j3){
            pinos.pino_j3.setLocation(x+5, y+25);
        }else{
            pinos.pino_j4.setLocation(x+25, y+25);
        }
    }

    public void updateBanco(float valor){
        this.casas.valor[10] += valor;
        String format = String.format("%.2f", this.casas.valor[10]);
        casas.subtitle[10].setText("Valor: " + format);
    }

    public void updatePropriedade(){
        int p;

        if(game.j1.getFalido()){
            for(int i= 0; i<game.j1.propriedades.size(); i++){
                p = game.j1.propriedades.get(i);
                paineisCasa[p].setBackground(casas.desocupada);
                owner[p] = null;
                casas.valor[p] = (float) 250;
            }
            game.j1.propriedades.clear();
        }

        if(game.j2.getFalido()){
            for(int i= 0; i<game.j2.propriedades.size(); i++){
                p = game.j2.propriedades.get(i);
                paineisCasa[p].setBackground(casas.desocupada);
                owner[p] = null;
                casas.valor[p] = (float) 250;
            }
            game.j2.propriedades.clear();
        }

        if(game.j3.getFalido()){
            for(int i= 0; i<game.j3.propriedades.size(); i++){
                p = game.j3.propriedades.get(i);
                paineisCasa[p].setBackground(casas.desocupada);
                owner[p] = null;
                casas.valor[p] = (float) 250;
            }
            game.j3.propriedades.clear();
        }

        if(game.j4.getFalido()){
            for(int i= 0; i<game.j4.propriedades.size(); i++){
                p = game.j4.propriedades.get(i);
                paineisCasa[p].setBackground(casas.desocupada);
                owner[p] = null;
                casas.valor[p] = (float) 250;
            }
            game.j4.propriedades.clear();
        }
    }	

    public void repaintBackgrounds(){
        for(int i=0; i<38; i++){
            if(owner[i] != null){
                paineisCasa[i].setBackground(owner[i].getColor());
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