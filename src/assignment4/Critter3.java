/*
 * CRITTERS Critter3.java
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

/* Critter 3 = The Fighter
 * If it has more than enough energy, then it will reproduce
 *     otherwise, it will walk
 * During an encounter, it will always choose to fight
 */

public class Critter3 extends Critter {

	/* Reproduces if energy > 200. Otherwise, 
	 * it walks.
	 */
	@Override
	public void doTimeStep() {
		if (getEnergy() > 200) {
			reproduce(this, Critter.getRandomInt(8));
		}
		else{
			walk(Critter.getRandomInt(8));
		}
	}

	/* Always fights
	 * 
	 * @param opponent to fight as a string
	 * @return true if wants to fight, false if doesn't
	 */
	@Override
	public boolean fight(String oponent) {
		return true;
	}
	
	/* String representation of Critter1.
	 * 
	 * @return String 3 to represent Critter #3
	 */
	@Override
	public String toString() {
		return "3";
	}
	
    /**
     * Shows number of type 3 critters and number of type 3
     * critters that have strong energy (>= 80), healthy
     * energy (80 > energy > 40), and weak energy (energy <= 40).
     * 
     * @param critter_class_name
     */
	public static void runStats(List<Critter> critter3s) {
		int strong = 0;		//	energy >= 80
		int healthy = 0;	//	80 > energy > 40
		int weak = 0;		// 	energy <= 40
		// count number of critters w those characteristics ^
		for(Critter critter : critter3s) {
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
		System.out.println(critter3s.size() + " critters as follows -- 3:" + critter3s.size());
		System.out.println("Energy status:");
		System.out.println("Number of Strong (80<=): " + strong);
		System.out.println("Number of Healthy (40 < energy < 80): " + healthy);
		System.out.println("Number of Weak (<=40): " + weak);
	}

}
