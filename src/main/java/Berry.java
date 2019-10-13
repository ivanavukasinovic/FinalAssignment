
public class Berry {

	private String name;
	private int size;
	private int growthTime;

	public Berry(String name, int size, int growthTime) {
		this.name = name;
		this.size = size;
		this.growthTime = growthTime;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public int getGrowthTime() {
		return growthTime;
	}

	public String toString() {
		return "- Name of the Berry: " + name 
				+ ", size of the Berry in millimeters: " + size
				+ ", growth time of the Berry in hours: " + growthTime 
				+ ".";
	}

}
