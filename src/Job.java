public class Job {
    private String jobId;
    private String jobName;
    private String jobDescription;
    private JobCategory jobCategory;
    private AbsRecruiter recruiter;

    public Job(String jobId, String jobName, String jobDescription, JobCategory jobCategory, AbsRecruiter recruiter) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobCategory = jobCategory;
        this.recruiter = recruiter;
    }

    public String getJobId() {return jobId;}
    public void setJobId(String jobId) {this.jobId = jobId;}
    public String getJobName() {return jobName;}
    public void setJobName(String jobName) {this.jobName = jobName;}
    public String getJobDescription() {return jobDescription;}
    public void setJobDescription(String jobDescription) {this.jobDescription = jobDescription;}
    public JobCategory getCategory() {return jobCategory;}
    public void setCategory(JobCategory jobCategory) {this.jobCategory = jobCategory;}
    public AbsRecruiter getRecruiter() {return recruiter;}
    public void setRecruiter(AbsRecruiter recruiter) {this.recruiter = recruiter;}
}
