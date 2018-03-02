package asssignmentalgo;
/* Author: Muhammad Bilal 
 * Title: JAVA Application that implements the AI Searching Algorithms
 * */
public class App {
    final static private String CURRENTSTATE = "724506831";
    final static private String GOAL = "012345678";



    public static void main(String[] args) {
        String rootState = CURRENTSTATE;
        Node rootNode=new Node(rootState);
        SearchTree search = new SearchTree(rootNode, GOAL);
        
        System.out.println("Depth First Search");
        search.depthFirstSearch();;
        
        System.out.println("Breadth First Search");
        search.breadthFirstSearch();
        
        System.out.println("Greedy Heuristic ONE Search");
        search.greedySearch(Heuristic.H_ONE);
        
        System.out.println("Greedy Heuristic TWO Search");
        search.greedySearch(Heuristic.H_TWO);
        
        System.out.println("A* Heuristic ONE Search");
        search.aStar(Heuristic.H_ONE);
  
        System.out.println("A* Heuristic TWO Search");
        search.aStar(Heuristic.H_TWO);

    }
}