package tabuleiro;
import objects.Jogo;
import javax.swing.*;
import java.awt.*;

public class Casas{

    //public final static Casas instance = new Casas();

    transient int q = 38;
    transient JPanel[] casas = new JPanel[q];
    transient Color desocupada = new Color(212, 203, 203);
    transient JLabel[] title = new JLabel[q];
    transient JLabel[] subtitle = new JLabel[q];
    transient JLabel[] indices = new JLabel[q];
    Jogo game;
    //float[] valor = new float[q];
    /* O valor das casas precisa ser gravado aqui, porque algumas delas, como o Banco,
    possuem valores que podem ser alterados durante o jogo e nao dependem de classes externas. 
    Logo, um metodo de reload nao seria coerente. 
    Porem, pude definir as outras como transient, para reduzir a necessidade da gravacao de dados e
    o risco de incompatibilidade entre versoes do swing.*/

    public Casas(Jogo game){
        this.game = game;
        initCasas();
        configCasas();
        defineFunctions();
    }

    public void initCasas(){
        for(int i= 0; i<q; i++){
            casas[i] = new JPanel();
            title[i] = new JLabel();
            subtitle[i] = new JLabel();
            indices[i] = new JLabel();
        }
    }

    public void configCasas(){
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
    }    

    public void defineFunctions(){
        Font titulos = new Font("Times New Roman", Font.BOLD, 12);

        
        title[0] = new JLabel("INICIO");
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
        subtitle[1].setText("Aluguel: " + game.valor[1]);
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
        subtitle[2].setText("Aluguel: " + game.valor[2]);
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
        subtitle[4].setText("Aluguel: " + game.valor[4]);
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
        subtitle[6].setText("Aluguel: " + game.valor[6]);
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
        game.valor[7]= (float) 0.00;
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
        subtitle[8].setText("Aluguel: " + game.valor[8]);
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
        subtitle[9].setText("Aluguel: " + game.valor[9]);
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
        subtitle[10]= new JLabel("Valor: " + game.valor[7]);
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
        subtitle[11].setText("Aluguel: " + game.valor[11]);
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
        subtitle[12].setText("Aluguel: " + game.valor[12]);
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
        subtitle[13] = new JLabel("Valor: 15%");
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
        subtitle[14].setText("Aluguel: " + game.valor[14]);
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
        subtitle[15].setText("Aluguel: " + game.valor[15]);
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
        game.valor[16] = (float) 400.00;
        subtitle[16] = new JLabel("Valor: $" + game.valor[16]);
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
        subtitle[17].setText("Aluguel: " + game.valor[17]);
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
        subtitle[18].setText("Aluguel: " + game.valor[18]);
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
        subtitle[20].setText("Aluguel: " + game.valor[20]);
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
        subtitle[22].setText("Aluguel: " + game.valor[22]);
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
        game.valor[24]= (float) 500.00;
        subtitle[24] = new JLabel("Valor: " + game.valor[24]);
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
        subtitle[25] = new JLabel("Valor: " + game.valor[25]);
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
        subtitle[26].setText("Aluguel: " + game.valor[26]);
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
        subtitle[27].setText("Aluguel: " + game.valor[27]);
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
        subtitle[28].setText("Aluguel: " + game.valor[28]);
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
        subtitle[30].setText("Aluguel: " + game.valor[30]);
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
        subtitle[32].setText("Aluguel: " + game.valor[32]);
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
        subtitle[33].setText("Aluguel: " + game.valor[33]);
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
        game.valor[34]= (float) 400.00;
        subtitle[34].setText("Valor: 10%");
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
        game.valor[35] = (float) 500.00;
        subtitle[35] = new JLabel("Valor: " + game.valor[35]);
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
        subtitle[36].setText("Aluguel: " + game.valor[36]);
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
        subtitle[37].setText("Aluguel: " + game.valor[37]);
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

    public JLabel[] getTitles(){
        return title;
    }

    public JLabel[] getSubtitles(){
        return subtitle;
    }

    public float[] getValores(){
        return game.valor;
    }

    public JPanel[] getCasas(){
        return casas;
    }
}