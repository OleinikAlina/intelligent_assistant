import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n" +
                "PREFIX my: <http://www.semanticweb.org/oleyn/ontologies/2022/4/version_2#> \n" +
                "SELECT ?х WHERE { ?х a my:Студент .\n" +
                "?х my:Курс ?у\n" +
                "FILTER regex(?у, \"3\")}";
//                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX owl: <http://www.w3.org/2002/07/owl#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> PREFIX my: <http://www.semanticweb.org/oleyn/ontologies/2022/4/version_2#> SELECT * WHERE { ?х my:руководит ?object }";
        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results =  qe.execSelect();
//        System.out.println(results.next());
        QuerySolution ss;
        while(results.hasNext()){
            ss = results.nextSolution();
            System.out.println(ss.getResource("х").getLocalName());
//            System.out.println(ss.getResource("ф").getLocalName());
//            System.out.println(ss.getResource("ф"));
//            System.out.println(ss.getResource("у").getLocalName());
//                    .listProperties().toSet());
//
        }

    }
}
