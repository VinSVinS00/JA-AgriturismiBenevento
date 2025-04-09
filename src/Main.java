import java.io.*;
import java.nio.file.attribute.AclEntryFlag;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        ElencoAgriturismo e = new ElencoAgriturismo();
        ElencoAgriturismo e1 = e.carica("Agriturismi-Benevento.csv");

        Stream<Agriturismo> s1 = e1.stream().filter(a -> a.getPostiLetto() > 0);
        s1.forEach(a -> a.setPernottamento(true));

        Stream<Agriturismo> s2 = e1.stream().filter(a -> (a.getPostiTenda() > 0 && a.getPostiRoulotte() > 0));
        s2.forEach(a -> a.setCamping(true));

        List<String> nomiComuni = e1.stream().map(a -> a.getComuneAzienda()).distinct().collect(Collectors.toList());
        System.out.println(nomiComuni);
        BufferedWriter writer = new BufferedWriter(new FileWriter("Lista_Comuni.csv"));
        for(String comune : nomiComuni){
            writer.write(comune + "\n");
        }
        writer.close();

        Stream<Agriturismo> nomeAzienda =e1.stream().distinct().sorted(Comparator.comparing(Agriturismo::getDenominazioneAzienda));
        nomeAzienda.forEach(System.out::println);

        Map<String,Integer> postiLettoComune = new HashMap<>();
        for(String comune : nomiComuni){
            Stream<Agriturismo> sa = e1.stream().filter(a -> a.getComuneAzienda().equals(comune));
            int somma = sa.mapToInt(a -> a.getPostiLetto()).sum();
            postiLettoComune.put(comune,somma);
        }
        System.out.println("\n" + postiLettoComune);

       /* Map<String,Integer> postiCampingMedi = new HashMap<>();
        for(String comune : nomiComuni){
            Stream<Agriturismo> sa = e1.stream().filter(a -> a.getComuneAzienda().equals(comune));
            double media =
        }
       */
    }
}