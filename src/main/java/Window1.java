import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kutkovetskiy on 26.04.2017.
 */
public class Window1 implements ActionListener {
    JFrame frame;
    JButton button1,leftButton;
    JPanel jPanel= new JPanel();
    JPanel jPanelBig=new JPanel();
    JPanel jPanelmin=new JPanel();

    Window1() {
        frame = new JFrame();
        button1 = new JButton("Відкрити");

        button1.addActionListener(this);
        button1.setPreferredSize(new Dimension(100,20));

        Icon leftIcon = UIManager.getIcon("OptionPane.errorIcon");
        leftButton = new JButton("Вихід");
        leftButton.setVisible(true);
        leftButton.setSize(150,50);
        leftButton.setLocation(200,250);
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

        frame.setVisible(true);
        frame.repaint();
        frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
    }
}
