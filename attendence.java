package tools;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class attendence {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int i = 100;
		while (i == 100) {
			System.out.println("You Are A:- ");
			System.out.println("1. Student");
			System.out.println("2. Teacher");
			System.out.println("3. Exit");
			int option = sc.nextInt();
			if (option == 1) {
				System.out.println("Welcome Student ");
				System.out.println("Enter your choice -");
				System.out.println("1. View Your Attendence ");
				System.out.println("2. Exit");
				int t = sc.nextInt();
				if (t == 1) {
					try {
						System.out.println("Enter your name: ");
						String name = sc.next();
						
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendence", "root",
								"root");
						PreparedStatement p = con.prepareStatement("select * from att where name=?");
						
						p.setString(1, name);
						ResultSet r = p.executeQuery();
						while (r.next()) {
							System.out.println(r.getString(1) + "\t\t" + r.getString(2));
						}

					} catch (Exception e) {

						e.printStackTrace();
					}

				}
				if (t == 2) {
					break;
				}
			}
			if (option == 2) {
				System.out.println("Welcome Teacher Enter Username: ");
				String tname = sc.next();
				System.out.println("Enter Password: ");
				String pass = sc.next();
				if (tname.equals("Siddhant") && pass.equals("pass")) {
					System.out.println("Enter your Choice - ");
					System.out.println("1. View Student's Attendence");
					System.out.println("2. Update Attendence");
					System.out.println("3. Exit");
					int t = sc.nextInt();
					if (t == 1) {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendence",
									"root", "root");
							PreparedStatement ps = con.prepareStatement("select * from att ");
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								System.out.println(rs.getString(1) + "\t\t " + rs.getString(2));

							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (t == 2) {
						try {
							System.out.println("Enter name of the student :");
							String n = sc.next();
							
							System.out.println("Enter attendence of the student :");
							String at = sc.next();

							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendence",
									"root", "root");
							PreparedStatement ps = con.prepareStatement("update att set attendence=? where name=? ");
							
							ps.setString(1, at);
							ps.setString(2, n);
							ps.executeUpdate();
						}

						catch (Exception e) {
							e.printStackTrace();
						}

					}
					if (t == 3) {
						break;
					}
				}

			}

		}
	}
}
