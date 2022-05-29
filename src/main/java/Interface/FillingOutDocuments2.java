package Interface;

import javax.swing.*;
import java.awt.*;

public class FillingOutDocuments2 {
    public static Window myWindow;
    public static void main(String[] args) {
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        myWindow = new Window("Порождение документов",sSize.width/15, sSize.height/15, 5*sSize.width/7, 5*sSize.height/7, "#EAD39C");
//        JButton agency = myWindow.addButton("Просмотреть данные",sSize.width/12, 20*sSize.height/63,400,40, "#7D5E3C");
//        JButton representation = myWindow.addButton("Обновить данные",sSize.width/12 ,5*sSize.height/21,400,40, "#7D5E3C");
//        JButton admin = myWindow.addButton("Заполнить документы",5*sSize.width/12 ,5*sSize.height/21,400,40, "#7D5E3C");
        JButton exit = myWindow.addButton("назад",4*sSize.width/7,4*sSize.height/7,140,35, "#B51D0A");
//        JComboBox box = myWindow.addBox(5*sSize.width/12 ,20*sSize.height/63,300,30, items);
        myWindow.addLabel("Выберете, какие документы нужно заполнить", 2*sSize.width/49,3,1000,200, "#B51D0A", 45);
        String[] items = {
                "Все документы",
                "Отчет о практике",
                "Индивидуальное задание",
                "Отзыв руководителя практики",
                "Заявление о направлении на практику",
                "Аннотация ВКР",
                "Рецензия",
                "Отзыв руководителя ВКР"
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
        JComboBox box = myWindow.addBox(sSize.width/20, 5*sSize.height/21,400,40, items, "#7D5E3C");
        JComboBox box2 = myWindow.addBox(8*sSize.width/20 ,5*sSize.height/21,400,40, items2, "#7D5E3C");
        myWindow.setVisible(true);
    }
}
