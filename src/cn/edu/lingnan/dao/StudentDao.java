package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.RelationClass;
import cn.edu.lingnan.dto.StudentCalss;

import java.sql.*;
import java.util.Vector;


/**
 * @author 18364
 */
public class StudentDao {


    /*
     *注册的时候使用
     *return 一个学生类，准备放到session里面
     */

    public StudentCalss findStudentByNamePassword(String sname, String password) {
        StudentCalss studentDto = new StudentCalss();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from student where state = 1 and sname = '" + sname + "'"
                    + " and password = '" + password + "'");
            //4.处理执行结果,要么找得到，要么找不到，找到rs.next()就不为空
            if (rs.next()) {
                studentDto.setSid(rs.getString("sid"));
                studentDto.setSname(rs.getString("sname"));
                studentDto.setAge(rs.getInt("age"));
                studentDto.setDepart(rs.getString("depart"));
                studentDto.setGender(rs.getString("gender"));
                studentDto.setSuperuser(rs.getInt("superuser"));
                studentDto.setPassword(rs.getString("password"));
                studentDto.setState(rs.getInt("state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭数据库连接(先开的后关，后开的先关）
            DataAccess.closeConnection(rs, stat, conn);
        }
        return studentDto;
    }

    /*
     * 在注册的时候使用，判断是不是存在这个学生
     * 类型：布尔型
     */

    public boolean findStuBySid(String sid) {
        boolean flag = false;
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from student where state = 1 and sid = '" + sid + "'");
            //4.处理执行结果,要么找得到，要么找不到，找到rs.next()就不为空
            if (rs.next()) {
                System.out.print("学号：" + rs.getString("sid") + "  ");
                System.out.println("姓名：" + rs.getString("sname") + "  ");
                flag = true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //5.关闭数据库连接(先开的后关，后开的先关）
            DataAccess.closeConnection(rs, stat, conn);
        }
        return flag;
    }

    /*
     *通过学号删除一个学生，不过做的是软删除
     *参数：一个学号
     *类型：布尔
     *步骤：先去团队表，把这个学生的选队关系删除了，然后再做学生表的软删除
     */

    public boolean deleteStuBySid(String sid) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            conn = DataAccess.getConnection();
            prep = conn.prepareStatement("DELETE FROM relation WHERE sid=?");
            prep.setString(1, sid);
            flag = prep.execute();
            prep1 = conn.prepareStatement("update student set state=0 where sid=? ");
            prep1.setString(1, sid);
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



    /*
     *修改学生信息，在修改个人信息的时候用到
     *参数：一个类
     *返回：布尔型
     */

    public boolean updateStu(StudentCalss s) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            String sql = "update student set sname=?,age=?,depart=?,gender=?,superuser=?,password=?,state=1 where sid=?";
            prep = conn.prepareStatement(sql);
            prep.setString(7, s.getSid());
            prep.setString(1, s.getSname());
            prep.setInt(2, s.getAge());
            prep.setString(3, s.getDepart());
            prep.setString(4, s.getGender());
            prep.setInt(5, s.getSuperuser());
            prep.setString(6, s.getPassword());
            prep.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭数据库连接(先开的后关，后开的先关）
            DataAccess.closeConnection(prep, conn);
        }
        return flag;
    }

    /*
     *插入一个学生
     * 参数：一个学生类
     * 注册的时候用到
     */

    public boolean insertStu(StudentCalss s) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep = null;
        PreparedStatement prep1 = null;
        ResultSet rs1 = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            prep1 = conn.prepareStatement("select * from student where state = 1 and sid = ?");
            prep1.setString(1, s.getSid());
            rs1 = prep1.executeQuery();
            if (rs1.next()) {
                System.out.println("学号已经存在");
            } else {
                String sql = "insert into student values(?,?,?,?,?,?,?,1)";
                prep = conn.prepareStatement(sql);
                prep.setString(1, s.getSid());
                prep.setString(2, s.getSname());
                prep.setInt(3, s.getAge());
                prep.setString(4, s.getDepart());
                prep.setString(5, s.getGender());
                prep.setInt(6, s.getSuperuser());
                prep.setString(7, s.getPassword());
                prep.executeUpdate();
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println("该学生记录已经存在");
        } finally {
            //5.关闭数据库连接(先开的后关，后开的先关）
            DataAccess.closeConnection(prep, conn);
            DataAccess.closeConnection(prep1, conn);
        }
        return flag;
    }

    //查询总数分页

    public int findAllStuNumber() {
        int count = -1;
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT COUNT(sid) from student where state =1");
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

    public Vector<StudentCalss> findAllStuFenYe(int q) {
        Vector<StudentCalss> v = new Vector<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.创建并执行SQL语句
            stat = conn.prepareStatement("select * from student where state = 1 limit ? ,5 ");
            stat.setInt(1, 5 * (q - 1));
            rs = stat.executeQuery();
            //4.处理执行结果
            while (rs.next()) {
                StudentCalss s = new StudentCalss();
                s.setSid(rs.getString("sid"));
                s.setSname(rs.getString("sname"));
                s.setAge(rs.getInt("age"));
                s.setDepart(rs.getString("depart"));
                s.setGender(rs.getString("gender"));
                s.setSuperuser(rs.getInt("superuser"));
                s.setPassword(rs.getString("password"));
                s.setState(rs.getInt("state"));
                v.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(rs, stat, conn);
        }
        return v;
    }
}