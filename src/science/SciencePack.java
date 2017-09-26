package science;

public class SciencePack {

	private String name;

	protected SciencePack(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}