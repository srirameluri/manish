
import java.util.*;
import java.io.*;

public class employee_details{								// Employ Details Class
	public static String Name = "User_Name";
	public static int ID = 000;
	public static long Phone_Number = 1234567890;
	public static int Salary = 0;
	public static String Text_File_Name = "Employee_Details.txt";

	public static void main(String[] args){					// Main Function
		Scanner sc = new Scanner(System.in);
		System.out.print("Written By - Yeshwanth\n1 - Save New Employee Details.\n2 - Get Employee Details.\nPlease enter your Option :");
		int Option = sc.nextInt();
		switch(Option){
			case 1:											// For Saving Employee Details
				Get_Employee_Details();										// Getting the employee Details
				Boolean Save_Status = Save_Employee_Details();				// Saving Employee Datails and Assigning the Save_Status
				if(Save_Status)												// If Save_Status is true
					System.out.println("Successful");						// Print Success
				else 														// If Save_Status is false
					System.out.println("Failed");							// Print Failed
				
				break;
			case 2:											// For Displaying User Details
				Boolean Find_Status = Print_Employee_Details();									// Printing out the Employee Details
				if(Find_Status)
					break;
				else
					System.out.println("Data Not Found...");
				break;

			default:
				System.out.print("Under Construction...");
		}
	}

	public static void Get_Employee_Details(){				// Getting the Employee Details from the User Input
		Scanner sc = new Scanner(System.in);

		System.out.print("Name : ");
		Name = sc.next();											// Scanning Name
		System.out.print("Employee ID : ");
		ID = sc.nextInt();											// Scanning ID
		System.out.print("Phone Number : ");
		Phone_Number = sc.nextInt();								// Scanning Phone_Number
		System.out.print("Salary : ");
		Salary = sc.nextInt();										// Scanning Salary
	}

	public static Boolean Save_Employee_Details(){			// Appending Employee_Details to the text File
		System.out.println("\nSaving...");							// Printing the Status
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String data = Name +"~"+ Integer.toString(ID) +"~"+ Long.toString(Phone_Number) +"~"+ Integer.toString(Salary) +"\n";
			File file = new File(Text_File_Name);					// Creating a new File Object

			if(!file.exists())										// if file doesnt exists, then create it
				file.createNewFile();
			fw = new FileWriter(file.getAbsoluteFile(), true);		// 'true' means append the data to the file.
			bw = new BufferedWriter(fw);
			bw.write(data);
			System.out.println("Done");
		}
		catch (IOException e){										// Catching the IO Exception
			e.printStackTrace();									// Printing the Exception
			return false;											// Returning false as it failed with an exception
		}
		finally{
			try{
				if(bw != null)										// Closing the Buffer Writer
					bw.close();
				if(fw != null)										// Closing the File Writer
					fw.close();
				return true;										// Returning true if Successful
			}
			catch (IOException ex){									// catching IO Exception
				ex.printStackTrace();								// Printing the Exception
				return false;										// Returning false as it Failed with an Exception
			}
		}
	}

	public static Boolean Print_Employee_Details(){			// Printing the Employee Details
		// String[] parts = string.split("_|_");
		Scanner sc = new Scanner(System.in);
		System.out.print("Please Enter the Employee ID : ");
		ID = sc.nextInt();											// Scanning ID
		BufferedReader reader;
		try {														// reading the file line by line
			reader = new BufferedReader(new FileReader(Text_File_Name));
			String line = reader.readLine();						// Reading the first line
			while (line != null) {									// if the line is not null
				String[] parts = line.split("~");					// Splitting the data 
				if(parts[1].equals(Integer.toString(ID))){			// Comparing the ID
					System.out.print("\n");
					System.out.println("Name : "+parts[0]);			// Printing Employee Name
					System.out.println("ID : "+parts[1]);			// Printing the ID
					System.out.println("Phone_Number : "+parts[2]); // Printing the Phone_Number
					System.out.println("Salary : "+parts[3]);		// Printing the Salary
					return true;									// Returning True
				}
				line = reader.readLine();					// read next line
			}
			reader.close();
		} catch (IOException e) {									// Catching the Exception
			e.printStackTrace();									// printing the Exception
			return false;											// Returning False
		}
		return false;												// Returning False
	}
}
