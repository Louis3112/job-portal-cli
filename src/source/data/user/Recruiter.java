package source.data.user;

import source.data.job.Job;

public class Recruiter extends User<Recruiter> {
    private static final int MAX_POSTED_JOBS = 10;

    private String companyId;
    private Job[] postedJobs;
    private int top;

    public Recruiter(String companyId, Job[] postedJobs) {
        if (postedJobs.length > MAX_POSTED_JOBS) {
            throw new IllegalArgumentException("Maximum number of posted jobs is " + MAX_POSTED_JOBS);
        }
        this.companyId = companyId;
        this.postedJobs = postedJobs;
        this.top = -1;
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

}
