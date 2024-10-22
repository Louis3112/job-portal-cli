package source.data.company;
import source.data.job.Job;

public class Company {

    private static final int MAX_POSTED_JOBS = 10;

    private String companyId;
    private String name;
    private String address;
    private String phone;
    private String industry;
    private Job[] postedJobs;
    private int top;

    public Company(String companyId, String name) {
        this.companyId = companyId;
        this.name = name;
        this.postedJobs = new Job[MAX_POSTED_JOBS];
        this.top = -1;
    }

    public Company(String companyId, String name, String address, String phone, String industry) {
        this(companyId, name);
        this.address = address;
        this.phone = phone;
        this.industry = industry;
    }

    @SuppressWarnings("all")
    public Company(String companyId, String name, String address, String phone, String industry, Job[] postedJobs) {
        if (postedJobs.length > MAX_POSTED_JOBS) {
            throw new IllegalArgumentException("Too many posted jobs, max job posted is " + MAX_POSTED_JOBS);
        } else {
            this.companyId = companyId;
            this.name = name;
            this.address = address;
            this.phone = phone;
            this.industry = industry;
            this.postedJobs = postedJobs;
        }
    }

    public String getCompanyId() {return companyId;}
    public void setCompanyId(String companyId) {this.companyId = companyId;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getIndustry() {return industry;}
    public void setIndustry(String industry) {this.industry = industry;}
    public Job[] getPostedJobs() {return postedJobs;}
    public void setPostedJobs(Job[] jobs) {this.postedJobs = jobs;}

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return (top == MAX_POSTED_JOBS - 1);
    }

    public void postJob(Job job) throws IllegalStateException {
        if (top >= MAX_POSTED_JOBS) {
            throw new IllegalStateException("Too many posted jobs, max job posted is " + MAX_POSTED_JOBS);
        }
        postedJobs[++top] = job;
    }

    public Job removeLatestJob() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Job posted is empty");
        }
        Job removedJob = postedJobs[top];
        postedJobs[top--] = null;
        return removedJob;
    }

    public Job viewLatestJob() throws IllegalStateException {
        if (isEmpty()) {
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