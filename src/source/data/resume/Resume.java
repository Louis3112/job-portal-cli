package source.data.resume;

import source.data.workExperience.WorkExperience;

import java.util.ArrayList;

public class Resume {
    private String reumseId;
    private String userId;
    private String lastEducation;
    private ArrayList<WorkExperience> workingExperiences;

    public Resume(String reumseId, String userId, String lastEducation) {
        this.reumseId = reumseId;
        this.userId = userId;
        this.lastEducation = lastEducation;
    }

    public Resume(String reumseId, String userId, String lastEducation, ArrayList<WorkExperience> workingExperiences) {
        this.reumseId = reumseId;
        this.userId = userId;
        this.lastEducation = lastEducation;
        this.workingExperiences = workingExperiences;
    }

    public String getReumseId() {return reumseId;}
    public void setReumseId(String reumseId) {this.reumseId = reumseId;}
    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}
    public String getLastEducation() {return lastEducation;}
    public void setLastEducation(String lastEducation) {this.lastEducation = lastEducation;}

    public ArrayList<WorkExperience> getWorkingExperiences() {return workingExperiences;}

    public void addWorkingExperience(WorkExperience workExperience) {this.workingExperiences.add(workExperience);}

    public void removeWorkingExperience(int index) {this.workingExperiences.remove(index);}
}
