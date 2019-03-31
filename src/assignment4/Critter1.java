package assignment4;

import java.util.List;

/* Critter1 = The Runner
 * The runner will choose one direction at birth and just forever
 * run in that direction
 * During doTimeStep, it will choose to run if its energy is above 70
 *     else it will reproduce
 * During fight, it will choose to run if its energy is above 40
 *     It will not run if energy is below 70, no need waste energy on running
 *     40 was chosen because the runner believes it can fight
 */
public class Critter1 extends Critter {
	
	private int dir;
	
	public Critter1() {
		dir = Critter.getRandomInt(8);
	}

	@Override
	public void doTimeStep() {
		if (getEnergy() > 70) {
			run(dir);
		}
		else {
			reproduce(this, Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String oponent) {
		if (getEnergy() < 70 && getEnergy() > 40) {
			run(dir);
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "1";
	}
	
	public static void runStats(List<Critter> critter1s) {
		System.out.println(critter1s.size() + " critters as follows -- 1:" + critter1s.size());
	}
}
