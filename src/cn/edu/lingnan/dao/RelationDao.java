package cn.edu.lingnan.dao;


import cn.edu.lingnan.dto.RelationClass;
import cn.edu.lingnan.dto.TeamNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


/**
 * @author 18364
 */
public class RelationDao {

    //通过学号删除

    public boolean deleteRelationBySid(String sid) {
        boolean flag = false;

        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.prepareStatement("delete from relation where sid = ?");
            stat.setString(1, sid);
            flag = stat.execute();
            flag = true;
            //4.处理执行结果
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(stat, conn);
        }
        return flag;
    }


    public boolean applyRelation(String sid) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();

            String sql = "update relation set state = 1 where sid = ? ";

            prep = conn.prepareStatement(sql);
            prep.setString(1, sid);
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
     *这里得返回一个类
     */

    public RelationClass findRelationBySid(String sid) {

        RelationClass relClass = new RelationClass();
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            prep = conn.prepareStatement("select * from relation where sid = ?");
            prep.setString(1, sid);
            rs = prep.executeQuery();
            //4.处理执行结果
            if (rs.next()) {
                relClass.setSid(rs.getString("sid"));
                relClass.setTid(rs.getString("tid"));
                relClass.setPosition(rs.getString("position"));
                relClass.setState(rs.getInt("state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(rs, prep, conn);
        }
        return relClass;
    }


    //修改关系记录(根据学号修改的)

    public boolean updateRelation(RelationClass r) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();

            String sql = "update relation set tid = ? ,Position = ?,"
                    + "state = 1 where sid = ?";
            prep = conn.prepareStatement(sql);

            prep.setString(2, r.getPosition());
            prep.setString(1, r.getTid());
            prep.setString(3, r.getSid());
            prep.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(prep, conn);
        }
        return flag;
    }

    //增加一条关系

    public boolean insertRelation(RelationClass r) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep3 = null;
        PreparedStatement prep2 = null;
        PreparedStatement prep1 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            conn = DataAccess.getConnection();
            prep1 = conn.prepareStatement("select * from student where state = 1 and sid = ?");
            prep1.setString(1, r.getSid());
            rs1 = prep1.executeQuery();
            if (!rs1.next()) {
                System.out.println("学号不存在");
            } else {
                prep2 = conn.prepareStatement("select * from team where state = 1 and tid = ?");
                prep2.setString(1, r.getTid());
                rs2 = prep2.executeQuery();
                if (!rs2.next()) {
                    System.out.println("团队号不存在");
                } else {
                    String sql = "insert into relation values(?,?,2,?) ";
                    prep3 = conn.prepareStatement(sql);
                    prep3.setString(1, r.getSid());
                    prep3.setString(2, r.getTid());
                    prep3.setString(3, r.getPosition());
                    prep3.executeUpdate();
                    flag = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("该关系已经存在");
        } finally {
            DataAccess.closeConnection(prep1, conn);
            DataAccess.closeConnection(prep2, conn);
            DataAccess.closeConnection(prep3, conn);
        }
        return flag;
    }


    //查询总数分页

    public int findAllRelationCount() {
        int count = -1;
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT COUNT(sid) from relation where state =1");
            //4.处理执行结果,要么找得到，要么找不到，找到rs.next()就不为空
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭数据库连接(先开的后关，后开的先关）
            DataAccess.closeConnection(rs, stat, conn);
        }
        return count;
    }


    //查找所有学生记录（分页）

    public Vector<RelationClass> findAllRelationFenYe(int q) {
        Vector<RelationClass> v = new Vector<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.prepareStatement("select * from relation where state = 1 order by sid asc limit ? ,5 ");
            stat.setInt(1, 5 * (q - 1));
            rs = stat.executeQuery();
            //4.处理执行结果
            while (rs.next()) {
                RelationClass RC = new RelationClass();
                RC.setSid(rs.getString("sid"));
                RC.setTid(rs.getString("tid"));
                RC.setState(rs.getInt("state"));
                RC.setPosition(rs.getString("position"));
                v.add(RC);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(rs, stat, conn);
        }
        return v;

    }


    //分页操作的辅助，查找总数

    public int findAllTeaNumber(String sid) {
        int count = -1;
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            prep = conn.prepareStatement("select count(student.sid)" +
                    " from student ,team ,relation" +
                    " where student.sid = relation.sid and team.tid = relation.tid" +
                    " and relation.tid in (select relation.tid from relation where relation.sid = ?)");
            prep.setString(1, sid);
            rs = prep.executeQuery();

            //4.处理执行结果,要么找得到，要么找不到，找到rs.next()就不为空
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭数据库连接(先开的后关，后开的先关）
            DataAccess.closeConnection(rs, prep, conn);
        }
        return count;
    }

    /*
     *功能：分页显示我的团队成员
     *返回：一个集合类，得写进一个session
     */

    public Vector<TeamNumber> findAllTeaNumberFenYe(String sid, int q) {
        Vector<TeamNumber> v = new Vector<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.prepareStatement("select student.sid as sid , student.sname as sname , student.gender as gender, team.tid as tid, team.tname as tname  , relation.position as position  ,relation.state as rstate" +
                    " from student ,team ,relation" +
                    " where student.sid = relation.sid and team.tid = relation.tid" +
                    " and  relation.state= 1 and relation.tid in (select relation.tid from relation where relation.sid = ?) LIMIT ?,3");
            stat.setString(1, sid);
            stat.setInt(2, 3 * (q - 1));
            rs = stat.executeQuery();
            //4.处理执行结果
            while (rs.next()) {
                TeamNumber TN = new TeamNumber();
                TN.setStudentSid(rs.getString("sid"));
                TN.setStudentSname(rs.getString("sname"));
                TN.setStudentGender(rs.getString("gender"));
                TN.setTeamTid(rs.getString("tid"));
                TN.setTeamTtname(rs.getString("tname"));
                TN.setRelationPosition(rs.getString("position"));
                TN.setRelationState(rs.getInt("rstate"));
                v.add(TN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(rs, stat, conn);
        }
        return v;
    }

    public Vector<TeamNumber> findAllCheck() {
        Vector<TeamNumber> v = new Vector<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.prepareStatement("SELECT  student.sid as sid , student.sname as sname , student.gender as gender, team.tid  as tid , team.tname as tname , relation.position as position, relation.state as rstate " +
                    " from student ,team ,relation" +
                    " WHERE relation.state=2 and student.sid = relation.sid and relation.tid = team.tid ");
            rs = stat.executeQuery();
            //4.处理执行结果
            while (rs.next()) {
                TeamNumber allChe = new TeamNumber();
                allChe.setStudentSid(rs.getString("sid"));
                allChe.setStudentSname(rs.getString("sname"));
                allChe.setStudentGender(rs.getString("gender"));
                allChe.setTeamTid(rs.getString("tid"));
                allChe.setTeamTtname(rs.getString("tname"));
                allChe.setRelationPosition(rs.getString("position"));
                allChe.setRelationState(rs.getInt("rstate"));
                v.add(allChe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(rs, stat, conn);
        }
        return v;
    }


}
