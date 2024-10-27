package source.data.experience;

import java.time.LocalDate;
import java.util.UUID;

public class WorkExperience extends Experience {
    private String workExperienceId;
    private String position;

    public WorkExperience(String institution, String description, String position) {
        this.workExperienceId = UUID.randomUUID().toString();
        this.institution = institution;
        this.description = description;
        this.position = position;
    }

    public WorkExperience(String institution, String description, String position, LocalDate startDate, LocalDate endDate) {
        this(institution, description, position);
        this.startDate = startDate;
        this.endDate = endDate;
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
