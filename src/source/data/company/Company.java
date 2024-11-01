package source.data.company;
import source.data.job.Job;

import java.util.UUID;

public class Company {

    private String companyId;
    private String name;
    private String address;
    private String phone;
    private String industry;

    public Company(String name) {
        this.companyId = UUID.randomUUID().toString();
        this.name = name;
    }

    public Company(String name, String address, String phone, String industry) {
        this(name);
        this.address = address;
        this.phone = phone;
        this.industry = industry;
    }

    public String getCompanyId() {return companyId;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getPhone() {return phone;}
    public String getIndustry() {return industry;}
}