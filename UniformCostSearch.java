// A Java program for UCS
// single source shortest path 
// algorithm. The program is for 
// adjacency matrix representation 
// of the graph. 
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
 
public class UniformCostSearch
{
    private PriorityQueue<Node> priorityQueue;
    private Set<Integer> settled;
    private int distances[];
    private int numberOfNodes;
    private int adjacencyMatrix[][];
    private LinkedList<Integer> path;
    private int[] parent;
    private int source, destination;
    public static final int MAX_VALUE = 999; 
 
    public UniformCostSearch(int numberOfNodes)
    {
        this.numberOfNodes = numberOfNodes;
        this.settled = new HashSet<Integer>();
        this.priorityQueue = new PriorityQueue<>(numberOfNodes, new Node());
        this.distances = new int[numberOfNodes + 1];
        this.path = new LinkedList<Integer>();
        this.adjacencyMatrix = new int[numberOfNodes + 1][numberOfNodes + 1]; 
        this.parent = new int[numberOfNodes + 1];
    }
 
    // Function that implements UCS
	// single source shortest path 
	// algorithm for a graph represented 
	// using adjacency matrix 
	// representation 
    public int uniformCostSearch(int adjacencyMatrix[][], int source, int destination)
    {
        int evaluationNode;
        this.source = source;
        this.destination = destination;
 
        for (int i = 1; i <= numberOfNodes; i++)
        {
            distances[i] = MAX_VALUE;
        }
 
        for (int sourcevertex = 1; sourcevertex <= numberOfNodes; sourcevertex++)
        {
            for (int destinationvertex = 1; destinationvertex <= numberOfNodes; destinationvertex++)
            {
                this.adjacencyMatrix[sourcevertex][destinationvertex] =
                       adjacencyMatrix[sourcevertex][destinationvertex];
	        }
        }
 
        // Making use of a priority queue to hold the data tree data
        priorityQueue.add(new Node(source, 0));
        distances[source] = 0;
 
        // Printing the node that is currently being evaluated.
        while (!priorityQueue.isEmpty())
        {
            evaluationNode = getNodeWithMinDistanceFromPriorityQueue();
            System.out.println("The eval Node is " + (evaluationNode-1));
            if (evaluationNode == destination)
            {
                return distances[destination];
            } 
            settled.add(evaluationNode);
            addFrontiersToQueue(evaluationNode);
        }
        return distances[destination];
    }
 
    // Function to add frontiers to the queue
    private void addFrontiersToQueue(int evaluationNode)
    {
        for (int destination = 1; destination <= numberOfNodes; destination++)
        {
            if (!settled.contains(destination))
            {
                if (adjacencyMatrix[evaluationNode][destination] != MAX_VALUE)
                {
                    if (distances[destination] > adjacencyMatrix[evaluationNode][destination]  
                                    + distances[evaluationNode])
                    {
                        distances[destination] = adjacencyMatrix[evaluationNode][destination]	
                                               + distances[evaluationNode]; 				 		
                        parent[destination] = evaluationNode;
                    }
 
                    Node node = new Node(destination, distances[destination]);
                    if (priorityQueue.contains(node))
                    {
                        priorityQueue.remove(node);
                    }
                    priorityQueue.add(node);
                }
            }
        }
    }
 
    private int getNodeWithMinDistanceFromPriorityQueue()
    {
        Node node = priorityQueue.remove();
        return node.node;
    }
 
    // Function to print path
    public void printPath()
    {
        path.add(destination);
        boolean found = false;
        int vertex = destination;
        while (!found)
        {
            if (vertex == source)
            {
                found = true;
                continue;
            }
            path.add(parent[vertex]);
            vertex = parent[vertex];
        }
 
        System.out.println("The Path between " + (source-1) + " and " + (destination-1) + " is ");
        Iterator<Integer> iterator = path.descendingIterator();
        while (iterator.hasNext())
        {
            System.out.print("-->"+(iterator.next()-1));
        }
    }
 
    public static void main(String... arg) throws FileNotFoundException
    {
        int adjacency_matrix[][];
        int number_of_vertices;
        int source = 0;
        int destination = 0;
        int distance;
        Scanner scan = new Scanner(System.in);
        try
        {
            number_of_vertices = 70;
            Scanner myReader = new Scanner(new File("out.txt"));
            adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];
            while(myReader.hasNextLine()){
                for (int i = 1; i <= number_of_vertices; i++)
                {   
                    for (int j = 1; j <= number_of_vertices; j++)
                    {
                        adjacency_matrix[i][j] = myReader.nextInt();
                        if (i == j)
                        {
                            adjacency_matrix[i][j] = 0;
                            continue;
                        }
                        if (adjacency_matrix[i][j] == 0)
                        {
                            adjacency_matrix[i][j] = MAX_VALUE;
                        }
                    }
                }
            }
            myReader.close();
            
            source = 46;
            destination = 1;
 
            UniformCostSearch uniformCostSearch = new UniformCostSearch(number_of_vertices);
            distance = uniformCostSearch.uniformCostSearch(adjacency_matrix,source, destination);
            uniformCostSearch.printPath();
 
            System.out.println("\nThe Distance between source " + (source-1) +
                          " and destination " + (destination-1) + " is " + distance);
 
        } catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input Format");
        }
        scan.close();
    }
}
 
class Node implements Comparator<Node>
{
    public int node;
    public int cost;
 
    public Node()
    {
 
    }
 
    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }
 
    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        if (node1.node < node2.node)
            return -1;
        return 0;
    }
 
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Node)
        {
            Node node = (Node) obj;
            if (this.node == node.node)
            {
                return true;
            }
        }
        return false;
    }
}