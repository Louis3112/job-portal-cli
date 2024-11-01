package source.data.user;

import source.data.application.Application;
import source.data.application.ApplicationStatus;
import source.data.job.Job;
import source.data.resume.Resume;
import source.utils.global.Global;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Recruiter extends User<Recruiter> {
    private static final int MAX_POSTED_JOBS = 10;

    private String companyId;
    private Job[] postedJobs;
    private int top;
    private LinkedList<Application> jobApplications;

    public Recruiter(String name, String email, String phone, String address, String companyId) {
        super(name, email, phone, address);
        this.companyId = companyId;
        this.postedJobs = new Job[MAX_POSTED_JOBS];
        this.top = -1;
        this.jobApplications = new LinkedList<Application>();
    }

    public String getCompanyId() {return companyId;}
    public Job[] getPostedJobs() {return postedJobs;}
    public int getTop() {return top;}

    public List<Application> getJobApplications(String jobId) {
        List<Application> filteredJobApplications = new LinkedList<Application>();

        for (Application application : jobApplications) {
            if (application.getJobId().equals(jobId)) {
                filteredJobApplications.add(application);
            }
        }

        return filteredJobApplications;
    }

    public List<Application> getJobApplicationsByUserId(String userId) {
        List<Application> filteredJobApplications = new LinkedList<Application>();

        for (Application application : jobApplications) {
            if (application.getApplicant().getUserId().equals(userId)) {
                filteredJobApplications.add(application);
            }
        }

        return filteredJobApplications;
    }

    @Override
    public void updateProfile(Recruiter newProfile) {
        this.companyId = newProfile.companyId;
        this.postedJobs = newProfile.postedJobs;
    }

    @Override
    public void search() {}

    public void addJobApplication(Application application) {
        jobApplications.add(application);
    }

    public boolean isPostedJobsEmpty() {return top == -1;}

    public boolean isPostedJobsFull() {return top == MAX_POSTED_JOBS - 1;}

    public void postJob(Job job) throws IllegalStateException {
        if (top >= MAX_POSTED_JOBS) {
            throw new IllegalStateException("Too many posted jobs, max job posted is " + MAX_POSTED_JOBS);
        }
        postedJobs[++top] = job;
    }

    public Job removeLatestJob() throws IllegalStateException {
        if (isPostedJobsEmpty()) {
            throw new IllegalStateException("Job posted is empty");
        }
        Job removedJob = postedJobs[top];
        postedJobs[top--] = null;
        return removedJob;
    }

    public Job viewLatestJob() throws IllegalStateException {
        if (isPostedJobsEmpty()) {
            throw new IllegalStateException("Job posted is empty");
        }
        return postedJobs[top];
    }

    public int getNumberPostedJobs() {return top + 1;}

    public Job[] getListOfPostedJobs() {
        Job[] activeJobs = new Job[getNumberPostedJobs()];
        for (int i = 0; i <= top; i++) {
            activeJobs[i] = postedJobs[i];
        }
        return activeJobs;
    }

    public void reviewJobApplications(Application application) {
        switch (application.getStatus()) {
            case APPLIED :
                reviewResume(application);
                break;
            case SCHEDULED_INTERVIEW:
                conductInterview(application);
                break;
            case INTERVIEW_COMPLETED:
                System.out.println("Scheduled interview to " + application.getJobId());
                break;
            case TECHNICAL_TEST_COMPLETED:
                System.out.println("Technical test completed to " + application.getJobId());
                break;
            case OFFERED:
                System.out.println("Offered to " + application.getJobId());
                break;
        }
    }

    public void reviewResume(Application application) {
        System.out.println("\n\t\t=== Resume Review ===");
        System.out.println("\t\tApplicant  : " + application.getApplicant().getName());
        System.out.println("\t\tJob ID     : " + application.getJobId());

        JobSeeker applicant = application.getApplicant();
        Resume resume = applicant.getResume();

        boolean isFullfilledBasicRequirements = checkBasicRequirements(resume);

        if (isFullfilledBasicRequirements) {
            System.out.println("\t\tScheduling the interview...");

            System.out.print("\t\tEnter Scheduled Date(dd MMM yyyy format)  : ");
            String scheduledDate = Global.sc.nextLine();

            System.out.print("\t\tEnter Location                            : ");
            String location = Global.sc.nextLine();

            LocalDate formattedScheduledDate = Global.formatDate(scheduledDate);

            application.updateStatus(
                    ApplicationStatus.SCHEDULED_INTERVIEW,
                    "Congratulations!, You are scheduled interview!," +
                            "Here's the details : " +
                            "Date       : " + formattedScheduledDate +
                            "Location   : " + location +
                            ". Good Luck!"
            );
        } else {
            application.updateStatus(
                    ApplicationStatus.REJECTED,
                    "Unfortunately, you are rejected"
            );
        }
    }

    public void conductInterview(Application application) {
        System.out.println("\n\t\t=== Conducting Interview ===");
        System.out.println("\t\tApplicant  : " + application.getApplicant().getName());
        System.out.println("\t\tJob ID     : " + application.getJobId());

        char choice = 'N';
        System.out.print("\tIs the applicant passed?(Y/N) : ");
        if (Global.sc.hasNext("[YyNn]")) {
            choice = Global.sc.next().toUpperCase().charAt(0);
            Global.sc.nextLine();

            if (choice == 'Y' || choice == 'y') {

                System.out.println("\t\tScheduling the technical test...");

                System.out.print("\t\tEnter Scheduled Date(dd MMM yyyy format)  : ");
                String scheduledDate = Global.sc.nextLine();

                LocalDate formattedScheduledDate = Global.formatDate(scheduledDate);

                application.updateStatus(
                        ApplicationStatus.INTERVIEW_COMPLETED,
                        "Congratulations!, You are passes the interview!," +
                                "Here's the details for technical test : " +
                                "Date       : " + formattedScheduledDate +
                                ". Good Luck!"
                );
            } else {
                application.updateStatus(
                        ApplicationStatus.REJECTED,
                        "Unfortunately, you are rejected"
                );
            }
        } else {
            System.out.println("Invalid input. Please enter Y or N.");
            Global.sc.nextLine();
        }
    }

    public void conductTechnicalTest(Application application) {
        System.out.println("\n\t\t=== Conducting Technical Test ===");
        System.out.println("\t\tApplicant  : " + application.getApplicant().getName());
        System.out.println("\t\tJob ID     : " + application.getJobId());

        System.out.print("\tEnter test final score (0-100) : ");
        int score = Integer.parseInt(Global.sc.nextLine());

        if (score > 75) {
            application.updateStatus(
                    ApplicationStatus.TECHNICAL_TEST_COMPLETED,
                    "Congratulations! You have passed the technical test with a score of "
                            + score + ". Your application will be reviewed for a final offer."
            );
        } else {
            application.updateStatus(
                    ApplicationStatus.REJECTED,
                    "Unfortunately, your technical test score of " + score +
                            " did not meet our requirements."
            );
        }
    }

    private boolean checkBasicRequirements(Resume resume) {
        if (resume.getWorkingExperiences().isEmpty()) {
            return false;
        } else if (!resume.getLastEducation().contains("University")) {
            return false;
        }
        return true;
    }

}
