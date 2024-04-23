package tabuleiro;
import objects.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Nav{
    Tabuleiro tab;
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

                tab.verificaFalidos();
                tab.pinos.layoutPinos(); //recarrega os pinos, para diminuir o bug visual de repintar a casa
                tab.updatePropriedade();
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

    public void attTurn(){
        vez.setText("Próximo: " + tab.game.turn.getName());
    }
    
    public void showRodada(Player player, final int p, boolean preso){
        JTextArea show = new JTextArea();
        show.setBounds(0, 20, 300, 540);
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        show.setEditable(false);
        show.setLineWrap(true);
        show.setWrapStyleWord(true);
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
        
        tab.game.quantFalidos();
        if(tab.game.qfalidos == 3){
            String text = "Fim de Jogo!\n";
            text += "O jogador " + tab.game.procuraNaoFalido().getName() + " venceu!";
            show.setFont(new Font(null, Font.BOLD, 20));
            show.setText(text);

            yes.setEnabled(true);
            yes.setText("Fim de Jogo");
            rolardado.setEnabled(false);
            yes.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.exit(0);
                }
            });
            return;
        }

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

        if(p==5||p==21){
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
                    text2 += "\nNão sofre as consequências da casa";
                    text2 += "\nCapital: " + tab.game.getCapital(player);
                    text2 += "\nContinue Jogando!";
                    show.setText(text2);

                    rolardado.setEnabled(true);
                }
            });

            show.setText(text);
            return;
        }

        if(p==16||p==35){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " parou no Imposto Geral";
            text += "\nTodos os jogadores perderão $" + tab.casas.valor[p];

            yes.setEnabled(true);
            yes.setText("Cobrar Imposto Geral");
            rolardado.setEnabled(false);
            yes.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    yes.setEnabled(false);
                    String text2 = "";
                    if(tab.game.getCapital(tab.game.j1)>tab.casas.valor[p]){
                        tab.game.dimCapital(tab.casas.valor[p], tab.game.j1);
                        tab.updateCapital(tab.game.j1);
                        tab.updateBanco(tab.casas.valor[p]);
                    }else{
                        tab.game.j1.falencia = true;
                    }
                    if(tab.game.getCapital(tab.game.j2)>tab.casas.valor[p]){
                        tab.game.dimCapital(tab.casas.valor[p], tab.game.j2);
                        tab.updateCapital(tab.game.j2);
                        tab.updateBanco(tab.casas.valor[p]);
                    }else{
                        tab.game.j2.falencia = true;
                    }
                    if(tab.game.getCapital(tab.game.j3)>tab.casas.valor[p]){
                        tab.game.dimCapital(tab.casas.valor[p], tab.game.j3);
                        tab.updateCapital(tab.game.j3);
                        tab.updateBanco(tab.casas.valor[p]);
                    }else{
                        tab.game.j3.falencia = true;
                    }
                    if(tab.game.getCapital(tab.game.j4)>tab.casas.valor[p]){
                        tab.game.dimCapital(tab.casas.valor[p], tab.game.j4);
                        tab.updateCapital(tab.game.j4);
                        tab.updateBanco(tab.casas.valor[p]);
                    }else{
                        tab.game.j4.falencia = true;
                    }
        
                    text2 += "\nTodos perderam $" + tab.casas.valor[p];
                    text2 += "\nNovo Valor do Banco: " + tab.casas.valor[10];
                    text2 += "\nContinue Jogando!";
                    show.setText(text2);
                    rolardado.setEnabled(true);
                    tab.verificaFalidos();
                    tab.updatePropriedade();
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
                        text2 += "\nNovo valor do banco: " + tab.casas.valor[10];
                    }
                    tab.updateCapital(player);
                    text2 += "\nNovo Capital: " + tab.game.getCapital(player);

                    show.setText(text2);
                    rolardado.setEnabled(true);
                    tab.verificaFalidos();
                    tab.updatePropriedade();
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
                    tab.game.addCapital(tab.casas.valor[10], player);
                    tab.updateCapital(player);
                    String text2 = "";
                    text2 += player_name + " coletou $" + tab.casas.valor[10];
                    text2 += "\nNovo Capital: $" + tab.game.getCapital(player);
                    text2 += "\nContinue jogando!";
                    tab.casas.valor[10] = 0f;
                    tab.updateBanco(0);
                    show.setText(text2);
                    rolardado.setEnabled(true);
                    return;
                }
            });

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
            text += "\nNovo Valor do Banco: " + tab.casas.valor[10];
            text += "\nContinue Jogando!" + p;
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
            }else{
                text += "\n" + player_name + " não possui capital suficiente para pagar fiança";
                text += "\n" + player_name + " ficará preso";
                text += "\nCapital: " + tab.game.getCapital(player);
                yes.setEnabled(true);
                yes.setText("Ok");
                rolardado.setEnabled(false);
                yes.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        yes.setEnabled(false);
                        String text2 = "";
                        text2 += player_name + " ficará preso";
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
            text += "\n" + player_name + " pagará $500 para continuar";
            if(tab.game.getCapital(player)>500){
                tab.game.dimCapital(500, player);
                tab.updateCapital(player);
                tab.updateBanco(500);
                text += "\nCapital: " + tab.game.getCapital(player);
                text += "\nNovo valor do Banco: " + tab.casas.valor[10];
                text += "\nContinue jogando!";
            }else{
                text += "Jogador faliu!";
                player.falencia = true;
                tab.verificaFalidos();
                tab.updatePropriedade();
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

            show.setText(text);
            return;
        }

        if(p==29){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " parou na Lotérica";
            text += "\nQue sorte!";
            text += "\n" + player_name + " poderá receber uma propriedade";
            text += "\nCaso não tenha nenhuma propriedade disponível, tomará uma aleatória";

            yes.setEnabled(true);
            yes.setText("Receber Propriedade");
            rolardado.setEnabled(false);

            yes.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    yes.setEnabled(false);
                    for(int i= 0; i<38; i++){
                        if(tab.casas.title[i].getText()=="Propriedade"&&tab.owner[i]==null){
                            tab.paineisCasa[i].setBackground(player.getColor());
                            tab.owner[i] = player;
                            player.addPropriedade(i);
                            String text2 = "";
                            text2 += player_name + " recebeu a propriedade " + i;
                            text2 += "\nContinue jogando!";
                            show.setText(text2);
                            rolardado.setEnabled(true);
                            return;
                        }
                    }

                    Player p;
                    while(true){
                        p = tab.game.randomPlayer();
                        if(p!=player&&!p.propriedades.isEmpty()){
                            break;
                        }
                    }
                    
                    int x = p.randomPropriedade();
                    p.removePropriedade(x);
                    tab.paineisCasa[x].setBackground(player.getColor());
                    tab.owner[x] = player;
                    player.addPropriedade(x);
                    String text2 = "";
                    text2 += player_name + " tomou a propriedade " + x + " de " + p.getName();
                    text2 += "\nContinue jogando!";
                    show.setText(text2);
                    rolardado.setEnabled(true);
                    return;
                }
            });

            show.setText(text);
            return;
        }

        if(p==31){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " parou na casa de Assalto";
            text += "\n" + player_name + " roubará uma propriedade de outro jogador";
            text += "\nPorém, " + player_name + " irá direto para a prisão";
            text += "\nCaso não haja propriedades disponíveis para roubo, nada acontecerá";

            yes.setEnabled(true);
            yes.setText("Roubar");
            rolardado.setEnabled(false);

            yes.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    yes.setEnabled(false);
                    Player p;
                    String text2 = "";
                    if(tab.game.j1.propriedades.isEmpty()&&tab.game.j2.propriedades.isEmpty()&&tab.game.j3.propriedades.isEmpty()&&tab.game.j4.propriedades.isEmpty()){
                        text2 += player_name + " não conseguiu roubar nenhuma propriedade";
                        text2 += "\nnão possui propriedades para roubo"; 
                        show.setText(text2);
                        rolardado.setEnabled(true);
                        return;
                    }
                    while(true){
                        p = tab.game.randomPlayer();
                        if(p!=player&&!p.propriedades.isEmpty()){
                            break;
                        }

                    }
                    
                    int x = p.randomPropriedade();
                    p.removePropriedade(x);
                    tab.paineisCasa[x].setBackground(player.getColor());
                    tab.owner[x] = player;
                    player.addPropriedade(x);
                    text2 += player_name + " tomou a propriedade " + x + " de " + p.getName();
                    show.setText(text2);
                    no.setEnabled(true);
                    no.setText("Ir para Prisão");
                    no.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            no.setEnabled(false);
                            tab.attPosition(player, 19);
                            showRodada(player, 19, false);
                            
                            return;
                        }
                    });
                }
            });

            show.setText(text);
            return;
        }

        if(p==34){
            String text = player_name + " sorteou o número: " + random + ".\n\n";
            text += player_name + " parou na casa de Imposto Veicular";
            text += "\n" + player_name + " pagou 5% de imposto sobre o Capital";
            tab.updateBanco(0.05f*tab.game.getCapital(player));
            tab.game.dimCapital(tab.game.getCapital(player)*0.05f, player);
            tab.updateCapital(player);
            text += "\nNovo Capital: " + tab.game.getCapital(player);
            text += "\nNovo Valor do Banco: " + tab.casas.valor[10];
            text += "\nContinue jogando!";
            show.setText(text);      
            return;      
        }

        //Caso Geral: Cair em Propriedade
        if(true){
            String text = player_name + " sorteou o número: " + random + ".\n\n";

            rolardado.setEnabled(false);
            //Primeiro Caso: sem proprietario
            if(tab.owner[p]==null||tab.owner[p].falencia){
                text += player_name + " caiu em uma propriedade vazia\n";

                if(tab.game.getCapital(player)>tab.casas.valor[p] && player == tab.game.getTurn()){
                    text += "Deseja comprar a propriedade por " + tab.casas.valor[p] + "?\n";
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
                            tab.paineisCasa[p].setBackground(player.getColor());
                            tab.game.dimCapital(tab.casas.valor[p], player);
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
                float custo_ampliar = tab.casas.valor[p]*2;
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
                            tab.casas.valor[p] = custo_ampliar;
                            tab.casas.subtitle[p].setText("Aluguel: " + tab.casas.valor[p]);
                            text2 += player_name + " ampliou a propriedade";
                            text2 += "\nNovo Custo: " + tab.casas.valor[p];
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
                            text2 += "\nCusto: " + tab.casas.valor[p];
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
                float custo_tomar = tab.casas.valor[p] * 3;
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
                            tab.paineisCasa[p].setBackground(player.getColor());
                            String text2 = "";
                            text2 += "\n" + player_name + " tomou a propriedade de " + tab.owner[p].getName();
                            text2 += "\n" + tab.owner[p].getName() + " recebeu $" + custo_tomar + " e perdeu a propriedade.";
                            text2 += "\nNovo Capital: $" + tab.game.getCapital(player);
                            text2 += "\nContinue Jogando!";
                            tab.owner[p].removePropriedade(p);
                            player.addPropriedade(p);
                            
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
                            tab.game.dimCapital(tab.casas.valor[p], player);
                            tab.game.addCapital(tab.casas.valor[p], tab.owner[p]);
                            tab.updateCapital(player);
                            tab.updateCapital(tab.owner[p]);
                            text2 += "\nValor do Aluguel: $" + tab.casas.valor[p];
                            text2 += "\n" + player_name + " pagou aluguel a " + tab.owner[p].getName();
                            text2 += "\nCapital: $" + tab.game.getCapital(player);
                            text2 += "\nContinue Jogando!";

                            show.setText(text2);
                            rolardado.setEnabled(true);
                            return;
                        }
                    });

                }else if(tab.game.getCapital(player) > tab.casas.valor[p]){
                    text += "\nNão há capital suficiente para tomar a casa.";
                    tab.game.dimCapital(tab.casas.valor[p], player);
                    tab.game.addCapital(tab.casas.valor[p], tab.owner[p]);
                    tab.updateCapital(player);
                    tab.updateCapital(tab.owner[p]);
                    text += "\nValor do Aluguel: $" + tab.casas.valor[p];
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
                    tab.paineisCasa[x].setBackground(tab.owner[p].getColor());
                    tab.casas.valor[x] = (float) 250;
                    tab.ampliacoes[x] = 0;
                    text += "\n" + player_name + " perdeu a propriedade da casa " + x;
                    text += "\n" + tab.owner[p].getName() + " agora é proprietário da casa " + x;
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