package assignment4;

import java.util.List;

/* Critter2 = The Mother
 * Will stay in 1 position
 * Will always choose to reproduce in doTimeStep()
 * Will always fight in encounter
 *     Will not fight if it is another Mother
 */
public class Critter2 extends Critter {

	@Override
	public void doTimeStep() {
		reproduce(this, Critter.getRandomInt(8));		
	}

	@Override
	public boolean fight(String oponnent) {
		if (oponnent.equals(toString())) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "2";
	}
	
	public static void runStats(List<Critter> critter2s) {
		System.out.println(critter2s.size() + " critters as follows -- 2:" + critter2s.size());
	}

}
