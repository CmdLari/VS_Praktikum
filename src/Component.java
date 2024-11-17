import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class Component {
    private String comUUID; // unique identifier
    private int comUUIDint;
    private InetAddress ipAdresse; // ip address of the participant
    private int port;
    private boolean isSol;
    private Map<String, Component> components;
    private int maxComponents;
    private LocalDateTime initialisedTime;
    private DatagramSocket udpSocket;
    private Map sol;

    // Constructor
    public Component(int port, int maxComponents) throws UnknownHostException, SocketException {
        generateComUuid();
        readIP();
        this.port = port;
        this.isSol =false;
        components = new HashMap<String, Component>();
        maxComponents = maxComponents;
        initialisedTime = LocalDateTime.now();
        udpSocket = new DatagramSocket();
        components = null;
        sol = HashMap.newHashMap(5);
        enterSystem();
    }

    // PRIVATE

    /**
     * generates 4 char identifier
     */
    private void generateComUuid() {
        comUUIDint = 1000 + (int) (Math.random() * 9000); // Ensure 4-digit
        assert comUUID != null;
        comUUID = Integer.toString(Integer.parseInt(comUUID));
    }

    private void readIP() throws UnknownHostException {
        ipAdresse = InetAddress.getLocalHost();
    }

    private void startListening() {
        Thread listenerThread = new Thread(() -> {
            try {
                receiveMessage();
            } catch (IOException e) {
                System.err.println("Error while receiving messages: " + e.getMessage());
            }
        });
        listenerThread.start();
    }

    private void enterSystem(){
        try {
            // Bind the UDP socket to the port if not already bound
            if (udpSocket == null || udpSocket.isClosed()) {
                udpSocket = new DatagramSocket(port);
                System.out.println("Socket bound to port: " + port);
            }

            // Broadcast HELLO to announce presence
            sendMessage("HELLO");
            System.out.println("Broadcasted HELLO message to join the system.");

            // Start listening for responses (can be multithreaded for non-blocking behavior)
            startListening();

        } catch (SocketException e) {
            System.err.println("Failed to bind socket to port: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error during communication: " + e.getMessage());
        }
    }

    private void exitSystem(){
        // todo
    }

    private void getSol() throws IOException {
        if(isSol){
            System.out.println("This component is SOL");
        } else {
            System.out.println("This component is looking for SOL");

        }
        // todo
        // Broadcast hello
        sendMessage("HELLO");
        // if answer = sol:
        // sol = answer
        // else:
        // sol = this
        // isSol = true
    }

    private void identifySol(JSONObject sol){
        sol.put("star", sol.get("star"));
        sol.put("sol", sol.get("sol"));
        sol.put("sol-ip", sol.get("sol-ip"));
        sol.put("sol-tcp", sol.get("sol-tcp"));
        sol.put("component", sol.get("component"));

    }

    private void becomeSol(){
        // todo
    }

    private void sendMessage(String message) throws IOException {
        sendMessage(message, InetAddress.getByName("255.255.255.255"));  // global Broadcast Address
    }

    private void sendMessage(String message, InetAddress address) throws IOException {
        byte[] data = message.getBytes();

        DatagramPacket packet = new DatagramPacket(
                data, data.length,
                address,
                port);

        udpSocket.send(packet);
        System.out.println("Sent: " + message);
    }


    private void receiveMessage() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while(true) {
            udpSocket.receive(packet);
            String readMessage = new String(packet.getData(), 0, packet.getLength());

            System.out.println("Received: " + readMessage);
        }

    }


    // GETTER

    public String getComUUID() {
        return comUUID;
    }

    public int getComUUIDint() {return comUUIDint;}

    public InetAddress getIpAdresse() {
        return ipAdresse;
    }

    public int getPort() {
        return port;
    }

    public boolean isSol() {
        return isSol;
    }


}
