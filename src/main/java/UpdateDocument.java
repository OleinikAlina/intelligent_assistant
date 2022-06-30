import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class UpdateDocument {

    public static void main(String[] args) throws IOException {

        UpdateDocument obj = new UpdateDocument();
//        Petrovich p = new Petrovich();
//        System.out.println(p.say("Алина", NameType.FirstName, Gender.Female, Case.Genitive));
//        System.out.println(p.say("Олеговна", NameType.PatronymicName, Gender.Female, Case.Genitive));
//        System.out.println(p.say("Олейник", NameType.LastName, Gender.Female, Case.Genitive));
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("названиеКафедры", "общей информатики");
        data.put("имяСтудентаР", "Олейник Алины Олеговны");
        data.put("group", "18205");
        data.put("местоПрактики", "ФГБУН Институт математики им. С. Л. Соболева СО РАН, 630090, г. Новосибирск, пр. Академика Коптюга, 4 ");
        data.put("имяРуководителяНГУ", "Пальчунов Дмитрий Евгеньевич");
        data.put("должРукНГУ", "заведующий КОИ ФИТ, д.ф.-м.н.");
        data.put("имяРуководителяП", "Пальчунов Дмитрий Евгеньевич");
        data.put("должРукП", "в.н.с., д.ф.-м.н.");
        data.put("имяРуководителяВКР", "Пальчунов Дмитрий Евгеньевич");
        data.put("должРукВКР", "заведующий КОИ ФИТ, д.ф.-м.н.");
        data.put("темаЗадания", "Разработка интеллектуального помощника секретаря кафедры");
        obj.updateDocument(
                "Otchet.docx",
                "D:\\Diploma\\intelligent_assistant\\Test2.docx",
                data);
}

    public void updateDocument(String input, String output, HashMap<String, String> data) throws IOException {

        InputStream is = getFileFromResource(input);
        XWPFDocument doc = new XWPFDocument(is);


        List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
        //Повторите список абзацев и проверьте наличие заменяемого текста в каждом абзаце
        for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
//            String docText = xwpfParagraph.getText();
//            System.out.println(docText);
            for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                String docText = xwpfRun.getText(0);
                //замена и установка положения
                System.out.println(docText);
                for (Map.Entry<String, String> entry: data.entrySet()) {
                    docText = docText.replace(entry.getKey(), entry.getValue());
                }
//            xwpfParagraph.tex(docText, 0);
                xwpfRun.setText(docText, 0);
            }
        }

        // save the docs
        FileOutputStream out = new FileOutputStream(output);
        doc.write(out);

    }

    // get file from the resource folder.
    private InputStream getFileFromResource(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
