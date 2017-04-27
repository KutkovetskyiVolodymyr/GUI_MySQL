import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by kutkovetskiy on 26.04.2017.
 */
public class Window1 implements ActionListener {
    JFrame frame;
    JButton button1,leftButton;
    JPanel jPanel= new JPanel();
    JPanel jPanelBig=new JPanel();
    JPanel jPanelmin=new JPanel();JLabel jl1,jl2,jl3,jl4,jl5;
    Boolean conect=false;
    JTextField tf1, tf2,tf4,tf5;
    JPasswordField tf3;
    SQL sql;
    Window1() {
        frame = new JFrame();
        button1 = new JButton("Відкрити");
        button1.addActionListener(this);
        button1.setSize(100,50);
        button1.setLocation(50,250);

        tf1 = new JTextField();
        tf1.setSize(170,25);
        tf1.setLocation(250,1);

        tf2 = new JTextField();
        tf2.setSize(170,25);
        tf2.setLocation(250,50);

        tf3 = new JPasswordField();
        tf3.setSize(170,25);
        tf3.setLocation(250,100);

        tf4 = new JTextField();
        tf4.setSize(170,25);
        tf4.setLocation(250,150);

        tf5 = new JTextField();
        tf5.setSize(170,25);
        tf5.setLocation(250,200);

        jl1 = new JLabel("IP");
        jl1.setSize(170,25);
        jl1.setLocation(100,1);

        jl2 = new JLabel("Логін");
        jl2.setSize(170,25);
        jl2.setLocation(100,50);

        jl3 = new JLabel("Пароль");
        jl3.setSize(170,25);
        jl3.setLocation(100,100);

        jl4 = new JLabel("Порт");
        jl4.setSize(170,25);
        jl4.setLocation(100,150);

        jl5 = new JLabel("Імя БД");
        jl5.setSize(170,25);
        jl5.setLocation(100,200);

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
        jPanel.add(tf1);
        jPanel.add(tf2);
        jPanel.add(tf3);
        jPanel.add(tf4);
        jPanel.add(tf5);

        jPanel.add(jl1);
        jPanel.add(jl2);
        jPanel.add(jl3);
        jPanel.add(jl4);
        jPanel.add(jl5);

        frame.setVisible(true);
        frame.repaint();
        frame.setLocationRelativeTo(null);
    }
    public void scan(){
        String ip=tf1.getText();;
        String user1 =tf2.getText();
        String password1 =tf3.getText();
        String port1 =tf4.getText();
        String namebd =tf5.getText();
        sql= new SQL(ip,port1,user1, password1,namebd);//
        sql.mySQLConect();
        if(sql.con!=null) {
            conect=true;
        }
        else
            conect=false;
    }

    public void closeframe() throws SQLException {
        if(this.conect==true) {
            jPanel.removeAll();
            frame.remove(jPanel);
            frame.repaint();
            frame.pack();
            frame.setVisible(false);
            frame.dispose();
            frame.setResizable(true);

        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        if ( btn == button1 ) {
            this.scan();
            try {
                this.closeframe();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        else
            frame.setVisible(false);
    }
}

