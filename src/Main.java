import java.io.*;
import java.nio.file.attribute.AclEntryFlag;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        ElencoAgriturismo e = new ElencoAgriturismo();
        ElencoAgriturismo e1 = e.carica("Agriturismi-Benevento.csv");

        Stream<Agriturismo> s1 = e1.stream().filter(a -> a.getPostiLetto() > 0);
        s1.forEach(a -> a.setPernottamento(true));
        System.out.println(e1);

        Stream<Agriturismo> s2 = e1.stream().filter(a -> (a.getPostiTenda() > 0 && a.getPostiRoulotte() > 0));
        s2.forEach(a -> a.setCamping(true));
        System.out.println(e1);

        List<String> nomiComuni = e1.stream().map(a -> a.getComuneAzienda()).distinct().collect(Collectors.toList());
        System.out.println(nomiComuni);
        BufferedWriter writer = new BufferedWriter(new FileWriter("Lista_Comuni.csv"));
        for(String comune : nomiComuni){
            writer.write(comune + "\n");
        }
        writer.close();

        Stream<Agriturismo> s3 = e1.stream().sorted((a, b) -> a.getDenominazioneAzienda().compareTo(b.getDenominazioneAzienda()));
        System.out.println("LISTA DEGLI AGRITURISMI ORDINATI PER DENOMINAZIONE AZIENDA: \n");
        s3.forEach(System.out::println);

        Stream<Agriturismo> s4 = e1.stream();
        Optional<Agriturismo> ag = s4.max(Comparator.comparingInt(a -> a.getPostiRoulotte() + a.getPostiTenda()));

        System.out.println("L'agriturismo che ha più posti camping è: " + ag.get().getDenominazioneAzienda() +
                ", che si trova a: " + ag.get().getComuneAzienda() + " \nNumero Posti Camping: " +
                Integer.parseInt(String.valueOf(ag.get().getPostiTenda()+ag.get().getPostiRoulotte())));







    }
}