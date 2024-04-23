package util;
import java.io.*;

import objects.*;
import tabuleiro.*;

public class Saves implements Serializable{
    //Alem de salvar os objetos, o intuito eh tambem ter uma classe "bau" que guarde os objetos.
    //Assim, a main nao precisara administrar nenhum uso de objeto
    //Dessa forma, o foco da main sera apenas a troca de telas

    public Jogo game;
    public Casas c;
    public transient Tabuleiro tab;
    public transient Nav nav;
    public transient Pinos p;
    
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
        p = new Pinos(); 
        tab = new Tabuleiro(c, game);
        nav = new Nav(tab);
        tab.setPinos(p);
        p.update(this);
        nav.attTurn();

    }
}
