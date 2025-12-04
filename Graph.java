import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<E> {

    private ArrayList<ArrayList<E>> data;

    public Graph() {
        data = new ArrayList<ArrayList<E>>();
    }

    // Helper: find index of vertex in data, or -1 if not found
    private int findVertexIndex(E ver) {
        for (int i = 0; i < data.size(); i++) {
            ArrayList<E> list = data.get(i);
            if (!list.isEmpty() && list.get(0).equals(ver)) {
                return i;
            }
        }
        return -1;
    }

    // Add a vertex as a new adjacency list
    public void addVertex(E ver) throws VertexExistException {
        if (findVertexIndex(ver) != -1) {
            throw new VertexExistException("Vertex already exists: " + ver);
        }

        ArrayList<E> list = new ArrayList<E>();
        list.add(ver); // first element = the vertex itself
        data.add(list);
    }

    // Add an undirected edge (ver1 ↔ ver2)
    public void addEdge(E ver1, E ver2) throws VertexNotExistException {
        int idx1 = findVertexIndex(ver1);
        int idx2 = findVertexIndex(ver2);

        if (idx1 == -1 || idx2 == -1) {
            throw new VertexNotExistException("One or both vertices do not exist.");
        }

        // Assignment says: DO NOT check for duplicate edges.
        data.get(idx1).add(ver2);
        data.get(idx2).add(ver1);
    }

    // Return neighbors of a vertex
    public ArrayList<E> getEdges(E ver) throws VertexNotExistException {
        int idx = findVertexIndex(ver);
        if (idx == -1) {
            throw new VertexNotExistException("Vertex does not exist: " + ver);
        }

        ArrayList<E> list = data.get(idx);
        ArrayList<E> result = new ArrayList<E>();

        // neighbors start from index 1
        for (int i = 1; i < list.size(); i++) {
            result.add(list.get(i));
        }

        return result;
    }

    // Return all vertices in order
    public ArrayList<E> getVertices() {
        ArrayList<E> vertices = new ArrayList<E>();

        for (ArrayList<E> list : data) {
            if (!list.isEmpty()) {
                vertices.add(list.get(0));
            }
        }

        return vertices;
    }

    // Breadth-First Search: find shortest path from 'from' to 'to'
    public ArrayList<E> bfs(E from, E to) {
        int startIdx = findVertexIndex(from);
        int endIdx = findVertexIndex(to);

        if (startIdx == -1 || endIdx == -1) {
            return null; // one or both vertices do not exist
        }

        // BFS structures
        ArrayList<E> vertices = getVertices();
        boolean[] visited = new boolean[vertices.size()];
        E[] parent = (E[]) new Object[vertices.size()];

        Queue<E> queue = new LinkedList<E>();
        queue.add(from);
        visited[startIdx] = true;
        parent[startIdx] = null;

        boolean found = false;

        while (!queue.isEmpty()) {
            E current = queue.remove();
            int currentIdx = findVertexIndex(current);

            // If we reached target
            if (current.equals(to)) {
                found = true;
                break;
            }

            try {
                ArrayList<E> neighbors = getEdges(current);
                for (E neighbor : neighbors) {
                    int nIdx = findVertexIndex(neighbor);

                    if (!visited[nIdx]) {
                        visited[nIdx] = true;
                        parent[nIdx] = current;
                        queue.add(neighbor);
                    }
                }
            } catch (VertexNotExistException e) {
                // Should not happen here
            }
        }

        if (!found) {
            return null; // no path
        }

        // Reconstruct path from 'to' backwards
        ArrayList<E> path = new ArrayList<E>();
        E step = to;

        while (step != null) {
            path.add(0, step);
            int idx = findVertexIndex(step);
