package ProcessCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Runner {

	private static void fcfs(List<Process> processes) {
		int sum = 0;
		int totalTime = 0;
		int size = processes.size();
		float average = 0;

		for (int i = 0; i < size; i++) {
			// set the wait times to zero.
			processes.get(0).setWait(0);
			// Assign values to sum
			sum += processes.get(i).getBurstTime();
			processes.get(i).setWait(sum);
			// total the sum
			totalTime += sum;
		}

		for (int j = 0; j < size; j++) {

		}
		// calculate and output Average
		average = ((totalTime - processes.get(size - 1).getWait()) / processes.size());
		System.out.printf("\nAverage Process Time for First Come First Serve => %.2f \n\n", average);

	}// fcfs

	// **********************************************************************************************
	private static void sjf(List<Process> processes) {

		int sum = 0;
		int totalTime = 0;
		int size = processes.size();
		float average = 0;
		// sort the list (ascending)
		Collections.sort(processes);

		for (int i = 0; i < size; i++) {
			// set the wait times to zero.
			processes.get(0).setWait(0);
			// Assign values to sum
			sum += processes.get(i).getBurstTime();
			processes.get(i).setWait(sum);
			// total the sum
			totalTime += sum;
		}
		for (int j = 0; j < size; j++) {

			System.out.printf("");
		}
		// calculate and output Average
		average = ((totalTime - processes.get(size - 1).getWait()) / processes.size());
		System.out.printf("\nAverage Process Time for Shortest Job First => %.2f \n\n", average);

	}// sjf

	// *************************************************************************************************
	private static void rr(List<Process> processes) {
		int bt[] = new int[processes.size()];
		int wt[] = new int[processes.size()];
		int q[] = new int[processes.size()];

		int sum = 0;
		int currentTime = 0;
		int size = processes.size();
		float average = 0;
		float temp = 0;

		// instance of scanner type.
		// allows use of the keyboard.
		Scanner console = new Scanner(System.in);
		// input data from the user.
		System.out.println("please enter the  quantum:  ");
		int quantum = console.nextInt();
		System.out.println();
		System.out.println("*********** Inital Data ************ ");
		for (int i = 0; i < size; i++) {

			bt[i] = processes.get(i).getBurstTime();
			wt[i] = 0;
			System.out.println("Process" + i + " Bt => " + bt[i] + " Wt => " + wt[i]);

		} // for
		System.out.println("Quantum =>" + quantum);

		// ******************************************************************************
		// get the initial sum of burst times.
		for (int i = 0; i < size; i++) {

			sum += bt[i];
		} // for
		System.out.println("inital sum is => " + sum);
		System.out.println();
		System.out.println();
		while (sum > 0) {

			for (int i = 0; i < size; i++) {

				if ((bt[i] > 0)) {

					if (bt[i] >= quantum) {
						// deduct a quantum from the burst Time.
						bt[i] -= quantum;
						sum -= quantum;
						wt[i] = currentTime;
						q[i] += 1;
						// update current time by one quantum
						currentTime += quantum;
						// print output to screen
						System.out.println("Process" + i + ", Bt remaining => " + bt[i] + ", Wt => " + wt[i]
								+ ", Sum => " + sum + " ctime" + currentTime + "  quantums " + i + ": " + q[i]);

					} else if ((bt[i] < quantum) && (bt[i] > 0)) {
						// update current time the burst time
						currentTime += bt[i];
						// calculate the sum of bt[].
						sum -= bt[i];
						// update the wait time.
						wt[i] = currentTime - bt[i];
						bt[i] -= bt[i];// setting bt[i] to zero
						// increase the number of quantum.
						q[i] += 1;
						// print output to screen
						System.out.println("Process" + i + ", Bt remaining => " + bt[i] + ", Wt => " + wt[i]
								+ ", Sum => " + sum + " ctime" + currentTime + "  quantums " + i + ": " + q[i]);
					} // end if

				} // if

			} // end for

		} // while

		// check if any of the process times are zero
		// if it is it will give a false average ie; set a minus number;;
		for (int i = 0; i < size; i++) {
			if (wt[i] == 0) {
				q[i] = 0;
				temp += wt[i] - ((q[i] - 1) * quantum);

			} else {
				temp += wt[i] - ((q[i] - 1) * quantum);
			}

		}
		// output to screen
		System.out.println();
		System.out.printf("Average Process Time for RR => %.2f", (temp / size));

	}// rr

	public static void main(String[] args) {
		int noOfProcesses = 0;
		int processName = 0;
		int processTime = 0;
		int wait = 0;
		int choice = 0;
		int sentinal = -1;

		//
		Scanner console = new Scanner(System.in);
		List<Process> processes = new ArrayList<>();

		// getting values from the user
		// for the process name(id) and the process time.
		System.out.println("Please enter the number of processes");
		noOfProcesses = console.nextInt();

		for (int i = 0; i < noOfProcesses; i++) {
			System.out.println("Please enter process Id");
			processName = console.nextInt();

			System.out.printf("Please enter process burst Time for process: %d \n", processName);
			processTime = console.nextInt();

			processes.add(new Process(processName, processTime, wait));

		} // for
			// output to screen
		System.out.println("***********************************************************************************");
		System.out.println("Please enter which type of scheduling you want to perform ?");
		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println(
				" Select 1 for First Come First Served\n\n Select 2 for Shortest job First:\n\n Select 3 for Round Robin:\n\n Select -1 to exit:");
		// get choice selection from user.
		choice = console.nextInt();

		while (choice != sentinal) {

			if (choice == 1) {
				System.out
						.println("***********************************************************************************");
				System.out.println("You selected 1 ==> First Come First Served");
				Runner.fcfs(processes);

				choice = 999999;

			}

			if (choice == 2) {
				System.out.println(
						"\n***********************************************************************************\n");
				System.out.println("You selected 2 ==> Shortest job First");
				Runner.sjf(processes);
				choice = 999999;

			}

			if (choice == 3) {
				System.out.println("You selected 3 ==> Round Robin");
				System.out.println(
						"\n***********************************************************************************\n");
				Runner.rr(processes);
				choice = 999999;
			}
			if (choice == sentinal) {

				System.exit(0);
				return;

			}
			// Initial/sub read for while condition.
			System.out
					.println("\n***********************************************************************************\n");
			System.out.println(
					"\n\nSelect (1) for First Come First Served\n\n Select (2) for Shortest job First:\n\n Select (3) for Round Robin:\n\n Select (-1) to exit:");
			choice = console.nextInt();
		} // while

	}// main

	// Runner.fcfs(processes);
}// class
