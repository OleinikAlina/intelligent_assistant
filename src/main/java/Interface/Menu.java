package Interface;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class Menu {
    public static Window myWindow;
    public static void main(String[] args) {
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        myWindow = new Window("Интеллектуальный помощник секретаря кафедры",sSize.width/15, sSize.height/15, 5*sSize.width/7, 5*sSize.height/7, "#EAD39C");
        JButton agency = myWindow.addButton("Просмотреть данные",sSize.width/12, 20*sSize.height/63,400,40, "#7D5E3C");
        JButton representation = myWindow.addButton("Обновить данные",sSize.width/12 ,5*sSize.height/21,400,40, "#7D5E3C");
        JButton representation2 = myWindow.addButton("Добавить данные",sSize.width/12 ,25*sSize.height/63,400,40, "#7D5E3C");
        JButton admin = myWindow.addButton("Заполнить документы",5*sSize.width/12 ,5*sSize.height/21,400,40, "#7D5E3C");
        JButton admin2 = myWindow.addButton("Работа с шаблонами",5*sSize.width/12 ,20*sSize.height/63,400,40, "#7D5E3C");
        JButton exit = myWindow.addButton("выход",4*sSize.width/7,4*sSize.height/7,140,35, "#B51D0A");
        myWindow.addLabel("Выберите действие", 5*sSize.width/49,5,800,200, "#B51D0A", 70);
        myWindow.setVisible(true);
    }
}
