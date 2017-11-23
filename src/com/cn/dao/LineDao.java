package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cn.pojo.Line;
import com.cn.utils.DbUtils;
import com.cn.utils.TypeEnum;

public class LineDao {
	
	
	//添加线路
	public  int  addLine(Line line){
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			 String sql=" insert into Line(lineName,lineTypeID,price,vehicle,introduction,lineID,reason,arrange,days,ontime) " +
			 		    " values(?,?,?,?,?,?,?,?,?,?)";
			 stat=conn.prepareStatement(sql);
			 stat.setString(1, line.getLineName());
			 stat.setString(2, line.getLineTypeID());
			 stat.setDouble(3,line.getPrice());
			 stat.setString(4,line.getVehicle());
			 stat.setString(5,line.getIntroduction());
			 stat.setString(7, line.getReason());
    		 stat.setString(8, line.getArrange());
			 stat.setString(6, line.getLineID());
			 stat.setInt(9, line.getDays());
			 stat.setString(10, line.getOnTime());
			 return stat.executeUpdate();
	    }catch (SQLException e) {
			 e.printStackTrace();
		}finally{
			try {
				DbUtils.close(null, stat, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
   }
	
	//修改线路信息
	public  int  updateLine(Line line){
		
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			 String sql=" update Line set lineName=?,days=?,price=?,vehicle=?," +
			 		    " reason=?,arrange=?,introduction=?,lineTypeID=? where lineID=?";
			 stat=conn.prepareStatement(sql);
			 stat.setString(1, line.getLineName());
			 stat.setInt(2, line.getDays());
			 stat.setDouble(3,line.getPrice());
			 stat.setString(4,line.getVehicle());
			 stat.setString(5, line.getReason());
    		 stat.setString(6, line.getArrange());
			 stat.setString(8, line.getLineTypeID());
			 stat.setString(7,line.getIntroduction());
			 stat.setString(9, line.getLineID());
			 return stat.executeUpdate();
	    }catch (SQLException e) {
			 e.printStackTrace();
		}finally{
			try {
				DbUtils.close(null, stat, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
		
		
	}
	//修改线路信息(团购)
		public  int  updateTeamLine(Line line){
			
			 Connection conn=null;
			 PreparedStatement stat=null;
			 try {
				 conn=DbUtils.getConnection();
				 String sql=" update Line set lineName=?,days=?,price=?,vehicle=?," +
				 		    " reason=?,arrange=?,introduction=?,lineTypeID=?,teamBuy=?," +
				 		    " beginTime=?, endTime=?,teamBuyPrice=? where lineID=?";
				 stat=conn.prepareStatement(sql);
				 stat.setString(1, line.getLineName());
				 stat.setInt(2, line.getDays());
				 stat.setDouble(3,line.getPrice());
				 stat.setString(4,line.getVehicle());
				 stat.setString(5, line.getReason());
	    		 stat.setString(6, line.getArrange());
				 stat.setString(7,line.getIntroduction());
				 stat.setString(8, line.getLineTypeID());
				 stat.setInt(9, line.getTeamBuy());
				 stat.setString(10, line.getBeginTime());
				 stat.setString(11, line.getEndTime());
				 stat.setDouble(12, line.getTeamBuyPrice());
				 stat.setString(13, line.getLineID());
				 return stat.executeUpdate();
		    }catch (SQLException e) {
				 e.printStackTrace();
			}finally{
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return 0;
			
			
		}
	 //查询所有线路
	public List<Line>  queryAllLine(int beginNum,int maxResult){
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		Line  line = null;
		List<Line> lineList=new ArrayList<Line>();
		try {
			conn = DbUtils.getConnection();
			String sql=" select top "+maxResult+" typeName,Line.* " +
					   " from  Line,LineType where " +
					   " line.lineTypeID=LineType.lineTypeID " +
					   " and Line.lineID not in ( select top "+beginNum+"  Line.lineID " +
					   " from  Line,LineType where line.lineTypeID=LineType.lineTypeID" +
					   " order by ontime desc)order by ontime desc";
			stat=conn.prepareStatement(sql);
			rs = stat.executeQuery();
			System.out.println(sql);
			while (rs.next()) {
				line=new Line();
				line.setDays(rs.getInt("days"));
				line.setLineID(rs.getString("lineID"));
				line.setIntroduction(rs.getString("introduction"));
				line.setLineName(rs.getString("lineName"));
				line.setLineTypeID(rs.getString("lineTypeID"));
				line.setPrice(rs.getDouble("price"));
				line.setTeamBuy(rs.getInt("teamBuy"));
				line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
				line.setVehicle(rs.getString("vehicle"));
				line.setTypeName(rs.getString("typeName"));
				lineList.add(line);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(null, stat, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lineList;
		
	}
	//查询所有路线总数
	public   int queryAllLineCount(){
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int count=0;
		try {
			conn = DbUtils.getConnection();
			String sql= "select count(*) as totals from Line,LineType where Line.lineTypeID=lineType.lineTypeID" ;
	        stat=conn.prepareStatement(sql);
			rs = stat.executeQuery();
			if (rs.next()) {
				count=rs.getInt("totals");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(null, stat, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
		
	}
	//根据线路类型查询线路
	public   List<Line>   findLine(String  lineTypeID,int maxResult){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		Line  line = null;
		List<Line> lineList=new ArrayList<Line>();
		try {
			conn = DbUtils.getConnection();
			String sql= " select top "+maxResult+" *  from Line" +
					    " where lineTypeID=? " +
					    " order by onTime desc ";
	        stat=conn.prepareStatement(sql); 
		    stat.setString(1, lineTypeID);
			rs = stat.executeQuery();
			while (rs.next()) {
				line=new Line();
				line.setDays(rs.getInt("days"));
				line.setLineID(rs.getString("lineID"));
				line.setIntroduction(rs.getString("introduction"));
				line.setLineName(rs.getString("lineName"));
				line.setLineTypeID(rs.getString("lineTypeID"));
				line.setPrice(rs.getDouble("price"));
				line.setTeamBuy(rs.getInt("teamBuy"));
				line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
				line.setVehicle(rs.getString("vehicle"));
				lineList.add(line);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(null, stat, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lineList;
		  
	}
//	//查询团购
//	public   List<Line>   findLine(int  teamBuy,int maxResult){
//		Connection conn = null;
//		PreparedStatement stat = null;
//		ResultSet rs = null;
//		Line  line = null;
//		List<Line> lineList=new ArrayList<Line>();
//		try {
//			conn = DbUtils.getConnection();
//			String sql= "select top "+maxResult+" * from  Line ";
//			if(!(teamBuy<0)){
//				 sql+= " where  teamBuy=? ";
//				
//			}
//		
//	       
//	        
//			stat=conn.prepareStatement(sql);
//			if(!(teamBuy<0)){
//				stat.setInt(1, teamBuy);
//				
//			}
//		
//	       
//		   
//			rs = stat.executeQuery();
//			while (rs.next()) {
//				line=new Line();
//				line.setDays(rs.getInt("days"));
//				line.setLineID(rs.getString("lineID"));
//				line.setIntroduction(rs.getString("introduction"));
//				line.setLineName(rs.getString("lineName"));
//				line.setLineTypeID(rs.getString("lineTypeID"));
//				line.setPrice(rs.getDouble("price"));
//				line.setTeamBuy(rs.getInt("teamBuy"));
//				line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
//				line.setVehicle(rs.getString("vehicle"));
//				lineList.add(line);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				DbUtils.close(null, stat, conn);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return lineList;
//		  
//	}
	
	//按照天数查询
		public   List<Line>   findLineByDays(int  days,int maxResult){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			List<Line> lineList=new ArrayList<Line>();
			try {
				conn = DbUtils.getConnection();
				String sql= "select top "+maxResult+" * from  Line ";
				if(!(days<0) && days<3){
					 sql+= " where  days=? ";
					
				}else if(days>=3){
					
					 sql+= " where  days>=? ";
				}
			    sql+=" order by onTime desc";
			
				stat=conn.prepareStatement(sql);
				if(!(days<0) || days>=3){
					stat.setInt(1,days);
					
				}
				rs = stat.executeQuery();
				while (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
					lineList.add(line);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return lineList;
			  
		}
		
		//查询旅游线路价格最低线路信息
		public   List<Line>   findLine(int maxResult){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			List<Line> lineList=new ArrayList<Line>();
			try {
				conn = DbUtils.getConnection();
				String sql= " select top "+maxResult+" dbo.Line.*" +
						    " from dbo.Line,(select  min(dbo.Line.price) as low_price, " +
						    " dbo.LineType.lineTypeID from Line,LineType" +
						    " where dbo.Line.lineTypeID=dbo.LineType.lineTypeID and dbo.LineType.state=1 " +
						    " group by  dbo.LineType.lineTypeID) as temp " +
						    " where   dbo.Line.lineTypeID=temp.lineTypeID and dbo.Line.price= temp.low_price";
		        stat=conn.prepareStatement(sql);
				rs = stat.executeQuery();
				while (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
					line.setVehicle(rs.getString("discount"));
					lineList.add(line);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return lineList;
			  
		}
		//根据线路ID查询线路
		public  Line findLineById(String lineId){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			try {
				conn = DbUtils.getConnection();
				String sql= "select  * from  Line  where lineId=?";
				stat=conn.prepareStatement(sql);
		        stat.setString(1, lineId);
				rs = stat.executeQuery();
				if (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
		            line.setReason(rs.getString("reason"));
		            line.setDiscount(rs.getInt("discount"));
		            line.setArrange(rs.getString("arrange"));
		            line.setBeginTime(rs.getString("beginTime"));
		            line.setEndTime(rs.getString("endTime"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return line;
		}
		
		/**
		 * 查询某种旅游类型中的热门路线
		 * @param lineTypeId 
		 * @param hot 1 热门路线
		 * @return
		 */
		public List<Line>  findLineByLinetypeAndHot(int lineTypeId,int hot){
			
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			List<Line> lineList=new ArrayList<Line>();
			try {
				conn = DbUtils.getConnection();
				String sql= "select top 5 * from  Line  where LineTypeID=? and hot=?";
		        stat=conn.prepareStatement(sql);
				stat.setInt(1, lineTypeId);
				stat.setInt(2, hot);
				rs = stat.executeQuery();
				while (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
					lineList.add(line);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return lineList;
			
		}
		/**
		 * 查询某种旅游类型中的团购路线
		 * @param lineTypeId 
		 * @param teamBuy 1 团购
		 * @return
		 */
		public List<Line>  findLine(String lineTypeId,int teamBuy,int beginNum,int maxResult){
			
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			List<Line> lineList=new ArrayList<Line>();
			try {
				conn = DbUtils.getConnection();
				String sql= "select top "+maxResult+" * from Line  where lineTypeID=?" +
						    " and  teamBuy=? and  ((select getDate()) between beginTime and  endTime)" +
						    " and lineID not in( select  top "+beginNum+" lineID lineID from Line  where lineTypeID=?" +
						    " and  teamBuy=? and  ((select getDate()) between beginTime and  endTime) " +
						    " order by beginTime desc) order by beginTime desc";
		        stat=conn.prepareStatement(sql);
		        stat.setString(1, lineTypeId);
		        stat.setInt(2, teamBuy);
		        stat.setString(3, lineTypeId);
				stat.setInt(4, teamBuy);
				
				
				rs = stat.executeQuery();
				while (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
					lineList.add(line);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return lineList;
			
		}
		
		//查询某一线路类型线路的所有线路总数
		
		public int     findTypeLineCount(String lineTypeID){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			int count=0;
			try {
				conn = DbUtils.getConnection();
				String sql= "select count (*) as totals from Line  where lineTypeID=?";
				stat=conn.prepareStatement(sql);
				stat.setString(1, lineTypeID);
				rs = stat.executeQuery();
				if (rs.next()) {
					count=rs.getInt("totals");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return count;
			
		}
	    //查询某一线路类型的在当前时间范围内的团购线路的总数
		public int     findTeamTypeLineCount(String lineTypeID,int  teamBuy){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			int count=0;
			try {
				conn = DbUtils.getConnection();
				String sql= "select count (*) as totals from Line  where lineTypeID=?" +
					    " and  teamBuy=? and  ((select getDate()) between beginTime and  endTime)";
				stat=conn.prepareStatement(sql);
				stat.setString(1, lineTypeID);
				stat.setInt(2, teamBuy);
				rs = stat.executeQuery();
				if (rs.next()) {
					count=rs.getInt("totals");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return count;
			
		}
		//通过线路类型ID查询线路
		public   List<Line>   findLineByLineTypeID(String  lineTypeId,int beginNum,int maxResult){
			
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			List<Line> lineList=new ArrayList<Line>();
			try {
				conn = DbUtils.getConnection();
				String sql= " select  top  "+maxResult+" * from Line " +
						    " where lineTypeID=? and onTime not in( " +
						    " select  top "+beginNum+" onTime from  Line  where  lineTypeID=?" +
						    " order by onTime desc) order by onTime desc";
		        stat=conn.prepareStatement(sql);
		        stat.setString(1, lineTypeId);
		        stat.setString(2, lineTypeId);
				rs = stat.executeQuery();
				while (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
					lineList.add(line);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return lineList;

		}
		//查询某一个旅游线路类型的前maxResult条热门路线
		public List<Line>  findHotLnie(String  lineTypeID,int maxResult){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			List<Line> lineList=new ArrayList<Line>();
			try {
				conn = DbUtils.getConnection();
				String sql=" select * from  Line where  Line.lineID " +
						   " in( select lin2.lineID from ( select  top "+maxResult+" count(lineID) as total,lineID" +
						   " from dbo.OrderDetail where lineID in ( select lineID from Line where LineTypeId=" +
						   " ( select  lineTypeId from LineType where lineTypeID=? ) ) group by  lineID " +
						   " order by total  desc) as lin2) ";
			    stat=conn.prepareStatement(sql);
			    stat.setString(1, lineTypeID);
			    rs = stat.executeQuery();
				while (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
					lineList.add(line);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return lineList;
			  
			
		}
		//查询所有线路中最热门的maxResult条线路
		public  List<Line>  findAllHotLine(int  maxResult){
			
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			List<Line> lineList=new ArrayList<Line>();
			try {
				conn = DbUtils.getConnection();
				String sql=" select   * from Line where  Line.lineID in" +
						   " ( select lin2.lineID from(" +
						   " select top "+maxResult+" count(lineID) as total,lineID " +
						   " from dbo.OrderDetail group by  lineID " +
						   " order by total  desc ) as lin2	)";
			    stat=conn.prepareStatement(sql);
			    rs = stat.executeQuery();
				while (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
					lineList.add(line);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return lineList;

		}

		//查询没有闭团路按照订单的数量查询的的路线
		public List<Line> queryTeamBuyLineList(int beginNum,int maxResult,int teamBuy){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			List<Line> lineList=new ArrayList<Line>();
			try {
				conn = DbUtils.getConnection();
				String sql=" select * from Line where lineID in( select lineID " +
						   " from (select top "+maxResult+" count(od.lineID) as total,od.lineID" +
						   " from OrderDetail as od where lineID in ( select l2.lineID " +
						   " from ( select getDate()as new, lineID as id from Line) as  lin,Line as l2" +
						   " where  lin.new between l2.beginTime  and l2.endTime   and  lin.id=l2.lineID and teamBuy=?) " +
						   " and  od.lineID not in(" +
						   " select lineID from ( select top "+beginNum+" count(od.lineID) as total,od.lineID " +
						   " from OrderDetail as od where lineID in( select l2.lineID from ( " +
						   " select getDate()as new, lineID as id from Line) as lin,Line as l2 " +
						   " where  lin.new between l2.beginTime  and l2.endTime   and  lin.id=l2.lineID and teamBuy=?) group by od.lineID" +
						   " order by  total desc) as temp) group by od.lineID order by  total desc) as lin3)";
			    stat=conn.prepareStatement(sql);
			    stat.setInt(1, teamBuy);
			    stat.setInt(2, teamBuy);
			    rs = stat.executeQuery();
				while (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
					lineList.add(line);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return lineList;

		}
		
		
		//查询没有闭团的团购线路按照降序开团时间的降序排序
		public List<Line> queryTeam(int beginNum,int maxResult,int teamBuy){
					Connection conn = null;
					PreparedStatement stat = null;
					ResultSet rs = null;
					Line  line = null;
					List<Line> lineList=new ArrayList<Line>();
					try {
						conn = DbUtils.getConnection();
						String sql=" select  top "+maxResult+" * from ( select getDate()as new, " +
								   " lineID as id from Line) as lin,Line as l2 where  " +
								   " lin.new between l2.beginTime  and l2.endTime   and  lin.id=l2.lineID and teamBuy=?" +
								   " and  id not in( select top "+beginNum+"  lid from ( select getDate()as new, " +
								   " lineID as lid from Line) as lin,Line as l2 where  " +
								   " lin.new between l2.beginTime  and l2.endTime   and " +
								   "  lin.lid=l2.lineID and teamBuy=? order by beginTime desc )order by beginTime desc";
					    stat=conn.prepareStatement(sql);
					    stat.setInt(1, teamBuy);
					    stat.setInt(2, teamBuy);
					    rs = stat.executeQuery();
					    System.out.println(sql);
						while (rs.next()) {
							line=new Line();
							line.setDays(rs.getInt("days"));
							line.setLineID(rs.getString("lineID"));
							line.setIntroduction(rs.getString("introduction"));
							line.setLineName(rs.getString("lineName"));
							line.setLineTypeID(rs.getString("lineTypeID"));
							line.setPrice(rs.getDouble("price"));
							line.setTeamBuy(rs.getInt("teamBuy"));
							line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
							line.setVehicle(rs.getString("vehicle"));
							lineList.add(line);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							DbUtils.close(null, stat, conn);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					return lineList;

				}
		//查询没有关闭的团的线路的总数
        public  int  queryTeamCount(int teamBuy){
			
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			int count=0;
			try {
				conn = DbUtils.getConnection();
				String sql= "select  count(*) as totals from ( select getDate()as new, " +
						   " lineID as lid from Line) as lin,Line as l2 where  " +
						   " lin.new between l2.beginTime  and l2.endTime   " +
						   " and  lin.lid=l2.lineID and teamBuy=?";
			    stat=conn.prepareStatement(sql);
                stat.setInt(1, teamBuy);
				rs = stat.executeQuery();
				if (rs.next()) {
					count=rs.getInt("totals");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return count;
			
			
		}
			
		
		//查询指定时间内的已经被订购的团购线路总数
		public  int  queryTeamBuyLineCount(int teamBuy){
			
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			int count=0;
			try {
				conn = DbUtils.getConnection();
				String sql= " select  count(od.lineID) as totals from OrderDetail as od " +
						    " where lineID in (select l2.lineID " +
						    " from ( select getDate()as new, lineID as id from Line) as lin,Line as l2" +
						    " where  lin.new between l2.beginTime  and l2.endTime   and  lin.id=l2.lineID and teamBuy=?)";
				
				System.out.println(sql);
		        stat=conn.prepareStatement(sql);
                stat.setInt(1, teamBuy);
				rs = stat.executeQuery();
				if (rs.next()) {
					count=rs.getInt("totals");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return count;
			
			
		}
		
     //查询团购
     public  List<Line> findTeamBuyLine(int beginNum,int maxResult,int teamBuy){

			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			Line  line = null;
			List<Line> lineList=new ArrayList<Line>();
			try {
				conn = DbUtils.getConnection();
				String sql= " select top "+maxResult+" * from  Line " +
						    " where  teamBuy=? and lineID not in (" +
						    " select top "+beginNum+" lineID from Line" +
						    " where  teamBuy=? order by  beginTime desc )" +
						    " order by  beginTime desc ";
		        stat=conn.prepareStatement(sql);
				stat.setInt(1, teamBuy);
				stat.setInt(2, teamBuy);
				rs = stat.executeQuery();
				while (rs.next()) {
					line=new Line();
					line.setDays(rs.getInt("days"));
					line.setLineID(rs.getString("lineID"));
					line.setIntroduction(rs.getString("introduction"));
					line.setLineName(rs.getString("lineName"));
					line.setLineTypeID(rs.getString("lineTypeID"));
					line.setPrice(rs.getDouble("price"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					line.setTeamBuyPrice(rs.getDouble("teamBuyPrice"));
					line.setVehicle(rs.getString("vehicle"));
					line.setBeginTime(rs.getString("beginTime"));
					line.setEndTime(rs.getString("endTime"));
					line.setTeamBuy(rs.getInt("teamBuy"));
					lineList.add(line);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return lineList;
    	 
     }		
     //查询团购总数
     public  int  queryTeamBuyCount(int teamBuy){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			int count=0;
			try {
				conn = DbUtils.getConnection();
				String sql= " select count(*) as totals  from " +
						    "  Line where  teamBuy=?";
		        stat=conn.prepareStatement(sql);
                stat.setInt(1, teamBuy);
				rs = stat.executeQuery();
				if (rs.next()) {
					count=rs.getInt("totals");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtils.close(null, stat, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return count;

		}
		
     
     
		
}
