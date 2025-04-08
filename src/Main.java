import java.io.*;
import java.nio.file.attribute.AclEntryFlag;
import java.util.Collection;
import java.util.List;
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

        try{
            List<String> nomiComuni = e1.stream().map(a -> a.getComuneAzienda()).distinct().collect(Collectors.toList());
            BufferedWriter bw = new BufferedWriter(new FileWriter("Nomi_Comuni.csv"));
            for (String stringa : nomiComuni) {
                bw.write(stringa);
                bw.newLine();
            }
        }catch(IOException ex){
        }



    }
}