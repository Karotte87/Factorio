package technology;

import java.util.LinkedList;
import java.util.List;

public class TechnologyFactory {
	private static List<Technology> singletons = new LinkedList<>();

	public static Technology getInstance(String name) {
		for (Technology p : singletons) {
			if (name.equals(p.getName())) {
				return p;
			}
		}
		Technology p = new Technology(name);
		singletons.add(p);
		return p;
	}
}