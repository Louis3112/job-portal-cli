package source.data.experience;

import java.time.LocalDate;

public class Education extends Experience {
    private String educationExperienceId;
    private String level;

    public Education(String educationExperienceId, String institution, String description, String level, LocalDate startDate, LocalDate endDate) {
        this.educationExperienceId = educationExperienceId;
        this.institution = institution;
        this.description = description;
        this.level = level;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Education(String educationExperienceId, String institution, String description, String level) {
        this.educationExperienceId = educationExperienceId;
        this.institution = institution;
        this.description = description;
        this.level = level;
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
