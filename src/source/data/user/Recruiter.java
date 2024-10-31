package source.data.user;

import source.data.application.Application;
import source.data.application.ApplicationStatus;
import source.data.job.Job;
import source.data.resume.Resume;

import java.util.ArrayList;
import java.util.List;

public class Recruiter extends User<Recruiter> {
    private static final int MAX_POSTED_JOBS = 10;

    private String companyId;
    private Job[] postedJobs;
    private int top;
    private List<Application> jobApplications;

    public Recruiter(String name, String email, String phone, String address, String companyId) {
        super(name, email, phone, address);
        this.companyId = companyId;
        this.postedJobs = new Job[MAX_POSTED_JOBS];
        this.top = -1;
        this.jobApplications = new ArrayList<Application>();
    }

    public String getCompanyId() {return companyId;}
    public Job[] getPostedJobs() {return postedJobs;}
    public int getTop() {return top;}
    public List<Application> getJobApplications(String jobId) {
        List<Application> filteredJobApplications = new ArrayList<Application>();

        for (Application application : jobApplications) {
            if (application.getJobId().equals(jobId)) {
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

    public void reviewJobApplications(String jobId) {
        List<Application> jobApplications = getJobApplications(jobId);

        for (Application application : jobApplications) {
            switch (application.getStatus()) {
                case APPLIED :
                    reviewResume(application);
                    break;
                case RESUME_REVIEWED:
                    System.out.println("Resume reviewed to " + application.getJobId());
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
    }

    public void reviewResume(Application application) {
        JobSeeker applicant = application.getApplicant();
        Resume resume = applicant.getResume();

        boolean isFullfilledBasicRequirements = checkBasicRequirements(resume);

        if (isFullfilledBasicRequirements) {
            application.updateStatus(
                    ApplicationStatus.SCHEDULED_INTERVIEW,
                    "Congratulations!, You are scheduled interview to " + application.getJobId()
            );
        } else {
            application.updateStatus(
                    ApplicationStatus.REJECTED,
                    "Unfortunately, you are rejected " + application.getJobId()
            );
        }
    }

    private boolean checkBasicRequirements(Resume resume) {
        return true;
    }

}
