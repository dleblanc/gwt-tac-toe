A simple Tic Tac Toe game written in GWT - mainly as an example of modern GWT MVP approaches, Gin+Guice injection, Declarative UI's and styles, Testing with JUnit 4 and Mockito, etc.

The maven integration isn't 100%, but it's enough to work standalone and in Eclipse.

I've left the Eclipse files in there so it can be imported quickly - I'm working with M2E - you should be able to import as an existing maven project. The "Run Dev Mode" launcher is included too, which should launch Dev Mode for the project.

Running:
 - Download the source and run "mvn clean package jetty:run-war -Djetty.port=9990"
 - Browse to localhost:9990/


--

 Dave LeBlanc <David.LeBlanc@WindhorseSoftware.com>
 December, 2011.
