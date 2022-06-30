import com.github.petrovich4j.Case;
import com.github.petrovich4j.Gender;
import com.github.petrovich4j.NameType;
import com.github.petrovich4j.Petrovich;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.*;
import java.util.HashMap;

import static com.github.petrovich4j.Gender.Female;

public class Start {
    public static final String owlFile = "D:\\Diploma\\База данных\\пр.xml";

    public static void main(String[] args) {
        Model model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        String file = "D:\\Diploma\\База данных\\пример.owl";

        InputStream in;
        try {
            in = new FileInputStream(file);
            model.read(in, "RDF/XML");
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // or any windows path
Start obj = new Start();
        obj.otzKMAD(model);
        obj.otzTRPS(model);
        obj.otzTRPS2(model);
        obj.otzPIKN(model);
        obj.otzPIKN2(model);
        obj.rezKMAD(model);
        obj.rezTRPS(model);
        obj.rezPIKN(model);
    }
    public static void rezKMAD(Model model){
        newDoc(model, "Магистратура", "09.04.01_KMiAD_VKR_recenziya.docx", "Компьютерное моделирование и анализ данных");
    }
    public static void rezTRPS(Model model){
        newDoc(model, "Магистратура", "09.04.01_TRPS_VKR_recenziya.docx", "Технология разработки программных систем");
    }
    public static void rezPIKN(Model model){
newDoc(model, "Бакалавриат", "09.03.01_PIiKN_VKR_recenziya (1).docx", "Программная инженерия и компьютерные науки");
    }
    public static void rezKNS(Model model){
        newDoc(model, "Бакалавриат", "", "Компьютерные науки и системотехника");
    }
    public static void otzKMAD(Model model){
        newDoc2(model, "Магистратура", "09.04.01_KMiAD_VKR_otzyv.docx", "Компьютерное моделирование и анализ данных");
    }

    public static void otzTRPS(Model model){
        newDoc2(model, "Магистратура", "09.04.01_TRPS_VKR_otzyv.docx", "Технология разработки программных систем");
    }
    public static void otzTRPS2(Model model){
        newDoc3(model, "Магистратура", "09.04.01_TRPS_VKR_otzyv2.docx", "Технология разработки программных систем");
    }
    public static void otzPIKN(Model model){
        newDoc2(model, "Бакалавриат", "09.03.01_PIiKN_VKR_otzyv.docx", "Программная инженерия и компьютерные науки");
    }
    public static void otzPIKN2(Model model){
        newDoc3(model, "Бакалавриат", "09.03.01_PIiKN_VKR_otzyv2.docx", "Программная инженерия и компьютерные науки");
    }
    public static void otzKNS(Model model){
        newDoc2(model, "Бакалавриат", "", "Компьютерные науки и системотехника");
    }
    public static void newDoc3(Model model, String group, String fileName, String profile){

        String queryString = "PREFIX my: <http://www.semanticweb.org/oleyn/ontologies/2022/4/кафедра#>\n" +
                "                SELECT ?фио_студента ?фио_руководителя ?должность_руководителя ?тема_вкр  ?фио_рук ?должность_рук WHERE { ?студент a my:" + group + " .\n" +
                "                ?студент my:защищает ?вкр .\n" +
                "                ?руководитель my:согласовывает ?вкр .\n" +
                "                ?вкр my:Тема ?тема_вкр . \n" +
                "                ?студент my:ФИО ?фио_студента .\n" +
                "                ?руководитель my:ФИО ?фио_руководителя .\n" +
                "                ?руководитель my:Должность ?должность_руководителя .\n" +
                "?сорук my:руководит_с ?вкр .\n" +
                "                ?сорук my:ФИО ?фио_рук .\n" +
                "                ?сорук my:Должность ?должность_рук .\n" +
                "                ?студент my:Профиль_обучения ?профиль .\n" +
                "FILTER regex(?профиль, \"" + profile + "\") }";
        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results =  qe.execSelect();
//        System.out.println(results.next());
        QuerySolution solution;
        Integer i = 0;
        while(results.hasNext()){
            i++;
            solution = results.nextSolution();
            UpdateDocument obj = new UpdateDocument();
//

            HashMap<String, String> data = new HashMap<String, String>();
            String name = solution.getLiteral("фио_студента").getString();
            String[] words = name.split(" ");
            Petrovich p = new Petrovich();
            Gender gender = p.gender(words[2], Gender.Both);
            String nameG = p.say(words[0], NameType.LastName, gender, Case.Genitive) + " " + p.say(words[1], NameType.FirstName, gender, Case.Genitive) + " " + p.say(words[2], NameType.PatronymicName, gender, Case.Genitive);
            data.put("имяСтудентаИ", name);
            data.put("имяСтудентаР", nameG);
            data.put("имяРуководителяВКР", solution.getLiteral("фио_руководителя").getString());
            data.put("должРукВКР", solution.getLiteral("должность_руководителя").getString());

            String topic = "«" + solution.getLiteral("тема_вкр").getString() + "»";
            data.put("темавкр", topic);
            data.put("имяСоруководителя", solution.getLiteral("фио_рук").getString());
            data.put("должСоруководителя", solution.getLiteral("должность_рук").getString());
            data.put(", КОИ ФИТ", "КОИ ФИТ");
            if (gender == Female){
                data.put("ознакомлен", "ознакомлена");
            }
            String fileout = "D:\\Diploma\\intelligent_assistant\\Отзывы ВКР\\" + group +"\\Отзыв_на_" + words[0] +".docx";
            try {
                obj.updateDocument(
                        fileName,
                        fileout,
                        data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println(i);
    }
    public static void newDoc2(Model model, String group, String fileName, String profile){

        String queryString = "PREFIX my: <http://www.semanticweb.org/oleyn/ontologies/2022/4/кафедра#>\n" +
                "SELECT ?фио_студента ?фио_руководителя ?должность_руководителя ?тема_вкр  WHERE { ?студент a my:" + group + " .\n" +
                "?студент my:защищает ?вкр .\n" +
                "?руководитель my:согласовывает ?вкр .\n" +
                "?вкр my:Тема ?тема_вкр .\n" +
                "?студент my:ФИО ?фио_студента .\n" +
                "?руководитель my:ФИО ?фио_руководителя .\n" +
                "?руководитель my:Должность ?должность_руководителя .\n" +
                "?студент my:Профиль_обучения ?профиль .\n" +
                "FILTER regex(?профиль, \"" + profile + "\") }";
        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results =  qe.execSelect();
//        System.out.println(results.next());
        QuerySolution solution;
        Integer i = 0;
        while(results.hasNext()){
            i++;
            solution = results.nextSolution();
            UpdateDocument obj = new UpdateDocument();
//

            HashMap<String, String> data = new HashMap<String, String>();
            String name = solution.getLiteral("фио_студента").getString();
            String[] words = name.split(" ");
            Petrovich p = new Petrovich();
            Gender gender = p.gender(words[2], Gender.Both);
            String nameG = p.say(words[0], NameType.LastName, gender, Case.Genitive) + " " + p.say(words[1], NameType.FirstName, gender, Case.Genitive) + " " + p.say(words[2], NameType.PatronymicName, gender, Case.Genitive);
            data.put("имяСтудентаИ", name);
            data.put("имяСтудентаР", nameG);
            data.put("имяРуководителяВКР", solution.getLiteral("фио_руководителя").getString());
            data.put("должРукВКР", solution.getLiteral("должность_руководителя").getString());
            String topic = "«" + solution.getLiteral("тема_вкр").getString() + "»";
            data.put("темавкр", topic);
            data.put(", КОИ ФИТ", "КОИ ФИТ");
            if (gender == Female){
                data.put("ознакомлен", "ознакомлена");
            }
            String fileout = "D:\\Diploma\\intelligent_assistant\\Отзывы ВКР\\" + group +"\\Отзыв_на_" + words[0] +".docx";
            try {
                obj.updateDocument(
                        fileName,
                        fileout,
                        data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println(i);
    }
    public static void newDoc(Model model, String group, String fileName, String profile){

        String queryString = "PREFIX my: <http://www.semanticweb.org/oleyn/ontologies/2022/4/кафедра#>\n" +
                "SELECT ?фио_студента ?фио_рецензента ?должность_рецензента ?тема_вкр  WHERE { ?студент a my:" + group + " .\n" +
                "?студент my:защищает ?вкр .\n" +
                "?рецензент my:рецензирует ?вкр .\n" +
                "?вкр my:Тема ?тема_вкр .\n" +
                "?студент my:ФИО ?фио_студента .\n" +
                "?рецензент my:ФИО ?фио_рецензента .\n" +
                "?рецензент my:Должность ?должность_рецензента .\n" +
                "?студент my:Профиль_обучения ?профиль .\n" +
                "FILTER regex(?профиль, \"" + profile + "\") }";
        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results =  qe.execSelect();
//        System.out.println(results.next());
        QuerySolution solution;
        Integer i = 0;
        while(results.hasNext()){
            i++;
            solution = results.nextSolution();
            UpdateDocument obj = new UpdateDocument();
//

            HashMap<String, String> data = new HashMap<String, String>();
            String name = solution.getLiteral("фио_студента").getString();
            String[] words = name.split(" ");
            Petrovich p = new Petrovich();
            Gender gender = p.gender(words[2], Gender.Both);
            String nameG = p.say(words[0], NameType.LastName, gender, Case.Genitive) + " " + p.say(words[1], NameType.FirstName, gender, Case.Genitive) + " " + p.say(words[2], NameType.PatronymicName, gender, Case.Genitive);
            data.put("имяСтудентаИ", name);
            data.put("имяСтудентаР", nameG);
            data.put("имярецензента", solution.getLiteral("фио_рецензента").getString());
            data.put("должностьрецензента", solution.getLiteral("должность_рецензента").getString());
            String topic = "«" + solution.getLiteral("тема_вкр").getString() + "»";
            data.put("темавкр", topic);
            if (gender == Female){
                data.put("ознакомлен", "ознакомлена");
            }
            String fileout = "D:\\Diploma\\intelligent_assistant\\Рецензии\\" + group +"\\Рецензия_на_" + words[0] +".docx";
            try {
                obj.updateDocument(
                        fileName,
                        fileout,
                        data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println(i);
    }

}
