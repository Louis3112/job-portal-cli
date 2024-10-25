package source.data.workExperience;

import java.time.LocalDate;

public class WorkExperience implements WorkExperienceInterface {
    private String workExperienceId;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    public WorkExperience(String workExperienceId, String companyName, LocalDate startDate, LocalDate endDate) {
        this.workExperienceId = workExperienceId;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WorkExperience(String workExperienceId, String companyName, LocalDate startDate, LocalDate endDate, String description) {
        this.workExperienceId = workExperienceId;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public LocalDate getStartDate() {return startDate;}

    @Override
    public LocalDate getEndDate() {return endDate;}

    @Override
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    @Override
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}

    public String getWorkExperienceId() {return workExperienceId;}
    public void setWorkExperienceId(String workExperienceId) {this.workExperienceId = workExperienceId;}
    public String getCompanyName() {return companyName;}
    public void setCompanyName(String companyName) {this.companyName = companyName;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

}
