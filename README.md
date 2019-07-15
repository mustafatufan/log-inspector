# Log Inspector
:squirrel: Log Inspector is an interview project.

###### Output Example
```
mustafatufan$ java -jar assignment.jar examples/timing.log 10

⌕ Log Inspector Result

✋ Top 10 Resources by Average Duration

# 1 /saldoDetails.do - (1768.67 ms)
# 2 /changeDueDate.do - (1626.5 ms)
# 3 action=BILLING - (1455.4 ms)
# 4 /do/wololo/play - (1337 ms)
# 5 getBroadbandSubscriptions - (1179.36 ms)
# 6 action=SUBSCRIPTION - (1043.64 ms)
# 7 updateSubscriptionFromBackend - (972.73 ms)
# 8 /api - (702.67 ms)
# 9 /saveECMPreferences.do - (653 ms)
#10 action=NOTIFICATIONS - (603.95 ms)

⏰ Histogram Based on Hours

Hour | 0% ███████████████████████████████████ 100%
_____|____________________________________________
  00 | █████████████████████████████
  01 | ██
  02 | █
  03 | █
  04 | █
  05 | █
  06 | █
  07 | ██████
  08 | █
  09 | █
  10 | █
  11 | █
  12 | ██
  13 | ██
  14 | █
  15 | █
  16 | ███
  17 | █
  18 | █
  19 | █
  20 | █
  21 | █
  22 | █
  23 | █

⏭ Total Runtime: 301 ms
```

## Introduction
Here is a short snippet of a log file. The goal of the assignment is to create a program that parses the log file and extracts, aggregates and presents the data from it. The log file contains request durations. The file format is best explained with the following examples:
```
2015-08-19 00:00:01,049 (http--0.0.0.0-28080-405) [] /checkSession.do in 187
2015-08-19 00:00:01,963 (http--0.0.0.0-28080-245) [] /checkSession.do in 113
2015-08-19 00:00:02,814 (http--0.0.0.0-28080-245) [CUST:CUS5T27233] /substypechange.do?msisdn=300501633574 in 17
2015-08-19 00:00:03,260 (http--0.0.0.0-28080-245) [CUST:CUS5T27233] /mainContent.do?action=TOOLS&contentId=main_tools in 5
```
##### It contains:
1. `date`
2. `timestamp`
3. `thread-id` (in brackets)
4. optional `user context` (in square brackets)
5. `URI + query string`
6. string `"in"`
7. `request duration` in milliseconds

...and...
```
2015-08-19 00:04:45,212 (http--0.0.0.0-28080-405) [] updateSubscriptionFromBackend 300445599231 in 203
2015-08-19 00:04:45,259 (http--0.0.0.0-28080-405) [ASP CUST:CUS5T27233] getPermission 300445599231 in 32
```
##### which contains:
1. `date`
2. `timestamp`
3. `thread-id` (in brackets)
4. optional `user context` (in square brackets)
5. `requested resource name` (one string)
6. `data payload elements` for resource (0..n elements)
7. string `"in"`
8. `request duration` in milliseconds
### What to do?
##### Required interface:
1. The program needs to run from command line and must take log filename as an argument.
2. The program must display help information documenting how to use it when running with `-h` argument 
###### Sample of expected command line:
```
aks-mbp:dist ak$ java -jar assignment.jar timing.log 10
```
##### Functional requirements:
1. Print out top `n` (exact value of `n` is passed as program argument) resources with highest average request duration.
2. Draw histogram of hourly number of requests.
3. Print out number of (milli)seconds your program run.
### Compilation and test environment
Do not send compiled code. We will compile the code ourselves. Prepare ant build file which default target creates jar packed executable to `dist` subfolder. We will compile and run the program in following environment:
1. Java 8 SE
2. Ant 1.9

Ask (and justify) if you want to use any other specific environment. The solution must be self containing - We don't want package managers to download "internet" to our test environment. If you use external libraries in your solution ship them with your code.
### Notes
- When calculating the average request duration. `URI + query string != resource`. Decide if any and which part of the query string might define unique resource and group the requests based on justified logic. Be prepared to explain why did you use this particular grouping.
- Do not expect that the sample file provided contains samples of all possible resources.
## License
[MIT](LICENSE)

