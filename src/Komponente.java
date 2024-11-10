import java.util.UUID;
public class Komponente {
    private String comUUID;
    private String ipAdresse;
    private int port;

    // Constructor to initialize a new component with IP and port
    public Komponente(String comUUID, String ipAdresse, int port) {
        this.comUUID = comUUID;
        this.ipAdresse = ipAdresse;
        this.port = port;
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
}
