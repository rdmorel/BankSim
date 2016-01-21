// adapted from Carrano & Prichard, and Weiss
public class BankSim2 {
    
    /* Simulate a bank, with one teller and one line of customers
     * Begin with an array of Arrival events, and proceed chronologically
     * through the Events (Arrivals and Departures) while accumulating
     * data about average wait times. */
    
    public void simulate(Arrival[] inputData, int numDataPoints) throws RuntimeException {
        // inputData is an array of Arrivals, each with arrival time and duration
        // numDataPoints specifies how many Events are in the array
        
        QueueDSVector<Arrival> bankQ1 = new QueueDSVector<Arrival>();  // customers in line, rep'd by Arrivals
        QueueDSVector<Arrival> bankQ2 = new QueueDSVector<Arrival>();  // customers in line, rep'd by Arrivals

        SortedList<Event> eventList = new SortedList<Event>(); // sorted by time
        Event newEvent;
        int inputDataIndex = 0, numDepartures = 0;
        double totalWait = 0;
        
        newEvent = inputData[inputDataIndex++];  // put first event into list
        eventList.insert(newEvent);
        
        while(!eventList.isEmpty()) {
            newEvent = eventList.removeFirst();  
            System.out.println(newEvent);    // tell user about event
            
            if (newEvent instanceof Arrival) {
            	if(bankQ1.size()<=bankQ2.size()){
                processArrival((Arrival)newEvent, inputData, inputDataIndex, eventList, bankQ1, bankQ2,1);
                inputDataIndex++;
                }
            	else {
            		processArrival((Arrival)newEvent, inputData, inputDataIndex, eventList, bankQ1, bankQ2,2);
                    inputDataIndex++;
            	}
            }
            else if (newEvent instanceof Departure) {
                Departure newDep = (Departure)newEvent;
                processDeparture(newDep, eventList, bankQ1, bankQ2);
                numDepartures++;
                totalWait += newDep.getWait();
            }
            else
                throw new RuntimeException("Have a generic Event but need Arrival or Departure: " + newEvent);  // shouldn't happen!
        } // end while
        
        if (numDepartures != 0) {   // at end, report average wait time 
            System.out.println("Average wait to be served is " + 
                               (totalWait/numDepartures));
        }
    } // end simulate
    
    public void processArrival(Arrival arrEvent, Arrival[] inputData, int inputDataIndex,
          SortedList<Event> eventList, QueueDSVector<Arrival> bankQ1, QueueDSVector<Arrival> bankQ2, int Q) {
    	
    	if (Q == 1){
        boolean atFront = bankQ1.isEmpty();  // am I the only one here?
        
        bankQ1.enqueue(arrEvent);  // put new customer into bank line to wait
        arrEvent.whichQ = 1;
        System.out.println("Arrived in Q1");
        
        if (atFront) {    // if no other customers, then immediately get served
            Departure newDep = new Departure(arrEvent.getArrTime(), arrEvent);
            // because this customer's next Event will be a departure
            eventList.insert(newDep); // put the departure into eventList
        } // end if
        
        // and then, regardless of whether this customer was served immediately or not,
        // get the next customer (next Arrival) from the inputData array 
        if (inputDataIndex < inputData.length) {   
            Arrival newEvent = inputData[inputDataIndex]; // get the next arrival from inputData
            eventList.insert(newEvent);        // and put into eventList
        } // end if
    	}
    	if (Q == 2){
    		boolean atFront = bankQ2.isEmpty();  // am I the only one here?
            
            bankQ2.enqueue(arrEvent);  // put new customer into bank line to wait
            arrEvent.whichQ = 2;
            System.out.println("Arrived in Q2");

            
            if (atFront) {    // if no other customers, then immediately get served
                Departure newDep = new Departure(arrEvent.getArrTime(), arrEvent);
                // because this customer's next Event will be a departure
                eventList.insert(newDep); // put the departure into eventList
            } // end if
            
            // and then, regardless of whether this customer was served immediately or not,
            // get the next customer (next Arrival) from the inputData array 
            if (inputDataIndex < inputData.length) {   
                Arrival newEvent = inputData[inputDataIndex]; // get the next arrival from inputData
                eventList.insert(newEvent);        // and put into eventList
            } // end if
    	}
    } // end processArrival
    
    public void processDeparture(Departure depEvent, SortedList<Event> eventList,
                                 QueueDSVector<Arrival> bankQ1, QueueDSVector<Arrival> bankQ2) {
    	if (depEvent.getWhichQ() == 1){
        bankQ1.dequeue();  // the person at the front of the queue (who is actually being
        // served by the teller) is now done and leaves the bank
        System.out.println("Left Q1");

        
        if (! bankQ1.isEmpty()) {
            // next customer starts transaction (don't dequeue until transation is done)
            Departure newDep = new Departure(depEvent.getDepTime(), bankQ1.getFront()); 
            // this customer's next event is a departure
            eventList.insert(newDep);  // put the Departure into list
        } // end if
    	}
    	if (depEvent.getWhichQ() == 2){
    		bankQ2.dequeue();  // the person at the front of the queue (who is actually being
            // served by the teller) is now done and leaves the bank
            System.out.println("Left Q2");

            if (! bankQ2.isEmpty()) {
                // next customer starts transaction (don't dequeue until transation is done)
                Departure newDep = new Departure(depEvent.getDepTime(), bankQ2.getFront()); 
                // this customer's next event is a departure
                eventList.insert(newDep);  // put the Departure into list
            } // end if
    	}
    } // end processDeparture
    
    public void printList(SortedList<Event> theList) {  // for debugging only
        System.out.println(theList);
    }
} // end class
