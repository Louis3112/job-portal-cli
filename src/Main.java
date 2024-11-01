import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import source.data.application.Application;
import source.data.company.Company;
import source.data.experience.WorkExperience;
import source.data.job.Job;
import source.data.job.JobCategory;
import source.data.resume.Resume;
import source.data.user.JobSeeker;
import source.data.user.Recruiter;
import source.data.user.User;
import source.utils.auth.Auth;
import source.utils.global.Global;
import source.utils.register.Register;

public class Main {
    private static Auth auth = new Auth();
    private static User<?> loggedInUser = auth.getLoggedInUser();
    private static JobSeeker loggedInJobSeeker = (JobSeeker) loggedInUser;
    private static Recruiter loggedInRecruiter = (Recruiter) loggedInUser;

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
                    auth.login();

                    loggedInUser = auth.getLoggedInUser();
                    if (loggedInUser instanceof JobSeeker) {
                        loggedInJobSeeker = (JobSeeker) loggedInUser;
                        menuJobseeker(loggedInJobSeeker);
                    } else {
                        loggedInRecruiter = (Recruiter) loggedInUser;
                        menuRecruiter(loggedInRecruiter);
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
                    System.out.println("===List of Applications Menu====");

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

                    System.out.println(" ");
                    System.out.print("\tEnter Job Name   : ");
                    String jobName = Global.sc.nextLine();

                    Job foundJob = Global.findJobByName(jobName);

                    int currentIndex = 0;

                    LinkedList<Application> applications = new LinkedList<>(loggedInRecruiter.getJobApplications(foundJob.getJobId()));
                    if (!applications.isEmpty()) {
                        boolean isJobApplicationsMenuRunning = true;
                        while (isJobApplicationsMenuRunning) {

                            Application currentApp = applications.get(currentIndex);

                            System.out.println("\tApplicants For Job : " + foundJob.getJobName());
                            System.out.println(" ");
                            System.out.println("\t\tApplicant Status   : " + currentApp.getStatus());
                            System.out.println("\t\tApplicant Name     : " + currentApp.getApplicant().getName());
                            System.out.println("\t\tApplicant Email    : " + currentApp.getApplicant().getEmail());
                            System.out.println("\t\tApplicant Address  : " + currentApp.getApplicant().getAddress());
                            System.out.println("\t\tApplicant Phone    : " + currentApp.getApplicant().getPhone());
                            System.out.println("\t\t=================================================");
                            System.out.println("\t\tApplicant Resume   : ");
                            System.out.println("\t\t\t-> Last Education      : " + currentApp.getApplicant().getResume().getLastEducation());
                            System.out.println("\t\t\t-> Working Experiences : ");
                            for (WorkExperience workExperience : currentApp.getApplicant().getResume().getWorkingExperiences()) {
                                System.out.println("\t\t\t\t+ Position     : " + workExperience.getPosition());
                                System.out.println("\t\t\t\t+ Institution  : " + workExperience.getInstitution());
                                if (workExperience.isCurrently()) {
                                    System.out.println("\t\t\t\t+ Duration     : Currently working here.");
                                } else {
                                    System.out.println("\t\t\t\t+ Duration     : " + workExperience.getStartDate() + " - " + workExperience.getEndDate());
                                }
                                System.out.println(" ");
                            }

                            int jobApplicationsMenuChoice = listOfJobApplicationsMenu();

                            switch (jobApplicationsMenuChoice) {
                                case 1 :
                                    if (currentIndex < applications.size() - 1) {
                                        currentIndex++;
                                    } else {
                                        System.out.println("Reached the end of the applications list.");
                                    }
                                    break;
                                case 2 :
                                    if (currentIndex > 0) {
                                        currentIndex--;
                                    } else {
                                        System.out.println("Reached the beginning of the applications list.");
                                    }
                                    break;
                                case 3 :
                                    loggedInRecruiter.reviewJobApplications(currentApp);
                                    System.out.println("Press enter to continue...");
                                    Global.sc.nextLine();
                                    break;
                                case 4 :
                                    isJobApplicationsMenuRunning = false;
                                    break;
                            }
                        }

                        System.out.println("Press enter to continue");
                        Global.sc.nextLine();
                    } else {
                        System.out.println("\n\t\tNo applications found for this job.");
                        return;
                    }
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
                "4. Check Applied Jobs Update",
                "5. Logout"
        };

