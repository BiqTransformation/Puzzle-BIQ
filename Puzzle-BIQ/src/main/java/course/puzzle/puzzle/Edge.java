package course.puzzle.puzzle;
/*
 * This class represents a edge of puzzlePiece
 * it might be not in use on the advanced puzzle solution
 * @author Svetlana
 */
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
    public void setName(String name) {
        this.name = name;
    }
	public int getValue() {
		return value;
	}

    public void setValue(Integer value) {
        this.value = value;
    }
    public boolean isMatch(Object obj){
        if(obj instanceof Edge){
            return (value + ((Edge) obj).getValue()) == 0 ;
        }
        return false;
    }

    public Edge getMatch(){


            int matchValue = 0 - value;
            if(name.equals("left")){
                return new Edge("right",matchValue) ;
            }
            if(name.equals("right")){
                return new Edge("left",matchValue) ;
            }
            if(name.equals("top")){
                return new Edge("bottom",matchValue) ;
            }
            if(name.equals("bottom")){
                return new Edge("top",matchValue) ;
            }


        return null;
    }
}
