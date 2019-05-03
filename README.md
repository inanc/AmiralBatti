# Amiral Battı Game (Sea Battle) 

A java application built for my CENG 201

The game is played on a grid where the grid is typically a square and the squares in the grid are identified by two numbers for the grid positions.  The positions are numbered from 0 to n for a nxn grid. The top left corner will be (0,0). For instance, for a 10x10 grid valid cells are (0,0)(0,1)(0,2) ....(0,9)(1,0)(1,1).....(1,9)....(9,9) which is totally 100 cells. 


| Ship        | Size          
| ------------- |:-------------:| 
| Battleship     | 4 | 
| Destroyer |3      |  
| Submarine | 2      | 
| Boat | 1      | 

Each ship occupies some fixed number of consecutive squares on the grid arranged secretly either horizontally or vertically. 

| Equipment | Property| Example
| ------------- |:-------------:| -------------:|
| Gun Shot | Shots 1 square at the specified position | Gun Shot made to (1,2)-->shots the position (1,2)|
| Hand Bomb |Shots 3 squares; horizontally left, right of the specified cell and the cell itself.       |  Hand Bomb sent  to (1,2)-->shots the positions (1,1),(1,2), and (1,3)|
| Rocket | Shots 5 squares; horizontally left, right of the specified cell, vertically up and down of the specified cell and the cell itself.     | Rocket sent  to (1,2)-->shots the positions (1,1),(1,2),(1,3),(0,2) and (2,2)|


|Difficulty Level|	Grid Size	|Ships	|Equipment of the Player |
| ------------- |:-------------:| -------------:|-------------:|
|Easy	|10x10	|•	1 boat, •	1 submarine, •	1 destroyer	1 rocket, |2 hand bombs and 7 gun shots|
|Normal|	15x15|	•	2 boats, •	1 submarine,•	1 destroyer•	1 carrier	1 rocket, |3 hand bombs and 10 gun shots|
|Hard |	20x20|	•	2 boats, •	1 submarine,•	1 destroyer•	1 carrier	1 rocket, |4 hand bombs and 12 gun shots|

How to Play the Game
-----
+	In each turn, the player attempts to “hit” a square from the grid by entering  two numbers which corresponds to a cell of the grid. 
+	If there exists a ship at the specified position, it is a hit.
+	When all the parts of a ship is destroyed, then that ship is sunk. 
+	The player does not know where the ships are. 
+	However, when a ship is hit and sinks, that is all parts of the ship is destroyed, the program prints out a message "You just sank a XXX." where XXX is the ship type.
+	After each shot, the computer redisplays the grid with the new information. How to display the grid is described below.
+	A ship is "sunk" when every square of the ship has been hit. Thus, it takes four hits  to sink a battleship, three to sink a destroyer, two for a submarine, and one for a boat. 
+	The main objective of the game  is to sink every ship in the grid with the available equipment. For instance, in order to win a game in easy mode, user has to shot all ships with 1 rocket, 2 hand bombs and 7 gun shots.


Input
-----

    [Weapon Number]
    [X coordinate]
    [Y coordinate]


Display
-----

+ 'S' indicate a location that the player have fired upon and hit a ship
+ '-' indicate a location that the player have fired upon and found nothing there
+ 'x' indication location containing a sunken ship 
+ '.' indicate a location that the player have never fired upon.   


           0     1     2    3    4    5    6     7    8    9
      0    .     .    .    .    .    .     .    .    .     .
      1    .     .    -    .    .    .     .    .    .     .
      2    .     x    x    x    .    .     .    .    .     .
      3    .     .    -    .    -    .     .    .    .     .
      4    .     .    .    .    .    .     .    .    .     . 
      5    .     -    S    S   .    .      .    .    .     .
      6    .     .    .    .    .    .     .    .    .     .
      7    .     .    .    .    .    .     .    .    .     .
      8    .     .    .    .    .    .     .    .    .     .
      9    .     .    .    .    .    .     .    .    .     .
      
 
 
 + Here in this sample, the player shots a destroyer at position (2,1)(2,2,)(2,3). The player may have shot it with a rocket since the up and down cells of (2,2) have also been shot. But, since there is no ship there, it is not a hit for the player. Therefore, if you use a rocket or bomb, only the parts that hit a ship will be displayed with x(for sunk) or S(for hit but the ship not sunk). The other cells should be displayed with '-'.


