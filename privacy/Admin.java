package privacy;
import tabuleiro.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Admin {
    JPanel panel = new JPanel();
    JButton entrar = new JButton("Entrar");
    JFrame frame = new JFrame("Admin");
    JFrame frame_alterar = new JFrame("Admin - Alterar jogador");
    private final String login = "admin";
    private final String senha = "monopoly.admin";
    Tabuleiro tab;

    public Admin(Tabuleiro tab){
        this.tab = tab;
    }

    public JPanel getJPanel(){
        panel.setLayout(null);
        panel.setSize(300, 50);

        JButton admin_button = new JButton("Admin");
        admin_button.setBounds(0, 0, 300, 37);
        admin_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setSize(500, 300);
                frame.setVisible(true);
                frame.setLayout(null);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setBackground(Color.BLACK);
                
                JLabel login_label = new JLabel("Login:");
                login_label.setBounds(100, 50, 50, 30);

                JTextField login_field = new JTextField();
                login_field.setBounds(150, 50, 200, 30);

                JLabel senha_label = new JLabel("Senha:");
                senha_label.setBounds(100, 100, 50, 30);
                
                JPasswordField senha_field = new JPasswordField();
                senha_field.setBounds(150, 100, 200, 30);
                
                entrar.setBounds(200, 150, 100, 30);
                
                entrar.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String senha_digitada = new String(senha_field.getPassword());
                        //System.out.println("Login: " + login_field.getText() + "\n" + "Senha: " +  senha_digitada);
                        if(login_field.getText().equals(login) && senha_digitada.equals(senha)){
                            entrar.setEnabled(false);
                            frame.dispose();
                            frame_alterar.setSize(500, 400);
                            frame_alterar.setVisible(true);
                            frame_alterar.setLayout(null);
                            frame_alterar.setResizable(false);
                            frame_alterar.setLocationRelativeTo(null);
                            frame_alterar.setBackground(Color.BLACK);

                            JLabel label = new JLabel("Bem vindo, admin!");
                            label.setBounds(0, 10, 500, 30);
                            label.setHorizontalAlignment(SwingConstants.CENTER);
                            label.setFont(new Font("Arial", Font.BOLD, 30));
                            
                            JLabel label_askplayer = new JLabel("Alterar Jogador:");
                            label_askplayer.setBounds(55, 80, 150, 30);

                            JComboBox<String> players = new JComboBox<String>();
                            players.setBounds(200, 80, 100, 30);
                            players.addItem(tab.game.j1.getName());
                            players.addItem(tab.game.j2.getName());
                            players.addItem(tab.game.j3.getName());
                            players.addItem(tab.game.j4.getName());
                            
                            JLabel capital_label = new JLabel("Capital:");
                            capital_label.setBounds(100, 130, 100, 30);

                            JLabel position_label = new JLabel("Posição:");
                            position_label.setBounds(100, 180, 100, 30);

                            JTextField capital = new JTextField("0.0");
                            capital.setBounds(200, 130, 100, 30);
                            
                            JTextField position = new JTextField("0");
                            position.setBounds(200, 180, 100, 30);

                            JLabel name_label = new JLabel("Nome:");
                            name_label.setBounds(100, 230, 100, 30);

                            JTextField name = new JTextField("name");
                            name.setBounds(200, 230, 100, 30);

                            JButton set = new JButton("Alterar");
                            set.setBounds(200, 300, 100, 30);

                            set.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    String player = (String) players.getSelectedItem();
                                    
                                    if(player.equals(tab.game.j1.getName())){
                                        tab.game.setCapital(Float.parseFloat(capital.getText()), tab.game.j1);
                                        tab.game.j1.setPosition(Integer.parseInt(position.getText()));
                                        tab.attPosition(tab.game.j1, tab.game.j1.getPosition());
                                        tab.updateCapital(tab.game.j1);
                                        tab.game.j1.setName(name.getText());
                                        tab.name_j1.setText(tab.game.j1.getName());

                                    }else if(player.equals(tab.game.j2.getName())){
                                        tab.game.setCapital(Float.parseFloat(capital.getText()), tab.game.j2);
                                        tab.game.j2.setPosition(Integer.parseInt(position.getText()));
                                        tab.attPosition(tab.game.j2, tab.game.j2.getPosition());
                                        tab.updateCapital(tab.game.j2);
                                        tab.game.j2.setName(name.getText());
                                        tab.name_j2.setText(tab.game.j2.getName());

                                    }else if(player.equals(tab.game.j3.getName())){
                                        tab.game.setCapital(Float.parseFloat(capital.getText()), tab.game.j3);
                                        tab.game.j3.setPosition(Integer.parseInt(position.getText()));
                                        tab.attPosition(tab.game.j3, tab.game.j3.getPosition());
                                        tab.updateCapital(tab.game.j3);
                                        tab.game.j3.setName(name.getText());
                                        tab.game.j3.setName(name.getText());

                                    }else if(player.equals(tab.game.j4.getName())){
                                        tab.game.setCapital(Float.parseFloat(capital.getText()), tab.game.j4);
                                        tab.game.j4.setPosition(Integer.parseInt(position.getText()));
                                        tab.attPosition(tab.game.j4, tab.game.j4.getPosition());
                                        tab.updateCapital(tab.game.j4);
                                        tab.game.j4.setName(name.getText());
                                        tab.game.j4.setName(name.getText());
                                    }
                                    
                                    capital.setText("");
                                    position.setText("");
                                    login_field.setText("");
                                    frame_alterar.dispose();
                                    entrar.setEnabled(true);
                                    tab.verificaFalidos();
                                }
                            });

                            frame_alterar.add(label);
                            frame_alterar.add(label_askplayer);
                            frame_alterar.add(name_label);
                            frame_alterar.add(players);
                            frame_alterar.add(capital);
                            frame_alterar.add(position);
                            frame_alterar.add(name);
                            frame_alterar.add(capital_label);
                            frame_alterar.add(position_label);
                            frame_alterar.add(set);
                            frame_alterar.revalidate();
                            frame_alterar.repaint();
                            
                        }
                    }
                });
                
                frame.add(login_label);
                frame.add(login_field);
                frame.add(senha_label);
                frame.add(senha_field);
                frame.add(entrar);
                frame.repaint();
            }
        });

        panel.add(admin_button);
        return panel;
    }
}
