package source.data.user;

public abstract class User<T> {
    protected String userId;
    protected String name;
    protected String email;
    protected String phone;
    protected String address;

    public abstract void updateProfile(T param);
    public abstract void search();
}