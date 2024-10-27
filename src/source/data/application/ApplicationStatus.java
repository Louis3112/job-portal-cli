package source.data.application;

public enum ApplicationStatus {
    APPLIED,              // Initial status when job seeker applies
    RESUME_REVIEWED,      // After recruiter reviews the resume
    REJECTED,             // If rejected at any stage
    SHORTLISTED,          // Passed resume screening
    SCHEDULED_INTERVIEW,  // Interview scheduled
    INTERVIEW_COMPLETED,  // Finished interview
    TECHNICAL_TEST_SENT,  // Technical assessment sent
    TECHNICAL_TEST_COMPLETED, // Completed technical assessment
    OFFERED,             // Job offer sent
    ACCEPTED,            // Candidate accepted offer
    DECLINED             // Candidate declined offer
}
