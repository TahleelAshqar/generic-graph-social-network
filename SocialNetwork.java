import java.util.ArrayList;

public class SocialNetwork {

    private Graph<String> network;

    public SocialNetwork() {
        network = new Graph<String>();
    }

    // helper: check if a user exists in the graph
    private boolean hasUser(String user) {
        ArrayList<String> vertices = network.getVertices();
        return vertices.contains(user);
    }

    public void addUser(String user) throws UserExistException {
        try {
            network.addVertex(user);
        } catch (VertexExistException e) {
            // wrap graph-level exception in user-level exception
            throw new UserExistException("User already exists: " + user);
        }
    }

    public void addFriends(String user1, String user2) throws UserNotFoundException {
        // if one of the users doesn't exist → throw UserNotFoundException
        if (!hasUser(user1) || !hasUser(user2)) {
            throw new UserNotFoundException("One or both users do not exist.");
        }

        try {
            network.addEdge(user1, user2);
        } catch (VertexNotExistException e) {
            // Should not happen because we checked hasUser, but keep it safe:
            throw new UserNotFoundException("One or both users do not exist.");
        }
    }

    public boolean knows(String user1, String user2) throws UserNotFoundException {
        // if one of the users doesn't exist → throw
        if (!hasUser(user1) || !hasUser(user2)) {
            throw new UserNotFoundException("One or both users do not exist.");
        }

        // Use BFS path: if there is a path (even indirect), they "know" each other
        ArrayList<String> path = network.bfs(user1, user2);

        // assignment says: empty list if no path
        return !path.isEmpty();
    }
}
