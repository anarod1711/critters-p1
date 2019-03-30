/*
 * CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Spring 2019
 */

package assignment4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* 
 * See the PDF for descriptions of the methods and fields in this
 * class. 
 * You may add fields, methods or inner classes to Critter ONLY
 * if you make your additions private; no new public, protected or
 * default-package code or data can be added to Critter.
 */

public abstract class Critter {

    private int energy = 0;

    private int x_coord;
    private int y_coord;
    private boolean moved;
    private boolean encounter;

    private static List<Critter> population = new ArrayList<Critter>();
    private static List<Critter> babies = new ArrayList<Critter>();

    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static Random rand = new Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the qualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name) throws InvalidCritterException {
    	String critter_name = myPackage + "." + critter_class_name;
    	try {
    		// creating new critter through reflection
			Class<?> critter_class = Class.forName(critter_name);
			Constructor<?> constructor = critter_class.getConstructor();
			Object new_critter = constructor.newInstance();	
			
			// setting parameters of newly created critter
			population.add((Critter) new_critter);
			population.get(population.size()-1).energy = Params.START_ENERGY;
			population.get(population.size()-1).x_coord = Critter.getRandomInt(Params.WORLD_WIDTH);
			population.get(population.size()-1).y_coord = Critter.getRandomInt(Params.WORLD_HEIGHT);
			population.get(population.size()-1).moved = false;
			population.get(population.size()-1).encounter = false;
			
		} catch (Exception e) {
			throw new InvalidCritterException(critter_class_name);
		}    	    	
    }

    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *        Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
        // TODO: Complete this method
        return null;
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
        population.clear();
        babies.clear();
    }

    public static void worldTimeStep() throws InvalidCritterException {
    	for (Critter critter : population) {
    		critter.doTimeStep();
    	}
    	doEncounters();
    	for (Critter critter : population) {
    		critter.energy -= Params.REST_ENERGY_COST;
    		if (critter.energy <= 0) {
    			population.remove(critter);
    		}
    	}
    	genClover();
		population.addAll(babies);
		babies.clear();
    }
    
    public static void displayWorld() {
        // TODO: Complete this method
    }

    /**
     * Prints out how many Critters of each type there are on the
     * board.
     *
     * @param critters List of Critters.
     */
    public static void runStats(List<Critter> critters) {
        System.out.print("" + critters.size() + " critters as follows -- ");
        Map<String, Integer> critter_count = new HashMap<String, Integer>();
        for (Critter crit : critters) {
            String crit_string = crit.toString();
            critter_count.put(crit_string,
                    critter_count.getOrDefault(crit_string, 0) + 1);
        }
        String prefix = "";
        for (String s : critter_count.keySet()) {
            System.out.print(prefix + s + ":" + critter_count.get(s));
            prefix = ", ";
        }
        System.out.println();
    }
    
    public abstract void doTimeStep();
    
    public abstract boolean fight(String oponent);

    /* a one-character long string that visually depicts your critter
     * in the ASCII interface */
    public String toString() {
        return "";
    }

    protected int getEnergy() {
        return energy;
    }
    
    private void checkMove(int direction, int step) {
        if (!moved) {
        	if (!encounter) {
        		move(direction, 1);
        		moved = true;
        	}
        	else {
        		int og_x = x_coord;
        		int og_y = y_coord;
        		move(direction, 1);
        		for (Critter critter : population) {
        			if (critter.x_coord == x_coord && critter.y_coord == y_coord) {
        				x_coord = og_x;
        				y_coord = og_y;
        				break;
        			}
        		}
        		
        	}
        }
    }
    
    protected final void walk(int direction) {
        energy -= Params.WALK_ENERGY_COST;
        checkMove(direction, 1);
    }

    protected final void run(int direction) {
    	energy -= Params.RUN_ENERGY_COST;
    	checkMove(direction, 2);

    }
    
    private void move(int direction, int steps) {
    	if (direction == 0) {
    		if (x_coord + steps >= Params.WORLD_WIDTH) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - (x_coord + steps));
    		}
    		else { 
    			x_coord++;
    		}
    	}
    	else if (direction == 1) {
    		if (x_coord + steps >= Params.WORLD_WIDTH) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - (x_coord + steps));
    		}
    		else { 
    			x_coord++;
    		}
    		
    		if (y_coord - steps < 0) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - steps);
    		}
    		else { 
    			y_coord--;
    		}
    	}
    	else if (direction == 2) {
    		if (y_coord - steps < 0) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - steps);
    		}
    		else { 
    			y_coord--;
    		}
    	}
    	else if (direction == 3) {
    		if (x_coord - steps < 0) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - steps);
    		}
    		else { 
    			x_coord--;
    		}
    		
    		if (y_coord - steps < 0) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - steps);
    		}
    		else { 
    			y_coord--;
    		}
    	}
    	else if (direction == 4) {
    		if (x_coord - steps < 0) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - steps);
    		}
    		else { 
    			x_coord--;
    		}
    	}
    	else if (direction == 5) {
    		if (x_coord - steps < 0) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - steps);
    		}
    		else { 
    			x_coord--;
    		}
    		
    		if (y_coord + steps >= Params.WORLD_HEIGHT) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - (y_coord + steps));
    		}
    		else { 
    			y_coord++;
    		}
    	}
    	else if (direction == 6) {
    		if (y_coord + steps >= Params.WORLD_HEIGHT) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - (y_coord + steps));
    		}
    		else { 
    			y_coord++;
    		}
    	}
    	else {
    		if (x_coord + steps >= Params.WORLD_WIDTH) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - (x_coord + steps));
    		}
    		else { 
    			x_coord++;
    		}
    		
    		if (y_coord + steps >= Params.WORLD_HEIGHT) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - (y_coord + steps));
    		}
    		else { 
    			y_coord++;
    		}
    	}
    }

    protected final void reproduce(Critter offspring, int direction) {
        // TODO: Complete this method
    }
    
    protected final static void doEncounters() {
    	for (Critter critter_1 : population) {
    		for (Critter critter_2 : population) {
        		if (critter_1 != critter_2) {
        			if (critter_1.x_coord == critter_2.x_coord && critter_1.y_coord == critter_2.y_coord) {
        				boolean fight_1 = critter_1.fight(critter_2.toString().toString());
        				boolean fight_2 = critter_2.fight(critter_1.toString().toString());
        				if (critter_1.energy > 0 && critter_2.energy > 0 
        						&& critter_1.x_coord == critter_2.x_coord 
        						&& critter_1.y_coord == critter_2.y_coord) {
        					int num_1, num_2;
        					if (fight_1) {
        						num_1 = getRandomInt(critter_1.energy);
        					}
        					else {
        						num_1 = 0;
        					}
        					
        					if (fight_2) {
        						num_2 = getRandomInt(critter_2.energy);
        					}
        					else {
        						num_2 = 0;
        					}
        					
        					if (num_1 > num_2) {
        						critter_1.energy += critter_2.energy/2;
        						critter_2.energy = 0;
        					}
        					else {
        						critter_2.energy += critter_1.energy/2;
        						critter_1.energy = 0;
        					}
        				}
        			}
        		}
        	}
    	}
    }
    
    protected final static void genClover() throws InvalidCritterException {
    	for (int i = 0; i < Params.REFRESH_CLOVER_COUNT; i++) {
    		createCritter("Clover");
    	}
    }
    /**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     * <p>
     * NOTE: you must make sure that the setter functions work with
     * your implementation of Critter. That means, if you're recording
     * the positions of your critters using some sort of external grid
     * or some other data structure in addition to the x_coord and
     * y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }

        /**
         * This method getPopulation has to be modified by you if you
         * are not using the population ArrayList that has been
         * provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /**
         * This method getBabies has to be modified by you if you are
         * not using the babies ArrayList that has been provided in
         * the starter code.  In any case, it has to be implemented
         * for grading tests to work.  Babies should be added to the
         * general population at either the beginning OR the end of
         * every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }
}
