package source.data.user;

import java.util.UUID;

public abstract class User<T> {
    protected String userId;
    protected String name;
    protected String email;
    protected String phone;
    protected String address;

    public User(String name, String email, String phone, String address) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public abstract void updateProfile(T param);
    public abstract void search();

    public void setUserId(String userId) {this.userId = userId;}
    public String getUserId() {return userId;}
    public void setName(String name) {this.name = name;}
    public String getName() {return name;}
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return email;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getPhone() {return phone;}
    public void setAddress(String address) {this.address = address;}
    public String getAddress() {return address;}
}