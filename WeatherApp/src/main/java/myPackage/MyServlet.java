package myPackage;

import java.net.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String inputdata=request.getParameter("userinput");
		//  System.out.println(inputdata);
		
		// Setting up Apikey
		String Apikey="1d2ea6bbf91b304b95cd7769f259f96b";
		
		// get city name from form
		String CityName=request.getParameter("CityName");
		
		// setting Api Url 
		String ApiUrl="https://api.openweathermap.org/data/2.5/weather?q=" +CityName+"&appid="+Apikey;
		
		try{// Api integeration
		URL url=new URL(ApiUrl);
		
		HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		
		connection.setRequestMethod("GET");
		
		// Reading data from network
		
		InputStream inputStream=connection.getInputStream();
		
		InputStreamReader reader= new InputStreamReader(inputStream);
		
		// store in string 
		StringBuilder responseContent=new  StringBuilder();
		
		
		// for input reader will take scanner object
		Scanner scanner=new Scanner(reader);
		while(scanner.hasNext()) {
			responseContent.append(scanner.nextLine());
			}scanner.close();
			
		//Type casting parsing the data into JSON
			
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(responseContent.toString(),JsonObject.class);
		
		
		
        //Temperature
        double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        int temperatureCelsius = (int) (temperatureKelvin - 273.15);
       
      //Weather Condition
        String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
        
        //Humidity
        int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
        
        //Wind Speed
        double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
        
      
      
        request.setAttribute("city",CityName );
        request.setAttribute("temperature",temperatureCelsius);
        request.setAttribute("weathercondition", weatherCondition);
        request.setAttribute("humidity", humidity);
        request.setAttribute("windspeed", windSpeed);
        request.setAttribute("weatherData", responseContent.toString());
		 
        connection.disconnect();
	
		 } catch (IOException e) {
			 request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
	            e.printStackTrace();
	        }
		
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	

}
