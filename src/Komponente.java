import java.util.UUID;
public class Komponente {
    private String comUUID; // eindeutige Identifikation der Komponenente
    private String ipAdresse; // IP der Komponente
    private int port;
    private boolean isStern;

    // Konstruktor um neue Komponente zu initialisieren
    public Komponente(String comUUID, String ipAdresse, int port) {
        this.comUUID = generateComUuid();
        this.ipAdresse = ipAdresse;
        this.port = port;
        this.isStern=true;
    }

    // Generate a unique 4-digit identifier (COM-UUID)
    private String generateComUuid() {
        int uniqueId = 1000 + (int) (Math.random() * 9000); // Ensure 4-digit
        return Integer.toString(uniqueId);
    }

    // Getters for the component attributes


    public String getComUUID() {
        return comUUID;
    }

    public String getIpAdresse() {
        return ipAdresse;
    }

    public int getPort() {
        return port;
    }

    public boolean isStern() {
        return isStern;
    }

    public void setStar(){
        isStern=true;
    }

   // sag Hallo
   // frag nach Stern
   // werde Stern
}
