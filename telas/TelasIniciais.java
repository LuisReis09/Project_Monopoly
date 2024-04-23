package telas;
import javax.swing.*;
import util.*;

public class TelasIniciais extends JFrame{
    public TelasIniciais(){
        setTitle("Monopoly");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon img = new ImageIcon("util/tstimg1.jpg");
        JLabel imagem = new JLabel(img);
        imagem.setBounds(0, 0, 1000, 800);


    }
}
