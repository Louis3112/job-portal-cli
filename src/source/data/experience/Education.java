package source.data.experience;

import java.time.LocalDate;
import java.util.UUID;

public class Education extends Experience {
    private String educationExperienceId;
    private String level;

    public Education(String institution, String description, String level) {
        this.educationExperienceId = UUID.randomUUID().toString();
        this.institution = institution;
        this.description = description;
        this.level = level;
    }

    public Education(String institution, String description, String level, LocalDate startDate, LocalDate endDate) {
        this(institution, description, level);
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
