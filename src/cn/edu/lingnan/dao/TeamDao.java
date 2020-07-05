package cn.edu.lingnan.dao;


import cn.edu.lingnan.dto.TeamCalss;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * @author 18364
 */
public class TeamDao {


    //通过团队号查找团队,看看这个团队号是不是存在的

    public boolean findTeaByTid(String tid) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            stat = conn.prepareStatement("select * from team where state = 1 and tid = ?");
            stat.setString(1,tid);
            rs = stat.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(rs, stat, conn);
        }
        return flag;
    }

    //查找所有团队

    public Vector<TeamCalss> findAllTeam() {
        Vector<TeamCalss> v = new Vector<TeamCalss>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from team where state = 1");
            //4.处理执行结果
            while (rs.next()) {
                TeamCalss t = new TeamCalss();
                t.setTid(rs.getString("tid"));
                t.setTname(rs.getString("tname"));
                t.setTteacher(rs.getString("tteacher"));
                t.setTitem(rs.getString("titem"));
                t.setState(rs.getInt("state"));
                v.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(rs, stat, conn);
        }
        return v;

    }


    //删除团队号

    public boolean deleteTeaByTid(String tid) {

        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            conn = DataAccess.getConnection();
            prep = conn.prepareStatement("DELETE FROM relation WHERE tid=?");
            prep.setString(1, tid);
            flag = prep.execute();
            prep1 = conn.prepareStatement("DELETE FROM team  where tid=? ");
            prep1.setString(1, tid);
            flag = prep1.execute();
            flag = true;
        } catch (SQLException e) {
            System.out.println("删除一个学生的操作");
        } finally {
            DataAccess.closeConnection(prep, conn);
            DataAccess.closeConnection(prep1, conn);
        }
        return flag;
    }


    //更新一个团队信息


    public boolean updateTeam(TeamCalss t) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();
            String sql = "update team set tname = ? ,tteacher = ? ,titem =? ,state = 1 where tid = ?";
            prep = conn.prepareStatement(sql);
            prep.setString(1, t.getTname());
            prep.setString(2, t.getTteacher());
            prep.setString(3, t.getTitem());
            prep.setString(4, t.getTid());
            prep.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(prep, conn);
        }
        return flag;
    }

    //插入一个团队记录

    public boolean insertTeam(TeamCalss t) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();
            String sql = "insert into team values(?,?,?,?,1)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, t.getTid());
            prep.setString(2, t.getTname());
            prep.setString(3, t.getTteacher());
            prep.setString(4, t.getTitem());
            prep.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(prep, conn);
        }
        return flag;
    }

    /*
     *功能：我的团队页面需要这个。显示我的团队信息，需要写进一个session
     */

    public TeamCalss findMyTeamBySid(String sid) {
        TeamCalss teamdto = new TeamCalss();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.prepareStatement("select team.* from relation,team where team.tid=relation.tid and sid =? ");
            stat.setString(1, sid);
            rs = stat.executeQuery();
            //4.处理执行结果,要么找得到，要么找不到，找到rs.next()就不为空
            //SELECT team.* FROM relation , team
            //WHERE team.tid=relation.tid and  sid = '2018764219' and relation.state = 1
            if (rs.next()) {
                teamdto.setTid(rs.getString("tid"));
                teamdto.setTname(rs.getString("tname"));
                teamdto.setTteacher(rs.getString("tteacher"));
                teamdto.setTitem(rs.getString("titem"));
                teamdto.setState(rs.getInt("state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭数据库连接(先开的后关，后开的先关）
            DataAccess.closeConnection(rs, stat, conn);
        }
        return teamdto;
    }


}
