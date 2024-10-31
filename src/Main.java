import java.util.*;

import source.data.company.Company;
import source.data.job.Job;
import source.data.job.JobCategory;
import source.data.user.JobSeeker;
import source.data.user.Recruiter;
import source.data.user.User;
import source.utils.auth.Auth;
import source.utils.global.Global;
import source.utils.register.Register;

public class Main {
    public static void main(String[] args) {
        Global.header();

        boolean isRunning = true;
        while (isRunning) {
            int homeChoice = home();
            Global.clearConsole();

            switch (homeChoice) {
                case 1:
                    int registerChoice = Register.showMenu();
                    Global.clearConsole();

                    switch (registerChoice) {
                        case 1:
                            Register.jobSeeker();
                            break;
                        case 2:
                            Register.recruiter();
                            break;
                        case 3:
                            System.out.println("Press enter to continue");
                            Global.sc.nextLine();
                            break;
                    }
                    break;
                case 2:
                    Auth auth = new Auth();
                    auth.login();

                    User<?> loggedInUser = auth.getLoggedInUser();
                    if (loggedInUser instanceof JobSeeker) {
                        JobSeeker jobSeeker =(JobSeeker) loggedInUser;
                        menuJobseeker(jobSeeker);
                    } else {
                        Recruiter recruiter = (Recruiter) loggedInUser;
                        menuRecruiter(recruiter);
                    }
                    break;
                case 3:
                    System.out.println("Exiting System. Goodbye!");
                    isRunning = false;
                    break;
            }
        }

    }

    static int home() {
        String[] menuOptions = {
                "1. Register",
                "2. Login",
                "3. Exit"
        };

        System.out.println("===Home Menu====");
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
            return home();
        }

        return choice;
    }

    static void menuRecruiter(Recruiter recruiter) {
        String[] menuOptions = {
                "1. View Profile",
                "2. Post a Job",
                "3. Remove Latest Job",
                "4. View Posted Job",
                "5. Review Job Application",
                "6. Logout"
        };

        System.out.println("===Recruiter Menu====");
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

            switch (choice) {
                case 1 :
                    System.out.println("===View Profile====");

                    Company company = Global.findCompanyById(recruiter.getCompanyId());

                    System.out.println("\tName    : " + recruiter.getName());
                    System.out.println("\tEmail   : " + recruiter.getEmail());
                    System.out.println("\tPhone   : " + recruiter.getPhone());
                    System.out.println("\tAddress : " + recruiter.getAddress());
                    System.out.println("\tCompany : " + company.getName());
                    System.out.println("Press enter to continue");

                    Global.sc.nextLine();
                    break;
                case 2 :
                    System.out.println("===Post a Job====");

                    System.out.print("\tEnter job name        : ");
                    String name = Global.sc.nextLine();

                    System.out.print("\tEnter job description : ");
                    String description = Global.sc.nextLine();

                    System.out.println("List job categories   : ");
                    for (Map.Entry<Integer, JobCategory> entry : Global.jobCategories.entrySet()) {
                        System.out.println("\t" + entry.getKey() + " : " + entry.getValue().getName());
                        System.out.println("\tDescription : " + entry.getValue().getDescription());
                        System.out.println(" ");
                    }

                    // accept categories input with "," as separator, so later it will be a multiple values
                    System.out.print("\tInput job categories index(separate with ',') : ");
                    String categories = Global.sc.nextLine();

                    String[] categoryIndexes = categories.split(",");
                    Set<Integer> selectedCategoriesAsIndex = new HashSet<>();
                    for (String index : categoryIndexes) {
                        selectedCategoriesAsIndex.add(Integer.parseInt(index.trim()));
                    }

                    // find match categories based on indexes above
                    ArrayList<JobCategory> selectedCategories = new ArrayList<>();
                    for (int categoryIndex : selectedCategoriesAsIndex) {
                        JobCategory jobCategory = Global.jobCategories.get(categoryIndex);
                        selectedCategories.add(jobCategory);
                    }

                    Job newJob = new Job(name, description, selectedCategories, recruiter.getUserId());
                    recruiter.postJob(newJob);
                    Global.postedJobs.add(newJob);

                    System.out.println("\nNew Job : " + name + " created!");
                    System.out.println("Press enter to continue...");
                    Global.sc.nextLine();
                    break;
                case 3 :
                    System.out.println("===Remove Newest Job====");

                    if (recruiter.isPostedJobsEmpty()) {
                        System.out.println("There's no job posted yet");
                    } else {
                        Job latestJob = recruiter.viewLatestJob();
                        System.out.println("\tJob Name        : " + latestJob.getJobName());
                        System.out.println("\tJob Description : " + latestJob.getJobName());
                        System.out.println("\tJob Categories  : ");
                        for (JobCategory category : latestJob.getJobCategories()) {
                            System.out.println("\t\t - " + category.getName());
                        }

                        System.out.println(" ");

                        char isRemove = 'N';
                        System.out.print("\tRemove job(Y/N) : ");
                        if (Global.sc.hasNext("[YyNn]")) {
                            isRemove = Global.sc.next().toUpperCase().charAt(0);
                            Global.sc.nextLine();

                            if (isRemove == 'Y' || isRemove == 'y') {
                                recruiter.removeLatestJob();
                            }
                        } else {
                            System.out.println("Invalid input. Please enter Y or N.");
                            Global.sc.nextLine();
                        }

                        String word = (isRemove == 'Y' || isRemove == 'y') ? "\nRemove Job Successful!" : "\nJob Not Removed!";
                        System.out.println(word);
                    }
                    System.out.println("Press enter to continue...");
                    Global.sc.nextLine();
                    break;
                case 4:
                    System.out.println("===View Posted Jobs by " + recruiter.getName() + " ====");

                    if (recruiter.isPostedJobsEmpty()) {
                        System.out.println("There's no job posted yet");
                    } else {
                        for (int i = 0; i < recruiter.getPostedJobs().length; i++) {
                            if (recruiter.getPostedJobs()[i] != null) {
                                int idx = i + 1;
                                System.out.println("\t" + (idx) + ". " + recruiter.getPostedJobs()[i].getJobName());
                            }
                        }
                    }

                    System.out.println("Press enter to continue...");
                    Global.sc.nextLine();
                    break;
                case 5:
                    System.out.println("Review Job Applications");
                    break;
                case 6:
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and " + menuOptions.length + ".");
            Global.clearConsole();
            menuRecruiter(recruiter);
        }
    }

    static void menuJobseeker(JobSeeker jobSeeker) {
        String[] menuOptions = {
                "1. View Profile",
                "2. Update Profile",
                "3. Apply Jobs",
                "4. Logout"
        };

        System.out.println("===Recruiter Menu====");
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

            switch (choice) {
                case 1 :
                    System.out.println("View Profile Menu");
                    break;
                case 2 :
                    System.out.println("Update Profile Menu");
                    break;
                case 3 :
                    System.out.println("Apply For a Job Menu");
                    break;
                case 4:
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and " + menuOptions.length + ".");
            Global.clearConsole();
            menuJobseeker(jobSeeker);
        }
    }

}
