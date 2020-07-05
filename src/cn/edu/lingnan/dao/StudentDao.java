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
     *ע���ʱ��ʹ��
     *return һ��ѧ���࣬׼���ŵ�session����
     */

    public StudentCalss findStudentByNamePassword(String sname, String password) {
        StudentCalss studentDto = new StudentCalss();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.������ִ��SQL���
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from student where state = 1 and sname = '" + sname + "'"
                    + " and password = '" + password + "'");
            //4.����ִ�н��,Ҫô�ҵõ���Ҫô�Ҳ������ҵ�rs.next()�Ͳ�Ϊ��
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
            //5.�ر����ݿ�����(�ȿ��ĺ�أ��󿪵��ȹأ�
            DataAccess.closeConnection(rs, stat, conn);
        }
        return studentDto;
    }

    /*
     * ��ע���ʱ��ʹ�ã��ж��ǲ��Ǵ������ѧ��
     * ���ͣ�������
     */

    public boolean findStuBySid(String sid) {
        boolean flag = false;
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.������ִ��SQL���
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from student where state = 1 and sid = '" + sid + "'");
            //4.����ִ�н��,Ҫô�ҵõ���Ҫô�Ҳ������ҵ�rs.next()�Ͳ�Ϊ��
            if (rs.next()) {
                System.out.print("ѧ�ţ�" + rs.getString("sid") + "  ");
                System.out.println("������" + rs.getString("sname") + "  ");
                flag = true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //5.�ر����ݿ�����(�ȿ��ĺ�أ��󿪵��ȹأ�
            DataAccess.closeConnection(rs, stat, conn);
        }
        return flag;
    }

    /*
     *ͨ��ѧ��ɾ��һ��ѧ����������������ɾ��
     *������һ��ѧ��
     *���ͣ�����
     *���裺��ȥ�Ŷӱ������ѧ����ѡ�ӹ�ϵɾ���ˣ�Ȼ������ѧ�������ɾ��
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
            System.out.println("ɾ��һ��ѧ���Ĳ���");
        } finally {
            DataAccess.closeConnection(prep, conn);
            DataAccess.closeConnection(prep1, conn);
        }
        return flag;
    }



    /*
     *�޸�ѧ����Ϣ�����޸ĸ�����Ϣ��ʱ���õ�
     *������һ����
     *���أ�������
     */

    public boolean updateStu(StudentCalss s) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();
            //3.������ִ��SQL���
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
            //5.�ر����ݿ�����(�ȿ��ĺ�أ��󿪵��ȹأ�
            DataAccess.closeConnection(prep, conn);
        }
        return flag;
    }

    /*
     *����һ��ѧ��
     * ������һ��ѧ����
     * ע���ʱ���õ�
     */

    public boolean insertStu(StudentCalss s) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep = null;
        PreparedStatement prep1 = null;
        ResultSet rs1 = null;
        try {
            conn = DataAccess.getConnection();
            //3.������ִ��SQL���
            prep1 = conn.prepareStatement("select * from student where state = 1 and sid = ?");
            prep1.setString(1, s.getSid());
            rs1 = prep1.executeQuery();
            if (rs1.next()) {
                System.out.println("ѧ���Ѿ�����");
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
            System.out.println("��ѧ����¼�Ѿ�����");
        } finally {
            //5.�ر����ݿ�����(�ȿ��ĺ�أ��󿪵��ȹأ�
            DataAccess.closeConnection(prep, conn);
            DataAccess.closeConnection(prep1, conn);
        }
        return flag;
    }

    //��ѯ������ҳ

    public int findAllStuNumber() {
        int count = -1;
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.������ִ��SQL���
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT COUNT(sid) from student where state =1");
            //4.����ִ�н��,Ҫô�ҵõ���Ҫô�Ҳ������ҵ�rs.next()�Ͳ�Ϊ��
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.�ر����ݿ�����(�ȿ��ĺ�أ��󿪵��ȹأ�
            DataAccess.closeConnection(rs, stat, conn);
        }
        return count;
    }


    //��������ѧ����¼����ҳ��

    public Vector<StudentCalss> findAllStuFenYe(int q) {
        Vector<StudentCalss> v = new Vector<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            //3.������ִ��SQL���
            stat = conn.prepareStatement("select * from student where state = 1 limit ? ,5 ");
            stat.setInt(1, 5 * (q - 1));
            rs = stat.executeQuery();
            //4.����ִ�н��
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