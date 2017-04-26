import javax.swing.*;

/**
 * Created by kutkovetskiy on 26.04.2017.
 */
public class Window1 {
    JFrame frame;
    JButton button1,button2;
    JPanel jPanel= new JPanel();
    JPanel jPanelBig=new JPanel();

    Window1() {
        frame = new JFrame();
        button1 = new JButton("Відкрити");
        button2 = new JButton("Запрос");
    }
    void frameG () {
        frame.getContentPane().add(jPanelBig);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 700);
        frame.setVisible(true);
        frame.repaint();
        frame.setLocationRelativeTo(null);
    }

}
