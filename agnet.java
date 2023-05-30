package bank;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class agnet {

	static Scanner user = new Scanner(System.in);
	static Scanner password = new Scanner(System.in);
	static Scanner name = new Scanner(System.in);
	static Scanner genders = new Scanner(System.in);
	static Scanner userN = new Scanner(System.in);
	static Scanner choisee = new Scanner(System.in);
	static Scanner Number = new Scanner(System.in);

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.out.println(
				"---------------------------------------------Welcome on Programme of Bank------------------------------------------------------------");

		System.out.println("For Registion on System type 1:");
		System.out.println("For Connction to Your Account type 2:");

		int choise = user.nextInt();
		if(choise == 1) {

			System.out.println("Please Username:");

			String fullname = name.nextLine();

			File name = new File("C:\\Users\\user\\Desktop\\fullname.txt");

			FileWriter full = new FileWriter(name,true);

			PrintWriter printname = new PrintWriter(full);

			printname.println(fullname);
			printname.close();

			System.out.println("What is Your Gender:");
			System.out.println("1-Male");
			System.out.println("2-Female");

			int gender = genders.nextInt();

			int male = 1;
			int female = 2;

			if (gender == 1) {
				System.out.println("Welcome Mr." + fullname);

			} else if (gender == 2) {
				System.out.println("Welcome Mrs." + fullname);
			} else {
				System.out.println("I'm Sorry Try Again!!");
			}

			System.out.println("");
			System.out.println("*********************************************************************");
			//-----------------------Cardid-----------------------------------------------------------------	
			Set<Integer> ID = new HashSet<Integer>();

			Random ids = new Random();

			int id_2 = 15;

			int id;

			do {
				id = ids.nextInt(id_2);

			} while (ID.contains(ids));

			ID.add(id);

			String id_1 = Integer.toString(id);

			File filev = new File("C:\\Users\\user\\Desktop\\java file\\price.txt");
			FileWriter writev = new FileWriter(filev, true);
			PrintWriter prntv = new PrintWriter(writev);
			prntv.println(id_1);
			prntv.close();

			System.out.println("Your ID is:"+id_1);

			//------------------------------------------password-----------------------------------------------------------------
			System.out.println(
					"*****************************************************************************************************************");
			System.out.println("");
			System.out.println("Enter your password:");

			File passwrod = new File("C:\\Users\\user\\Desktop\\password.txt");

			BufferedReader readers = new BufferedReader(new FileReader(passwrod));
			String lines;
			Boolean founds = false;

			while ((lines = readers.readLine()) != null) {

				String passwords = password.nextLine();
				if (!founds) {
					FileWriter writer = new FileWriter(passwrod, true);
					PrintWriter printt = new PrintWriter(writer);
					printt.println(passwords);
					printt.close();

				}

				System.out.println("Confirm your password:");
				String confirmpassword = password.nextLine();

				if (passwords.equals(confirmpassword)) {

					System.out.println("Your Account Ready!");
					System.out.println("***********************");
					System.out.println("Rest Program!!");
					break;

				} else if (!passwords.equals(confirmpassword)) {
					System.out.println("Password Not Same, Please Try Again");

				}
			}
			System.out.println("");

			//------------------------------------------basic price-----------------------------------------------------------------
			
			File Mn = new File("C:\\Users\\user\\Desktop\\java file\\taman.txt");
			FileWriter tira = new FileWriter(Mn, true);
			PrintWriter prints = new PrintWriter(tira);
			int pric_basic = 0;

			prints.println(pric_basic);
			prints.close();

			System.out.println("Your price is:"+pric_basic);

			System.out.println(
					"*****************************************************************************************************************");
			System.out.println("");


		}
		//-------------------------------------------------------------------------------------------------

		if(choise == 2) {

				System.out.println("Enter Username:");

				File nameN = new File("C:\\Users\\user\\Desktop\\fullname.txt");
		
				try (BufferedReader readN = new BufferedReader(new FileReader(nameN))) {
		
					String usern = userN.nextLine();
					String lineN;
					Boolean foundN = false;
		
					while ((lineN = readN.readLine()) != null) {
		
						if (lineN.equals(usern)) {
							foundN = true;
							break;
						}
					}
		
					if (foundN) {
						System.out.println("");
					} else {
						System.out.println("Username Not Found. Please Try Again!");
						return;
					}
		
				} catch (IOException e) {
					System.out.println("Error reading file: " + e.getMessage());
				}

				System.out.println("******************");

				System.out.println("");
				System.out.println("Enter your Password:");
				List<String> pasword = Files.readAllLines(Paths.get("C:\\Users\\user\\Desktop\\password.txt"));
				String passwords = password.nextLine();
				int N1 = Integer.parseInt(passwords);
				for (String line : pasword) {
					int N2 = Integer.parseInt(line);
					if (N2 == N1) {
						System.out.println("Welcome on Your Account");
						break;
					} else if (N2 != N1) {
						System.out.println("Your Password Not Correct! Please Try Again");
						break;
					}

				}
				
				Scanner scanner = new Scanner(System.in);

        account bAccount;

        System.out.println("Enter initial balance:");
        double intialbalance = scanner.nextDouble();

        File flos = new File("C:\\Users\\user\\Desktop\\java file\\taman.txt");
        if (flos.exists()) {
            bAccount = loadAccount(intialbalance);
        } else {
            bAccount = new account(intialbalance);
        }
        while (true) {
            System.out.println("1.Deposit");
            System.out.println("2.Whitdraw");
            System.out.println("3.check balance");
            System.out.println("4.Exite");
            System.out.println("Enter your choise");
            int choiss = scanner.nextInt();
            switch (choiss) {
                case 1:
                    System.out.println("Enter deposit Amount :");
                    double DM = scanner.nextDouble();
                    bAccount.Deposite(DM);
                    saveTransaction("Deposite", DM);
                    break;
                case 2:
                    System.out.println("Enter Whitdraw Amount :");
                    double WM = scanner.nextDouble();
                    bAccount.withdraw(WM);
                    saveTransaction("withdraw", WM);
                    break;
                case 3:
                    System.out.println("Current balance:" + bAccount.getbalance());
                    break;
                case 4:
                    saveAccount(bAccount);
                    System.out.println("Exting");
                default:
                    System.out.println("Invalid choise");
            }
            System.out.println();
        }

    }
	//System.out.println(aBnk);

}

	private static void saveAccount(account bAccount) {
		try (BufferedWriter writ = new BufferedWriter(
				new FileWriter("C:\\Users\\user\\Desktop\\java file\\taman.txt", true))) {
			writ.write("Balance" + bAccount.getbalance());
			writ.newLine();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error saving account: " + e.getMessage());
		}
	}

	private static void saveTransaction(String transactionType , double amount) {
		try (BufferedWriter writ = new BufferedWriter(
				new FileWriter("C:\\Users\\user\\Desktop\\java file\\taman.txt", true))) {
			writ.write(transactionType + "," + amount);
			writ.newLine();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error saving");
		}
	}

	private static account loadAccount(double intialbalance) {
		try (BufferedReader reads = new BufferedReader(
				new FileReader("C:\\Users\\user\\Desktop\\java file\\taman.txt"))) {
			String line;
			while ((line = reads.readLine()) != null) {
				String[] parts = line.split(",");
				String transactionType = parts[0];
				double amount = Double.parseDouble(parts[1]);

				if (transactionType.equals("Deposite")) {
					intialbalance += amount;

				} else if (transactionType.equals("withdraw")) {
					intialbalance -= amount;

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error loading account: " + e.getMessage());
		}
		return new account(intialbalance);
	}

}

