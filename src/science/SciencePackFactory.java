package science;

import java.util.LinkedList;
import java.util.List;

public class SciencePackFactory {
	private static List<SciencePack> singletons = new LinkedList<>();

	public static SciencePack getInstance(String name) {
		for (SciencePack sp : singletons) {
			if (name.equals(sp.getName())) {
				return sp;
			}
		}
		SciencePack sp = new SciencePack(name);
		singletons.add(sp);
		return sp;
	}
}