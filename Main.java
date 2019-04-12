import java.util.Scanner;

import bases.*;
import iterators.*;
import manager.*;
import drones.*;
import flight.*;
import orders.*;

/**
 * @author m.lami & tbmsilva
 */
public class Main {

	// Constants
	private static final int MINIMUM_RANGE = 10;
	private static final String HERMIT = "hermit";
	private static final String SOCIABLE = "sociable";

	// Swarm Error Codes
	private static final int ERROR_SAME_DRONE = 0;
	private static final int ERROR_HERMIT_DRONE = 1;
	private static final int ERROR_DRONE_UNAVAILABLE = 2;
	private static final int ERROR_SWARM_ID = 3;
	private static final int SWARM_CHECK_OK = 4;

	// Commands
	private static final String EXIT = "exit";
	private static final String BASE = "base";
	private static final String LIST_BASES = "listbases";
	private static final String DRONE = "drone";
	private static final String SERVICE = "service";
	private static final String SWARM = "swarm";
	private static final String SWARM_COMPONENTS = "swarmcomponents";
	private static final String DISBAND = "disband";
	private static final String LIST_DRONES = "listdrones";
	private static final String FLY_TO_BASE = "flytobase";
	private static final String ADD_ORDERS = "addorder";
	private static final String ORDERS = "orders";
	private static final String ALL_ORDERS = "allorders";
	private static final String DELIVER = "deliver";
	private static final String DELIVERED = "delivered";
	private static final String IN_TRANSIT = "intransit";
	private static final String TIC_TAC = "tictac";
	private static final String HELP = "help";

