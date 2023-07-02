package bank;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.mysql.cj.xdevapi.Result;

public class agnet {

	
	static Scanner name = new Scanner(System.in);
	
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

		String url = "jdbc:mysql://localhost:3306/bank";
		String username = "root";
		String password = "Holag12x@";
		String query = "SELECT * FROM bankinfo";

		System.out.println("For Registion on System type 1:");
		System.out.println("For Connction to Your Account type 2:");

		int choise = user.nextInt();
		if (choise == 1) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return;
			}

			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement checkStatement = connection
							.prepareStatement("SELECT COUNT(*) FROM bankinfo WHERE fullname = ? OR password = ?");
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO bankinfo(fullname,email,password,basicprice) VALUES (?,?,?,?)")) {



				System.out.println("Please Enter Your Fullname:");
				String fullname = name.nextLine();

				System.out.println("Enter Your Email:");
				String email = name.nextLine();

				System.out.println("Enter Password:");
				int pasword = name.nextInt();

                System.out.println("You Should Deposet Some Money On Your Account For Create ");
                int basic = Number.nextInt();

				checkStatement.setString(1, fullname);
				checkStatement.setString(2, password);

				ResultSet resulte = checkStatement.executeQuery();

				resulte.next();

				int count = resulte.getInt(1);

				if (count > 0) {
					System.out.println("Fullname or Password already exists.");
					return; // Stop execution if fullname or password is repeated
				}

				statement.setString(1, fullname);
				statement.setString(2, email);
                statement.setInt(3, pasword);
                statement.setInt(4, basic);
                statement.executeUpdate();




              


				String checkid = "SELECT id FROM bankinfo WHERE fullname = ?";
				String fullname1 = fullname;
				try {
					PreparedStatement chek = connection.prepareStatement(checkid);
					chek.setString(1, fullname1);
				
					ResultSet rust = chek.executeQuery();
				
					if (rust.next()) {
						int id = rust.getInt("id");
						System.out.println("ID: " + id);
					} else {
						System.out.println("This ID does not exist.");
					}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				

				int rowsAffected = statement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("You Can Now Log In");
				} else {
					System.out.println("Info Not Correct !!");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}


		System.out.println("");
		System.out.println("*********************************************************************");








	//-------------------------------------------------------------------------------------------------










	if(choise==2)

	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		try (Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement checkStatement1 = connection.prepareStatement("SELECT COUNT(*) FROM bankinfo WHERE fullname = ? AND password = ?");
        java.sql.Statement statement1 = connection.createStatement();) {

    System.out.println("Enter Username:");
    String name1 = name.nextLine();
    System.out.println("Enter Password:");
    int passwordd = name.nextInt();

    checkStatement1.setString(1, name1);
    checkStatement1.setInt(2, passwordd);

    ResultSet resulte1 = checkStatement1.executeQuery();

    if (resulte1.next()) {
        int count = resulte1.getInt(1);

        if (count > 0) {
            System.out.println("Welcome");
            System.out.println("");


			System.out.println("Enter Your ID:");
			int N1 = name.nextInt();

            String checkprice = "SELECT basicprice FROM bankinfo WHERE id = ?";
            PreparedStatement chek = connection.prepareStatement(checkprice);
			chek.setInt(1, N1);
			
            String updateSql = "UPDATE bankinfo SET basicprice = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setInt(2, N1);

			
            
			ResultSet resultSetm = chek.executeQuery();
			int n1 = N1;
			
			

			

            if (resultSetm.next()) {
                int price = resultSetm.getInt("basicprice");

                if (price == 0) {
                    System.out.println("Your Account has 0$ ");
                    System.out.println("");
                    System.out.println("You Should Deposit Money");
                    System.out.println("");
                    int money = name.nextInt();

                    updateStatement.setInt(1, money);
                    updateStatement.setInt(2, n1);
                    updateStatement.executeUpdate();

                    
                        System.out.println("Your price is: " + price);
                    
                    resultSetm.close();

                    return;

                } else if (price > 0) {

                    System.out.println("1-Withdraw ");
                    System.out.println("");
                    System.out.println("2-Wallet ");
					System.out.println("");
					System.out.println("3-Deposet");

                    Integer choises = name.nextInt();
                    if (choises == 1) {
                        System.out.println("Enter Price You Want to Withdraw");
                        int N2 = name.nextInt();
                        if (price >= N2) {
                            int n3 = price - N2;
                            updateStatement.setInt(1, n3);
                            updateStatement.setInt(2, N1);
                            updateStatement.executeUpdate();
                        } else {
                            System.out.println("You Don't Have Enough Money To Withdraw");
                            return;
                        }

					} else if (choises == 2) {
						
						
							
                            System.out.println("");
							System.out.println("Your price is: " + price);
                        
							resultSetm.close();
							return;
						
                        
                    } else if(choises == 3) {
						System.out.println("Enter How Much You Want Deposet :");
						int money = name.nextInt();

						updateStatement.setInt(1, money);
						updateStatement.setInt(2, n1);
						updateStatement.executeUpdate();

                        return;
                    }
                }else if (price < 0) {
					System.out.println("Your Account had - ");
					System.out.println("");
					System.out.println("1-Deposet");
					System.out.println("2-Wallet ");
                    System.out.println("");

					Integer choises1 = name.nextInt();
					if (choises1 == 1) {
						System.out.println("");
						System.out.println("You Should Deposit Money");
						System.out.println("");
						int money = name.nextInt();

						updateStatement.setInt(1, money);
						updateStatement.setInt(2, n1);
						updateStatement.executeUpdate();
					} else if (choises1 == 2) {
						
                        System.out.println("Your price is: " + price);
                    
                    resultSetm.close();

                    return;
				}
                }
            }
        }
    }
} catch (Exception e) {
    e.printStackTrace();
}


		
		

	}
	}

}

