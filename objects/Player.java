package objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.*;

public class Player implements Serializable{
    private static final long serialVersionUID = 2;

    /*
        Fornece atributos basicos que possibilitam manipular jogadores de maneira individual.
        Eh uma classe primordial, nao depende de nenhuma outra classe para funcionar.
        Eh uma das unicas classes que realmente tem seus atributos guardados ao salvar o jogo.
        Alem disso, eh tambem uma das unicas classes que nao precisam estar armazenadas na classe bau "Save".
    */

    private String name;
    protected float capital;
    Color cor;
    public int posicao = 0;
    private boolean preso = false;
    public ArrayList<Integer> propriedades = new ArrayList<Integer>();
    public boolean falencia = false;
    private int tempo_preso = 0;

    protected void setColor(int r, int g, int b){
        cor = new Color(r, g, b);
    }

    public Color getColor(){
        return cor;
    }

    public void setPosition(int n){
        posicao = n;
    }

    public void addPosition(int n){
        if(n>=1&&n<=6){
            posicao += n;
            if(posicao >= 38){
            posicao -= 38;
            }
        }
    }

    public int getPosition(){
        return posicao;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addPropriedade(int n){
        propriedades.add(n);
    }

    public void removePropriedade(int n){
        propriedades.remove(propriedades.indexOf(n));
    }

    public int randomPropriedade(){
        int n = (int)(Math.random()*propriedades.size());
        return propriedades.get(n);
    }

    public boolean getFalido(){
        return falencia;
    }

    public boolean getPreso(){
        return preso;
    }

    public int updatePreso(){
        if(preso){
            tempo_preso--;
            if(tempo_preso==0){
                preso = false;
            }
        }

        return tempo_preso+1;
    }

    public int getTempoPreso(){
        return tempo_preso;
    }

    public void setPreso(boolean v){
        if(v){
            preso= true;
            tempo_preso = 3;
        }else{
            preso = false;
            tempo_preso = 0;
        }
    }
}