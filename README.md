<h1>Introduction</h1>
<p>We want to create a Java implementation of the game called Battleships. We are going to write the code for the game “engine” – that is the part that manages the actual rules and framework of the game, NOT the user interface for it. As a consequence our displays may be a little crude but we should still be able to test the engine to ensure that it works properly.</p>
<h1>How to play</h1>
<p>Each player has a fleet of ships. A fleet is made up of one Battleship, two Cruisers, three Destroyers and three Submarines. The ships are numbered so that they can be identified. For example</p>
	"Ship#	Type	Length
	1	Battleship	4
	2	Cruiser	3
	3	Crusier	3
	4	Destroyer	2
	5 	Destroyer	2
	6 	Destroyer	2
	7	Submarine	1
	8 	Submarine	1
	9 	Submarine	1

<p>Each player uses two grids. The grids are usually 10x10 (i.e. the default size) but it would be useful to be able to vary the size. Because letters of the alphabet are used to identify the columns of the grid, restricting the size to a maximum of 26x26 would be a good idea. The same size is used for both grids.
One grid records where the player has “placed” the ships in their own fleet. A ship is placed by randomly choosing a location for it on the grid. Placing it involves filling in the appropriate number of squares represented by the length of the ship. The squares can be filled in horizontally or vertically, but not diagonally. For example, a Cruiser covers three squares so having randomly chosen a location for it you simply fill-in three adjacent squares.
Two ships cannot overlap (i.e. cannot be placed at the same square or share a square).
The squares can be “filled in” with any mark or symbol you prefer but it may be useful to use the number of the ship as the fill-in symbol. Using the number will allow you to easily locate/identify the ship in the fleet list and should facilitate operations like checking how long the ship is or what type it is, and so on.</p>
Here is an example of a grid with all the ships in the fleet placed. Note, the blank cells might actually have zeros in them. They are not shown here because they clutter the example.

	A	B	C	D	E	F	G	H	I	J
1	 	 	6	 	 	 	3	3	3	 
2	 	 	6	9	 	 	 	 	 	 
3	 	 	 	 	 	 	8	 	 	 
4	 	1	1	1	1	 	 	 	 	 
5	 	 	 	 	 	 	 	 	 	4
6	 	 	 	 	 	 	 	 	 	4
7	5	5	 	 	 	 	 	 	 	 
8	 	 	 	 	2	 	 	 	 	 
9	 	 	 	 	2	 	 	 	 	 
10	7	 	 	 	2	 	 	 	 	 

<p>The second grid records the locations that the player has chosen as targets for shots at the opponent’s fleet. Target squares could be selected randomly. Marking the previously selected targets ensures that a shot/turn is not wasted on a previously chosen target. The squares can be marked with any symbol. In the following example previously chosen targets are marked with the value 1. If you think it would be useful you could mark the shots that were hits with one symbol/number (e.g. 1) and shots that were misses with another (e.g. 2). As before, the blank cells might actually have zeros in them. Again, they are not shown here because they clutter the example.</p>

	A	B	C	D	E	F	G	H	I	J
1	 	 	 	 	 	 	1	 	 	 
2	 	 	 	 	 	 	 	 	 	 
3	 	 	 	 	 	 	 	 	 	 
4	 	 	1	 	 	 	1	 	 	 
5	 	 	 	 	 	 	 	 	 	 
6	 	 	 	 	 	 	 	 	 	 
7	 	 	 	 	 	 	 	 	 	 
8	 	 	1	 	 	 	1	 	 	 
9	 	 	 	 	 	 	 	 	 	 
10	 	 	 	 	 	 	 	 	 	 

<p>Players take turns to “shoot” at their opponents’ fleet by calling out a reference to a particular square (e.g. “A1” or “H4”). The opponent checks the fleet grid and reports whether the shot has missed or hit and if so, what it has hit (“you’ve hit a submarine” for example). The information contained in the report could be used to make more intelligent decisions about subsequent target choices. (Please note, this is not a requirement in your assignment solution. I am just mentioning it.)
Note, the player whose turn it is “calls out” the reference; the opponent has to do the checking. This will become important when we look at the code samples below.
If a player misses, it’s the end of their turn. If a player hits a ship then they are allowed to “shoot” again.
Play continues until one player wins by destroying their opponent’s fleet.</p>
<h1>Assignment</h1>
<p>You are required to write the code for a Player class. The class stores and manages the two grids, handles incoming shots and reports the success or failure of those shots.
The class should provide a default constructor that creates two 10x10 grids. It should also provide a constructor that allows the size of the grids to be specified, up to a maximum size of 26. If a grid size greater than 26 is specified the constructor should create a 12x12 grid.
The constructor should also place the ships on the grid.
The Player class should provide at least the following methods</p>

•	shipsRemaining has the following header
<p align="center"><b>public int shipsRemaining()</b></p>
shipsRemaining returns an integer value indicating the number of ships the player has remaining (i.e. the number of ships not totally destroyed)

•	display has the following header
<p align="center"><b>public void display()</b></p>
display displays the player’s fleet grid on the screen

•	nextTarget has the following header
<p align="center"><b>public String nextTarget()</b></p>
nextTarget randomly picks a grid square to shoot at in the opponents fleet. The method returns the cell reference (i.e. “F7” or “B3”) of the chosen square.

•	incoming has the following header
<p align="center"><b>public String incoming(String targetRef)</b></p>
incoming is the method used by opponents to shoot at the player’s fleet. The method should check if the square identified by targetRef contains a part of one of the ships in the player’s fleet. If it does then the opponent has had a hit and incoming should return an appropriate message. If it is not a square containing a part of one of the player’s ships then the method should return the word “Miss.”

<p>Consider the following code fragment and note how nextTarget and incoming are used.</p>
<p align="center>
public static void main(String[] args)
{
	Player computer = new Player() ;
	Player human = new Player() ;
	Boolean humansTurn = true ;
	String chosenTarget, outcome ;

	if(humansTurn) {
		// Human picks a target using ITS OWN nextTarget method
		chosenTarget = human.nextTarget() ; 
		// Human simulates “calling out” target reference by ‘asking’
		// or ‘getting’ the computer (i.e. the OPPONENT) to use its
		// incoming method to report if the shot was successful or not
		outcome = computer.incoming(chosenTarget) ; 
	} else {
		// Computer picks a target using ITS OWN nextTarget method
		chosenTarget = computer.nextTarget() ; 
		// Computer simulates “calling out” target reference by 
		// ‘asking’ or ‘getting’ the human (i.e. the OPPONENT)
		// to use its incoming method to report if the shot was
		// successful or not
		outcome = human.incoming(chosenTarget) ; 
	}
}
</p>
<p>You can include any other methods you feel are necessary to facilitate the efficient realisation of the Player class.
In addition to the Player class you must write a test method that tests the Player class. The test method should create two instances of the Player class and simulate a game. The test method should terminate when one of the players has won the game (i.e. when one of the player’s shipsRemaining method returns zero). For testing purposes it would be useful if all successful shots were reported to the screen (i.e. BlueJ terminal window). Saving the outputs to the terminal window to a file would also by helpful.</p>
<h1>Resources Provided</h1>
<p>The following java classes have been provided to assist you with the assignment…</p>
<p align="center>
Fleet.java	a java class to create a fleet object loaded with details of the ships in a fleet
Ship.java	a java class to create a ship object for each ship in a fleet
exploreClasses	a java class with a main method to let you explore the Fleet and Ship classes
</p>