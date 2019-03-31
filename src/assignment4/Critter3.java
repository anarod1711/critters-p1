package assignment4;

import java.util.List;

/* Critter 3 = The Fighter
 * If it has more than enough energy, then it will reproduce
 *     otherwise, it will walk
 * During an encounter, it will always choose to fight
 */

public class Critter3 extends Critter {

	@Override
	public void doTimeStep() {
		if (getEnergy() > 200) {
			reproduce(this, Critter.getRandomInt(8));
		}
		walk(Critter.getRandomInt(8));
	}

	@Override
	public boolean fight(String oponent) {
		return true;
	}
	
	@Override
	public String toString() {
		return "3";
	}
	
	public static void runStats(List<Critter> critter3s) {
		System.out.println(critter3s.size() + " critters as follows -- 3:" + critter3s.size());
	}

}
