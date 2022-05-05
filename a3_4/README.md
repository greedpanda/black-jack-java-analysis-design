# Assignment 3 - Grade 4
##### Albert Henmyr, Atakan Coban, Paolo Molinaro 

## Application purpose

This Java console application is developed with the purpose of having a
playable Black Jack game with a "modular set of rules".

_Note: Since a number of classes didn't change since the previous
implementation (Grade 3) we will skip the description of those, and we will focus only on the changes._

## View changes

This new version of the application includes a new package, gui.
In said package the Gui is implemented, and is composed of the following classes:
- **GuiLoader**: in charge of loading the GUI and building the scene.
- **GuiController**: the controller of the GUI, this class does the heavy lifting for the GUI implementation. 
- **CardGraphic**: has the task to generate and load the images for the cards.
- **CardUpdate**: functions as supporting class for the observer new card update, giving also a "screenshot" of the player hand.
- **RulesVisitor**: a necessary change of the previous RulesVisitor, with different types for returns to adapt to the GUI.

# GUI implementation report

Adding JavaFX to an existing project was a painful story since it felt dangerous to just slam two separate projects 
together. The journey took a lot of googling, ugly hacks and trial-and-error. At one point, we had a version that 
would run for one of us but not build, and for another, it would build but not run.

We tried to maintain the MVC structure by making the GUI implement View and hack it into being passive, but it seems 
that the GUI runs in its own while-loop and the controller we used for the console version doesn't get to do 
anything until the GUI is closed. So, we had to give that up.

Also, the way a JavaFX controller works, it really felt like it belonged in the view package. Putting it in the 
controller package meant we had to make a lot of the view package public for it to work. We had it like that for a 
while, but placed it in the view eventually. It made more sense there and the only argument against it was really 
getting to call it MVC structure.

Animation was another problem, because pausing the app was an adventure all in its own. Thread.sleep() didn't seem 
like the way forward. The problem with pauses was generally that we could tell the program to "pause for this long, 
then do this", but it didn't wait for the pause to finish to execute any code that came after that. In effect this 
meant that when the user pressed Stand and it was time to animate the dealer's decisions, the app immediately 
announced the winner.

We solved this by storing snapshots of the players' hands every time there was an update in the observer. We also 
threw in an ugly hack of a fake snapshot at the end of the data structure, that essentially told the app to check for 
the winner when it had gone through all the stored observer updates.

On a positive note, the model went almost completely unchanged through all this. The only modification we made was 
to add a boolean to the observer to communicate whether a new card was actually a new card or just an existing card 
being revealed. We felt it was the model's responsibility to communicate this, rather than any other part comparing 
old/new versions of hands.