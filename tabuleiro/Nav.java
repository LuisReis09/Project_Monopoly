package tabuleiro;
import objects.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//import java.awt.event.*;

public class Nav {
    Tabuleiro tab = new Tabuleiro(null);
    JLabel vez = new JLabel();
    JButton rolardado = new JButton("Rolar o Dado");
    JPanel rodada = new JPanel();
    int random;
    JButton yes = new JButton("yes");
    JButton no = new JButton("no");

    public Nav(Tabuleiro tab){
        this.tab = tab;
    }

    public JPanel getPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 300, 650);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.setLayout(null);

        updateTurn();
        vez.setBounds(0, 0, 300, 50);
        vez.setFont(new Font("Arial", Font.BOLD, 20));
        vez.setHorizontalAlignment(SwingConstants.CENTER);
        
        rodada.setBounds(0, 100, 300, 550);
        rodada.setBackground(Color.BLACK);
        yes.setBounds(50, 500, 100, 80);
        no.setBounds(150, 500, 100, 80);
        
        rolardado.setBounds(50, 50, 200, 50);
        rolardado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                random = tab.randomNumber();
                if(tab.game.getTurn().getPreso() == false){
                    tab.attPosition(tab.game.getTurn(), random);
                }
                showRodada(tab.game.getTurn(), tab.game.getTurn().getPosition(), tab.game.getTurn().getPreso());

                
                tab.updateFalido();
                tab.game.nextTurn();
                updateTurn();
            }
        });
        
        panel.add(rodada);
        panel.add(rolardado);
        panel.add(vez);
        return panel;
    }
    
    public void updateTurn(){
        vez.setText("Próximo: " + tab.game.turn.getName());
    }
    
    public void showRodada(Player player, final int p, boolean preso){
        JTextArea show = new JTextArea();
        show.setBounds(0, 10, 300, 540);
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        show.setEditable(false);
        show.setLineWrap(true);
        rodada.removeAll();
        resetButtons();
        rodada.add(show);
        rodada.revalidate();
        String player_name = player.getName();
        
        yes.setEnabled(false);
        no.setEnabled(false);
        if(yes.getActionListeners().length > 0)
            yes.removeActionListener(yes.getActionListeners()[0]);

        if(no.getActionListeners().length > 0)
            no.removeActionListener(no.getActionListeners()[0]);

        if(preso){
            String text = player_name + " está preso por mais " + player.updatePreso() + " rodadas";
            text += "\nNão pode jogar nessa rodada";
            show.setText(text);
            return;
        }

        if(p==0){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " caiu na casa inicial, não acontece nada.";
            text += "\nCapital: " + tab.game.getCapital(player);
            text += "\nContinue Jogando!" + p;
            show.setText(text);
            return;
        }

        if(p==3){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " parou no Parque Público";
            text += "\nNão há consequência";
            text += "\nRelaxe e aproveite a estadia!";
            text += "\nEnquanto prepara-se e cria novas estratégias";
            show.setText(text);
            return;
        }

        if(p==5||p==35||p==21){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += "\n" + player_name + " caiu no Redemoinho";
            text += "\n" + player_name + " será redirecionado pra uma casa aleatória";
            text += "\n" + player_name + " não poderá jogar nessa rodada";
            text += "\nPorém, não sofrerá consequências de onde cair";
            rolardado.setEnabled(false);
            yes.setEnabled(true);
            yes.setText("Ok!");

            yes.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    yes.setEnabled(false);
                    int x = tab.randomSwirl();
                    tab.attPositionbySwirl(player, x);
                    String text2 = "";
                    text2 += player_name + " foi redirecionado para a casa " + x;
                    text2 += "\nCapital: " + tab.game.getCapital(player);
                    text2 += "\nContinue Jogando!";
                    show.setText(text2);

                    rolardado.setEnabled(true);
                }
            });

            show.setText(text);
            return;
        }

        if(p==16){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " parou no Imposto Geral";
            text += "\nTodos os jogadores perderão $200";

            yes.setEnabled(true);
            yes.setText("Cobrar Imposto Geral");
            rolardado.setEnabled(false);
            yes.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    yes.setEnabled(false);
                    String text2 = "";
                    if(tab.game.getCapital(tab.game.j1)>200){
                        tab.game.dimCapital(200, tab.game.j1);
                        tab.updateCapital(tab.game.j1);
                        tab.updateBanco(200);
                    }else{
                        tab.game.j1.falencia = true;
                    }
                    if(tab.game.getCapital(tab.game.j2)>200){
                        tab.game.dimCapital(200, tab.game.j2);
                        tab.updateCapital(tab.game.j2);
                        tab.updateBanco(200);
                    }else{
                        tab.game.j2.falencia = true;
                    }
                    if(tab.game.getCapital(tab.game.j3)>200){
                        tab.game.dimCapital(200, tab.game.j3);
                        tab.updateCapital(tab.game.j3);
                        tab.updateBanco(200);
                    }else{
                        tab.game.j3.falencia = true;
                    }
                    if(tab.game.getCapital(tab.game.j4)>200){
                        tab.game.dimCapital(200, tab.game.j4);
                        tab.updateCapital(tab.game.j4);
                        tab.updateBanco(200);
                    }else{
                        tab.game.j4.falencia = true;
                    }
        
                    text2 += "\nTodos perderam $200";
                    text2 += "\nNovo Valor do Banco: " + tab.valor[10];
                    text2 += "\nContinue Jogando!";
                    show.setText(text2);
                    rolardado.setEnabled(true);
                    return;
                }
            });

            show.setText(text);
            return;
        }

        if(p==7||p==23){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += "\n" + player_name + " caiu no [Sorte ou Azar?]";
            text += "\n" + player_name + " poderá ganhar ou perder 5% de seu Capital";
            text += "\nCruze os dedos!";
            yes.setEnabled(true);
            yes.setText("Sortear!");
            rolardado.setEnabled(false);

            yes.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    yes.setEnabled(false);
                    int sorteio = (int) (Math.random()*2);
                    boolean lucky = (sorteio==1);
                    String text2 = "";
                    float cpt = tab.game.getCapital(player);
                    if(lucky){
                        tab.game.addCapital(0.05f*cpt, player);
                        text2 += player_name + " ganhou 5% de seu capital";
                        text2 += "\nParabéns!";
                    }else{
                        tab.game.dimCapital(0.05f*cpt, player);
                        text2 += player_name + " perdeu 5% de seu capital";
                        tab.updateBanco(0.05f*cpt);
                        text2 += "\nAh, não! Mais sorte na próxima!";
                    }
                    tab.updateCapital(player);
                    text2 += "\nNovo valor do banco: " + tab.valor[10];
                    text2 += "\nNovo Capital: " + tab.game.getCapital(player);

                    show.setText(text2);
                    rolardado.setEnabled(true);
                    return;
                }
            });

            show.setText(text);
            return;
        }

        if(p==10){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += "Que sorte! " + player_name + " parou no BANCO!";
            text += "\n" + player_name + " poderá coletar todo o dinheiro do Banco";
            text += "\nSem consequências, muita sorte!";

            yes.setEnabled(true);
            yes.setText("Coletar");
            rolardado.setEnabled(false);

            yes.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    tab.game.addCapital(tab.valor[10], player);
                    tab.updateCapital(player);
                    String text2 = "";
                    text2 += player_name + " coletou $" + tab.valor[10];
                    text2 += "\nNovo Capital: $" + tab.game.getCapital(player);
                    text2 += "\nContinue jogando!";
                    tab.valor[10] = 0f;
                    tab.updateBanco(0);
                    show.setText(text2);
                    rolardado.setEnabled(true);
                    return;
                }
            });

            show.setText(text);
            return;
        }

        if(p==19){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " parou na PRISÃO";
            text += "\n" + player_name + " ficará preso por 3 rodadas";
            player.setPreso(true);
            text += "\nRodada atual: " + player.updatePreso();
            if(tab.game.getCapital(player)>1000){
                text += "\nPorém, " + player_name + " poderá pagar $1000 para sair da prisão";
                yes.setEnabled(true);
                yes.setText("Pagar");
                no.setEnabled(true);
                no.setText("Não Pagar");
                rolardado.setEnabled(false);
                yes.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        yes.setEnabled(false);
                        no.setEnabled(false);
                        tab.game.dimCapital(1000, player);
                        tab.updateBanco(1000);
                        tab.updateCapital(player);
                        player.setPreso(false);
                        String text2 = "";
                        text2 += player_name + " pagou $1000 e saiu da prisão";
                        text2 += "\nO banco recebeu $1000";
                        text2 += "\nNovo Capital: " + tab.game.getCapital(player);
                        text2 += "\nContinue jogando!";
                        show.setText(text2);
                        rolardado.setEnabled(true);
                        return;
                    }
                });
                no.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        yes.setEnabled(false);
                        no.setEnabled(false);
                        String text2 = "";
                        text2 += player_name + " não pagou e ficará preso";
                        text2 += "\nCapital: " + tab.game.getCapital(player);
                        text2 += "\nContinue jogando!";
                        show.setText(text2);
                        rolardado.setEnabled(true);
                        return;
                    }
                });
            }

            show.setText(text);
            return;
        }

        if(p==24){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " parou no Pedágio";
            text += "\n" + player_name + " pagará $250 para continuar";
            if(tab.game.getCapital(player)>250){
                tab.game.dimCapital(250, player);
                tab.updateCapital(player);
                tab.updateBanco(250);
                text += "\nCapital: " + tab.game.getCapital(player);
                text += "\nNovo valor do Banco: " + tab.valor[10];
                text += "\nContinue jogando!";
            }else{
                text += "Jogador faliu!";
                player.falencia = true;
            }

            show.setText(text);
            return;
        }

        if(p==25){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " parou na casa de Cobrança";
            text += "\n" + player_name + " poderá cobrar $250 de cada jogador";
            text += "\nCada jogador pagará $250 para " + player_name;

            yes.setEnabled(true);
            yes.setText("Cobrar!");
            rolardado.setEnabled(false);

            yes.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    yes.setEnabled(false);
                    if(tab.game.j1!=player){
                        if(tab.game.getCapital(tab.game.j1)>250){
                            tab.game.dimCapital(250, tab.game.j1);
                            tab.updateCapital(tab.game.j1);
                        }else{
                            tab.game.j1.falencia = true;
                        }
                    }
                    if(tab.game.j2!=player){
                        if(tab.game.getCapital(tab.game.j2)>250){
                            tab.game.dimCapital(250, tab.game.j2);
                            tab.updateCapital(tab.game.j2);
                        }else{
                            tab.game.j2.falencia = true;
                        }
                    }
                    if(tab.game.j3!=player){
                        if(tab.game.getCapital(tab.game.j3)>250){
                            tab.game.dimCapital(250, tab.game.j3);
                            tab.updateCapital(tab.game.j3);
                        }else{
                            tab.game.j3.falencia = true;
                        }
                    }
                    if(tab.game.j4!=player){
                        if(tab.game.getCapital(tab.game.j4)>250){
                            tab.game.dimCapital(250, tab.game.j4);
                            tab.updateCapital(tab.game.j4);
                        }else{
                            tab.game.j4.falencia = true;
                        }
                    }
                    tab.game.addCapital(750, player);
                    String text2 = "";
                    text2 += player_name + " cobrou $250 de cada jogador";
                    text2 += "\nNovo Capital: " + tab.game.getCapital(player);
                    text2 += "\nContinue jogando!";
                    show.setText(text2);
                    rolardado.setEnabled(true);
                    return;
                }
            });
        }

        if(((p>=1&&p<=9)&&p!=3&&p!=5&&p!=7)||p==11||p==12||p==14||p==15||p==17||p==18){
            String text = player_name + " sorteou o número: " + random + ".\n\n";

            rolardado.setEnabled(false);
            //Primeiro Caso: sem proprietario
            if(tab.owner[p]==null){
                text += player_name + " caiu em uma propriedade vazia\n";

                if(tab.game.getCapital(player)>tab.valor[p] && player == tab.game.getTurn()){
                    text += "Deseja comprar a propriedade por " + tab.valor[p] + "?\n";
                    yes.setEnabled(true);
                    no.setEnabled(true);
                    yes.setText("Comprar");
                    no.setText("Não Comprar");
                    show.setText(text);
                    yes.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            yes.setEnabled(false);
                            no.setEnabled(false);
                            String text2 = "";
                            text2 += player_name + " comprou a propriedade.";
                            tab.casas[p].setBackground(player.getColor());
                            tab.game.dimCapital(tab.valor[p], player);
                            tab.updateCapital(player);
                            tab.owner[p] = player;
                            text2 += "\nCapital: " + tab.game.getCapital(player);
                            text2 += "\nContinue jogando!";
                            player.addPropriedade(p);

                            show.setText(text2);
                            rolardado.setEnabled(true);
                            return;
                        }
                    });
                    no.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            yes.setEnabled(false);
                            no.setEnabled(false);
                            String text2 = "";
                            text2 += player_name + " não comprou a propriedade.\n";
                            text2 += "Capital: " + tab.game.getCapital(player);
                            text2 += "\nContinue jogando!";
                            show.setText(text2);
                            rolardado.setEnabled(true);
                            return;
                        }
                    });

                }else{
                    text += "Capital insuficiente para comprar a propriedade.\n";
                    text += "Continue jogando!";
                    rolardado.setEnabled(true);
                    show.setText(text);
                    return;
                }
            }
            
            //Segundo Caso: O proprietario eh o jogador
            if(tab.owner[p]==player){
                text += player_name + " parou em sua própria propriedade";
                float custo_ampliar = tab.valor[p]*2;
                if(tab.game.getCapital(player)>custo_ampliar&&tab.ampliacoes[p]<5){
                    rolardado.setEnabled(false);
                    text += "\nDeseja ampliar a propriedade por $" + custo_ampliar + " ?";
                    yes.setEnabled(true);
                    no.setEnabled(true);
                    yes.setText("Ampliar");
                    no.setText("Não Ampliar");
                    yes.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            yes.setEnabled(false);
                            no.setEnabled(false);
                            String text2 = "";
                            tab.game.dimCapital(custo_ampliar, player);
                            tab.updateCapital(player);
                            tab.valor[p] = custo_ampliar;
                            tab.updatePropriedade(p);
                            text2 += player_name + " ampliou a propriedade";
                            text2 += "\nNovo Custo: " + tab.valor[p];
                            text2 += "\nCapital: " + tab.game.getCapital(player);
                            text2 += "Continue jogando!";
                            tab.ampliacoes[p]++;

                            show.setText(text2);
                            rolardado.setEnabled(true);
                            return;
                        }
                    });

                    no.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            yes.setEnabled(false);
                            no.setEnabled(false);
                            String text2 = "";
                            text2 += player_name + " não ampliou sua propriedade";
                            text2 += "\nCusto: " + tab.valor[p];
                            text2 += "\nCapital: " + tab.game.getCapital(player);
                            text2 += "\nContinue jogando!";

                            show.setText(text2);
                            rolardado.setEnabled(true);
                            return;
                        }
                    });
                }else{
                    text += "\nNão há capital suficiente para ampliar";
                    text += "\nOu já não é mais possível ampliar";
                    text += "\nContinue Jogando!";
                    rolardado.setEnabled(true);
                    show.setText(text);
                    return;
                }
            }

            //Terceiro Caso: O proprietario eh outro jogador
            if(tab.owner[p] != player && tab.owner[p] != null){
                text += player_name + " parou na propriedade de " + tab.owner[p].getName();

                //Condicao 1: Poder tomar a casa
                float custo_tomar = tab.valor[p] * 3;
                if(tab.game.getCapital(player) > custo_tomar){
                    rolardado.setEnabled(false);
                    text += "\n" + player_name + " possui capital suficiente para tomar a casa\n";
                    text += "\nDeseja comprar a casa de " + tab.owner[p].getName() + " por $" + custo_tomar + " ?";
                    yes.setEnabled(true);
                    no.setEnabled(true);
                    yes.setText("Tomar Casa");
                    no.setText("Não Tomar");
                    yes.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            yes.setEnabled(false);
                            no.setEnabled(false);
                            tab.game.dimCapital(custo_tomar, player);
                            tab.game.addCapital(custo_tomar, tab.owner[p]);
                            tab.updateCapital(player);
                            tab.updateCapital(tab.owner[p]);
                            tab.casas[p].setBackground(player.getColor());
                            String text2 = "";
                            text2 += "\n" + player_name + " tomou a propriedade de " + tab.owner[p].getName();
                            text2 += "\n" + tab.owner[p].getName() + " recebeu $" + custo_tomar + " e perdeu a propriedade.";
                            text2 += "\nNovo Capital: $" + tab.game.getCapital(player);
                            text2 += "\nContinue Jogando!";
                            player.addPropriedade(p);
                            tab.owner[p].removePropriedade(p);
                            
                            tab.owner[p] = player;
                            show.setText(text2);
                            rolardado.setEnabled(true);
                            return;
                        }
                    });

                    no.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            yes.setEnabled(false);
                            no.setEnabled(false);
                            String text2 = "";
                            text2 += "\n" + player_name + " não comprou a propriedade de " + tab.owner[p].getName();
                            tab.game.dimCapital(tab.valor[p], player);
                            tab.game.addCapital(tab.valor[p], tab.owner[p]);
                            tab.updateCapital(player);
                            tab.updateCapital(tab.owner[p]);
                            text2 += "\nValor do Aluguel: $" + tab.valor[p];
                            text2 += "\n" + player_name + " pagou aluguel a " + tab.owner[p].getName();
                            text2 += "\nCapital: $" + tab.game.getCapital(player);
                            text2 += "\nContinue Jogando!";

                            show.setText(text2);
                            rolardado.setEnabled(true);
                            return;
                        }
                    });

                }else if(tab.game.getCapital(player) > tab.valor[p]){
                    text += "\nNão há capital suficiente para tomar a casa.";
                    tab.game.dimCapital(tab.valor[p], player);
                    tab.game.addCapital(tab.valor[p], tab.owner[p]);
                    tab.updateCapital(player);
                    tab.updateCapital(tab.owner[p]);
                    text += "\nValor do Aluguel: $" + tab.valor[p];
                    text += "\n" + player_name + " pagou aluguel a " + tab.owner[p].getName();
                    text += "\nCapital: $" + tab.game.getCapital(tab.owner[p]);
                    text += "\nContinue Jogando!";
                    rolardado.setEnabled(true);

                }else if(!player.propriedades.isEmpty()){
                    text += "\nNão há capital suficiente para tomar a casa.";
                    text += "\nNão há capital suficiente para pagar aluguel.";
                    text += "\n" + player_name + " terá de ceder uma propriedade para quitar o aluguel.";
                    int x = player.randomPropriedade();
                    player.removePropriedade(x);
                    tab.casas[x].setBackground(tab.owner[p].getColor());
                    text += "\n" + player_name + " perdeu a propriedade da casa " + x;
                    text += "\n" + tab.owner[p] + " agora é proprietário da casa " + x;
                    text += "\nContinue Jogando!";

                    tab.owner[x] = tab.owner[p];
                    tab.owner[p].addPropriedade(x);
                    rolardado.setEnabled(true);

                }else{
                    text += "\nJogador faliu!";
                    player.falencia = true;
                }
            }

            show.setText(text);
            return;
        }
        
        
        
        if(p==13){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " caiu na casa de imposto de renda, perdeu 10% do seu capital.";
            float value = tab.game.getCapital(player)*0.1f;
            tab.game.dimCapital(value, player);
            tab.updateCapital(player);
            tab.updateBanco(value);
            text += "\nCapital: " + tab.game.getCapital(player);
            text += "\nNovo Valor do Banco: " + tab.valor[10];
            text += "\nContinue Jogando!" + p;
            show.setText(text);
            return;
        }

    }

    public void resetButtons(){
        yes.setBounds(50, 500, 100, 80);
        no.setBounds(150, 500, 100, 80);
        yes.setText("Yes");
        no.setText("No");
        rodada.add(yes);
        rodada.add(no);
    }
}