package source.utils.register;

import source.data.company.Company;
import source.data.user.JobSeeker;
import source.data.user.Recruiter;
import source.utils.global.Global;

import java.util.InputMismatchException;

public class Register {
    public static int showMenu() {
        String[] menuOptions = {
                "1. Register as Job Seeker",
                "2. Register as Recruiter",
                "3. Back"
        };

        System.out.println("===Register Menu====");
        for (String option : menuOptions) {
            System.out.println(option);
        }
        System.out.println("==================");
        System.out.print("Enter your choice (1-" + (menuOptions.length) + ") : ");

        int choice;
        try {
            choice = Integer.parseInt(Global.sc.nextLine());
            if(choice < 1 || choice > menuOptions.length) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and " + menuOptions.length + ".");
            Global.clearConsole();
            return showMenu();
        }

        return choice;
    }

    public static void jobSeeker() {
        System.out.println("===Register Job Seeker====");

        System.out.print("\tEnter Your Name : ");
        String name = Global.sc.nextLine();

        String email;
        while (true) {
            System.out.print("\tEnter Your Email : ");
            email = Global.sc.nextLine();

            if (Global.hashUsers.containsKey(email)) {
                System.out.println("\tError: An account with this email already exists!");
                System.out.println("\tPress enter to retry...");
                Global.sc.nextLine();
                Global.clearConsole();
                System.out.println("===Register Job Seeker====");
                System.out.println("\tName: " + name);
                continue;
            }

            break;
        }

        System.out.print("\tEnter Your Phone Number : ");
        String phone = Global.sc.nextLine();

        System.out.print("\tEnter Your Address : ");
        String address = Global.sc.nextLine();

        JobSeeker newJobSeeker = new JobSeeker(name, email, phone, address);
        Global.hashUsers.put(email, newJobSeeker);

        System.out.println("\nRegistration Successful!");
        System.out.println("Press enter to continue...");
        Global.sc.nextLine();
    }

    public static void recruiter() {
        System.out.println("===Register Recruiter====");

        System.out.print("\tEnter Your Name : ");
        String name = Global.sc.nextLine();

        String email;
        while (true) {
            System.out.print("\tEnter Your Email : ");
            email = Global.sc.nextLine();

            if (Global.hashUsers.containsKey(email)) {
                System.out.println("\tError: An account with this email already exists!");
                System.out.println("\tPress enter to retry...");
                Global.sc.nextLine();
                Global.clearConsole();
                System.out.println("===Register Job Seeker====");
                System.out.println("\tName: " + name);  // Show previously entered name
                continue;
            }

            break;
        }

        System.out.print("\tEnter Your Phone Number : ");
        String phone = Global.sc.nextLine();

        System.out.print("\tEnter Your Address : ");
        String address = Global.sc.nextLine();

        String companyName;
        Company company;
        while (true) {
            System.out.print("\tEnter Your Company Name : ");
            companyName = Global.sc.nextLine();

            company = Global.findCompanyByName(companyName);

            if (company == null) {
                System.out.println("\tInvalid: Company didn't exist!");
                System.out.println("\tPress enter to retry...");
                Global.sc.nextLine();
                Global.clearConsole();
                continue;
            }

            break;
        }

        Recruiter newRecruiter = new Recruiter(name, email, phone, address, company.getCompanyId());
        Global.hashUsers.put(email, newRecruiter);

        System.out.println("\nRegistration Successful!");
        System.out.println("Press enter to continue...");
        Global.sc.nextLine();
    }
}
