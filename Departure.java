public class Departure extends Event {
    
    private int dep_time;
    private int wait;    
    
    public Departure(int currentTime, Arrival arr) {
    	this.whichQ = arr.getWhichQ();
        dep_time = currentTime + arr.getDuration(); // which will happen "duration" time units from now
        wait = currentTime - arr.getArrTime();     // and they've waited until now to start being served
    }

    
    public int getDepTime() {
        return dep_time;
    }
    
    public int getTime() {
        return dep_time;
    }
    
    public int getWait() {
        return wait;
    }
    
    public String toString() {
        return("\t\t\t\t\tDeparture event at time " + dep_time + " with wait " + wait); // + ", queue " + whichQ);
    }
}
