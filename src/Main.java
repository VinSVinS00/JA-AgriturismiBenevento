import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        int i=0;
        List<Titolare> titolariAgriturismi = new ArrayList<>();
        List<String> titolari = e1.stream().map(Agriturismo::getTitolare).toList();
        for(String titolare : titolari){
            String[] arrayTitolare = titolare.split(" ");
            String nome = "";
            String cognome = "";
            String mail = e1.getAgriturismi().get(i).getRecapiti();
            if(mail.equalsIgnoreCase("nd")){
                mail = "info@agriturismibenevento.it";
            }

            if(arrayTitolare.length == 2){
                cognome = arrayTitolare[0];
                nome = arrayTitolare[1];
            }else if(arrayTitolare.length == 3){
                if(arrayTitolare[0].length() < 5){
                    cognome = arrayTitolare[0] + " " + arrayTitolare[1];
                    nome = arrayTitolare[2];
                }else{
                    cognome = arrayTitolare[0];
                    nome = arrayTitolare[1] + " " + arrayTitolare[2];
                }
            }else{
                cognome = arrayTitolare[0] + " " + arrayTitolare[1];
                nome = arrayTitolare[2] + " " + arrayTitolare[3];
            }

            titolariAgriturismi.add(new Titolare(nome,cognome,mail));
            i++;
        }
        System.out.println(titolariAgriturismi);

        Path filePath = Paths.get("Agriturismi-Benevento.csv");
        try {
            List<Agriturismo> agriturismi = Files.lines(filePath).skip(1).map(line -> {
                        String[] data = line.split(";");

                        String comuneAzienda = data[0].trim();
                        String titolare = data[1].trim();
                        String denominazioneAzienda = data[2].trim();
                        String indirizzoAzienda = data[3].trim();

                        String postiLetto = data[4].trim();
                        if(postiLetto.equals(""))
                            postiLetto = "0";
                        String postiMacchina = data[5].trim();
                        if(postiMacchina.equals(""))
                            postiMacchina = "0";
                        String postiTenda = data[6].trim();
                        if(postiTenda.equals(""))
                            postiTenda = "0";
                        String postiRoulotte = data[7].trim();
                        if(postiRoulotte.equals(""))
                            postiRoulotte = "0";

                        String recapiti = data[8].trim();

                        return new Agriturismo(comuneAzienda, titolare, denominazioneAzienda, indirizzoAzienda, Integer.parseInt(postiLetto), Integer.parseInt(postiMacchina), Integer.parseInt(postiTenda), Integer.parseInt(postiRoulotte), recapiti, false, false);
                    }).collect(Collectors.toList());

            agriturismi.forEach(System.out::println);

        } catch (IOException ex) {

        }
    }
}