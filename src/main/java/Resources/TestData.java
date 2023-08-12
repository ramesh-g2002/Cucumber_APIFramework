package Resources;

import java.util.ArrayList;
import java.util.List;

import POJOS.AddPlace;
import POJOS.Location;


public class TestData {
public AddPlace addPlacePayload(String name, String language, String address)
{
	AddPlace a=new AddPlace();
	a.setAccuracy(50);
	a.setName(name);
	a.setWebsite("https://rahulshettyacademy.com/");
	a.setLanguage(language);
	a.setPhone_number("(+91) 983 893 3937");
	a.setAddress(address);
	List<String> l1=new ArrayList<String>();
	l1.add("shoe park");
	l1.add("shop");
	a.setTypes(l1);
	Location l=new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	a.setLocation(l);
	
	return a;

	
}
public String deletePlacePayload(String placeid)
{
	return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";
}
}
