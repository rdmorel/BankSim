# BankSim
Simulation of lines for bank tellers

Assignment:

1. Download the files for the bank simulation we went over in class on Thursday. (There are a bunch of files, so just get them from the main course Moodle page.)

Modify these classes to create the following variation:

Variation 1 Simulate a bank with two tellers, each with his/her own queue of customers. Assume that each customer, when arriving, gets at the back of the shorter queue. If both are the same length, put the customer in the first queue. Assume that customers remain in the same queue they are initially placed in - no moving to a shorter queue while waiting to be served.
Note: Test your modified simulations using RunBankSim and RunBankSim2. Carefully work out, by hand, the expected departure times and average wait for these customers, and make sure that your program's output matches! Note that while we did work out RunBankSim's expected departure times and waits in class, that was for one teller/one line; having 2 of each will give different results.

Once you have tested your program and are confident that it works correctly, modify the RunBankSimRandom driver to generate a random collection of 160 customers with start times randomly chosen between 1 and 400 and durations randomly chosen between 2 and 8 time units. Run this five times and for each run, report both the average wait time and the departure time of the final customer.

(How did I pick the numbers of customers, length of simulation, etc? Notice that the transaction durations will average 5 minutes, so with 160 customers we expect roughly 800 customer-minutes. With two tellers working 400 minutes each, that's 800 teller-minutes (plus extra time at the end of the day to finish processing customers already in line). Given this equivalence, we'd expect to have the tellers busy most of the time, which is efficient for the bank. But what's the cost in waiting time for the customers? Notice also that a more thorough simulation might take a more complete set of statistics, including the variance of customer waiting times, the maximum waiting time, etc. If you would like to, you are very welcome to extend the statistics reported for each simulation, and I will give a very small amount of extra credit for doing so.)

Turn in: Your modified classes, and a text file reporting the average wait and latest departure time for each of your five runs with the randomly generated collections, and also telling me which methods you modified in which classes. Note that I will test your simulations using RunBankSim2 (as well as another driver program), so make sure you get the right answers.

2. OPTIONAL:

If you choose, you may also implement this variation:

Variation 2 Simulate a bank with two tellers, with one shared queue of customers. Note that it may make sense to remove each customer from the queue when his/her transaction begins, rather than waiting until it ends - but that's up to you.
Variation 3 Simulate a bank with two tellers, each with his/her own line, as in Variation 1. However, simulate the possibility that customers will switch queues if the queue they aren't in becomes shorter. Make reasonable design choices; I'd suggest that queue-switches only happen if the difference in queue length is at least 2, and that even in those conditions the switch may or may not happen (depending on some random number generated, say).
If you choose to either of these variations, run both Variation 1 and the other variation on the same randomly-generated data. Report the statistics on both/all variations; it might be instructive to compare them.

Turn in: Your modified classes, and a text file reporting the average wait and latest departure time for each of your five runs with the randomly generated collections, and also telling me both which classes go with which variation and which methods you modified in which classes.
