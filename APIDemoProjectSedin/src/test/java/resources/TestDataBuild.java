package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.DeleteLocation;
import pojo.Location;

public class TestDataBuild {

	
	
	public AddPlace addPlacePayLoad(String name, String language, String address, String ph,String website,String type1, String type2)
	{
		AddPlace addPlace =new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setPhone_number(ph);
		addPlace.setWebsite(website);
		addPlace.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add(type1);
		myList.add(type2);
		addPlace.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		addPlace.setLocation(l);
		return addPlace;
	}
	
	public DeleteLocation deletePlacePayload(String placeId)
	{
		DeleteLocation deleteLocn=new DeleteLocation();
		deleteLocn.setPlace_id(placeId);
		return deleteLocn;
	}
}
