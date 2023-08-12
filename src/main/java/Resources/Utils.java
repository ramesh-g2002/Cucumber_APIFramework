package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	RequestSpecification req;
	public RequestSpecification requestSpecfication() throws IOException
	{
		if(req==null)
		{
			PrintStream log=new PrintStream(new FileOutputStream("Output.txt"));
			req=new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseurl"))
					.addQueryParam("key", "qaclick12")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			return req;
		}
		return req;
	}
	public static String getGlobalValue( String key) throws IOException
	{
		FileInputStream fis=new FileInputStream(".//data/data.properties");
		Properties p=new Properties();
		p.load(fis);
		return  p.getProperty(key);
		
	}
	public String getJsonpath(Response reponse,String key)
	{
		String res=reponse.asString();
		JsonPath j=new JsonPath(res);
		return j.get(key).toString();
	}
	
}
