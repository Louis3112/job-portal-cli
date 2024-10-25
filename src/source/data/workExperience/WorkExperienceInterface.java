package source.data.workExperience;

import java.time.LocalDate;

public interface WorkExperienceInterface {
    LocalDate getStartDate();
    LocalDate getEndDate();
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);

    default boolean isCurrentJob() {
        return getEndDate() == null;
    }

    default String getDuration() {
        if (getStartDate() == null) {
            return "Invalid dates";
        }
        LocalDate end = getEndDate() != null ? getEndDate() : LocalDate.now();
        return calculateDuration(getStartDate(), end);
    }

    private static String calculateDuration(LocalDate startDate, LocalDate endDate) {
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
