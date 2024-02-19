<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WeatherApp</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

   <div class="maincontainer">
        <div class="header">
            <div class ="search-box">
           	 	<form action="MyServlet" method="post"></form>
                	<input type="text" placeholder="Enter city name " name="CityName" >
                	<button><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
            </div>
            <div >
           <p class="CityName">${city }</p>
          
             </div>
            <div class="weather-body"> 
            
           <img id="weather-icon" alt="Weather" src="">
           
           <input type="hidden" id="wc" value="${weathercondition}"> 
           
            
           		
                    <div class="weather-box">
                         <p class="temperature">${temperature }<sup>°C</sup></p>
                        <p class="discreption">${weathercondition }</p>
                    </div>
                    <div class="weather-details">
                        <div class="humidity"><i class="fa-solid fa-droplet"></i>
                            <div class="text">
                                <span id="humidity">${humidity}%</span>
                                <p>Humidity</p>
                            </div>
                        </div>

                        <div class="wind-speed"><i class="fa-solid fa-wind"></i>
                            <div class="text">
                                <span id="wind-speed">${windspeed}km/H</span>
                                <p>Windspeed</p>
                            </div>
                        </div>
                    </div>
            </div>
        </div>  
   
   
     <script src="https://kit.fontawesome.com/07964f47a2.js" crossorigin="anonymous"></script>
     
     <script src="script.js"></script>
</body>
</html>
