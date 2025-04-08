import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ElencoAgriturismo
{
    private List<Agriturismo> agriturismi;

    public ElencoAgriturismo(){
        agriturismi = new ArrayList<>();
    }

    public Stream<Agriturismo> stream(){
        return agriturismi.stream();
    }

    public void aggiungi(Agriturismo a){
        agriturismi.add(a);
    }

    public static ElencoAgriturismo carica(String filename) {
        ElencoAgriturismo ea = new ElencoAgriturismo();
        try(Scanner s = new Scanner(new BufferedReader(new FileReader(filename)))){

            s.useDelimiter(";|\n");
            s.nextLine();

            while(s.hasNext()){
                String comuneAzienda = s.next();
                String titolare = s.next();
                String denominazioneAzienda = s.next();
                String indirizzoAzienda = s.next();

                String postiLetto = s.next();
                if("".equals(postiLetto)) postiLetto = "0";

                String postiMacchina = s.next();
                if("".equals(postiMacchina)) postiMacchina = "0";

                String postiTenda = s.next();
                if("".equals(postiTenda)) postiTenda = "0";

                String postiRoulotte = s.next();
                if("".equals(postiRoulotte)) postiRoulotte = "0";

                String recapiti = s.next();

                ea.aggiungi(new Agriturismo(comuneAzienda, titolare, denominazioneAzienda, indirizzoAzienda, Integer.parseInt(postiLetto), Integer.parseInt(postiMacchina),
                        Integer.parseInt(postiTenda), Integer.parseInt(postiRoulotte), recapiti, false, false));
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return ea;
    }

    public void esporta(List<?> list, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for(int i=0; i<list.size(); i++){
                writer.write(list.get(i).toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Agriturismo> getAgriturismi(){
        return agriturismi;
    }

    @Override
    public String toString() {
        return "ElencoAgriturismo" +
                "{" +
                "agriturismi=" + agriturismi +
                "}\n";
    }
}
