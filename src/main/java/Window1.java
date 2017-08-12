

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Window1 implements ActionListener{
    JFrame frame;
    JButton buttonStart, buttonExit;
    String ip,port1,user1,password1,namebd;
    Window2 w2;
    JPanel jPanelBig;
    JPanel jPanelmin;
    JPanel jPanel;
    JLabel jl1,jl2,jl3,jl4,jl5;
    Boolean conect=false;
    SQL sql;
    JTextField tf1, tf2,tf4,tf5;
    JPasswordField tf3;

    Window1(){
        frame = new JFrame();
        jPanelBig=new JPanel();
        jPanelmin=new JPanel();
        jPanel= new JPanel();
        
        buttonStart = new JButton("Відкрити");
        buttonExit = new JButton("Запрос");

        tf1 = new JTextField("localhost");
        tf1.setSize(170,25);
        tf1.setLocation(250,1);

        tf2 = new JTextField("root");
        tf2.setSize(170,25);
        tf2.setLocation(250,50);

        tf3 = new JPasswordField("kuka12345");
        tf3.setSize(170,25);
        tf3.setLocation(250,100);

        tf4 = new JTextField("3306");
        tf4.setSize(170,25);
        tf4.setLocation(250,150);

        tf5 = new JTextField("railway");
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

        buttonStart.setVisible(true);
        buttonStart.setSize(100,50);
        buttonStart.setLocation(50,250);

        jPanel.setLayout(null);

        jPanelBig.setLayout(new BorderLayout());
        jPanelBig.add(jPanelmin,BorderLayout.NORTH);
        jPanelBig.add(jPanel,BorderLayout.CENTER);
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

    void frameG (){
        frame.getContentPane().add(jPanelBig);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,700);

        buttonStart.addActionListener(this);
        buttonStart.setPreferredSize(new Dimension(100,20));

        Icon leftIcon = UIManager.getIcon("OptionPane.errorIcon");
        JButton leftButton = new JButton("Вихід");
        leftButton.setVisible(true);
        leftButton.setSize(150,50);
        leftButton.setLocation(200,250);
        leftButton.setIcon(leftIcon);
        leftButton.addActionListener(this);

        jPanelmin.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("database (1).jpg"))));
        jPanel.add(leftButton);

        jPanel.add(buttonStart);

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
    public void closeframe() throws SQLException {
            jPanel.removeAll();
            frame.remove(jPanel);
            frame.repaint();
            frame.pack();
            frame.setVisible(false);
            frame.dispose();

            w2 = new Window2(sql);
            w2.tab();
    }
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        if ( btn == buttonStart) {
            this.scan();
            try {
                this.closeframe();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        else
            System.exit(1);
    }

}
