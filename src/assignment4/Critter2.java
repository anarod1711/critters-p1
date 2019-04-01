/*
 * CRITTERS Critter2.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Analaura Rodriguez>
 * <ar55665>
 * <16225>
 * <Kevin Han>
 * <kdh2789>
 * <16190>
 * Slip days used: <0>
 * Spring 2019
 */

package assignment4;

import java.util.List;

/* Critter2 = The Mother
 * Will stay in 1 position
 * Will always choose to reproduce in doTimeStep()
 * Will always fight in encounter
 *     Will not fight if it is another Mother
 */

public class Critter2 extends Critter {

	/* Critter2 always reproduces in its
	 * time step
	 */
	@Override
	public void doTimeStep() {
		reproduce(this, Critter.getRandomInt(8));		
	}
	
	
	/* Critter2 fights all the time, unless it is 
	 * another Critter2.
	 * 
	 * @param opponent to fight as a string
	 * @return true if wants to fight, false if doesn't
	 */
	@Override
	public boolean fight(String oponnent) {
		if (oponnent.equals(toString())) {
			return false;
		}
		return true;
	}
	
	/* String representation of Critter1.
	 * 
	 * @return String 2 to represent Critter #2
	 */
	@Override
	public String toString() {
		return "2";
	}
	
    /**
     * Shows number of type 2 critters and number of type 2
     * critters that have strong energy (>= 80), healthy
     * energy (80 > energy > 40), and weak energy (energy <= 40).
     * 
     * @param critter_class_name
     */
	public static void runStats(List<Critter> critter2s) {
		int strong = 0;		//	energy >= 80
		int healthy = 0;	//	80 > energy > 40
		int weak = 0;		// 	energy <= 40
		// count number of critters w those characteristics ^
		for(Critter critter : critter2s) {
			int energy = critter.getEnergy();
			if (energy >= 80) {
				strong++;
			}
			else if (80 > energy && energy > 40) {
				healthy++;
			}
			else {
				weak++;
			}
		}
		// print stats
		System.out.println(critter2s.size() + " critters as follows -- 2:" + critter2s.size());
		System.out.println("Energy status:");
		System.out.println("Number of Strong (80<=): " + strong);
		System.out.println("Number of Healthy (40 < energy < 80): " + healthy);
		System.out.println("Number of Weak (<=40): " + weak);
	}
	
}
