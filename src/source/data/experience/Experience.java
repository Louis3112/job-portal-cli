package source.data.experience;

import java.time.LocalDate;

public abstract class Experience {
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected String institution;
    protected String description;

    public abstract void setStartDate(LocalDate startDate);
    public abstract void setEndDate(LocalDate endDate);
    public abstract void setInstitution(String institution);
    public abstract void setDescription(String description);    

    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public String getInstitution() {return institution;}
    public String getDescription() {return description;}

    public String getDuration() {
        if (getStartDate() == null) {
            return "invalid dates";
        }
        LocalDate end = getEndDate() != null ? getEndDate() : LocalDate.now();
        return calculateDuration(getStartDate(), end);
    }

    public boolean isCurrently() {
        return getEndDate() == null;
    }

    public String calculateDuration(LocalDate startDate, LocalDate endDate) {
        int years = endDate.getYear() - startDate.getYear();
        int months = endDate.getMonthValue() - startDate.getMonthValue();

        if (months < 0) {
            years--;
            months += 12;
        }

        if (years > 0 && months > 0) {
            return String.format("%d years %d months", years, months);
        } else if (months > 0) {
            return String.format("%d months", months);
        } else {
            return String.format("%d years", years);
        }
    }
}
