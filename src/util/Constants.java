package util;

public class Constants {

	//Building speeds
	public final static double BURNER_MINING_DRILL_POWER = 2.5;
	public final static double BURNER_MINING_DRILL_SPEED = 0.35;
	public final static double ELECTRIC_MINING_DRILL_POWER = 3.0;
	public final static double ELECTRIC_MINING_DRILL_SPEED = 0.5;
	public final static int OIL_REFINERY = 1;
	public final static double ASSEMBLING_MACHINE_1 = 0.5;
	public final static double ASSEMBLING_MACHINE_2 = 0.75;
	public final static double ASSEMBLING_MACHINE_3 = 1.25;
	public final static int CHEMICAL_PLANT = 1;
	public final static double STONE_FURNACE = 0.5;
	public final static int STEEL_FURNACE = 1;
	public final static int ELECTRIC_FURNACE = 2;
	
	//any kind of productivity increasing technologies TODO add methods and fetch formulas
	public static int miningDrillProductivity = 0;
	public static int combatRobotFollowerCount = 0;
	public static int labResearch = 0;
	
	//this is for calculating mining speeds (mining power - mining hardness) * mining speed / mining time
	public static int ironOreTime = 2;
	public static double ironOreHardness = 0.9;
	public static int copperOreTime = 2;
	public static double copperOreHardness = 0.9;
	public static int coalTime = 2;
	public static double coalHardness = 0.9;
	public static int stoneOreTime = 2;
	public static double stoneHardness = 0.4;
	public static int uraniumOreTime = 4;
	public static double uraniumOreHardness = 0.9;
	
	//items per second on a belt
	public static double normalBelt = 40/3;
	public static double fastBelt = 40/3*2;
	public static double expressBelt = 40;
	
	//moving speeds of inserters?
	public static double burner = 1.667;
	public static double normal = 1.191;
	public static double longHanded = 0.833;
	public static double fast = 0.417;
	public static double filter = 0.417;
	public static double stack = 0.417;
	public static double stackfilter = 0.417;
}
