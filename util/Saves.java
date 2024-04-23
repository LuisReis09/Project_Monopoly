package util;
import java.io.*;

import objects.*;
import tabuleiro.*;

public class Saves implements Serializable{
    //Alem de salvar os objetos, o intuito eh tambem ter uma classe "bau" que guarde os objetos.

    public Jogo game;
    public transient Tabuleiro tab;
    public transient Nav nav;
    public transient Pinos p;
    public Casas c;
    
    public final static long serialVersionUID = 1;

    public Saves(){
        p = new Pinos();
        c = new Casas();
        game = new Jogo();
        tab = new Tabuleiro(c, game);
        tab.setPinos(p);
        nav = new Nav(tab);
        p.update(this);
    }

    public void initTab(){ 
        tab = new Tabuleiro(c, game);
        nav = new Nav(tab);
        nav.attTurn();
    }
}
