import java.util.*;
import java.time.LocalDateTime;

public class Kernsystem {
    private String starUUID;
    private String solUUID;
    private String ipAdresse;
    private int starPort;
    private Map<String, Komponente> komponenten;
    private int maxKomponenten;
    private LocalDateTime initiierungZeitstempel;

    // Konstruktor
    public Kernsystem(String ipAdresse, int starPort, int maxKomponenten) {
        this.ipAdresse = ipAdresse;
        this.starPort = starPort;
        this.maxKomponenten = maxKomponenten;
        this.solUUID = generiereSolUUID();
        this.starUUID = generiereStarUUID();
        this.komponenten = new HashMap<>();
        this.initiierungZeitstempel = LocalDateTime.now();

        // Register SOL as the first component
        registriereSolKomponente();
    }

    private String generiereSolUUID() {
        // Random 4-digit number for SOL UUID
        return String.valueOf(1000 + new Random().nextInt(9000));
    }

    private String generiereStarUUID() {
        // Generate MD5 hash based on IP and SOL UUID for unique star ID
        return Utils.md5(ipAdresse + solUUID);
    }

    private void registriereSolKomponente() {
        // Register SOL itself in the star
        Komponente sol = new Komponente(solUUID, ipAdresse, starPort);
        komponenten.put(solUUID, sol);
    }

    // Empfange Broadcast-Anfrage und antworte
    public void empfangeBroadcastAnfrage() {
        // Code for listening to UDP "HELLO?" broadcast and responding with JSON data
    }

    // Registrierung einer neuen Komponente
    public synchronized boolean registriereKomponente(Komponente komponente) {
        if (komponenten.size() >= maxKomponenten) {
            System.out.println("Keine Plätze mehr verfügbar.");
            return false;
        }
        komponenten.put(komponente.getComUUID(), komponente);
        return true;
    }

    // Entfernen einer nicht mehr erreichbaren Komponente
    public synchronized void entferneKomponente(String comUUID) {
        komponenten.remove(comUUID);
    }

    // Aktualisiert den Status der Komponenten
    public void aktualisiereStatus() {
        // Loop through components and update last interaction time
    }

    // Prüft die Verfügbarkeit der Komponenten und entfernt inaktive
    public void pruefeKomponentenVerfügbarkeit() {
        // Check each component’s last interaction time every 60 seconds
    }
}
