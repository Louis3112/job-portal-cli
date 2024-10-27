package source.data.user;

import source.data.job.Job;

import java.util.ArrayList;

public class Recruiter extends User<Recruiter> {
    private String companyId;
    private ArrayList<Job> postedJobs;

    @Override
    public void updateProfile(Recruiter newProfile) {
        this.companyId = newProfile.companyId;
        this.postedJobs = newProfile.postedJobs;
    }

    @Override
    public void search() {}
}
