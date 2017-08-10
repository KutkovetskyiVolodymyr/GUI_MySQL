import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kutkovetskiy on 26.04.2017.
 * Вікно авторизації
 */
public class Window1 implements ActionListener {
   
    JFrame frame;
    JButton button1,leftButton;
    JPanel jPanel;
    JPanel jPanelBig;
    JPanel jPanelmin=new JPanel();
    JTextField tf1, tf2,tf4,tf5;
    JPasswordField tf3;
    Icon leftIcon
    
    Window1() {
        
        frame = new JFrame();
        jPanel= new JPanel();
        jPanelBig=new JPanel();
        button1 = new JButton("Відкрити");
        button1.addActionListener(this);

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JPasswordField();
        tf4 = new JTextField();
        tf5 = new JTextField();

        leftIcon = UIManager.getIcon("OptionPane.errorIcon");
        leftButton = new JButton("Вихід");
        leftButton.setVisible(true);
        leftButton.setIcon(leftIcon);
        leftButton.addActionListener(this);

        jPanel.setLayout(null);

        jPanelBig.setLayout(new BorderLayout());
        jPanelBig.add(jPanelmin,BorderLayout.NORTH);
        jPanelBig.add(jPanel,BorderLayout.CENTER);

    }
    void frameG () {
        frame.getContentPane().add(jPanelBig);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 700);

        jPanelmin.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("database (1).jpg"))));
        jPanel.add(leftButton);
        jPanel.add(button1);
        jPanel.add(tf1);
        jPanel.add(tf2);
        jPanel.add(tf3);
        jPanel.add(tf4);
        jPanel.add(tf5);

        frame.setVisible(true);
        frame.repaint();
        frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
    }
}
