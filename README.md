# ft-demo
This project is executing the following test cases: 

1. Make sure we can register on the casino.
2. Make sure we can log in with an existing user.
3. Make sure we can deposit and check so the balance matches.
4. Make sure that we can play a game and the balance updates.
5. Make sure we can buy a lottery ticket and balance is updated.

In order to run entire test suite you have to modify "email" in DemoTest.java file to any else email and click run on "public class DemoTest"

You can also run TC separately. To do this you have to run first TC newUserRegistration() and then any other TC in separate.
Or you can run entire test suite and then any TC separately.

Some time some TC could fail because of some pop-ups does not apeared, and then it apeared in subsequent TC. 
In this case you should modify email and re-un all Test Suite from the start.

In current release we don't have proper balance calculation (after palying the game for example) beacuse we don't have knowledge on how this is implemented on back-end. 
This will be implemented in nest releases. 
