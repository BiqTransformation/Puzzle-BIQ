package course.puzzle.puzzle;
/*
 * This class represents a puzzle shape 
 * @author Svetlana and Lior
 */
public class PuzzleShape {
    private int edges[] = new int[4];

    public PuzzleShape(int[] edges) {
        this.edges = edges;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleShape other = (PuzzleShape) o;
        return edges[0] == other.edges[0] && edges[1] == other.edges[1] && edges[2] == other.edges[2] && edges[3] == other.edges[3];
    }

    @Override
    public int hashCode() {
        return edges[0] * 54 + edges[1] * 21 + edges[2] * 3 + edges[3];
    }

    @Override
    public String toString() {
        return edges[0] + " " + edges[1]  + " " + edges[2]  + " " + edges[3];
    }
}
