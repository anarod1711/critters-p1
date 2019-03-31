package assignment4;

import java.util.List;

/* Critter4 = Wise Guy
 * doTimeStep():
 *     Will walk if energy is above 70, no rush
 *     Will run if energy is 70 or below, starting to panic
 *     Will stay put if energy is below 40, need to reserve energy to fight
 *     Will never reproduce, doesn't want to waste 1/2 of its energy
 * fight():
 *     Will fight no matter what
 */

public class Critter4 extends Critter {

	@Override
	public void doTimeStep() {
		if (getEnergy() > 70) {
			run(Critter.getRandomInt(8));
		}
		else if (getEnergy() > 40) {
			walk(Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String oponent) {
		return true;
	}
	
	@Override
	public String toString() {
		return "4";
	}
	
	public static void runStats(List<Critter> critter4s) {
		System.out.println(critter4s.size() + " critters as follows -- 4:" + critter4s.size());
	}

}
