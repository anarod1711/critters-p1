/*
 * CRITTERS Main.java
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

/*
 * Usage: java <pkg name>.Main <input file> test input file is
 * optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */

public class Main {

    /* Scanner connected to keyboard input, or input file */
    static Scanner kb;

    /* Input file, used instead of keyboard input if specified */
    private static String inputFile;

    /* If test specified, holds all console output */
    static ByteArrayOutputStream testOutputString;

    /* Use it or not, as you wish! */
    private static boolean DEBUG = true;

    /* if you want to restore output to console */
    static PrintStream old = System.out;

    /* Gets the package name.  The usage assumes that Critter and its
       subclasses are all in the same package. */
    private static String myPackage; // package of Critter file.

    /* Critter cannot be in default pkg. */
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     *
     * @param args args can be empty.  If not empty, provide two
     *             parameters -- the first is a file name, and the
     *             second is test (for test output, where all output
     *             to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java <pkg name>.Main OR java <pkg name>.Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java <pkg name>.Main OR java <pkg name>.Main <input file> <test output>");
            }
            if (args.length >= 2) {
                /* If the word "test" is the second argument to java */
                if (args[1].equals("test")) {
                    /* Create a stream to hold the output */
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    /* Save the old System.out. */
                    old = System.out;
                    /* Tell Java to use the special stream; all
                     * console output will be redirected here from
                     * now */
                    System.setOut(ps);
                }
            }
        } else { // If no arguments to main
            kb = new Scanner(System.in); // Use keyboard and console
        }
        commandInterpreter(kb);

        System.out.flush();
    }

    /* Do not alter the code above for your submission. */
    /** Runs the simulation taking a single string input from user.
     * Displays "error processing" when a known command was inputed
     * in an array of strings.
     * Does the same when a number format exception is caught and invalid 
     * critter exception, amongst others, occurs. 
     * Displays "invalid command" if the command isn't remotely known.
     * Simulation stops when user enters "quit."
     *
     * @param Scanner variable for user input
     */
    private static void commandInterpreter(Scanner kb) {
    	// obtain input and parse
		System.out.print("critters> ");
		String input = kb.nextLine();
		String[] inputs = input.trim().split("\\s+");
		// keep running simulator while no quit command
		while(!inputs[0].equals("quit")) {	
			// show
			if (inputs[0].equals("show")) {
				if (inputs.length == 1) {
					Critter.displayWorld();
				}
				else {
					System.out.println("error processing: " + input);
				}
			}
			// step [<count>]
			else if (inputs[0].equals("step")) {
				if (inputs.length < 3) {
					int count = 0;
					if (inputs.length == 1) {
						count = 1;
					}
					else {
						try {
							count = Integer.parseInt(inputs[1]);
						} catch (NumberFormatException e) {
							System.out.println("error processing: " + input);
						}
					}
					for (int i = 0; i < count; i++) {
						try {
							Critter.worldTimeStep();
						} catch (InvalidCritterException e) {
							System.out.println("error processing: " + input);
						}
					}
				}
				else {
					System.out.println("error processing: " + input);
				}
			}
			// seed [<number>]
			else if (inputs[0].equals("seed")) {
				if (inputs.length < 3) {
					long seed;
					if (inputs.length == 1) {
						System.out.println("error processing: " + input);
					}
					else {
						try {
							seed = Long.parseLong(inputs[1]);
						} catch (NumberFormatException e) {
							System.out.println("error processing: " + input);
						}
					}	
				}
				else {
					System.out.println("error processing: " + input);
				}
			}
			// create <class_name> [<count>]
			else if (inputs[0].equals("create")) {
				if (inputs.length < 4 && inputs.length > 1) {
					int count = 0;
					if (inputs.length == 3) {
						try {
							count = Integer.parseInt(inputs[2]);
						} catch (NumberFormatException e) {
							System.out.println("error processing: " + input);
						}
					}
					else {
						count = 1;
					}
					try {
						for (int i = 0; i < count; i++) {
							Critter.createCritter(inputs[1]);
						}
					} catch (InvalidCritterException e) {
						System.out.println("error processing: " + input);
					}
				}
				else {
					System.out.println("error processing: " + input);
				}
			}
			// stats <class_name>
			else if (inputs[0].equals("stats")) {
				if (inputs.length == 2) {
					try {
						List<Critter> critters = Critter.getInstances(inputs[1]);
						if (inputs[1].equals("Clover") || inputs[1].equals("Critter")) {
							Critter.runStats(critters);
						}
						else {
							Class<?> critterClass = Class.forName(myPackage + "." + inputs[1]);
							Constructor<?> constructor = critterClass.getConstructor();
							Object new_critter = constructor.newInstance();
							Method m = new_critter.getClass().getDeclaredMethod("runStats", List.class); 
							m.invoke(critterClass, critters);
						}
					} catch (InvalidCritterException | ClassNotFoundException 
							| NoSuchMethodException | SecurityException | IllegalAccessException 
							| IllegalArgumentException | InvocationTargetException 
							| InstantiationException e) {
						System.out.println("error processing: " + input);
					}
				}
				else {
					System.out.println("error processing: " + input);
				}
			}
			// clear
			else if (inputs[0].equals("clear")) {
				if (inputs.length == 1) {
					Critter.clearWorld();
				}
				else {
					System.out.println("error processing: " + input);
				}
			}
			else {
				System.out.println("invalid command: " + input);
			}
			System.out.print("critters> ");
			input = kb.nextLine();
			inputs = input.trim().split("\\s+");
		}
    }
}