	// Messages
	private static final String HELP_LIST = "base - registers a new distribution base\n"
			+ "listBases - lists existing distribution bases\n" + "drone - registers a new drone in a starting base\n"
			+ "service - service all grounded drones requiring service in a base\n"
			+ "swarm - creates a new swarm from free drones in a base\n"
			+ "swarmComponents - lists the drones within a swarm\n" + "disband - disbands the whole swarm\n"
			+ "listDrones - lists all existing drones (and swarms)\n" + "flyToBase - fly a drone (or swarm) to a base\n"
			+ "addOrder - add a new order to a base\n" + "orders - lists all pending orders from a base\n"
			+ "allOrders - lists all pending orders from all bases\n" + "deliver - deliver a package to a customer\n"
			+ "delivered - lists all delivered orders\n"
			+ "inTransit - lists all drones (and swarms) currently flying\n"
			+ "ticTac - advances the simulation clock n timestamps\n" + "help - shows the available commands\n"
			+ "exit - terminates the execution of the program";
	private static final String PROMPT = "> ";
	private static final String UNKNOWN = "Unknown command. Type help to see available commands.";
	private static final String EXIT_MESSAGE = "Bye!";
	private static final String BASE_EXISTS = "Base already exists!";
	private static final String COORDINATES_EXIST = "There is already another base at ";
	private static final String NO_BASES = "There are no bases!";
	private static final String NO_DRONES = "There are no drones!";
	private static final String INVALID_RANGE = "Invalid range for a new drone!";
	private static final String INVALID_CAPACITY = "Capacity has to be a positive integer!";
	private static final String INVALID_TYPE = "Invalid drone type!";
	private static final String EMPTY_HANGAR = "The hangar is empty!";
	private static final String EMPTY_SERVICE_BAY = "The service bay is empty!";
	private static final String INVALID_DIMENSION = "Order dimension must be a positive integer!";
	private static final String ORDER_QUEUED = "Order queued for delivery.";
	private static final String NO_PENDING_ORDERS = "There are no pending orders";
	private static final String ORDERS_IN = "Orders in ";
	private static final String INVALID_INITIAL_DRONES = "Swarm must have at least two drones!";
	private static final String NO_DRONES_SENT_TO_SERVICE = "No drones were sent to the service station!";
	private static final String NO_FLYING_DRONES = "No drones are flying!";
	private static final String NO_ORDERS_DELIVERED = "No orders delivered so far!";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Manager mn = new ManagerClass();
		System.out.print(PROMPT);
		String option = readOption(in);
		while (!option.equals(EXIT)) {
			executeOption(mn, option, in);
			option = readOption(in);
			System.out.print(PROMPT);
		}
		processExit();
		in.close();
	}

	/*
	 * Switch to redirect input to adequate process of command
	 */
	private static void executeOption(Manager mn, String option, Scanner in) {
		switch (option) {
		case BASE:
			processBase(mn, in);
			break;
		case LIST_BASES:
			processListBases(mn);
			break;
		case DRONE:
			processDrone(mn, in);
			break;
		case SERVICE:
			processService(mn, in);
			break;
		case SWARM:
			processSwarm(mn, in);
			break;
		case SWARM_COMPONENTS:
			processSwarmComponents(mn, in);
			break;
		case DISBAND:
			processDisband(mn, in);
			break;
		case LIST_DRONES:
			processListDrones(mn);
			break;
		case FLY_TO_BASE:
			processFlyToBase(mn, in);
			break;
		case ADD_ORDERS:
			processAddOrder(mn, in);
			break;
		case ORDERS:
			processOrders(mn, in);
			break;
		case ALL_ORDERS:
			processAllOrders(mn);
			break;
		case DELIVER:
			processDeliver(mn, in);
			break;
		case DELIVERED:
			processDelivered(mn, in);
			break;
		case IN_TRANSIT:
			processInTransit(mn, in);
			break;
		case TIC_TAC:
			processTicTac(mn, in);
			break;
		case HELP:
			processHelp();
			break;
		default:
			showUnknownCommand();
			break;
		}
	}

	/*
	 * Reads the input from the user and inserts it in variables that store the
	 * latitude, longitude and ID of the new base. Checks if a base with the given
	 * ID exists. Checks if the bases's coordinates are valid by confirming they
	 * don't coincide with another base's coordinates. If everything checks out, it
	 * creates the base.
	 */
	private static void processBase(Manager mn, Scanner in) {
		int latitude = in.nextInt();
		int longitude = in.nextInt();
		String baseId = in.nextLine().trim();
		if (mn.isBaseIdValid(baseId)) {
			if (mn.areBaseCoordinatesValid(latitude, longitude)) {
				mn.addBase(baseId, latitude, longitude);
				System.out.println("Base " + baseId + " created at [" + latitude + "," + longitude + "].");
			} else
				System.out.println(COORDINATES_EXIST + "[" + latitude + "," + longitude + "]!");
		} else
			System.out.println(BASE_EXISTS);
	}

	/*
	 * Lists all the bases in the simulation. Creates an iterator for the bases. For
	 * each base, creates another iterator for the drones in the hangar of that base
	 * and prints their information. Creates another iterator for the drones in the
	 * service bay and prints their information. Also checks if the base's hangar or
	 * service bay are empty, or even if there are no created bases, in which case,
	 * prints the adequate message.
	 */
	private static void processListBases(Manager mn) {
		if (mn.areThereBases()) {
			Iterator itB = mn.iteratorBases();
			while (itB.hasNext()) {
				Base b = (Base) itB.next();
				System.out.println(b.baseID() + " " + b.coordinates());
				Iterator itDH = b.droneIteratorHangar();
				if (!itDH.hasNext())
					System.out.println(EMPTY_HANGAR);
				else {
					System.out.print("Hangar: [");
					while (itDH.hasNext()) {
						Drone d = (Drone) itDH.next();
						System.out.print(d.info());
						if (itDH.hasNext())
							System.out.print(", ");
					}
					System.out.println("]");
				}
				Iterator itDSB = b.droneIteratorServiceBay();
				if (!itDSB.hasNext())
					System.out.println(EMPTY_SERVICE_BAY);
				else {
					System.out.print("Service bay: [");
					while (itDSB.hasNext()) {
						Drone d = (Drone) itDSB.next();
						System.out.print(d.info());
						if (itDSB.hasNext())
							System.out.print(", ");
					}
					System.out.println("]");
				}
			}
		} else
			System.out.println(NO_BASES);
	}

	/*
	 * Creates a drone. Reads the input from the user and inserts it in the adequate
	 * variables that store the drone's id, starting base, type of drone, capacity
	 * and range. Checks if another drone with the given ID already exists. Checks
	 * if a base witht he given baseID exists. Checks if the type inputed by the
	 * user is Hermit or Sociable, or even, an unknown drone type, in which case, it
	 * prints the adequate message. Checks if the inputed capacity is invalid.
	 * Checks if the inputed range is invalid. If everything checks out, creates a
	 * drone with the inputed parameters.
	 */
	private static void processDrone(Manager mn, Scanner in) {
		String id = in.nextLine().trim();
		String base = in.nextLine();
		String kind = in.next();
		int capacity = in.nextInt();
		int range = in.nextInt();
		in.nextLine();
		if (mn.existsDrone(id)) {
			System.out.println("Drone " + id + " already exists!");
		} else {
			if (!mn.existsBase(base)) {
				System.out.println("Base " + base + " does not exist!");
			} else {
				if (!kind.equals(HERMIT) && !kind.equals(SOCIABLE)) {
					System.out.println(INVALID_TYPE);
				} else {
					if (capacity <= 0) {
						System.out.println(INVALID_CAPACITY);
					} else {
						if (range < MINIMUM_RANGE) {
							System.out.println(INVALID_RANGE);
						} else {
							mn.addDrone(id, base, kind, range, capacity);
							System.out.println("Drone " + id + " created.");
						}
					}
				}
			}
		}
	}

	/*
	 * Sends drones to service bay. Reads the input of the user and sets a new
	 * variable with it, indicating the base to send the drones of to the service
	 * bay, and the treshold of range to send the drones. Creates a temporary drone
	 * collection. Creates an int variable to count how many drones were sent to the
	 * service bay. Checks if the inputed baseID exists. Creates an iterator for
	 * that base's drones in hangar. Adds the drones that are below the range
	 * treshold to the temp collection. Adds the drone in the temp collection to the
	 * service bay of the base, and deletes them from the hangar.
	 */
	private static void processService(Manager mn, Scanner in) {
		String base = in.nextLine();
		int range = in.nextInt();
		in.nextLine();
		DroneCollection temp = new DroneCollectionClass();
		int dSent = 0;
		if (!mn.existsBase(base))
			System.out.println("Base " + base + " does not exist!");
		else {
			Base b = mn.getBase(base);
			Iterator itDH = b.droneIteratorHangar();
			if (!itDH.hasNext())
				System.out.println(NO_DRONES_SENT_TO_SERVICE);
			else {
				while (itDH.hasNext()) {
					Drone d = (Drone) itDH.next();
					if (d.range() < range) {
						temp.addDrone(d);
						dSent++;
					}
				}
				if (dSent == 0) {
					System.out.println(NO_DRONES_SENT_TO_SERVICE);
				} else {
					Iterator itTemp = temp.iterator();
					while (itTemp.hasNext()) {
						Drone d = (Drone) itTemp.next();
						b.moveToServiceBay(d);
						System.out.println(d.droneID() + " moved to service bay.");
					}
				}
			}
		}
	}

	/*
	 * Creates a swarm. Reads the input from the user, adds it to the adequate
	 * variables that hold the base's ID, swarmID, initial number of drones and all
	 * the ids of the drones in a String array. Checks if the base exists. Checks if
	 * the initial drone number is invalid. Holds the error code and incorrect drone
	 * index in an int array and uses a switch to print the adequate messages. If
	 * everything if OK, creates a swarm
	 */
	private static void processSwarm(Manager mn, Scanner in) {
		String baseID = in.nextLine().trim();
		String swarmID = in.nextLine().trim();
		int initDrones = in.nextInt();
		in.nextLine();
		String[] formingDronesIDS = new String[initDrones];
		for (int i = 0; i < initDrones; i++) {
			String d = in.nextLine().trim();
			formingDronesIDS[i] = d;
		}
		if (!mn.existsBase(baseID))
			System.out.println("Base " + baseID + " does not exist!");
		else if (initDrones < 2)
			System.out.println(INVALID_INITIAL_DRONES);
		else {
			int[] errorArray = mn.swarmCheck(baseID, swarmID, formingDronesIDS);
			switch (errorArray[0]) {
			case ERROR_SAME_DRONE:
				System.out.println("Cannot add drone " + formingDronesIDS[errorArray[1]] + " twice!");
				break;
			case ERROR_HERMIT_DRONE:
				System.out.println("Cannot add hermit drone " + formingDronesIDS[errorArray[1]] + "!");
				break;
			case ERROR_DRONE_UNAVAILABLE:
				System.out.println("Drone " + formingDronesIDS[errorArray[1]] + " is not available in this base!");
				break;
			case ERROR_SWARM_ID:
				System.out.println("Swarm " + swarmID + " already exists!");
				break;
			case SWARM_CHECK_OK:
				mn.addSwarm(swarmID, baseID, formingDronesIDS);
				System.out.println(swarmID + " created.");
			}
		}
	}

	/*
	 * Prints the drone IDS forming a given swarm.
	 */
	private static void processSwarmComponents(Manager mn, Scanner in) {
		String swarmID = in.nextLine().trim();
		if (!mn.existsDrone(swarmID))
			System.out.println(swarmID + " is not a swarm!");
		else {
			Iterator sIt = mn.swarmComponentsIterator(swarmID);
			while (sIt.hasNext()) {
				Drone drone = (Drone) sIt.next();
				System.out.println(drone.droneID());
			}
			Swarm s = mn.getSwarm(swarmID);
			System.out.println(s.capacity() + " " + s.range());
		}
	}

	/*
	 * Removes drones that form a swarm and adds them back to the hangar of the
	 * current base, delting the swarm.
	 */
	private static void processDisband(Manager mn, Scanner in) {
		String baseID = in.nextLine();
		String swarmID = in.nextLine();
		if (!mn.existsBase(baseID))
			System.out.println("Base " + baseID + " does not exist!");
		else {
			Base b = mn.getBase(baseID);
			if (!b.isInHangar(swarmID))
				System.out.println(swarmID + " is not at " + baseID + "!");
			else {
				mn.disband(swarmID, b);
				System.out.println(swarmID + " disbanded.");
			}
		}
	}

	/*
	 * 
	 */
	private static void processListDrones(Manager mn) {
		Iterator dIt = mn.iteratorDrones();
		if (!dIt.hasNext())
			System.out.println(NO_DRONES);
		while (dIt.hasNext()) {
			Drone d = (Drone) dIt.next();
			System.out.println(d.droneID());
		}
	}

	private static void processFlyToBase(Manager mn, Scanner in) {
		String originBase = in.nextLine();
		String droneId = in.nextLine();
		String targetBase = in.nextLine();
		if (!mn.existsBase(originBase)) {
			System.out.println("Source base " + originBase + " does not exist!");
		} else {
			if (!mn.existsBase(targetBase)) {
				System.out.println("Target base " + targetBase + " does not exist!");
			} else {
				if (!mn.getBase(originBase).isInHangar(droneId)) {
					System.out.println(droneId + " is not at " + originBase + "!");
				} else {
					Location l1 = mn.getBase(originBase).location();
					Location l2 = mn.getBase(targetBase).location();
					int d = mn.distanceToRelocation(l1, l2);
					if (!mn.hasRange(droneId, d)) {
						System.out.println("Drone " + droneId + " cannot reach " + targetBase + "!");
					} else {
						mn.relocation(droneId, originBase, targetBase);
						System.out.println(droneId + " flying from " + originBase + " to " + targetBase + ".");
					}
				}
			}
		}
	}

	private static void processAddOrder(Manager mn, Scanner in) {
		String baseID = in.nextLine().trim();
		String orderID = in.nextLine().trim();
		int dimension = in.nextInt();
		int latitude = in.nextInt();
		int longitude = in.nextInt();
		in.nextLine();
		if (!mn.existsBase(baseID))
			System.out.println("Source base " + baseID + " does not exist!");
		else if (mn.existsOrder(orderID))
			System.out.println("Order " + orderID + " already exists!");
		else if (dimension <= 0)
			System.out.println(INVALID_DIMENSION);
		else {
			mn.addOrder(baseID, orderID, dimension, latitude, longitude);
			System.out.println(ORDER_QUEUED);
		}
	}

	private static void processOrders(Manager mn, Scanner in) {
		String baseID = in.nextLine().trim();
		if (!mn.existsBase(baseID))
			System.out.println("Base " + baseID + " does not exist!");
		else {
			Base b = mn.getBase(baseID);
			Iterator itO = b.orderIterator();
			if (!itO.hasNext())
				System.out.println(NO_PENDING_ORDERS + "!");
			while (itO.hasNext()) {
				Order o = (Order) itO.next();
				System.out.println(o.info());
			}
		}
	}

	private static void processAllOrders(Manager mn) {
		if (mn.noOrders())
			System.out.println(NO_PENDING_ORDERS + "!");
		else {
			Iterator itB = mn.iteratorBases();
			while (itB.hasNext()) {
				Base b = (Base) itB.next();
				Iterator itO = b.orderIterator();
				if (!itO.hasNext())
					System.out.println(NO_PENDING_ORDERS + " in " + b.baseID() + ".");
				if (itO.hasNext())
					System.out.println(ORDERS_IN + b.baseID() + ":");
				while (itO.hasNext()) {
					Order o = (Order) itO.next();
					System.out.println(o.info());
				}
			}
		}
	}

	private static void processInTransit(Manager mn, Scanner in) {
		if (mn.noFlights()) {
			System.out.println(NO_FLYING_DRONES);
		} else {
			Iterator itF = mn.iteratorFlights();
			while (itF.hasNext()) {
				Flight f = (Flight) itF.next();
				if (f instanceof Relocation) {
					System.out.println(f.drone().droneID() + " " + f.origin().baseID() + " "
							+ ((Relocation) f).destination().baseID() + " " + f.distanceCovered() + " " + f.distance()
							+ " relocation!");
				} else {
					System.out.println(f.drone().droneID() + " " + f.origin().baseID() + " " + f.origin().baseID() + " "
							+ f.distanceCovered() + " " + f.distance() + " delivery!");
				}
			}
		}
	}

	private static void processDeliver(Manager mn, Scanner in) {
		String baseID = in.nextLine().trim();
		String droneID = in.nextLine().trim();
		String orderID = in.nextLine().trim();
		if (!mn.existsBase(baseID))
			System.out.println("Base " + baseID + " does not exist!");
		else {
			Base b = mn.getBase(baseID);
			if (!b.isInHangar(droneID))
				System.out.println(droneID + " is not at " + baseID + "!");
			else if (!b.existsOrder(orderID))
				System.out.println(orderID + " is not pending!");
			else {
				Order o = mn.getOrder(orderID);
				int distance = (mn.distanceToDelivery(b.location(), o.destination()));
				if (!mn.hasRange(droneID, distance))
					System.out.println(orderID + " is too far for " + droneID + "!");
				else if (b.getDrone(droneID).capacity() < o.dimension())
					System.out.println(orderID + " is too heavy for " + droneID + "!");
				else {
					mn.startDelivery(b, droneID, o);
					System.out.println(droneID + " will deliver " + orderID + ".");
				}
			}

		}
	}

	private static void processDelivered(Manager mn, Scanner in) {
		if (mn.noOrders() || mn.noOrderDelivered())
			System.out.println(NO_ORDERS_DELIVERED);
		else {
			Iterator itOD = mn.iteratorOrderDelivered();
			while (itOD.hasNext()) {
				OrderDelivered oD = (OrderDelivered) itOD.next();
				System.out.println(oD.timeStamp() + " " + oD.id() + " " + oD.origin().baseID() + ".");
			}
		}
	}

	private static void processTicTac(Manager mn, Scanner in) {
		int tickAdvance = in.nextInt();
		in.nextLine();
		mn.advanceTick(tickAdvance);
	}

	private static void processExit() {
		System.out.println(EXIT_MESSAGE);
	}

	private static void processHelp() {
		System.out.println(HELP_LIST);
	}

	private static void showUnknownCommand() {
		System.out.println(UNKNOWN);
	}

	private static String readOption(Scanner in) {
		return in.nextLine().toLowerCase();
	}

}
