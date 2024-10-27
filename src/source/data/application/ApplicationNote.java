package source.data.application;

import java.time.LocalDateTime;

public class ApplicationNote {
    private LocalDateTime timestamp;
    private ApplicationStatus status;
    private String comment;
    private String recruiterId;

    public ApplicationNote(ApplicationStatus status, String comment) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.comment = comment;
    }

    public ApplicationNote(ApplicationStatus status, String comment, String recruiterId) {
        this(status, comment);
        this.recruiterId = recruiterId;
    }
}