        boolean isMenuJobSeekerRunning = true;
        while (isMenuJobSeekerRunning) {

            System.out.println("===Job Seeker Menu====");
            for (String option : menuOptions) {
                System.out.println(option);
            }
            System.out.println("==================");
            System.out.print("Enter your choice (1-" + (menuOptions.length) + ") : ");

            String input = Global.sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Empty input is not allowed. Please enter a number between 1 and " + menuOptions.length + ".");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if(choice < 1 || choice > menuOptions.length) {
                    throw new InputMismatchException();
                }

                switch (choice) {
                    case 1 :
                        System.out.println("\tBASIC PROFILE");
                        System.out.println("\tName    : " + jobSeeker.getName());
                        System.out.println("\tEmail   : " + jobSeeker.getEmail());
                        System.out.println("\tPhone   : " + jobSeeker.getPhone());
                        System.out.println("\tAddress : " + jobSeeker.getAddress());

                        System.out.println(" ");

                        System.out.println("\tRESUME");
                        Resume resumeInfo = jobSeeker.getResume();
                        if (resumeInfo != null) {
                            if (resumeInfo.getLastEducation() != null) {
                                System.out.print("\tLast Education     : ");
                                System.out.println(resumeInfo.getLastEducation());
                            } else {
                                System.out.println("\tNo last education profile yet!");
                            }

                            if (resumeInfo.getWorkingExperiences() != null) {
                                if (resumeInfo.getWorkingExperiences().size() > 0) {
                                    System.out.println("\tWork Experiences :");
                                    for (int i = 0; i < resumeInfo.getWorkingExperiences().size(); i++) {
                                        int idx = i + 1;
                                        System.out.println("\t\t" + (idx) + ". " + resumeInfo.getWorkingExperiences().get(i).getPosition());
                                        System.out.println("\t\t\tInstitution    : " + resumeInfo.getWorkingExperiences().get(i).getInstitution());
                                        System.out.println("\t\t\tDescription    : " + resumeInfo.getWorkingExperiences().get(i).getDescription());
                                        if (resumeInfo.getWorkingExperiences().get(i).isCurrently()) {
                                            System.out.println("\t\t\tWorking from   : " + resumeInfo.getWorkingExperiences().get(i).getStartDate() + " until now.");
                                        } else {
                                            System.out.println("\t\t\tWorking from   : " + resumeInfo.getWorkingExperiences().get(i).getStartDate() + " - " + resumeInfo.getWorkingExperiences().get(i).getEndDate());
                                        }
                                    }
                                } else {
                                    System.out.println("\tWorking experiences not set!");
                                }
                            } else {
                                System.out.println("\tNo working experiences yet!");
                            }
                        }

                        System.out.println("Press enter to continue");
                        Global.sc.nextLine();
                        break;
                    case 2 :
                        System.out.println("===Update Profile====");

                        JobSeeker authenticatedJobSeeker = (JobSeeker) auth.getLoggedInUser();

                        boolean isProfileMenuRunning = true;
                        while (isProfileMenuRunning) {
                            int profileMenuChoice = updateProfileMenu();

                            switch (profileMenuChoice) {
                                case 1 :
                                    System.out.println("\tCHOOSE BASIC PROFILE TO UPDATE");

                                    boolean isBasicProfileMenuRunning = true;
                                    while (isBasicProfileMenuRunning) {
                                        int basicProfileMenuChoice = updateBasicProfileMenu();

                                        switch (basicProfileMenuChoice) {
                                            case 1 :
                                                System.out.print("\tEnter New Name : ");
                                                String newName = Global.sc.nextLine();

                                                jobSeeker.setName(newName);
                                                Global.hashUsers.put(authenticatedJobSeeker.getEmail(), jobSeeker);
                                                authenticatedJobSeeker.setName(newName);

                                                System.out.println("\nName updated!");
                                                System.out.println("Press enter to continue");
                                                Global.sc.nextLine();
                                                break;
                                            case 2 :
                                                System.out.print("\tEnter New Email : ");
                                                String newEmail = Global.sc.nextLine();

                                                jobSeeker.setEmail(newEmail);
                                                Global.hashUsers.put(authenticatedJobSeeker.getEmail(), jobSeeker);
                                                authenticatedJobSeeker.setEmail(newEmail);

                                                System.out.println("\nEmail updated!");
                                                System.out.println("Press enter to continue");
                                                Global.sc.nextLine();
                                                break;
                                            case 3 :
                                                System.out.print("\tEnter New Phone Number : ");
                                                String newPhone = Global.sc.nextLine();

                                                jobSeeker.setPhone(newPhone);
                                                Global.hashUsers.put(authenticatedJobSeeker.getEmail(), jobSeeker);
                                                authenticatedJobSeeker.setPhone(newPhone);

                                                System.out.println("\nPhone updated!");
                                                System.out.println("Press enter to continue");
                                                Global.sc.nextLine();
                                                break;
                                            case 4 :
                                                System.out.print("\tEnter New Address : ");
                                                String newAddress = Global.sc.nextLine();

                                                jobSeeker.setAddress(newAddress);
                                                Global.hashUsers.put(authenticatedJobSeeker.getEmail(), jobSeeker);
                                                authenticatedJobSeeker.setName(newAddress);

                                                System.out.println("\nAddress updated!");
                                                System.out.println("Press enter to continue");
                                                Global.sc.nextLine();
                                                break;
                                            case 5 :
                                                isBasicProfileMenuRunning = false;
                                                break;
                                        }
                                    }

                                    break;
                                case 2 :
                                    System.out.println("\tCHOOSE RESUME PROPERTY TO UPDATE");

                                    boolean isResumeMenuRunning = true;
                                    while (isResumeMenuRunning) {
                                        int resumeMenuChoice = updateResumeMenu();

                                        switch (resumeMenuChoice) {
                                            case 1 :
                                                System.out.print("\tEnter New Last Education : ");
                                                String newLastEdu = Global.sc.nextLine();

                                                jobSeeker.getResume().setLastEducation(newLastEdu);
                                                Global.hashUsers.put(authenticatedJobSeeker.getEmail(), jobSeeker);
                                                authenticatedJobSeeker.getResume().setLastEducation(newLastEdu);

                                                System.out.println("\nLast Education updated!");
                                                System.out.println("Press enter to continue");
                                                Global.sc.nextLine();
                                                break;
                                            case 2 :
                                                System.out.print("\tAdd New Job Experience");

                                                System.out.println("\tFill Form Below : ");

                                                System.out.print("\t\tEnter Position : ");
                                                String position = Global.sc.nextLine();

                                                String institution;
                                                Company company;
                                                while (true) {
                                                    System.out.print("\t\tEnter Institution : ");
                                                    institution = Global.sc.nextLine();

                                                    company = Global.findCompanyByName(institution);

                                                    if (company == null) {
                                                        System.out.println("\tInvalid: Company didn't exist!");
                                                        System.out.println("\tPress enter to retry...");
                                                        Global.sc.nextLine();
                                                        Global.clearConsole();
                                                        continue;
                                                    }

                                                    break;
                                                }

                                                System.out.print("\t\tEnter Description : ");
                                                String description = Global.sc.nextLine();

                                                System.out.print("\t\tEnter Start Date(dd MMM yyyy format)  : ");
                                                String startDate = Global.sc.nextLine();

                                                System.out.print("\t\tEnter End Date(dd MMM yyyy format, input '-' if you currently working here)    : ");
                                                String endDate = Global.sc.nextLine();

                                                LocalDate formattedStartDate = Global.formatDate(startDate);
                                                LocalDate formattedEndDate = endDate.equals("-") ? null : Global.formatDate(endDate);

                                                WorkExperience newWorkExperience = new WorkExperience(institution, description, position, formattedStartDate, formattedEndDate);
                                                jobSeeker.getResume().addWorkingExperience(newWorkExperience);
                                                Global.hashUsers.put(authenticatedJobSeeker.getEmail(), jobSeeker);

                                                System.out.println("\nNew Job Experienced Added!");
                                                System.out.println("Press enter to continue...");
                                                Global.sc.nextLine();
                                            case 3 :
                                                System.out.print("\tRemove Job Experience");
                                                break;
                                            case 4 :
                                                isResumeMenuRunning = false;
                                                break;
                                        }
                                    }
                                case 3 :
                                    isProfileMenuRunning = false;
                                    break;
                            }
                        }

                        break;
                    case 3 :
                        System.out.println("===Apply For a Job Menu====");

                        System.out.println(" ");
                        System.out.println("\tList of Available Jobs");
                        if (Global.postedJobs.size() < 1) {
                            System.out.println("There's no job posted yet");
                        } else {
                            for (int i = 0; i < Global.postedJobs.size(); i++) {
                                if (Global.postedJobs.get(i) != null) {
                                    int idx = i + 1;
                                    System.out.println("\t\t" + (idx) + ". " + Global.postedJobs.get(i).getJobName());
                                }
                            }

                            System.out.print("\tEnter your job name : ");
                            String job = Global.sc.nextLine();

                            Job foundJob = Global.findJobByName(job);

                            showJobDetails(foundJob);

                            char isApply = 'N';
                            System.out.print("\tApply for the job(Y/N) : ");
                            if (Global.sc.hasNext("[YyNn]")) {
                                isApply = Global.sc.next().toUpperCase().charAt(0);
                                Global.sc.nextLine();

                                if (isApply == 'Y' || isApply == 'y') {
                                    Application application = new Application(foundJob.getJobId(), jobSeeker, LocalDateTime.now());
                                    loggedInRecruiter.addJobApplication(application);
                                }
                            } else {
                                System.out.println("Invalid input. Please enter Y or N.");
                                Global.sc.nextLine();
                            }

                            System.out.println("\tLogged in recruiter name : " + loggedInRecruiter.getName());
                            System.out.println("Press enter to continue...");
                            Global.sc.nextLine();
                        }
                        break;
                    case 4 :
                        System.out.println("===Applied Job Status====");
                        List<Application> appliedJobs = loggedInRecruiter.getJobApplicationsByUserId(jobSeeker.getUserId());

                        for (int i = 0; i < appliedJobs.size(); i++) {
                            Job foundJob = Global.findJobById(appliedJobs.get(i).getJobId());
                            if (appliedJobs.get(i) != null) {
                                int idx = i + 1;
                                System.out.println("\t" + (idx) + ". " + foundJob.getJobName());
                                System.out.println("\t\tStatus   : " + appliedJobs.get(i).getStatus());
                                if (appliedJobs.get(i).getNotes() != null) {
                                    System.out.println("\t\tNotes    : " + appliedJobs.get(i).getNotes());
                                }
                            }
                        }

                        System.out.println("Press enter to continue...");
                        Global.sc.nextLine();
                        break;
                    case 5:
                        isMenuJobSeekerRunning = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and " + menuOptions.length + ".");
                Global.clearConsole();
                menuJobseeker(jobSeeker);
            }
        }
    }

    static int updateProfileMenu() {
        System.out.println("===Update Profile Menu====");

        String[] menuOptions = {
                "1. Update Basic Profile",
                "2. Update Resume",
                "3. Back",
        };

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
            return updateProfileMenu();
        }

        return choice;
    }

    static int updateBasicProfileMenu() {
        System.out.println("===Update Basic Profile Menu====");

        String[] menuOptions = {
                "1. Name",
                "2. Email",
                "3. Phone",
                "4. Address",
                "5. Back",
        };

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
            return updateBasicProfileMenu();
        }

        return choice;
    }

    static int updateResumeMenu() {
        System.out.println("===Update Resume Menu====");

        String[] menuOptions = {
                "1. Change Last Education",
                "2. Add Work Experience",
                "3. Remove Work Experience",
                "4. Back",
        };

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
            return updateResumeMenu();
        }

        return choice;
    }

    static void showJobDetails(Job job) {
        System.out.println("===Job Details Menu====");

        System.out.println("\tJob Name        : " + job.getJobName());
        System.out.println("\tJob Description : " + job.getJobDescription());
        System.out.println("\tCategories      :");
        for (JobCategory category : job.getJobCategories()) {
            System.out.println("\t\t - " + category.getName());
        }
    }

    static int listOfJobApplicationsMenu() {
        String[] menuOptions = {
                "1. Next",
                "2. Prev",
                "3. Automate Review",
                "4. Back",
        };

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
            return listOfJobApplicationsMenu();
        }

        return choice;
    }

}
