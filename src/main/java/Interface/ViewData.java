package Interface;

import javax.swing.*;
import java.awt.*;

public class ViewData {
    public static Window myWindow;
    public static void main(String[] args) {
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        myWindow = new Window("Просмотр данных",sSize.width/15, sSize.height/15, 5*sSize.width/7, 5*sSize.height/7, "#EAD39C");
        myWindow.addLabel("Просмотреть информацию о", 2*sSize.width/49,3,1000,200, "#B51D0A", 45);
        JButton exit = myWindow.addButton("назад",4*sSize.width/7,4*sSize.height/7,140,35, "#B51D0A");
        String[] items = {
                "обучающихся",
                "Рецензентах",
                "Руководителях ВКР",
                "Руководителях практики"
        };
        String[] items2 = {
                "Для всех",
                "Бакалавры",
                "Магистранты",
                "ПИиКН 3 курс",
                "ПИиКН 4 курс",
                "КНиС 3 курс",
                "КНиС 4 курс",
                "КМиАД 1 курс",
                "КМиАД 2 курс",
                "ТРПС 1 курс",
                "ТРПС 2 курс",
                "Для конкретного студента"
        };
        String[] items3 = {
                "Рецензенты для 4 курса бакалавриата",
                "Рецензенты для 2 курса магистратуры"
        };
        JComboBox box = myWindow.addBox(sSize.width/20, 5*sSize.height/21,400,40, items, "#7D5E3C");
        JComboBox box2 = myWindow.addBox(8*sSize.width/20 ,5*sSize.height/21,400,40, items2, "#7D5E3C");
        myWindow.setVisible(true);
    }
}
