package cn.edu.lingnan.dto;

public class StudentCalss {
    private String sid;
    private String sname;
    private int age;
    private String depart;
    private String gender;
    private int superuser;
    private String password;
    private int state;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public int getSuperuser() {
        return superuser;
    }

    public void setSuperuser(int superuser) {
        this.superuser = superuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return
                "\t sid='" + sid + '\'' +
                        "\t sname='" + sname + '\'' +
                        "\t age=" + age +
                        "\t depart='" + depart + '\'' +
                        "\t gender='" + gender + '\'' +
                        "\t superuser=" + superuser +
                        "\t password='" + password + '\'' +
                        "\t state=" + state
                ;
    }


}
