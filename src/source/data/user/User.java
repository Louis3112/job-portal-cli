package source.data.user;

import java.util.ArrayList;
import source.data.job.*;

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
    protected  UserData basicData;
    protected  UserPreference preferenceData;

    public AbsUser(UserData basicData){
        this.basicData = basicData;
        this.preferenceData = null;
    }
    
    // overloading constructor with preference
    public AbsUser(UserData basicData, UserPreference preferenceData){
        this.basicData = basicData;
        this.preferenceData = preferenceData;
    }
    public abstract void displayInfo();
}

public class User extends AbsUser{
    public User(UserData basicData){
        super(basicData);
    }

    public User(UserData basicData, UserPreference preferenceData){
        super(basicData,preferenceData);
    }

    public void displayInfo(){
        System.out.println("Name\t\t: " + basicData.getName());
        System.out.println("NIK\t\t: " + basicData.getNIK());
        System.out.println("Email\t\t: " + basicData.getEmail());
        System.out.println("Phone Number\t :" + basicData.getPhoneNumber());
        System.out.println();
        if(preferenceData != null){
            System.out.println("Preferences : ");
            System.out.println("Domicile\t\t:" + preferenceData.getDomicile());
            System.out.println("Category\t\t:" + preferenceData.getJobCategories());
            System.out.println("Salary\t\t:" + preferenceData.getSalary());
            System.out.println("Position\t\t:" + preferenceData.getPosition());
        }
        else{
            System.out.println("No preferences yet");
        }
    }
    
    public void updateName(String newName){
        basicData.setName(newName);
        System.out.println("Name updated to: " + newName);
    }
    public void updateNIK(String newNIK){
        basicData.setNIK(newNIK);
        System.out.println("NIK updated to: " + newNIK);
    }
    public void updateEmail(String newEmail){
        basicData.setEmail(newEmail);
        System.out.println("NIK updated to: " + newEmail);
    }
    public void updatePhoneNumber(String newPhoneNumber){
        basicData.setPhoneNumber(newPhoneNumber);
        System.out.println("Phone Number updated to: " + newPhoneNumber);
    }

    public boolean isPreferenceSet(){ // to check whether preference is set or not
        return this.preferenceData != null;
    }
    public void updateDomicile(String newDomicilePreference){
        preferenceData.setDomicile(newDomicilePreference);
        System.out.println("Domicile preference updated to: " + newDomicilePreference);
    }
    public void updateSalary(int newSalaryPreference){
        preferenceData.setSalary(newSalaryPreference);
        System.out.println("Salary preference updated to: " + newSalaryPreference);
    }
    public void updateJobCategory(ArrayList<JobCategory> newJobCategory){
        preferenceData.setCategory(newJobCategory);
        System.out.println("Job category preference updated to: " + newJobCategory);
    }
    public void updatePosition(String newPosition){
        preferenceData.setPosition(newPosition);
        System.out.println("Phone Number updated to: " + newPosition);
    }
 
}   