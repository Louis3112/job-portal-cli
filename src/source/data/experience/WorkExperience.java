package source.data.experience;

import java.time.LocalDate;

public class WorkExperience extends Experience {
    private String workExperienceId;
    private String position;

    public WorkExperience(String workExperienceId, String institution, String description, String position, LocalDate startDate, LocalDate endDate) {
        this.workExperienceId = workExperienceId;
        this.institution = institution;
        this.description = description;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WorkExperience(String workExperienceId, String institution, String description, String position) {
        this.workExperienceId = workExperienceId;
        this.institution = institution;
        this.description = description;
        this.position = position;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
