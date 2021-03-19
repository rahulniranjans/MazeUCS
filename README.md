# Maze UCS

Here's a full Uniform Cost Search Maze solver.
It returns a full shortest path to the end point if found.
MazeProject is an Image which contains the maze that is being traversed. The maze has been converted into a graph. The graph is shown in GraphMaze.
An adjacency matrix representation of the maze has been used.
The representations of nodes 1-44 are themselves. Whereas, The alphabets have been represented as shown:
a	-	45
b	-	46
c	-	47
d	-	48
e	-	49
f	-	50
g	-	51
h	-	52
i	-	53
j	-	54
k	-	55
l	-	56
m	-	57
n	-	58
o	-	59
p	-	60
q	-	61
s	-	62
t	-	63
u	-	64
v	-	65
w	-	66
y	-	67
z	-	68
g1	-	0
g2	-	69

The code follows the UCS approach and the starting point is A. The goal state is g1 and g2.
The adjacency matrix for the graph is stored in a file called out.txt and the resulting output screenshots along with maze representations have been attached.

Note: It is important to note that the BFS method for weighted graphs is also same as the Dijkstra's algorithm. If a weighted graph comes into picture, then the BFS algorithm can be modified slightly and it will result in the Dijkstra's algorithm.
