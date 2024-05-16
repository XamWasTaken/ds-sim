package Workshop.src;

import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class Client {
    public Socket clientSocket;
    public PrintWriter writer;
    public BufferedReader reader;

    Client() {
        try {
            int port = 50000;
            this.clientSocket = new Socket("127.0.0.1", port);
            this.writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void send(String msg) {
        System.out.println(String.format("Sending: %s", msg));
        writer.println(msg);
    }

    public String rcv() {
        try {
            String receivedMsg = this.reader.readLine();
            // System.out.println(String.format("Received: %s", receivedMsg));
            return receivedMsg;
        } catch (IOException e) {
            System.out.println("Error reading from socket: " + e.getMessage());
            return "ERR";
        }
    }

    public int[] rcvData() {
        try {
            String receivedMsg = this.reader.readLine();
            String[] tokens = receivedMsg.split(" ");
            if (tokens.length != 3) {
                throw new IllegalArgumentException("Unexpected DATA msg");
            } else {
                int length = Integer.parseInt(tokens[1]);
                int size = Integer.parseInt(tokens[2]);
                int[] values = new int[] { length, size };
                System.out.println(String.format("Received values: %d %d", values[0], values[1]));
                return values;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Unexpected DATA msg", e);
        }
    }

    public ArrayList<Resource> rcvResources(int count) {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        for (int i = 0; i < count; i++) {
            resources.add(new Resource(this.rcv().trim().split(" ")));
        }
        return resources;
    }

    public void close() {
        try {
            this.reader.close();
            this.writer.close();
            this.clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}