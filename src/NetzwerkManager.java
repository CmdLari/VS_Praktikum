import java.io.IOException;
import java.net.*;

/*** @ author Mirzakarimova
 * Die Klasse repräsentiert die Handlung eines Netztwerkes, das einen Broadcast Nachricht an
 * aktiven Stern sendet, lauschen des Anworts und übergeben an Sternsystem
 */

public class NetzwerkManager {
    private static final int STARPORT = 8002; // die letzten Zahlen repräsentieren unseren Teamnummer
    private DatagramSocket udpSocket;

    public NetzwerkManager() throws SocketException {
        this.udpSocket = new DatagramSocket();
    }

    /**
     * um "HELLO?" zu senden
     * @throws IOException
     */
    public void sendeBroadcastHello() throws IOException {
        String nachricht = "HELLO?";
        byte[] daten = nachricht.getBytes();

        DatagramPacket packet = new DatagramPacket(
                daten, daten.length,
                InetAddress.getByName("255.255.255.255"),  // die globale Broadcast Addresse
                STARPORT
        );

        udpSocket.send(packet);
        System.out.println("Broadcast gesendet: " + nachricht);
    }

    /** lauscht auf Antwort und liest die eingehende Nachricht
     * @throws IOException
     */
    public void empfangeAntworten() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        udpSocket.receive(packet);
        String antwort = new String(packet.getData(), 0, packet.getLength());

        System.out.println("Antwort empfangen: " + antwort);
        // Here you would parse the JSON and pass it to SternSystem
    }
}
