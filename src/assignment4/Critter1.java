/*
 * CRITTERS Critter1.java
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

/* Critter1 = The Runner
 * The runner will choose one direction at birth and just forever
 * run in that direction
 * During doTimeStep, it will choose to run if its energy is 70 or above
 *     else it will reproduce
 * During fight, it will choose to run if its energy is above 40
 *     and below 70 as it has hope of staying alive for next time step. 
 *     If its energy is 40 or below, it will choose to fight
 *     as it is already weak :( What's there to lose?
 */

public class Critter1 extends Critter {
	
	private int dir;
	
	/* Constructor: Critter is assigned
	 * a random direction at birth.
	 */
	public Critter1() {
		dir = Critter.getRandomInt(8);
	}

	/* Critter1 runs if energy > 70.
	 * If not, just reproduces.
	 */
	@Override
	public void doTimeStep() {
		if (getEnergy() >= 70) {
			run(dir);
		}
		else {
			reproduce(this, Critter.getRandomInt(8));
		}
	}

	/* Critter1 doesn't fight and runs if its energy is
	 * below 70 and above 40, as it still has hopes of
	 * living. It fights only if his energy is 40 or 
	 * below (weak energy status).
	 * 
	 * @param opponent to fight as a string
	 * @return true if wants to fight, false if doesn't
	 */
	@Override
	public boolean fight(String oponent) {
		if (getEnergy() < 70 && getEnergy() > 40) {
			run(dir);
			return false;
		}
		return true;
	}
	
	/* String representation of Critter1.
	 * 
	 * @return String 1 to represent Critter #1
	 */
	@Override
	public String toString() {
		return "1";
	}
	
    /**
     * Shows number of type 1 critters and number of type 1
     * critters that have strong energy (>= 80), healthy
     * energy (80 > energy > 40), and weak energy (energy <= 40).
     * 
     * @param critter_class_name
     */
	public static void runStats(List<Critter> critter1s) {
		int strong = 0;		//	energy >= 80
		int healthy = 0;	//	80 > energy > 40
		int weak = 0;		// 	energy <= 40
		// count number of critters w those characteristics ^
		for(Critter critter : critter1s) {
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
		System.out.println(critter1s.size() + " critters as follows -- 1:" + critter1s.size());
		System.out.println("Energy status:");
		System.out.println("Number of Strong (80<=): " + strong);
		System.out.println("Number of Healthy (40 < energy < 80): " + healthy);
		System.out.println("Number of Weak (<=40): " + weak);
	}
	
}
