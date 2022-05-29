import com.github.petrovich4j.Case;
import com.github.petrovich4j.Gender;
import com.github.petrovich4j.NameType;
import com.github.petrovich4j.Petrovich;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.github.petrovich4j.Gender.Female;
import static com.hp.hpl.jena.query.ResultSetFormatter.*;

public class Sp {
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


        // Create a new query
        String queryString = "PREFIX my: <http://www.semanticweb.org/oleyn/ontologies/2022/4/кафедра#>\n" +
                "SELECT ?фио_студента ?фио_рецензента ?должность_рецензента ?тема_вкр  WHERE { ?студент a my:Бакалавриат .\n" +
                "?студент my:защищает ?вкр .\n" +
                "?рецензент my:рецензирует ?вкр .\n" +
                "?вкр my:Тема ?тема_вкр .\n" +
                "?студент my:ФИО ?фио_студента .\n" +
                "?рецензент my:ФИО ?фио_рецензента .\n" +
                "?рецензент my:Должность ?должность_рецензента}";
//                "PREFIX my: <http://www.semanticweb.org/oleyn/ontologies/2022/4/кафедра#>\n" +
//                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
//                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
//                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n" +
//                "SELECT ?студент ?фио WHERE { my:Руководитель_от_НГУ my:руководит ?практика .\n" +
//                "?студент my:проходит ?практика .\n" +
//                "?студент my:ФИО ?фио}";
//                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
//                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
//                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n" +
//                "PREFIX my: <http://www.semanticweb.org/oleyn/ontologies/2022/4/version_2#> \n" +
//                "SELECT ?х WHERE { ?х a my:Студент .\n" +
//                "?х my:Курс ?у\n" +
//                "FILTER regex(?у, \"3\")}";
//                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX owl: <http://www.w3.org/2002/07/owl#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> PREFIX my: <http://www.semanticweb.org/oleyn/ontologies/2022/4/version_2#> SELECT * WHERE { ?х my:руководит ?object }";
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
            String fileout = "D:\\Diploma\\intelligent_assistant\\Рецензии\\Рецензия_на_" + words[0] +".docx";
            try {
                obj.updateDocument(
                        "09.03.01_PIiKN_VKR_recenziya (1).docx",
                        fileout,
                        data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
