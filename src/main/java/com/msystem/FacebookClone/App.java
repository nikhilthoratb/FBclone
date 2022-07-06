package com.msystem.FacebookClone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DaoLayer.FbDaoInterface;
import DaoLayer.FbDaoInterfaceImpl;
import FbModels.FbClone;
import FbModels.FbTimeLine;
import FbExceptions.NotFoundException;


/**
 * Hello world!
 *
 */
public class App 
{
	  public static Scanner sc=new Scanner(System.in);
	  private static String useremail;
	  private static String firstnm;
	  private static String lastnm;
	
	int profileOptions() {
		String choice="";
		do {
			
			System.out.println("-----MENU-----"
					+ "\n 1.View Profile"
					+ "\n 2.View All Profile"
					+ "\n 3.Update Profile"
					+ "\n 4.Delete Profile"
					+ "\n 5.Search Profile"
					+ "\n 6.Create Post"
					+ "\n 7.TimeLine"
					+ "\n 8.Others Post"
					+ "\n 9.Log Out"
					);
			System.out.println("Enter your option");
			int option =sc.nextInt();
			FbDaoInterface dao1=new FbDaoInterfaceImpl();
			switch(option) {
			case 1:
				System.out.println("Profile Information");
				FbClone f=new FbClone();
				FbDaoInterface daoNew=new FbDaoInterfaceImpl();
					
    			f.setEmail(useremail);
				List<FbClone> fList=daoNew.viewProfile(f);
				for(FbClone f1: fList) {
					System.out.println("*************************************");
					System.out.println("First Name 	  -> "+f1.getF_name());
					System.out.println("Last Name 	  -> "+f1.getL_name());
					System.out.println("Mobile Number -> "+f1.getMobile());
					System.out.println("Mail ID		  -> "+f1.getEmail());
					System.out.println("Date of Birth -> "+f1.getDob());
					System.out.println("Gender 		  -> "+f1.getGender());
					System.out.println("*************************************");
						
				}
				break;
			case 2:
				System.out.println("*************************************");
				
				System.out.println("All the User Profiles");
				
				List<FbClone> pList=dao1.viewAllProfile();
				for(FbClone f2: pList) {
					printProfileDetails(f2);
				}
				System.out.println("*************************************");
				
				break;
			case 3:
				System.out.println("*************************************");
				
        		System.out.println("Enter First Name To Update ");
        		String f_nm = sc.next();
        		System.out.println("Enter Last Name To Update: ");
        		String l_nm = sc.next();
        		System.out.println("Enter email to confirm updation ");
        		String e_nm = sc.next();
        		
        		dao1.UpdateProfile(f_nm,l_nm,e_nm);
        		System.out.println("*************************************");
				
				break;
			
				
				
    		case 4:
    			System.out.println("*************************************");
				
				System.out.println("Confirm your Email");
				String e=sc.next();
				System.out.println("Confirm your Password");
				String p=sc.next();
				FbDaoInterface fbc=new FbDaoInterfaceImpl();
				fbc.DeleteProfile(e,p);
				System.out.println("*************************************");
				
				break;
    		case 5:
    			System.out.println("*************************************");
				
    			System.out.println("Search Name: ");
        		String s1 = sc.next();
        		String s2=sc.next();
        		FbClone fb=dao1.SearchProfile(s1, s2);
        		printProfileDetails(fb);
        		System.out.println("*************************************");
				
        		break;
    		
    		case 6:
    			System.out.println("*************************************");
				
    			FbTimeLine fbc1=new FbTimeLine();
    			System.out.println("Write something to post");
    			String stringPost=sc.next();
    			
    			SimpleDateFormat Dateformatter = new SimpleDateFormat("yyyy-MM-dd");
    			SimpleDateFormat Timeformatter = new SimpleDateFormat("HH:mm:ss");
    			
    		    
    			Date date = new Date();  
    		    
    			FbDaoInterface dao=new FbDaoInterfaceImpl();
    			fbc1.setEmail(useremail);
   				fbc1.setPost(stringPost);
   				fbc1.setDate(Dateformatter.format(date));
    		    fbc1.setTime(Timeformatter.format(date)); 
    			dao.CreatePost(fbc1); 
    			System.out.println("*************************************");
				
    			break;
    		case 7:
    			System.out.println("*************************************");
				
    			FbDaoInterface dao2=new FbDaoInterfaceImpl();
    			FbTimeLine ftl=new FbTimeLine();
    			ftl.setEmail(useremail);
    			List<FbTimeLine> timelinearray=dao2.Timeline(ftl);
    			for(FbTimeLine timeline:timelinearray) {
    				 System.out.println("Post 	   ->	"+timeline.getPost());
    				 System.out.println("Post Time ->	"+timeline.getTime());
    				 System.out.println("Post Date ->	"+timeline.getDate());
    				 
    				
    			 }
    			 
    			System.out.println("*************************************");
				
				break;
    		case 8:
    			System.out.println("*************************************");
				
    			System.out.println("Enter Name to see their post");
    			String fnm=sc.next();
    			String lnm=sc.next();
    			
    			
    			FbDaoInterface Daonew=new FbDaoInterfaceImpl();
    			List<FbTimeLine> FbOtherPost=Daonew.PostByOther(fnm,lnm);
    			for(FbTimeLine timeline:FbOtherPost) {
    				 System.out.println("Post 		->	"+timeline.getPost());
    				 System.out.println("Post time  ->  "+timeline.getTime());
    				 System.out.println("Post date  ->  "+timeline.getDate());
    				 
    				
    			 }
    			System.out.println("*************************************");
				
    			break;
    		case 9:
    			App.main(null);
			default:
				System.out.println("You entered invalid Option");	
    	}
        	System.out.println("Do you want to continue (yes/no)");
        	choice = sc.next();
		}
		while(choice.equalsIgnoreCase("yes"));
		
		return 0;

	}
    public static void main( String[] args )
    {
    	System.out.println("*************************************");
		
    	System.out.println("Welcome To FACEBOOK");
    	
		while(true) {
			
			System.out.println("1.Sign Up");
	    	System.out.println("2.Sign In");
	    	System.out.println("*************************************");
			
			int a=sc.nextInt();
			
			if(a==1) {
				
			System.out.println("Enter First Name");
			String a1=sc.next();
			firstnm=a1;
			System.out.println("Enter Last Name");
			String a2=sc.next();
			lastnm=a2;
			System.out.println("Enter Mobile Number");
			long a3=sc.nextLong();
			System.out.println("Enter Email");
			String a4=sc.next();
			System.out.println("Enter Password");
			String a5=sc.next();
			System.out.println("Enter Date of Birth");
			String a6=sc.next();
			System.out.println("Enter Gender");
			String a7=sc.next();
			FbClone fbc1=new FbClone();
			FbDaoInterface dao=new FbDaoInterfaceImpl();
			fbc1.setF_name(a1);
			fbc1.setL_name(a2);
			fbc1.setMobile(a3);
			fbc1.setEmail(a4);
			fbc1.setPassword(a5);
			fbc1.setDob(a6);
			fbc1.setGender(a7);
			
			dao.InsertUser(fbc1);
			
		}
		else if(a==2){
			System.out.println("*************************************");
			
			System.out.println("Enter Username");
			String u=sc.next();

			System.out.println("Enter Password");
			String pa=sc.next();
			System.out.println("*************************************");
			
			FbDaoInterface dao=new FbDaoInterfaceImpl();
			
			int i=dao.Signin(u, pa);
			if(i==1) {
				useremail=u;
				App app=new App();
				app.profileOptions();
			}else {
				System.out.println("Wrong inputs");
				
				
			}
				
			}
		}

      //\System.out.println("Thank you!!!!");
    }
private static void printProfileDetails(FbClone fb) {
	// TODO Auto-generated method stub
	String fnm=fb.getF_name();
	String lnm=fb.getL_name();
	
	
	System.out.println(fnm +  " " + lnm+ " ");
	
}
    }
