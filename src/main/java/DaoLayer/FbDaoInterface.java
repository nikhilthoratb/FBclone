package DaoLayer;

import java.util.List;


import FbModels.FbClone;
import FbModels.FbTimeLine;

public interface FbDaoInterface {
	
	List<FbClone> viewAllProfile();

	List<FbClone> viewProfile(FbClone f);
	
	int Signin(String Username, String Password);
	void UpdateProfile(String fnm,String lnm,String em);
	void InsertUser(FbClone f);
	void DeleteProfile(String email,String Pass );
	FbClone SearchProfile(String Fname, String Lname);
	void CreatePost(FbTimeLine fbc1);
	List<FbTimeLine> Timeline(FbTimeLine ftl);
	List<FbTimeLine> PostByOther(String fnm,String lnm);
	
	
}
