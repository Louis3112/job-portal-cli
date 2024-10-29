//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
import source.data.experience.*;
import source.data.job.Job;
import source.data.resume.*;
import source.data.user.*;

public class Main {
    private static HashMap<String, User<?>> hashUsers = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        boolean programRunning = true;
        while (programRunning ==  true){        
            registerLogin();
        }

        System.out.println("Thank you");
    }
public static void registerLogin(){
    System.out.println("\nWelcome to Job Portal");
    System.out.println("1. Register");
    System.out.println("2. Login");
    System.out.println("3. Exit"); // sebenarnya, itu aku mau atur setingannya biar 
    System.out.print("> ");
    
    int choiceStart = sc.nextInt();
    sc.nextLine();
    
    switch(choiceStart){
        case 1:{
            System.out.println("\t1. Register as Job Seeker");
            System.out.println("\t2. Register as Recruiter");
            System.out.print("\t> ");
    
            int choiceRegister = sc.nextInt();
            sc.nextLine();
    
            switch (choiceRegister){
                case 1: registerJobSeeker();
                    break;
                case 2: registerRecruiter();
                    break;
            }
            registerLogin();
        }    
        break;

        case 2: loginPage();
        break;
        
        case 3:{
            System.out.println();
            System.out.println("Are you sure you want to exit? (y/n)");
            System.out.print("> ");

            String logout = sc.nextLine();
            if(logout.equalsIgnoreCase("y")){
                return;
            }
            else{
                break;
            }
        }
        default:{
            System.out.println("Invalid input");
        }
    }
}

    private static void registerJobSeeker() {
        System.out.println("");
        System.out.print("\tEnter name: ");
            String registerName = sc.nextLine();
        
        System.out.print("\tEnter email: ");
            String registerEmail = sc.nextLine();
        
            if (hashUsers.containsKey(registerEmail)) {
                System.out.println("\tAn account with this email already exists.");
                return;
            }
    
        System.out.print("\tEnter phone number: ");
            String registerPhone = sc.nextLine();
    
        System.out.print("\tEnter address: ");
            String registerAddress = sc.nextLine();

        JobSeeker newJobSeeker = new JobSeeker(registerName, registerEmail, registerPhone, registerAddress);
        hashUsers.put(registerEmail, newJobSeeker);
        
        System.out.println();
        System.out.println("\tJob Seeker registered successfully, Welcome " + registerName);
        System.out.println("\tPress enter to continue");

        sc.nextLine();
    }

    private static void registerRecruiter() {
        System.out.print("\tEnter name: ");
            String registerName = sc.nextLine();
        
        System.out.print("\tEnter email: ");
            String registerEmail = sc.nextLine();

            if (hashUsers.containsKey(registerEmail)) {
                System.out.println("\tAn account with this email already exists.");
                return;
            }

        System.out.print("\tEnter phone number: ");
            String registerPhone = sc.nextLine();
    
        System.out.print("\tEnter address: ");
            String registerAddress = sc.nextLine();

        String registerCompanyID = UUID.randomUUID().toString(); // since Class Company hasn't been done yet

        Recruiter newRecruiter = new Recruiter(registerName, registerEmail, registerPhone, registerAddress, registerCompanyID);
           hashUsers.put(registerEmail, newRecruiter);
        
        System.out.println();
        System.out.println("\tRecruiter registered successfully, Welcome " + registerName);
        System.out.println("\tPress enter to continue");
        sc.nextLine();
    }
    
    private static void loginPage(){
        System.out.print("Enter email: ");
        String emailLogin = sc.nextLine();
        
        if(!hashUsers.containsKey(emailLogin)){
            System.out.println("No account found with this email");
            return;
        }

        User<?> user = hashUsers.get(emailLogin);
        if(user instanceof JobSeeker){
            MenuJobSeeker((JobSeeker)user);
        }
        else if(user instanceof Recruiter){
            MenuRecruiter((Recruiter)user);
        }
    }    
    
    private static void MenuJobSeeker(JobSeeker user){
        Resume resume = new Resume();
        ArrayList<WorkExperience> workingExperiences = resume.getWorkingExperiences();
        System.out.println("\nWelcome, " + user.getName());
        boolean loggedIn = true;
     
        while(loggedIn){
            System.out.println("\nJob Seeker Menu");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Apply Jobs");
            System.out.println("4. Logout");
            System.out.print("> ");

            int choiceMenu = sc.nextInt();
            sc.nextLine();

            switch (choiceMenu){
                case 1:{
                    System.out.println();
                    System.out.println("ID : " + user.getUserId());
                    System.out.println("Name : " + user.getName());
                    System.out.println("Email: " + user.getEmail());
                    System.out.println("Phone number: " + user.getPhone());
                    System.out.println("Address: " + user.getAddress());

                    System.out.println("\nResume");
                    if (resume.getResumeId() != null) {
                        System.out.println("Resume ID: " + resume.getResumeId());
                        System.out.println("Last Education: " + resume.getLastEducation());
                        System.out.println(); 
                        if (workingExperiences != null && !workingExperiences.isEmpty()){
                            for(WorkExperience checkExperience : workingExperiences){
                                System.out.println("Work Experience ID: " + checkExperience.getWorkExperienceID());
                                System.out.println("Institution: " + checkExperience.getInstitution());
                                System.out.println("Position: " + checkExperience.getPosition());
                                System.out.println("Duration: " + checkExperience.getDuration());
                                System.out.println(); 
                            }
                        } else {
                                System.out.println("No work experience yet");
                        }
                        
                    } else {
                        System.out.println("\tNo resume uploaded yet");
                        System.out.println();
                    }

                    System.out.println("Press enter to continue");
                    sc.nextLine();
                }
                break;

                case 2: {
                    System.out.println();
                    System.out.println("Set Resume and Work Experience"); // BTW, AKU BINGUNG CARA MANGGIL FUNGSINYA

                    System.out.println("What is your academic background (ex: High School, Bachelor, Master, Doctoral)");
                    System.out.print(">");
                        String lastEducation = sc.nextLine();
                    
                        // Refresh setelah pembuatan resume
                        
                        /*boolean addingWorkExperiences = true;
                        while (addingWorkExperiences) {
                            System.out.println("Add Work Experience (Type 'no' to finish): ");
                            System.out.print("Institution: ");
                            String institution = sc.nextLine();
                            
                            if (institution.equalsIgnoreCase("no")) {
                                addingWorkExperiences = false;
                                break;
                            }
        
                            System.out.print("Position: ");
                            String position = sc.nextLine();
                            
                            System.out.print("Duration (in months): ");
                            int duration = sc.nextInt();
                            sc.nextLine();
        
                            // Menambahkan pengalaman kerja ke resume
                            resume.addWorkExperience(institution, position, duration);
                        }
                        System.out.println("Resume and Work Experiences updated successfully.");
                        break;
                    }*/

                    /*System.out.println("Which one do you want to update");   AWALNYA, AKU MAU BUAT FUNGSI INI JADI BISA SET NAMA, DLL.. CUMA SETTER NYA BLM ADA ATAU EMG GK USH AJA??
                    System.out.println("1. Name");  
                    System.out.println("2. Email");
                    System.out.println("3. Phone Number");
                    System.out.println("4. Address");
                    System.out.println("5. Resume ");

                    int choiceSet = sc.nextInt();
                    sc.nextLine();

                    switch (choiceSet){
                        case 1 : {
                            System.out.println("Insert new name: ");
                            String setName = sc.nextLine();
                            
                            System.out.println(setName + " has been to set to your new name");
                            user.setName();
                        }
                    }*/
                }
                break;
                
                case 3 : {
                    System.out.println();
                    System.out.println("Apply Job");
                }
                break;

                case 4 : {
                    System.out.println();
                    System.out.println("Are you sure you want to log out? (y/n)");
                    System.out.print("> ");

                    String logout = sc.nextLine();
                    if(logout.equalsIgnoreCase("y")){
                        loggedIn = false;
                    }
                    else{
                        break;
                    }
                }
                break;

                default : 
                    System.out.println("Invalid input");
                break;
            }
        }
    }
    
    private static void MenuRecruiter(Recruiter user){
        System.out.println("\nWelcome, " + user.getName());
        boolean loggedIn = true;

        while(loggedIn){
            System.out.println("\nRecruiter Menu");
            System.out.println("1. View Profile");
            System.out.println("2. Post Job");
            System.out.println("3. Remove Latest Job");
            System.out.println("4. View Posted Job");
            System.out.println("5. Review Job Applciations");
            System.out.println("6. Logout");
            System.out.print("> ");

            int choiceMenu = sc.nextInt();
            sc.nextLine();

            switch (choiceMenu) {
                case 1:{
                    System.out.println();
                    System.out.println("ID : " + user.getUserId());
                    System.out.println("Company ID : " + user.getCompanyId());
                    System.out.println("Name : " + user.getName());
                    System.out.println("Email: " + user.getEmail());
                    System.out.println("Phone number: " + user.getPhone());
                    System.out.println("Address: " + user.getAddress());
                    
                    System.out.println("Press enter to continue");
                    sc.nextLine();
                }
                break;
                
                case 2:{                    
                    System.out.print("Enter job title: ");
                        String titleJob = sc.nextLine();
                    
                    System.out.print("Enter job description: ");
                        String descriptionJob = sc.nextLine();
                    
                    /*System.out.println("Enter job category");
                        String categoryJob = sc.nextLine();     // aku agak lupa sama pengaplikasiannya 

                    System.out.println("Enter job category description");
                        String categoryJobDescription = sc.nextLine();  
                    */

                    Job addJob = new Job(titleJob, descriptionJob, user.getUserId());
                    user.postJob(addJob);
                    System.out.println(addJob.getJobName() + " posted successfully");
                    
                    System.out.println("Press enter to continue");
                    sc.nextLine();
                }
                break;

                case 3:{
                    Job removedJob = user.removeLatestJob();
                    System.out.println(removedJob.getJobName() + " removed successfully");

                    System.out.println("Press enter to continue");
                    sc.nextLine();
                }
                break;

                case 4:{
                    System.out.println("Posted Jobs:");
                    Job[] listJobs = user.getListOfPostedJobs();
                    for (int i = 0; i < listJobs.length; i++) {
                        System.out.println((i + 1) + ". " + listJobs[i].getJobName() + " - " + listJobs[i].getJobDescription());
                    }

                    System.out.println("Press enter to continue");
                    sc.nextLine();
                }
                break;

                case 5:{
                    // Bingung pake reviewResume atau reviewJobApplicant
                    System.out.println("Press enter to continue");
                    sc.nextLine();
                }
                break;

                case 6 : {
                    System.out.println();
                    System.out.println("Are you sure you want to log out? (y/n)");
                    System.out.print("> ");

                    String logout = sc.nextLine();
                    if(logout.equalsIgnoreCase("y")){
                        loggedIn = false;
                    }
                    else{
                        break;
                    }
                }
                break;
                default:
                    System.out.println("Invalid input");
                    break;
                }
                

            }
        }

    }
