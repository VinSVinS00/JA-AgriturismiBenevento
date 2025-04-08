public class Agriturismo {
    private String comuneAzienda;
    private String titolare;
    private String denominazioneAzienda;
    private String indirizzoAzienda;
    private int postiLetto;
    private int postiMacchina;
    private int postiTenda;
    private int postiRoulotte;
    private String recapiti;
    private boolean pernottamento;
    private boolean camping;

    public Agriturismo(String comuneAzienda, String titolare, String denominazioneAzienda, String indirizzoAzienda, int postiLetto, int postiMacchina, int postiTenda, int postiRoulotte, String recapiti, boolean pernottamento, boolean camping) {
        this.comuneAzienda = comuneAzienda;
        this.titolare = titolare;
        this.denominazioneAzienda = denominazioneAzienda;
        this.indirizzoAzienda = indirizzoAzienda;
        this.postiLetto = postiLetto;
        this.postiMacchina = postiMacchina;
        this.postiTenda = postiTenda;
        this.postiRoulotte = postiRoulotte;
        this.recapiti = recapiti;
        this.pernottamento = pernottamento;
        this.camping = camping;
    }

    public String getComuneAzienda() {
        return comuneAzienda;
    }

    public void setComuneAzienda(String comuneAzienda) {
        this.comuneAzienda = comuneAzienda;
    }

    public String getTitolare() {
        return titolare;
    }

    public void setTitolare(String titolare) {
        this.titolare = titolare;
    }

    public String getDenominazioneAzienda() {
        return denominazioneAzienda;
    }

    public void setDenominazioneAzienda(String denominazioneAzienda) {
        this.denominazioneAzienda = denominazioneAzienda;
    }

    public String getIndirizzoAzienda() {
        return indirizzoAzienda;
    }

    public void setIndirizzoAzienda(String indirizzoAzienda) {
        this.indirizzoAzienda = indirizzoAzienda;
    }

    public int getPostiLetto() {
        return postiLetto;
    }

    public void setPostiLetto(int postiLetto) {
        this.postiLetto = postiLetto;
    }

    public int getPostiMacchina() {
        return postiMacchina;
    }

    public void setPostiMacchina(int postiMacchina) {
        this.postiMacchina = postiMacchina;
    }

    public int getPostiTenda() {
        return postiTenda;
    }

    public void setPostiTenda(int postiTenda) {
        this.postiTenda = postiTenda;
    }

    public int getPostiRoulotte() {
        return postiRoulotte;
    }

    public void setPostiRoulotte(int postiRoulotte) {
        this.postiRoulotte = postiRoulotte;
    }

    public String getRecapiti() {
        return recapiti;
    }

    public void setRecapiti(String recapiti) {
        this.recapiti = recapiti;
    }

    public boolean isPernottamento() {
        return pernottamento;
    }

    public void setPernottamento(boolean pernottamento) {
        this.pernottamento = pernottamento;
    }

    public boolean isCamping() {
        return camping;
    }

    public void setCamping(boolean camping) {
        this.camping = camping;
    }

    @Override
    public String toString() {
        return comuneAzienda + ";" + titolare + ";" + denominazioneAzienda + ";" + indirizzoAzienda + ";" + postiLetto + ";" +
                postiMacchina + ";" + postiTenda + ";" + postiRoulotte + ";" + recapiti + "\n";
    }
}
