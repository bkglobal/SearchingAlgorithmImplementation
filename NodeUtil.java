package asssignmentalgo;

import java.util.*;


public class NodeUtil { // USE
    public static List<String> getNextNodes(String st) {
        List<String> next = new ArrayList<String>();
        if(st.indexOf("0")== 0)
        {
            next.add(st.replace(st.charAt(0), 'x').replace(st.charAt(1), st.charAt(0)).replace('x', st.charAt(1)));
            next.add(st.replace(st.charAt(0), 'x').replace(st.charAt(3), st.charAt(0)).replace('x', st.charAt(3)));
        }
        else if(st.indexOf("0")== 1)
        {
            next.add(st.replace(st.charAt(1), 'x').replace(st.charAt(0), st.charAt(1)).replace('x', st.charAt(0)));
            next.add(st.replace(st.charAt(1), 'x').replace(st.charAt(2), st.charAt(1)).replace('x', st.charAt(2)));
            next.add(st.replace(st.charAt(1), 'x').replace(st.charAt(4), st.charAt(1)).replace('x', st.charAt(4)));
        
        }
        else if(st.indexOf("0")== 2)
        {
            next.add(st.replace(st.charAt(2), 'x').replace(st.charAt(1), st.charAt(2)).replace('x', st.charAt(1)));
            next.add(st.replace(st.charAt(2), 'x').replace(st.charAt(5), st.charAt(2)).replace('x', st.charAt(5))); 
        }
        else if(st.indexOf("0")== 3)
        {
            next.add(st.replace(st.charAt(3), 'x').replace(st.charAt(0), st.charAt(3)).replace('x', st.charAt(0)));
            next.add(st.replace(st.charAt(3), 'x').replace(st.charAt(4), st.charAt(3)).replace('x', st.charAt(4)));
            next.add(st.replace(st.charAt(3), 'x').replace(st.charAt(6), st.charAt(3)).replace('x', st.charAt(6))); 
        }
        else if(st.indexOf("0")== 4)
        {
            next.add(st.replace(st.charAt(4), 'x').replace(st.charAt(1), st.charAt(4)).replace('x', st.charAt(1)));
            next.add(st.replace(st.charAt(4), 'x').replace(st.charAt(3), st.charAt(4)).replace('x', st.charAt(3)));
            next.add(st.replace(st.charAt(4), 'x').replace(st.charAt(5), st.charAt(4)).replace('x', st.charAt(5)));
            next.add(st.replace(st.charAt(4), 'x').replace(st.charAt(7), st.charAt(4)).replace('x', st.charAt(7)));
        }
        else if(st.indexOf("0")== 5)
        {
            next.add(st.replace(st.charAt(5), 'x').replace(st.charAt(2), st.charAt(5)).replace('x', st.charAt(2)));
            next.add(st.replace(st.charAt(5), 'x').replace(st.charAt(4), st.charAt(5)).replace('x', st.charAt(4)));
            next.add(st.replace(st.charAt(5), 'x').replace(st.charAt(8), st.charAt(5)).replace('x', st.charAt(8)));
        }
        else if(st.indexOf("0")== 6)
        {
            next.add(st.replace(st.charAt(6), 'x').replace(st.charAt(3), st.charAt(6)).replace('x', st.charAt(3)));
            next.add(st.replace(st.charAt(6), 'x').replace(st.charAt(7), st.charAt(6)).replace('x', st.charAt(7)));
        }
        else if(st.indexOf("0")== 7)
        {
            next.add(st.replace(st.charAt(7), 'x').replace(st.charAt(4), st.charAt(7)).replace('x', st.charAt(4)));
            next.add(st.replace(st.charAt(7), 'x').replace(st.charAt(6), st.charAt(7)).replace('x', st.charAt(6)));
            next.add(st.replace(st.charAt(7), 'x').replace(st.charAt(8), st.charAt(7)).replace('x', st.charAt(8)));
        }
        else if(st.indexOf("0")== 8)
        {
            next.add(st.replace(st.charAt(8), 'x').replace(st.charAt(5), st.charAt(8)).replace('x', st.charAt(5)));
            next.add(st.replace(st.charAt(8), 'x').replace(st.charAt(7), st.charAt(8)).replace('x', st.charAt(7)));
        }

      
        return next;


    }



    /**
     This is Function
     To Display the Nodes Next to the Parent........ 
     */
    public static void display(Node gNode, Set<String> vn, Node root, int count) {  //USE

        int totalCost = 0;

        Stack<Node> sst = new Stack<Node>();
        sst.push(gNode);
        while (!gNode.getState().equals(root.getState())) {
            sst.push(gNode.getParent());
            gNode = gNode.getParent();
        }
        String source = root.getState();
        String stDestination;
        int cost = 0;
        for (int i = sst.size() - 1; i >= 0; i--) {
            stDestination = sst.get(i).getState();
            if (!source.equals(stDestination)) {
                cost = Character.getNumericValue(stDestination.charAt(source.indexOf('0')));
                totalCost += cost;
            }
           source = stDestination;        
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("- Transition from initial to Goal (Number of Steps):  " + (sst.size() - 1) +  "\n- Cost in Total: " + totalCost + "\n- Count of Nodes (no. of Nodes Expanded): " + count);
        System.out.println("---------------------------------------------------------");

    }


   

    public static StepToMove findTransition(String initial, String finalv) {      // USE
        int diff = finalv.indexOf('0') - initial.indexOf('0');
        switch (diff) {
            case -3:
                return StepToMove.TOP;
            case 3:
                return StepToMove.BOTTOM;
            case 1:
                return StepToMove.LEFT;
            case -1:
                return StepToMove.RIGHT;
        }
        return null;
    }
}




