package dfs;

public class CloneGraph {

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if(node == null) return node;
		LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();

		// use a map to record the original node and cloned node
		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

		UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
		queue.add(newNode);
		map.put(node, newNode);
		
		while(!queue.isEmpty()) {
			UndirectedGraphNode curr = queue.pop();
			List<UndirectedGraphNode> neighbors = curr.neighbors;
			for(UndirectedGraphNode adjNode: neighbors) {
				if(map.containsKey(adjNode)) {
					map.get(curr).neighbors.add(map.get(adjNode));
				} else {
					UndirectedGraphNode newAdj = new UndirectedGraphNode(adjNode.label);
					map.get(curr).neighbors.add(newAdj);
					map.put(adjNode, newAdj);
					queue.add(adjNode);
				}
			}
		}
		return newNode;
    }

}

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { 
		label = x; neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
}