package source.data.resume;

import java.util.ArrayList;
import java.util.UUID;
import source.data.experience.WorkExperience;

public class Resume {
    private String resumeId;
    private String userId;
    private String lastEducation;
    private ArrayList<WorkExperience> workingExperiences;

    public Resume(){

    }
    
    public Resume(String userId, String lastEducation) {
        this.resumeId = UUID.randomUUID().toString();
        this.userId = userId;
        this.lastEducation = lastEducation;
    }

    public Resume(String resumeId, String userId, String lastEducation, ArrayList<WorkExperience> workingExperiences) {
        this(userId, lastEducation);
        this.workingExperiences = workingExperiences;
    }

    public String getResumeId() {return resumeId;}
    public void setResumeId(String reumseId) {this.resumeId = reumseId;}
    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}
    public String getLastEducation() {return lastEducation;}
    public void setLastEducation(String lastEducation) {this.lastEducation = lastEducation;}

    public ArrayList<WorkExperience> getWorkingExperiences() {return workingExperiences;}

    public void addWorkingExperience(WorkExperience workExperience) {this.workingExperiences.add(workExperience);}

    public void removeWorkingExperience(int index) {this.workingExperiences.remove(index);}
}
