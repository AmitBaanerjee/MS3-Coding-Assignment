# MS3-Coding-Assignment

<b>Summary</b> <br>
The project requirement was building of a java application to consume CSV data from a file consisting on 10 columns named from A to J. The number of rows of the cvs file was large and required an optimized coding algorithm for efficiency. While parsing through the input file, if there were any columns in a single row which were null the row was to be put in a MS3Interview_Bad.db database else the row was to be appended to the MS3Interview.db file. After the file was parsed, the number of successful insertions in MS3Interview.db , MS3Interview_Bad.db and total number of records parsed was to be logged in a logging file.

<b>Approach</b><br>
Based on the problem statement, i decided to develop the application using springboot framework. The application would require dynamically creating and writing to multiple databases and Springboot has an in memory H2 sql database which does not need configuration and hence it seemed an apt decision to use Spring.MyBatis is used for running the SQL statements from our spring application. Maven would be a useful tool for downloading the database,opencsv,mybatis and spring dependencies required to seemlessly running the application. Since there was nothing mentioned about how the application would get called by the user and the problem statement mentioned that this application would be a support app for a bigger code, i decided to create a REST service and expose the application as a web service so that users can easily call the application using a POST call attaching the CSV file in the body as a form-data. For the last part of the application which requires logging of successful entries, spring by default contains a logger functionality. I added the count entries into the logger info which can be seen in the server.log file very conveniently whenever the application is run.

<b>Steps for running the Application </b><br>
1)Install Eclipse or any other Java supported IDE. <br>
2)Install Postman for using the RESTFul API of our application to send CSV input files for computation.<br>
3)Clone or download the code from this github repository<br>
4)Open Eclipse and import this project into your workspace as the folder MS3-Coding-Assignment-master.<br>
5)From the eclipse marketplace, install m2e extension for using maven commands like maven clean and maven install.<br>
6)Right click on the project folder and run the application as maven clean.
7)Right click on the project folder and run the application as maven install.
8)Right click on the project folder and run the application as a java application.
9)On the console , you would be able to see the application is running successfully.
10)Start the postman app and send a post request to http://localhost:8080/assignment/import as import is our request mapping.
   Select form-data inside Body tab to send the csv file as a key and file. Enter "file" as the key and attach the file as          value.<br>
11)As the resonse comes as true,it means the application has sucessfully parsed the csv input file. <br>
12)To check the database entries, go to http://localhost:8080/h2 on google. Enter jdbc:h2:file:db:/MS3Interview as the JDBC          connecting URL. <br>
13)After logging to the Database, there are two tables each for successful entries and bad entries.<br>
14)To check the count entries, switch back to Eclipse IDE and open the console tab. It should show the following entries and their counts :- "Recieved records", "Successful insertion", "Unsuccessful Insertion". <br>
