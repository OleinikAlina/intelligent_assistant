package Interface;


import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    JLayeredPane LayeredPane;
    //"#ffc8a8"
    public Window(String title, int x, int y, int width, int height, String decode){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(x, y, width, height);
        LayeredPane = new JLayeredPane();
        LayeredPane.setLayout(null);
        LayeredPane.setDoubleBuffered(true);
        setContentPane(LayeredPane);
        addPanel(null, 0, 0, width, height, decode);
    }
    public Window(int x, int y, int width, int height, String decode){
        //super(title);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(x, y, width, height);
        LayeredPane = new JLayeredPane();
        LayeredPane.setLayout(null);
        LayeredPane.setDoubleBuffered(true);
        setContentPane(LayeredPane);
        addPanel(null, 0, 0, width, height, decode);
    }

    void addToLayeredPane(Component element){
        LayeredPane.add(element);
        LayeredPane.moveToFront(element);
    }
    public JPanel addPanel (LayoutManager Layout, int x, int y, int width, int height, String decode){
        JPanel panel = new JPanel();
        panel.setLayout(Layout);
        panel.setBounds(x, y, width, height);
        panel.setBackground(Color.decode(decode));
        panel.setVisible(true);
        panel.setDoubleBuffered(true);
        addToLayeredPane(panel);
        return panel;
    }


    public JButton addButton(String title, int x, int y, int width, int height, String color){
        JButton button = new JButton(title);
        button.setSize(width, height);
        button.setLocation(x,y);
        button.setBackground(Color.decode("#EAD39C"));
        button.setForeground(Color.decode(color));
        Font font = new Font(" ", Font.PLAIN, 30);
        button.setFont(font);
        addToLayeredPane(button);
        return button;
    }
    public JTextField addTextField(int x, int y, int width, int height, String color){
        JTextField field = new JTextField("");
        field.setSize(width, height);
        field.setLocation(x,y);
        field.setForeground(Color.decode(color));
        Font font = new Font(" ", Font.PLAIN, 30);
        field.setFont(font);
        addToLayeredPane(field);
        return field;
    }
public JComboBox addBox(int x, int y, int width, int height, String[] items, String color){
    Font font = new Font(" ", Font.PLAIN, 25);
    JComboBox editComboBox = new JComboBox(items);
    editComboBox.setForeground(Color.decode(color));
    editComboBox.setFont(font);
    editComboBox.setBounds(x, y, width, height);
    editComboBox.setEditable(true);
    addToLayeredPane(editComboBox);
    return editComboBox;
}
    public JLabel addLabel(String title, int x, int y, int width, int height, String decode, int size_of_font){
        Font font = new Font(" ", Font.PLAIN, size_of_font);
        JLabel label = new JLabel(title);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        label.setVisible(true);
        label.setForeground(Color.decode(decode));//"#ffa8b4"
        label.setPreferredSize(new Dimension(40, 40));
        addToLayeredPane(label);
        return label;
    }

    public void updateLabel(JLabel label, String titel){
        label.setText(titel);
    }

    public JTable addTable(String[][] data, String[] columnNames){
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        JTable table = new JTable(data, columnNames);
        table.setFont( new Font(" ", Font.PLAIN, 20));
        table.setRowHeight(sSize.height/30);
        table.setBackground(Color.decode("#fff4a8"));
        table.setForeground(Color.decode("#ffa8b4"));
        table.setBounds(sSize.width/50, sSize.height/50, 8*sSize.width/10, sSize.height);
        table.setGridColor(Color.decode("#ffa8b4"));
        table.setVisible(true);
        addToLayeredPane(table);
        return table;
    }

    public void upgradeTable(JTable table, String[][] data, int rows, int cols){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                table.setValueAt(data[i][j], i, j);
            }
        }
    }

    public Component addElement(Component field){
        addToLayeredPane(field);
        return field;
    }
}

