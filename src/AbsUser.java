import java.util.ArrayList;

class UserData{
    private String name;
    private String email;
    private String phoneNumber;
    private String NIK; // nomor induk kependudukan

    public UserData(String name, String email, String phoneNumber, String NIK){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.NIK = NIK;
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

    public String getNIK(){
        return NIK;
    }
    public void setNIK(String NIK){
        this.NIK = NIK;
    }
}

class UserPreference{
    private String domicile; // kota
    private int salary;
    private ArrayList<JobCategory> jobCategories;
    private String position;

    public UserPreference(String domicile, int salary, ArrayList<JobCategory> jobCategories){
        this.domicile = domicile;
        this.salary = salary;
        this.jobCategories = jobCategories;
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

    public ArrayList<JobCategory> getJobCategories(){
        return jobCategories;
    }
    public void setCategory(ArrayList<JobCategory> jobCategories){
        this.jobCategories = jobCategories;
    }

    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }
}

abstract class AbsUser{
    protected UserData basicData;
    protected UserPreference preferenceData;

    public AbsUser(UserData basicData, UserPreference preferenceData){
        this.basicData = basicData;
        this.preferenceData = preferenceData;
    }

    public abstract void displayInfo();
    public abstract void updatePreference(UserPreference preference);
}
