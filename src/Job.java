public class Job {
    private String jobId;
    private String jobName;
    private String jobDescription;
    private EnumCategory category;
    private AbsRecruiter recruiter;

    public Job(String jobId, String jobName, String jobDescription, EnumCategory category, AbsRecruiter recruiter) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.category = category;
        this.recruiter = recruiter;
    }

    public String getJobId() {return jobId;}
    public void setJobId(String jobId) {this.jobId = jobId;}
    public String getJobName() {return jobName;}
    public void setJobName(String jobName) {this.jobName = jobName;}
    public String getJobDescription() {return jobDescription;}
    public void setJobDescription(String jobDescription) {this.jobDescription = jobDescription;}
    public EnumCategory getCategory() {return category;}
    public void setCategory(EnumCategory category) {this.category = category;}
    public AbsRecruiter getRecruiter() {return recruiter;}
    public void setRecruiter(AbsRecruiter recruiter) {this.recruiter = recruiter;}
}
