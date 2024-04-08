package objects;
import java.io.Serializable;

public class Jogo implements Serializable{
    private static final long serialVersionUID = 1;
    
    public Player j1 = new Player();
    public Player j2 = new Player();
    public Player j3 = new Player();
    public Player j4 = new Player();

    public Player turn = j1;

    public Jogo(){
        //algumas inicializoes padroes
        j1.setName("Player1");
        j2.setName("Player2");
        j3.setName("Player3");
        j4.setName("Player4");

        j1.setColor(238, 245, 54);
        j2.setColor(59, 245, 59);
        j3.setColor(62, 212, 250);
        j4.setColor(252, 73, 130);

        j1.capital = (float) 8000;
        j2.capital = (float) 8000;
        j3.capital = (float) 8000;
        j4.capital = (float) 8000;
    }

    public String Str_Players(){
        return j1.getName() + ", " + j2.getName() + ", " + j3.getName() + ", " + j4.getName();
    }

    public void nextTurn(){
        if(turn == j1){
            if(!j2.getFalido())
                turn = j2;
            else if(!j3.getFalido())
                turn = j3;
            else if(!j4.getFalido())
                turn = j4;
        }else if(turn == j2){
            if(!j3.getFalido())
                turn = j3;
            else if(!j4.getFalido())
                turn = j4;
            else if(!j1.getFalido())
                turn = j1;
        }else if(turn == j3){	
            if(!j4.getFalido())
                turn = j4;
            else if(!j1.getFalido())
                turn = j1;
            else if(!j2.getFalido())
                turn = j2;
        }else if(turn == j4){
            if(!j1.getFalido())
                turn = j1;
            else if(!j2.getFalido())
                turn = j2;
            else if(!j3.getFalido())
                turn = j3;
        }


    }

    public Player getTurn(){
        if(turn == j1)
            return j1;

        if(turn == j2)
            return j2;

        if(turn == j3)
            return j3;

        if(turn == j4)
            return j4;

        return null;
    }

    public float getCapital(Player pl){
        if(pl == j1){
            return j1.capital;
        }else if(pl == j2){
            return j2.capital;
        }else if(pl == j3){
            return j3.capital;
        }else if(pl == j4){
            return j4.capital;
        }

        return (float) 0.0;
    }

    public void addCapital(float valor, Player pl){
        if(pl == j1){
            j1.capital += valor;
        }else if(pl == j2){
            j2.capital += valor;
        }else if(pl == j3){
            j3.capital += valor;
        }else if(pl == j4){
            j4.capital += valor;
        }
    }

    public void dimCapital(float valor, Player pl){
        if(pl == j1){
            j1.capital -= valor;
            if(j1.capital<=0){
                j1.falencia = true;
            }
            return;
        }else if(pl == j2){
            j2.capital -= valor;
            if(j2.capital<=0){
                j2.falencia = true;
            }
            return;
        }else if(pl == j3){
            j3.capital -= valor;
            if(j3.capital<=0){
                j3.falencia = true;
            }
            return;
        }else if(pl == j4){
            j4.capital -= valor;
            if(j4.capital<=0){
                j4.falencia = true;
            }
            return;
        }
    }
}