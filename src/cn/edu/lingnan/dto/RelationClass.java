package cn.edu.lingnan.dto;

public class RelationClass {
    private String sid;
    private String tid;
    private int state;
    private String position;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    //重写toString 方法

    @Override
    public String toString() {
        return
                "sid='" + sid + '\'' +
                        ", tid='" + tid + '\'' +
                        ", state=" + state +
                        ", position='" + position + '\''
                ;
    }
}
