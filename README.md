# generic-graph-social-network
Generic graph implementation in Java with BFS traversal and a SocialNetwork class built on top of it. Includes custom exceptions for invalid vertices.

# Generic Graph & Social Network (Java)

This project implements a **generic undirected graph** using adjacency lists, 
along with a **Breadth-First Search (BFS)** path-finding method and a 
`SocialNetwork` class built on top of the graph.  
The project follows the exact assignment requirements. :contentReference[oaicite:0]{index=0}

## 📦 Components

### `Graph<E>`
A generic graph where each vertex stores a list of its neighbors.

- Uses `ArrayList<ArrayList<E>>`  
- Supports:
  addVertex(E ver)  
  addEdge(E ver1, E ver2) (undirected)  
  getEdges(E ver)  
  getVertices()
  bfs(E from, E to) → returns the shortest path as a list  
- Throws:
  - `VertexExistException` if a duplicate vertex is added  
  - `VertexNotExistException` if an operation refers to a missing vertex  

The graph **does not check for duplicate edges**, as required by the assignment.

### `VertexExistException`
Custom exception thrown when attempting to add a vertex that already exists.

### `VertexNotExistException`
Custom exception thrown when referencing a vertex not present in the graph.

### `SocialNetwork`
A simple wrapper that uses `Graph<String>` to represent relationships between people.
Stores a graph of names  
Supports:
  - Adding people  
  - Connecting people  
  - Checking direct friends  
  - Finding connection paths via BFS  
  - Demonstrates real usage of the underlying graph logic.
