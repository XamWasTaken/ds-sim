package Workshop.src;

public class Resource {
    public enum State {
        inactive, booting, idle, active, unavailable
    }

    public String type;
    public State state;
    public int id;
    public int start;
    public int cores;
    public int memory;
    public int disk;
    public int wJobs;
    public int rJobs;

    Resource(String[] data) {
        this.type = data[0];
        this.id = Integer.parseInt(data[1]);
        this.state = State.valueOf(data[2]);
        this.start = Integer.parseInt(data[3]);
        this.cores = Integer.parseInt(data[4]);
        this.memory = Integer.parseInt(data[5]);
        this.disk = Integer.parseInt(data[6]);
        this.wJobs = Integer.parseInt(data[7]);
        this.rJobs = Integer.parseInt(data[8]);
    }

    public void print() {
        System.out.println(String.format("Resource: %s %s %d %d %d %d %d %d", this.type, this.state, this.start,
                this.cores, this.memory, this.disk, this.wJobs, this.rJobs));
    }
}