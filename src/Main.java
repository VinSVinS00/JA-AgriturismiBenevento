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

        Stream<Agriturismo> nomeAzienda =e1.stream().sorted(Comparator.comparing(Agriturismo::getDenominazioneAzienda));
        nomeAzienda.forEach(System.out::println);

        Optional<Agriturismo> azienda = e1.stream().max(Comparator.comparing(Agriturismo::getPostiTenda).thenComparing(Agriturismo::getPostiRoulotte));
        System.out.println("L'agriturismo con piu' posti campeggio è quello che si trova nel comune: " + azienda.get().getComuneAzienda());

        Map<String,Integer> postiLettoComune = new HashMap<>();
        for(String comune : nomiComuni){
            Stream<Agriturismo> sa = e1.stream().filter(a -> a.getComuneAzienda().equals(comune));
            int somma = sa.mapToInt(a -> a.getPostiLetto()).sum();
            postiLettoComune.put(comune,somma);
        }
        System.out.println("\n" + postiLettoComune);

        Map<String,Double> postiCampingMedi = new HashMap<>();
        List<Agriturismo> agriturismiCamping = e1.stream().filter(a -> a.getPostiTenda() > 0 && a.getPostiRoulotte() > 0).collect(Collectors.toList());
        if(!agriturismiCamping.isEmpty()) {
            for (String comune : nomiComuni) {
                List<Agriturismo> inQuelComune = agriturismiCamping.stream().filter(a -> a.getComuneAzienda().equals(comune)).collect(Collectors.toList());
                Double media = inQuelComune.stream().mapToInt(a -> a.getPostiRoulotte() + a.getPostiTenda()).average().orElse(0.0);
                postiCampingMedi.put(comune, media);
            }
            System.out.println(postiCampingMedi);
        }else{
            System.out.println("Nessun agriturismo adibito al camping");
        }


    }
}