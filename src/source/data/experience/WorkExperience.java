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

    public void setPosition(String position){this.position = position;}

    public String getWorkExperienceID(){return workExperienceId;}
    public String getPosition(){return position;}
    public String getDescription(){return description;}
    public String getInstitution(){return institution;}
    
    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setDescription(String description) {
        this.description= description;
    }

    @Override
    public void setInstitution(String institution) {
        this.institution = institution;
    }

}
