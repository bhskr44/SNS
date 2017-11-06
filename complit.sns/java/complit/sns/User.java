package complit.sns;

public class User {
    String stuemail;
    String stuname;
    String stupassword;
    int stuphone;
    String sturoll;
    int stusem;

    public User(String stuname, String sturoll, String stuemail, String stupassword, int stusem, int stuphone) {
        this.stuname = stuname;
        this.sturoll = sturoll;
        this.stuemail = stuemail;
        this.stuphone = stuphone;
        this.stusem = stusem;
        this.stupassword = stupassword;
    }

    public User(Object o, Object o1) {
    }
}
