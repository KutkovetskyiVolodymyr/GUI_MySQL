import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Window2 extends JFrame implements ActionListener {
    JTextArea area;
    JTable table;
    JLabel jl1;
    GridBagConstraints constraints;
    DefaultTableModel dtm   = null;
    JPanel jpanelleft,jpanelright,jpanelup,jpaneldown;
    JTextField jTextField;
    JFrame frame;
    JButton jButton,jb,jb1,jb2,jb3,ok,createtable;
    JButton[] buttons;
    SQL sql;
    JScrollPane jsp;


    Window2() {
        jl1 = new JLabel("Таблиці в БД:");
        jl1.setSize(110,15);
        jl1.setLocation(0,1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(150, 25);
        setSize(900,700);
        jpanelleft= new JPanel();
        jpanelright = new JPanel();
        jpaneldown= new JPanel();
        jpanelup = new JPanel();

        constraints = new GridBagConstraints();
        setLayout (new GridBagLayout());
        int x,y;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.weightx=0.1;
        constraints.weighty=1.0;
        constraints.gridheight=2;
        setInGrid(jpanelleft, x=0, y=0);
        constraints.gridheight=1;
        //для верхнього
        constraints.weightx=0.2;
        constraints.weighty=0.5;
        setInGrid(jpanelup, 1, 0);
        //для нижнього
        constraints.weightx=0.2;
        constraints.weighty=0.5;
        setInGrid(jpaneldown, 1, 1);


        jpanelleft.setLayout(null);
        jpanelleft.setVisible(true);


        setVisible(true);
        try {
            getTextArea();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void tab(){
        String[] showtable;
        showtable=sql.showtables();
        createleft(showtable);
    }

    public void getTextArea() throws SQLException {
        area= new JTextArea(1, 1);
        area.setText("Введіть SQL запрос");
        area.setVisible(true);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        area.setSize(400,50);
        jpanelup.add(area);
        area.setLocation(150,50);
        createright();
    }
    public void createright(){

        jb1=new JButton("Добавити таблицю");
        jb1.setLocation(500,500);
        jb1.setSize(200,100);
        jb=new JButton("Добавити кортеж");
        jb2= new JButton("Видалити таблицю");
        jb3=new JButton("Оновити таблиці");


        jpaneldown.setLayout(null);
        jpaneldown.add(jb1);
        jpaneldown.add(jb);
        jpaneldown.add(jb2);
        jpaneldown.add(jb3);
    }
    private void deletetable(){

    }

    private void frame1(final JTable table2){
        frame = new JFrame();
        frame.setSize(500,120);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        JScrollPane jspp = new JScrollPane(table2);
        panel.add(jspp);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
    public void JScrol(String jSP1) throws SQLException {
        table=sql.mySQLqery("select * from "+jSP1);
        frame1(table);
    }
    private void  setInGrid (Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
    public void createleft(String[] table){
        int down = 20;
        int i=table.length;

        buttons=new JButton[i];//это только массив создан.
        ok=new JButton("OK");
        jpanelup.add(ok);
        ok.setVisible(true);
        ok.setLocation(100,10);
        ok.setSize(150,20);
        ok.addActionListener(this);

        jpanelleft.add(jl1);
        //for(int   =0 ; i<);
        for(int a=0;a<table.length;a++) {
            buttons[a] = new JButton(table[a]);// вызов конструктора для создания объектов

            buttons[a].addActionListener(this);;
            buttons[a].setLocation(1,down);
            buttons[a].setSize(150,20);
            buttons[a].setVisible(true);
            jpanelleft.add(buttons[a]);
            down+=22;
        }

/*
        jpaneldown.add(buttons[5]);
        jpaneldown.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed (MouseEvent e){
                buttons[5].setLocation(e.getX(), e.getY());
            }
        });
*/

        }

    public void actionPerformed(ActionEvent e) {

        JButton btn = (JButton)e.getSource();
        if(btn==jb3){
            jpanelleft.repaint();
            tab();
        }
        if(btn==jb1){
            frame= new JFrame("Створення таблиці");

            JPanel jptable = new JPanel();
            JTextField jtf,jtf1;
            jtf= new JTextField("Введіть назву таблиці");
            jtf1=new JTextField("Введіть назву полів в таблиці в дужках, вкажіть тип . Наприклад: (Car INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Bike VARCHAR(20)");
            JLabel jl,jl1;
            jl=new JLabel();
            jl1=new JLabel();
            jptable.add(jtf);
            jptable.add(jl);
            jptable.add(jtf1);
            jptable.add(jl1);
            createtable = new JButton("Створити");
            if(jl1!=null){
                if(jl==null)
                    jl.setText("Введіть назву таблиці");
                else {
                    try {
                        sql.qery("CREATE TABLE " + jl.getText() + " " + jl1.getText());

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            else{

            }
            frame.setContentPane(jptable);

        }
        if(ok==btn){
            try {
                table=sql.mySQLqery(area.getText());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            frame1(table);
           // jsp.setVisible(true);
        }
        for(int i=0;i<buttons.length;i++) {
            frame=null;
            if (buttons[i] == btn) {
                String str;
                str = buttons[i].getText();
                try {
                    JScrol(str);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}