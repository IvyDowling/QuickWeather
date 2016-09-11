import urllib.request
import sys

if len(sys.argv) is 0:
    print("try: QuickWeather [city]")
else :
    WEATHER_URL = "api.openweathermap.org/data/2.5/weather?q=" + sys.argv[1:] + " &APPID=b280819425992bbd289902c6fbf2ce55"
    print("Searching for weather in " + sys.argv[1:] + " @ \n" + WEATHER_URL)
    json = urllib.request.urlopen(WEATHER_URL).read()
    urllib.close
    print(json)