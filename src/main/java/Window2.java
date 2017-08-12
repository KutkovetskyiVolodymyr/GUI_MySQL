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
    JPanel jPaneLeft, jPanelRight, jPanelUp,jpaneldown;
    JTextField jTextField;
    JFrame frame;
    JButton jButton,jb,jb1,jb2,jb3, button, buttonCreateTable;
    JButton[] buttons;
    SQL sql;
    JScrollPane jsp;

    JFrame jFrameCreate ;
    JPanel jPTable;

    Window2(SQL sql) {
        this.sql=sql;
        jl1 = new JLabel("Таблиці в БД:");
        jl1.setSize(110,15);
        jl1.setLocation(0,1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(150, 25);
        setSize(900,700);
        jPaneLeft = new JPanel();
        jPanelRight = new JPanel();
        jpaneldown= new JPanel();
        jPanelUp = new JPanel();

        constraints = new GridBagConstraints();
        setLayout (new GridBagLayout());
        int x,y;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.weightx=0.1;
        constraints.weighty=1.0;
        constraints.gridheight=2;
        setInGrid(jPaneLeft, x=0, y=0);
        constraints.gridheight=1;
        //для верхнього
        constraints.weightx=0.2;
        constraints.weighty=0.5;
        setInGrid(jPanelUp, 1, 0);
        //для нижнього
        constraints.weightx=0.2;
        constraints.weighty=0.5;
        setInGrid(jpaneldown, 1, 1);

        jPaneLeft.setLayout(null);
        jPaneLeft.setVisible(true);

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

    private void getTextArea() throws SQLException {
        area= new JTextArea(1, 1);
        area.setText("Введіть SQL запрос");
        area.setVisible(true);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        area.setSize(400,50);
        jPanelUp.add(area);
        area.setLocation(150,50);
        createright();
    }
    private void createright(){

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
    private void JScrol(String jSP1) throws SQLException {
        table=sql.mySQLqery("select * from "+jSP1);
        frame1(table);
    }
    private void  setInGrid (Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
    private void createleft(String[] table){
        int down = 20;
        int i=table.length;

        buttons=new JButton[i];
        button =new JButton("OK");
        jPanelUp.add(button);
        button.setVisible(true);
        button.setLocation(100,10);
        button.setSize(150,20);
        button.addActionListener(this);

        jPaneLeft.add(jl1);
        
        for(int a=0;a<table.length;a++) {
            buttons[a] = new JButton(table[a]);

            buttons[a].addActionListener(this);;
            buttons[a].setLocation(1,down);
            buttons[a].setSize(150,20);
            buttons[a].setVisible(true);
            jPaneLeft.add(buttons[a]);
            down+=22;
        }
    }
    public void actionPerformed(ActionEvent e) {

        JButton btn = (JButton)e.getSource();
        if(btn==jb3){
            jPaneLeft.repaint();
            tab();
        }
        if(btn==jb1){
            frame= new JFrame("Створення таблиці");
            jFrameCreate = new JFrame();
            jPTable = new JPanel();
            jFrameCreate.getContentPane().add(jPTable);
            JTextField jtf,jtf1;
            jtf= new JTextField("Введіть назву таблиці");
            jtf1=new JTextField("Введіть назву полів в таблиці в дужках, вкажіть тип . Наприклад: (Car INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Bike VARCHAR(20)");
            JLabel jl,jl1;
            jl=new JLabel();
            jl1=new JLabel();
            jPTable.add(jtf);
            jPTable.add(jl);
            jPTable.add(jtf1);
            jPTable.add(jl1);
            buttonCreateTable = new JButton("Створити");
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
            frame.setContentPane(jPTable);

        }
        if(button ==btn){
            try {
                table=sql.mySQLqery(area.getText());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            frame1(table);
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