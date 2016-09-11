import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SyncFailedException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class QuickWeather{

//    private static final String WEATHER_API_ZIP =
//            "api.openweathermap.org/data/2.5/weather?zip=ZIPCODE,us&APPID=KEY";
    private static final String WEATHER_API_CITY =
            "api.openweathermap.org/data/2.5/weather?q=CITY&APPID=KEY";
    private static final String KEY =
            "b280819425992bbd289902c6fbf2ce55";

    public static void main(String[] args){
        if(args.length < 1){
            //nope
            System.out.println("try: QuickWeather [city]");
        } else {
            if(args[0].equals("-z")){
                //zipcode api
                //doesnt seem to work :<
            } else {
                String report = getCityWeather(args[0].trim());
                if(report == null){
                    System.out.println("Connection to weather api failed, are you online?");
                } else {
                    System.out.println(report);
                }
            }
        }
    }

    private static String getCityWeather(String city) {
        //api.openweathermap.org/data/2.5/weather?q=CITY,KEY=KEY
        String adj = WEATHER_API_CITY.replace("KEY", KEY).replace("CITY", city).trim();
        System.out.println("Getting city forecast for " + city + " @ \n-" + adj);
        try {
            URI addr = new URI("http://" + adj);
            HttpURLConnection con = (HttpURLConnection) addr.toURL().openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            StringBuilder json = new StringBuilder(1000);
            String tmp = "";
            while ((tmp = reader.readLine()) != null) {
                System.out.print(tmp);
                json.append(tmp).append("\n");
            }
            reader.close();

            String data = json.toString();

            return data;
        } catch (Exception e) {
            System.out.println("\n" + e.toString() + "\n");
            return null;
        }
    }
}
