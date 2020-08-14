package com.gec.hrm.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public abstract class AliUtil<T> {
	Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

	private static Properties p = new Properties();
	
	private static DataSource ds;
	
	static{
		try(InputStream in = AliUtil.class.getResourceAsStream("/db.properties")){
			//将文件流加载到p对象
			p.load(in);
			//创建数据库连接池
			ds = DruidDataSourceFactory.createDataSource(p);  //自动填充属性,创建出数据源来
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection(){
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	 //通用删改方法
    public boolean update(String sql, List<Object> obj) {
        try {
            pst = getConnection().prepareStatement(sql);
            if(obj != null) {
                for(int i = 0;i<obj.size();i++) {
                    pst.setObject(i+1, obj.get(i));
                }
            }
            
            System.out.println(pst);
            int row = pst.executeUpdate();
            if(row>0) {
                return true;
            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            close(conn, pst, rs);
        }
        return false;
    }
    //通用插入方法
    public Integer insert(String sql, List<Object> obj){
        try {
            pst = getConnection().prepareStatement(sql);
            if(obj != null) {
                for(int i = 0;i<obj.size();i++) {
                    pst.setObject(i+1, obj.get(i));
                }
            }
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            close(conn, pst, rs);
        }
        return -1;
    }
    
    public int queryCount(String sql , Object...obj) {
    	int row = 0;
    	try {
			pst = getConnection().prepareStatement(sql);
			for(int i = 0 ; i<obj.length;i++) {
				pst.setObject(i+1, obj[i]);
			}
			rs = pst.executeQuery();
			if(rs.next()) {
				row = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn, pst, rs);
		}
		return row;
    	
    }
    
    //通用查询方法
    public List<T> query(String sql , Object...obj){
        List<T> list = new ArrayList<>();
        try {
            pst = getConnection().prepareStatement(sql);
            for(int i = 0 ; i<obj.length ; i++) {
                pst.setObject(i+1, obj[i]);
            }
            rs = pst.executeQuery();
            while(rs.next()) {
                //将重写方法获取的对象，放入集合
                list.add(getEntity(rs));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(conn, pst, rs);
        }

        return null;
    }

    public abstract T getEntity(ResultSet rs) throws Exception;


    public void close(Connection conn , PreparedStatement pst , ResultSet rs) {
        try {
            if(rs != null)
                rs.close();
            if(pst != null)
                pst.close();
            if(conn != null)
                conn.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
