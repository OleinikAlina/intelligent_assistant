package Interface;

import javax.swing.*;
import java.awt.*;

public class UpdateData {
    public static Window myWindow;
    public static void main(String[] args) {
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        myWindow = new Window("Обновление данных",sSize.width/15, sSize.height/15, 5*sSize.width/7, 5*sSize.height/7, "#EAD39C");
        myWindow.addLabel("Обновить информацию о", 2*sSize.width/49,sSize.height/20,1000,50, "#B51D0A", 40);
        JButton exit = myWindow.addButton("назад",4*sSize.width/7,4*sSize.height/7,140,35, "#B51D0A");
        JButton exit2 = myWindow.addButton("обновить",9*sSize.width/20,18*sSize.height/42,180,40, "#B51D0A");
        String[] items = {
                "Обучающегося",
                "Руководителя ВКР",
                "Соруководителя ВКР",
                "Консультанта",
                "Рецензента",
                "Руководителя практики от НГУ",
                "Руководителя практики от профильной организации",
                "Практике обучающегося",
                "ВКР обучающегося"
        };
// для обучающегося
        String[] items2 = {
                "ФИО",
                "Профиль обучения",
                "Номер группы",
                "Курс"
        };
        //для практики
        myWindow.addLabel("Введите ФИО обучающегося:", 8*sSize.width/20,8*sSize.height/42,450,40, "#7D5E3C", 30);
        myWindow.addLabel("Введите тему:", 8*sSize.width/20,12*sSize.height/42,450,40, "#7D5E3C", 30);
        String[] items3 = {
                "Тема",
                "Приказ на прохождение практики",
                "Место прохождения практики",
                "Дата защиты отчета"
        };
        JTextField search = myWindow.addTextField(8*sSize.width/20, 10*sSize.height/42,400,40, "#7D5E3C");
        JTextField search2 = myWindow.addTextField(8*sSize.width/20, 14*sSize.height/42,400,40, "#7D5E3C");
        JComboBox box = myWindow.addBox(sSize.width/20, 5*sSize.height/21,400,40, items, "#7D5E3C");
        JComboBox box2 = myWindow.addBox(sSize.width/20 ,7*sSize.height/21,400,40, items3, "#7D5E3C");
        myWindow.setVisible(true);
    }
}
