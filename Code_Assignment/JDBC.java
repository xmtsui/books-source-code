import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	//oracle  oracle.jdbc.driver.OracleDriver
	//mysql    com.mysql.jdbc.Driver
	private static final String DRIVER = "com.mysql.jdbc.Driverr";
	private static final String URL = "jdbc:mysql://121.9.227.79:3366;databaseName=stockdb";
//	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private static final String URL = "jdbc:sqlserver://192.168.1.111:1433;databaseName=DBReport";
	private static final String USERNAME = "szteam";
	private static final String PASSWORD = "fs&*()2011";

	public Connection conn;
	public ResultSet rs;

	//创建连结
	public Connection openConn() throws SQLException{

		try {
			Class.forName(DRIVER);
			this.conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;

	}
	
	//获取到指定数据库的连结
	public static Connection openDB(String url, String user, String passwd){
		try {
			return (DriverManager.getConnection(url,user,passwd));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//创建查询SQL语句对象
	public static Statement getQueryStat(Connection con){
		try {
			return(con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//创建执行用SQL语句对象
	public static Statement getExStat(Connection con){
		try {
			return(con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//查询SQL语句，获取结果集
	public static ResultSet openQuery(Statement stat,String sql){
		try {
			return(stat.executeQuery(sql));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//关闭连结
	public void closeAll(ResultSet rs,Connection conn){
		if(this.rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(this.conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

//	//修改
//	public synchronized boolean update(String sql, Object... objects) throws SQLException, ClassNotFoundException {
//				 conn =this.openConn();
//		PreparedStatement pstat = null;
//
//		try {
//			pstat = conn.prepareStatement(sql);
//			bind(pstat, objects);
//			pstat.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			this.closeAll(rs, pstat, conn);
//		}
//		return false;
//	}
//
//	//查找
//	public ResultSet query(String sql, Object... objects) throws SQLException {
//		conn =this.openConn();
//		try {
//			PreparedStatement pstat = conn.prepareStatement(sql);
//			bind(pstat, objects);
//			return pstat.executeQuery();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	
//	public void bind(PreparedStatement pstat, Object... objects) {
//		int i = 1;
//		for (Object object : objects) {
//			try {
//				if (object == null) {
//					pstat.setNull(i++, Types.VARCHAR);
//				} else if (object instanceof Date) {
//					Date date = (Date) object;
//					java.sql.Date d = new java.sql.Date(date.getTime());
//					pstat.setDate(i++, d);
//				} else {
//					pstat.setObject(i++, object);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
