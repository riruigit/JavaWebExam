package cn.edu.lingnan.dto;

/**
 * @author 18364
 */
public class TeamNumber {
    private String studentSid;
    private String studentSname;
    private String studentGender;
    private String teamTid;
    private String teamTtname;
    private int relationState;
    private String relationPosition;

    public String getTeamTid() {
        return teamTid;
    }

    public void setTeamTid(String teamTid) {
        this.teamTid = teamTid;
    }

    public String getRelationPosition() {
        return relationPosition;
    }

    public void setRelationPosition(String relationPosition) {
        this.relationPosition = relationPosition;
    }

    public String getTeamTtname() {
        return teamTtname;
    }

    public void setTeamTtname(String teamTtname) {
        this.teamTtname = teamTtname;
    }

    public String getStudentSid() {
        return studentSid;
    }

    public void setStudentSid(String studentSid) {
        this.studentSid = studentSid;
    }

    public String getStudentSname() {
        return studentSname;
    }

    public void setStudentSname(String studentSname) {
        this.studentSname = studentSname;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }


    public int getRelationState() {
        return relationState;
    }

    public void setRelationState(int relationState) {
        this.relationState = relationState;
    }
}
