
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.io.File;
import java.util.Set;

public class Start {
    public static void main(String[] args) {
        try {
//сначала нужно создать OWLOntologyManager
            OWLOntologyManager owlManager = OWLManager.createOWLOntologyManager();
            File ontologyOwlFile = new File("D:\\Diploma\\База данных\\Первый вариант.owl");

            OWLOntology theOntology = owlManager.loadOntologyFromOntologyDocument(ontologyOwlFile);

////            java.util.Set<OWLEntity> entOnt = theOntology.getSignature();
////            for (OWLEntity a : entOnt) {
////                System.out.println("Entity "+a);//this print only the entities
////            }
            OWLDataFactory df = owlManager.getOWLDataFactory();
            Set<OWLObjectProperty> arr = theOntology.getObjectPropertiesInSignature();
//            java.util.Set<OWLEntity> entOnt = theOntology.getSignature();
            for (OWLEntity a : arr) {
                System.out.println("Entity " + a);//this print only the entities
            }
//            System.out.println(arr);

        } catch (OWLOntologyCreationException owlcre) {
            System.out.println("The ontology could not be created: " + owlcre.getMessage());
        }
    }
}
