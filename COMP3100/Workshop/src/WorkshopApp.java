package Workshop.src;

import java.util.ArrayList;


public class WorkshopApp {
    public static void main(String[] args) {
        Client client = new Client();

        client.send("HELO");
        client.rcv();

        client.send("AUTH MAX");
        client.rcv();

        client.send("REDY");
        client.rcv();

        client.send("GETS All");

        int[] dataInfo = client.rcvData();
        client.send("OK");

        ArrayList<Resource> resources = client.rcvResources(dataInfo[0]);

        resources.sort((a, b) -> {
            int comp = b.cores - a.cores;
            return comp;
        });

        resources.get(0).print();

        client.send("OK");
        client.rcv();

        client.send("QUIT");
        client.rcv();

        client.close();
    }
}
