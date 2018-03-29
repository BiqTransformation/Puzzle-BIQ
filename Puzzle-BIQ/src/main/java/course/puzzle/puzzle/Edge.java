package course.puzzle.puzzle;

public class Edge {
    private String name;
    private Integer value;

    public Edge(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Edge){
            return name.equals(((Edge) obj).name) && value.equals(((Edge) obj).value);
        }
        return false;
    }

	public Integer getValue() {
		return value;
	}

	
    
    
    
}
