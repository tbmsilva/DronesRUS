import java.util.Scanner;

import bases.*;
import iterators.*;
import manager.*;
import drones.*;
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
	private static final String DELIVERY = "delivery";
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
	}

	private static void executeOption(Manager mn, String option, Scanner in) {
		switch (option) {
		case BASE:
			processBase(mn, in);
			break;
		case LIST_BASES:
			processListBases(mn, in);
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
			processListDrones(mn, in);
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
			processAllOrders(mn, in);
			break;

		/*
		 * case DELIVERY: processPessoas(mn, in); break; case DELIVERED:
		 * processPessoas(mn, in); break; case IN_TRANSIT: processPessoas(mn, in);
		 * break; case TIC_TAC: processPessoas(mn, in); break;
		 */

		case HELP:
			processHelp();
			break;
		default:
			showUnknownCommand();
			break;
		}
	}

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

	private static void processListBases(Manager mn, Scanner in) {
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
			System.out.println(PROMPT + NO_BASES);
	}

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
				System.out.println("Drone " + formingDronesIDS[errorArray[1]] + " is not available at this base!");
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

	private static void processListDrones(Manager mn, Scanner in) {
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
					System.out.println(droneId + " is not at " + originBase);
				} else {
					int d = mn.distance(originBase, targetBase);
					if (!mn.hasRange(droneId, d)) {
						System.out.println("Drone " + droneId + " cannot reach " + targetBase);
					} else {
						mn.relocation(droneId, originBase, targetBase);
						System.out.println(droneId + " flying from " + originBase + " to " + targetBase);
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

	private static void processAllOrders(Manager mn, Scanner in) {
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
