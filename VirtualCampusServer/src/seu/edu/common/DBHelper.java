
/**
 * @author song/li
 * @Time 8.31
 */
package seu.edu.common;

import java.io.File;
import java.sql.*;

public final class DBHelper {

	private static String driver = "com.hxtt.sql.access.AccessDriver";
	//private static String dbpath = "///./Database/vCampus.accdb";
	static String dbpath = new File("").getAbsolutePath().replace('\\', '/') + "/Database/vCampus.accdb";
	private static String url = "jdbc:Access:///" + dbpath;
	
    // 获得与数据库的连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver).newInstance();
            if (conn == null) {
                conn = DriverManager.getConnection(url, "", "");
            	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    /**
     *判断是否更新数据成功
     * @param sql
     * @return int
     */
    public static boolean executeUpdate(String sql) {
        int result = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            free(null, stmt, conn);
        } finally {
            free(null, stmt, conn);
        }
        if(result == 0) {
        	return false;
        }
        return true;    
    }
    
    /**
     * 判断多个数据是否更新成功
     *
     * @param sql
     * @param obj
     * @return int
     */
    public static boolean executeUpdate(String sql, Object... obj) {
       int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            result = pstmt.executeUpdate();
        } catch (SQLException err) {
           err.printStackTrace();
            free(null, pstmt, conn);
        } finally {
            free(null, pstmt, conn);
        }
        if(result == 0) {
        	return false;
        }
        return true;
    }
    
    /**
     * @param sql
     * @return ResultSet
     */
    // 进行数据库查询等操作
    public static ResultSet executeQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);
            //free(conn);
            //free(stmt);
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs, stmt, conn);
        }
       return rs;
    }
    
    
   public static ResultSet executeQuery(String sql, Object... obj) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
               pstmt.setObject(i + 1, obj[i]);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException err) {
           err.printStackTrace();
            free(rs, pstmt, conn);
        }
       return rs;
    }
    
    /**
     *
     * @param sql
     * @return Boolean
     */
    
    // 判断sql查询的数据是否存在
    public static Boolean isExist(String sql) {
        ResultSet rs = null;
        try {
            rs = executeQuery(sql);
            rs.last();
            int count = rs.getRow();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs);
            return false;
        } finally {
            free(rs);
        }
    }
    
    /**
     * 判断sql查询的多个数据是否存在
     *
     * @param sql
     * @return Boolean
     */
    public static Boolean isExist(String sql, Object... obj) {
        ResultSet rs = null;
        try {
            rs = executeQuery(sql, obj);
            rs.last();
            int count = rs.getRow();
            if (count > 0){
                return true;
            } else {
                return false;
            }
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs);
            return false;
        } finally {
            free(rs);
        }
    }
    /**
     * 获得查询数据的个数
     *
     * @param sql
     * @return int
     */
    public static int getCount(String sql) {
        int result = 0;
        ResultSet rs = null;
        try {
            rs = executeQuery(sql);
            rs.last();
            result = (int)rs.getObject("ID");
        } catch (SQLException err) {
            free(rs);
            err.printStackTrace();
        } finally {
            free(rs);
        }
        return result;
    }
    
    /**
     *获得查询数据的个数
     *
     * @param sql
     * @param obj
     * @return int
     */
    public static int getCount(String sql, Object obj) {
        int result = 0;
        ResultSet rs = null;
        try {
            rs = executeQuery(sql, obj);
            rs.last();
            result = rs.getRow();
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            free(rs);
        }
        return result;
    }
    // 获得最后一行的ID
    public static int getLastRow(String t) {
    	int result = 0;
    	String sql = "select * from " + t;
        ResultSet rs = null;
        try {
            rs = new DBHelper().executeQuery(sql);
            rs.last();
            result = (int)rs.getObject("ID") + 1;
        } catch (SQLException err) {
            new DBHelper().free(rs);
            err.printStackTrace();
        } finally {
            new DBHelper().free(rs);
        }
        return result;
    }
    
    public static int getLastRow1(String t) {
    	int result = 0;
    	String sql = "select * from " + t;
        ResultSet rs = null;
        try {
            rs = new DBHelper().executeQuery(sql);
            rs.last();
            result = rs.getRow() + 1;
        } catch (SQLException err) {
            new DBHelper().free(rs);
            err.printStackTrace();
        } finally {
            new DBHelper().free(rs);
        }
        return result;
    }
    
	public static String print(ResultSet rs) {
		String result = "查询信息如下：\n";
		try {;
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int iNumCols = resultSetMetaData.getColumnCount();
			while (rs.next()) {
				for(int i = 1; i <= iNumCols; i++) {
					result += resultSetMetaData.getColumnLabel(i) + ": " + rs.getObject(i) + "  ";
				}
				result += "\n";
			}
			System.out.println(result);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			free(rs);
		}
		return result;
	}
    /**
     *释放Result
     * @param rs
     */
    public static void free(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    /**
     *释放Statement
     * @param st
     */
    public static void free(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    /**
     * 释放Connnection
     * @param conn
     */
    public static void free(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    /**
     * 全部释放
     * @param rs
     * @param st
     * @param conn
     */
    public static void free(ResultSet rs, Statement st, Connection conn) {
        free(rs);
        free(st);
        free(conn);
    }
}
