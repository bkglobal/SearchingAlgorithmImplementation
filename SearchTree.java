package asssignmentalgo;

import java.util.*;

public class SearchTree {
    private Node root;
    private String goal;
    
    public SearchTree(Node root, String goalSate) {
        this.root = root;
        this.goal = goalSate;
    }
    
    public String getGoalSate() {
        return goal;
    }

    public void setGoalSate(String goalSate) {
        this.goal = goalSate;
    }



    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

   

  

    //******************************************************************************************************
    //    breadthFirstSearch() find the goal state using Breadth First Search algorithm
    //    we start with the initial state. check if it is goal or not and expand its children if it is not the goal.
    //    pop the first element of the queue and check if it is goal node. if not add its children to the queue.
    //    repeat the process untill the solution is found


    public void breadthFirstSearch() {
        // stateSet is a set that contains node that are already visited
        HashSet<String> hs = new HashSet<String>();
        int totalCost = 0;
        int time = 0;
        Node node = new Node(root.getState());
        Queue<Node> queue = new LinkedList<Node>();
        Node cNode = node;
        while (!cNode.getState().equals(goal)) {
            hs.add(cNode.getState());
            List<String> nodeSuccessors = NodeUtil.getNextNodes(cNode.getState());
            for (String n : nodeSuccessors) {
                if (hs.contains(n))
                    continue;
                hs.add(n);
                Node child = new Node(n);
                cNode.addChild(child);
                child.setParent(cNode);
                queue.add(child);

            }
            cNode = queue.poll();
            time += 1;
        }

        NodeUtil.display(cNode, hs, root, time);

    }
//**********************************************************************************************

    public void depthFirstSearch() {
        // stateSet is a set that contains node that are already visited
        Set<String> hs = new HashSet<String>();
        int totalCost = 0;
        int time = 0;
        Node node = new Node(root.getState());
        //the queue that store nodes that we should expand
        MyQueue<Node> queue = new MyQueue<>();
        //the queue that contains the successors of the expanded node
        MyQueue<Node> nodeQueueSuccessor = new MyQueue<>();
        Node cNode = node;
        while (!cNode.getState().equals(goal)) {
            hs.add(cNode.getState());
            List<String> nodeSuccessors = NodeUtil.getNextNodes(cNode.getState());
            for (String n : nodeSuccessors) {
                if (hs.contains(n))
                    continue;
                hs.add(n);
                Node child = new Node(n);
                cNode.addChild(child);
                child.setParent(cNode);
                nodeQueueSuccessor.enqueue(child);

            }
            //we add the queue that contains the successors of the visted node to the beginning of the main queue
            queue.addQueue(nodeQueueSuccessor);
            //successors queue should be cleared in order to store the next expaneded's successors
            nodeQueueSuccessor.clear();
            cNode = queue.dequeue();
            time += 1;
            nodeSuccessors.clear();
        }
        NodeUtil.display(cNode, hs, root, time);

    }


    /**
     * Find the goal using Best Search First algorithm. The heuristic is
     * the estimated cost from the current node to the goal node
     */
    public void greedySearch(Heuristic heuristic) {
        int totalCost = 0;
        Set<String> hs = new HashSet<String>();
        int time = 0;
        Node node = new Node(root.getState());
        node.setCost(0);

        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();

        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(10, nodePriorityComparator);
        Node cNode = node;
        while (!cNode.getState().equals(goal)) {
            hs.add(cNode.getState());
            List<String> nodeSuccessors = NodeUtil.getNextNodes(cNode.getState());
            for (String n : nodeSuccessors) {
                if (hs.contains(n))
                    continue;
                hs.add(n);
                Node child = new Node(n);
                cNode.addChild(child);
                child.setParent(cNode);
                if(heuristic == Heuristic.H_ONE)
                    child.setTotalCost(0, h1(child.getState(), goal));
                else if (heuristic == Heuristic.H_TWO)
                    child.setTotalCost(0, h2(child.getState(), goal));
    
                nodePriorityQueue.add(child);

            }
            cNode = nodePriorityQueue.poll();
            time += 1;
        }
        // Here we try to navigate from the goal node to its parent( and its parent's parent and so on) to find the path
        NodeUtil.display(cNode, hs, root, time);

    }


    /**
     A* Searching Using Heuristic Function
     */
    public void aStar(Heuristic heuristic) {
        int totalCost = 0;
        Set<String> hs = new HashSet<String>();
        int time = 0;
        Node node = new Node(root.getState());
        node.setTotalCost(0);

        // the comparator compare the cost values and make the priority queue to be sorted based on cost values
        NodePriorityComparator comparePriority = new NodePriorityComparator();

        // a queue that contains nodes and their cost values sorted. 10 is the initial size
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(10, comparePriority);
        Node cNode = node;
        while (!cNode.getState().equals(goal)) {
            hs.add(cNode.getState());
            List<String> nodeSuccessors = NodeUtil.getNextNodes(cNode.getState());
            for (String n : nodeSuccessors) {
                if (hs.contains(n))
                    continue;
                hs.add(n);
                Node child = new Node(n);
                cNode.addChild(child);
                child.setParent(cNode);

                if (heuristic == Heuristic.H_ONE)
                    child.setTotalCost(cNode.getTotalCost() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), h1(child.getState(), goal));
                else if (heuristic == Heuristic.H_TWO)
                    child.setTotalCost(cNode.getTotalCost() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), h2(child.getState(), goal));
                nodePriorityQueue.add(child);

            }
            cNode = nodePriorityQueue.poll();
            time = time + 1;
        }
        NodeUtil.display(cNode, hs, root, time);
    }


    //------------------------------------------------------------------------------------
    // =Heuristic For misplaced Tiles
    private int h1(String current, String goal) {
        int count = 0;
        for (int i = 0; i < current.length(); i += 1)
            if (current.charAt(i) != goal.charAt(i))
                count += 1;
        return count;
    }

    //-----------------------------------------------------------------
    // Heuristic for Manhtann Distance
    private int h2(String current, String goal) {
        int calcDiff = 0;
        int clength=current.length();
        int glength=goal.length();
        for (int i = 0; i < clength; i += 1)
            for (int j = 0; j < glength; j += 1)
                if (current.charAt(i) == goal.charAt(j))
                    calcDiff = calcDiff + ((Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 + j / 3));
        return calcDiff;
    }

}