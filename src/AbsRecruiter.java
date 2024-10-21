class RecruiterData{
    private String name;
    private String email;
    private String phoneNumber;
    private String location;
    private String specialization; // Spesialisasi, ex: IT, health

    public RecruiterData(String name, String email, String phoneNumber, String location, String specialization){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.specialization = specialization;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public String getSpecialization(){
        return specialization;
    }
    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }
}

class RecruiterPreference{
    private String domicile; // Kota
    private int salary;
    private JobCategory jobCategory;

    public RecruiterPreference(String domicile, int salary, JobCategory jobCategory){
        this.domicile = domicile;
        this.salary = salary;
        this.jobCategory = jobCategory;
    }

    public String getDomicile(){
        return domicile;
    }
    public void setDomicile(String domicile){
        this.domicile = domicile;
    }

    public int getSalary(){
        return salary;
    }
    public void setSalary(int salary){
        this.salary = salary;
    }
    public JobCategory getJobCategories(){return jobCategory;}
    public void setCategory(JobCategory jobCategory){
        this.jobCategory = jobCategory;
    }
}

abstract class AbsRecruiter{
    protected RecruiterData basicData;
    protected RecruiterPreference preferenceData;

    public AbsRecruiter(RecruiterData basicData, RecruiterPreference preferenceData){
        this.basicData = basicData;
        this.preferenceData = preferenceData;
    }

    public abstract void displayInfo();
    public abstract void updatePreference(RecruiterPreference preference);
}
