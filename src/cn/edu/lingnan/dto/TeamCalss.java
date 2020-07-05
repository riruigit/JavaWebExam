package cn.edu.lingnan.dto;

public class TeamCalss {
    private String tid;
    private String tname;
    private String tteacher;
    private String titem;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTteacher() {
        return tteacher;
    }

    public void setTteacher(String tteacher) {
        this.tteacher = tteacher;
    }

    public String getTitem() {
        return titem;
    }

    public void setTitem(String titem) {
        this.titem = titem;
    }

    @Override
    public String toString() {
        return
                "tid='" + tid + '\'' +
                        ", \ttname='" + tname + '\'' +
                        ", \ttteacher='" + tteacher + '\'' +
                        ", \ttitem='" + titem + '\'' +
                        ", \tstate=" + state;
    }
}
