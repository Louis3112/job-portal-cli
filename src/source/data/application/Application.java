package source.data.application;

import source.data.user.JobSeeker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private String applicationId;
    private String jobId;
    private JobSeeker applicant;
    private LocalDateTime appliedTime;
    private ApplicationStatus status;
    private List<ApplicationNote> notes;

    public Application(){

    }
    public Application(String jobId, JobSeeker applicant, LocalDateTime appliedTime) {
        this.jobId = jobId;
        this.applicant = applicant;
        this.appliedTime = appliedTime;
        this.status = ApplicationStatus.APPLIED;
        this.notes = new ArrayList<ApplicationNote>();
    }

    public String getApplicationId() {return applicationId;}
    public String getJobId() {return jobId;}
    public JobSeeker getApplicant() {return applicant;}
    public LocalDateTime getAppliedTime() {return appliedTime;}
    public ApplicationStatus getStatus() {return status;}
    public List<ApplicationNote> getNotes() {return notes;}

    public void addNote(ApplicationNote note) {
        notes.add(note);
    }

    public void updateStatus(ApplicationStatus newStatus, String comment) {
        status = newStatus;
        notes.add(new ApplicationNote(newStatus, comment));
    }
}
