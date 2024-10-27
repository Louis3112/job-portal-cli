package source.data.company;
import source.data.job.Job;

public class Company {

    private String companyId;
    private String name;
    private String address;
    private String phone;
    private String industry;

    public Company(String companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public Company(String companyId, String name, String address, String phone, String industry) {
        this(companyId, name);
        this.address = address;
        this.phone = phone;
        this.industry = industry;
    }
}