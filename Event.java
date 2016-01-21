// adapted from Carrano & Prichard
public abstract class Event implements Comparable<Event> {
    
   protected int whichQ;
    
    public Event() {
        whichQ = 1;
    }
    
    public int compareTo (Event other) {
        int thisTime = this.getTime();
        int otherTime = other.getTime();
        
        return (thisTime - otherTime);
    }
    
    public int getWhichQ() {
        return whichQ;
    }
    
    public void setWhichQ(int q) {
        if (q>=3)
            whichQ = 2;
        else if (q<=0)
            whichQ = 1;
        else
            whichQ = q;
    }
    
    public abstract int getTime(); 
    public abstract String toString(); 
    
} // end Event class
