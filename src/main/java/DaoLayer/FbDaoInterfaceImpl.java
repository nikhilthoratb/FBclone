package DaoLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.msystem.FacebookClone.App;

import FbExceptions.NotFoundException;
import FbModels.FbClone;
import FbModels.FbTimeLine;


public class FbDaoInterfaceImpl implements FbDaoInterface {
	PreparedStatement preparedStmt=null;
	public List<FbClone> viewProfile(FbClone f) {
			List<FbClone> fList = new ArrayList<FbClone>();
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
				PreparedStatement ps = con.prepareStatement("select fname,lname,Mob,email,dob,gender from FacebookUser where email=?");
				ps.setString(1, f.getEmail());
				ResultSet rs = ps.executeQuery();
				if(rs.next()==false) {
					fList=null;
				}
				else {
					do{
						String fnm=rs.getString(1);
						String lnm=rs.getString(2);
						long mob=rs.getLong(3);
						String em=rs.getString(4);
						String dob=rs.getString(5);	
						String gender=rs.getString(6);
						f.setF_name(fnm);
						f.setL_name(lnm);
						f.setMobile(mob);
						f.setEmail(em);
						f.setDob(dob);
						f.setGender(gender);
						fList.add(f);
					}while (rs.next());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
					
			
			return fList;
		}

	public List<FbClone> viewAllProfile() {
		// TODO Auto-generated method stub
		
		List<FbClone> eList = new ArrayList<FbClone>();
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
			
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select fname,lname  from FacebookUser");
			while (rs.next()) {
				FbClone f = new FbClone();
				String fnm=rs.getString(1);
				String lnm=rs.getString(2);
				
				f.setF_name(fnm);
				f.setL_name(lnm);
				eList.add(f);
				
			}

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return eList;
	}


	public void UpdateProfile(String fnm, String lnm,String em) {
		// TODO Auto-generated method stub
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
			PreparedStatement preparedStmt = con.prepareStatement(" update FacebookUser set fname = ?,lname=? where email = ?");

			preparedStmt.setString(1, fnm);
			
			preparedStmt.setString(2, lnm);
			preparedStmt.setString(3, em);
			

			if (preparedStmt.executeUpdate() == 1)
			{
				System.out.println("Profile Updated");
			}
			else {
				throw new NotFoundException();
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	public void DeleteProfile(String email,String pass) {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
		
			PreparedStatement preparedStmt = con.prepareStatement("delete from FacebookUser where email=? and pass=?");
				
			preparedStmt.setString(1,email);
			
			preparedStmt.setString(2,pass);
			
			
			 if(preparedStmt.executeUpdate() == 1)
			 {
				 System.out.println("Account Deleted Successfully");
					App app=new App();
					app.main(null);
			 }
			 else {
					throw new NotFoundException(); 
				}
			}
			

			
		 catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}


	public FbClone SearchProfile(String Fname, String Lname ) {
		// TODO Auto-generated method stub
		FbClone fb  = new FbClone();
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
			PreparedStatement preparedStmt = con.prepareStatement("select fname,lname from FacebookUser where fname = ? and lname=?");
			preparedStmt.setString(1, Fname);
			preparedStmt.setString(2, Lname);
			
			ResultSet rs = preparedStmt.executeQuery();
			boolean found =false;
			while (rs.next()) {
				found=true;
				String fnm = rs.getString(1);
				String lnm= rs.getString(2);
				fb.setF_name(fnm);
				fb.setL_name(lnm);


			}
			if(found==false) {
				throw new NotFoundException();
			}
		
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
return fb;

	}
	
	public void CreatePost(FbTimeLine fbc1) {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
			PreparedStatement preparedStmt = con.prepareStatement("insert into FBtimeline values(?,?,?,?,?)");
			
			preparedStmt.setString(1,fbc1.getPost_id());
				
			preparedStmt.setString(2,fbc1.getPost());
			preparedStmt.setString(3,fbc1.getEmail());
			preparedStmt.setString(4,fbc1.getTime());
			preparedStmt.setString(5,fbc1.getDate());
			
		
			if (preparedStmt.executeUpdate() == 1)
				System.out.println("Post is Created");

		} catch (Exception e1) {
			System.out.println(e1);
		}
		
	}
	public List<FbTimeLine> Timeline(FbTimeLine ftl) {
		List<FbTimeLine> fbtList=new ArrayList<FbTimeLine>();

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
			
			PreparedStatement ps = con.prepareStatement("select post,t_time,t_date from FBtimeline where t_email=?");
			ps.setString(1, ftl.getEmail());
			ResultSet rs = ps.executeQuery();
			if(rs.next()==false) {
				fbtList=null;
			}
			else {
				do{
					FbTimeLine fbtr=new FbTimeLine();
					fbtr.setPost(rs.getString(1));
					fbtr.setTime(rs.getString(2));
					fbtr.setDate(rs.getString(3));
					fbtList.add(fbtr);
				}while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fbtList;
		
	}
	public List<FbTimeLine> PostByOther(String fnm,String lnm) {
		// TODO Auto-generated method stub
		List<FbTimeLine> FbOtherPost=new ArrayList<FbTimeLine>();

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
			
			PreparedStatement ps = con.prepareStatement("SELECT FBtimeline.post,FBtimeline.t_date,FBtimeline.t_time FROM Fbtimeline RIGHT JOIN FacebookUser ON FBtimeline.t_email = FacebookUser.email where fname=? and  lname=? ");
			ps.setString(1, fnm);
			ps.setString(2, lnm);
			ResultSet rs = ps.executeQuery();
			if(rs.next()==false) {
				FbOtherPost=null;
			}
			else {
				do{
					FbTimeLine fbtr=new FbTimeLine();
					
					fbtr.setPost(rs.getString(1));
					fbtr.setTime(rs.getString(2));
					fbtr.setDate(rs.getString(3));
					FbOtherPost.add(fbtr);
				}while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FbOtherPost;
		
		
	}




	public void InsertUser(FbClone f) {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
			PreparedStatement preparedStmt = con.prepareStatement("insert into FacebookUser(fname,lname,mob,email,pass,dob,gender) values(?, ?, ?, ?,?,?,?)");
			preparedStmt.setString(1, f.getF_name());
			preparedStmt.setString(2,f.getL_name());

			preparedStmt.setLong(3, f.getMobile());
			
			preparedStmt.setString(4, f.getEmail());
			preparedStmt.setString(5, f.getPassword());
			preparedStmt.setString(6, f.getDob());
			preparedStmt.setString(7, f.getGender());
			
			
			// 1 means -> 1 rows affected (msg see in mysql workbench)
			// 1 row inserted to the table
			// exceuteUpdate method -> insert, delete, update - because make changes on the
			// table
			if (preparedStmt.executeUpdate() == 1)
				System.out.println("Profile is Created");
		} catch (Exception e1) {
			System.out.println(e1);
		}
		
		
	}

	public int Signin(String Username, String Password) {
		int i=0;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8086/FBClone", "root", "Pass@123");
			
			PreparedStatement preparedStmt = con.prepareStatement("select email, pass from FacebookUser where email=? and pass=?");
			preparedStmt.setString(1,Username);
			preparedStmt.setString(2,Password);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			boolean UserNot=false;
			if (rs.next()) {
				UserNot=true;
				i=1;
				
				System.out.println("Sign In Successfully");
			
			}
			
			if (UserNot==false) {
				throw new NotFoundException();
				
			}	
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return i;
	}

	
		

	}


