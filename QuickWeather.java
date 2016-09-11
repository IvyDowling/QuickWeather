import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuickWeather {

    private static final String WEATHER_API_ZIP =
            "api.openweathermap.org/data/2.5/weather?zip=ZIPCODE&APPID=KEY";
    private static final String WEATHER_API_CITY =
            "api.openweathermap.org/data/2.5/weather?q=CITY&APPID=KEY";
    private static final String KEY =
            "b280819425992bbd289902c6fbf2ce55";

    public static void main(String[] args){
        if(args.length < 1){
            //nope
            System.out.println("try [city] or -z [zipcode]");
        } else {
            if(args[0].equals("-z")){
                //zipcode
                if(args[1] != null) {
                    String report = getZipWeather(args[1]);
                    System.out.println(report);
                } else {
                    System.out.println("Include a zip code -z [zipcode]");
                }
            } else {
                String report = getCityWeather(args[0]);
                System.out.println(report);
            }
        }
    }

    private static String getCityWeather(String city) {
        //api.openweathermap.org/data/2.5/weather?q=CITY
        String adj = WEATHER_API_CITY
                .replace("KEY", KEY)
                .replace("CITY", city);

        try {
            URL url = new URL(String.format(adj));
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(10000);
            String tmp = "";
            while ((tmp = reader.readLine()) != null) {
                json.append(tmp).append("\n");
            }
            reader.close();

            String data = json.toString();

            return data;
        } catch (Exception e) {
            return null;
        }
    }

    private static String getZipWeather(String zip) {
        //api.openweathermap.org/data/2.5/weather?zip=ZIPCODE"
        String adj = WEATHER_API_ZIP
                .replace("KEY", KEY)
                .replace("ZIPCODE", zip);
        try {
            URL url = new URL(String.format(adj));
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(10000);
            String tmp = "";
            while ((tmp = reader.readLine()) != null) {
                json.append(tmp).append("\n");
            }
            reader.close();

            String data = json.toString();

            return data;
        } catch (Exception e) {
            return null;
        }
    }
}
