/*
 * CRITTERS Critter4.java
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

	/* Will walk if energy is above 70, no rush
	 * Will run if energy is 70 or below, starting to panic
	 * Will stay put if energy is below 40, need to 
	 * reserve energy to fight
	 */
	@Override
	public void doTimeStep() {
		if (getEnergy() > 70) {
			walk(Critter.getRandomInt(8));
		}
		else if (getEnergy() >= 40) {
			run(Critter.getRandomInt(8));
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
	 * @return String 4 to represent Critter #4
	 */
	@Override
	public String toString() {
		return "4";
	}
	
    /**
     * Shows number of type 4 critters and number of type 4
     * critters that have strong energy (>= 80), healthy
     * energy (80 > energy > 40), and weak energy (energy <= 40).
     * 
     * @param critter_class_name
     */
	public static void runStats(List<Critter> critter4s) {
		int strong = 0;		//	energy >= 80
		int healthy = 0;	//	80 > energy > 40
		int weak = 0;		// 	energy <= 40
		// count number of critters w those characteristics ^
		for(Critter critter : critter4s) {
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
		System.out.println(critter4s.size() + " critters as follows -- 4:" + critter4s.size());
		System.out.println("Energy status:");
		System.out.println("Number of Strong (80<=): " + strong);
		System.out.println("Number of Healthy (40 < energy < 80): " + healthy);
		System.out.println("Number of Weak (<=40): " + weak);
	}

}
