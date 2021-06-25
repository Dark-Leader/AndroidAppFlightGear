# EX3 - Android App
**Flight Simulator App**


Creators: Sam Katz, Amit Ben Shimon

**Main Project Features:**
- Connect to flightgear simulator via socket and control the plane with the app.
- Two seek bars that allows the user to control the *rudder* and *throttle* values.
- Custom Joystick which allows the user to control the *aileron* and *elevator* values.

Together The user can control the plane via the app.

**Project Structure:**
- All view related object are inside the View Folder.
- All viewModel related objects are inside the viewModel Folder.
- All Model related objects are inside the Model Folder.


**Requirements:**

- Flight simulator (preferably the latest version, not necessary though).
- Android studio (preferably the latest version, not necessary though).

**How To Use:**
1.	Run the command 


	```ipconfig```
  
 in cmd and look for the IPv4 field in your wifi / wired connection section.
 
2.	Open FlightGear and Navigate to the settings section and add the following line in the additional settings section:

        --telnet=socket,in,10,127.0.0.1,6400,tcp
        
3.	Run the app from android studio ( or your preffered IDE ).
4.	Click the `fly` button in FlightGear and wait for the map to load.
5.	Enter the Ip address you found in cmd inside the IP Address text box.
6.	Enter `6400` inside the Port number text box.
7.	Click the `connect` button in the app.
8.	Click the `Cessna` tab in flightgear and then click start engine button from the drop menu.

From this point you have complete control over the plane.

Move the bottom seek bar left or right to change the values of the rudder (left -> decrease, right -> increase).

Move the left seek bar up or down to change the values of the throttle (down -> decrease, up -> increase).

Move the joystick up or down to change the values of the Elevator (down -> decrease, up -> increase).

Move the joystick left or right to change the values of the Aileron (left -> decrease, right -> increase).


**For More Information:**
Please watch our short premiere for our app at Youtube: https://youtu.be/axhPvu0AaMs
