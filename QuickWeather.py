import requests
import sys
import json

def getWeather():
    if len(sys.argv) < 2:
        print("try: QuickWeather [city]")
    else:
        WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=" + sys.argv[1] + "&APPID=b280819425992bbd289902c6fbf2ce55"
        print("Searching for weather in " + sys.argv[1] + " @ \n" + WEATHER_URL)
        api_response = requests.get(WEATHER_URL).json()
        print(api_response)


if __name__ == '__main__':
    getWeather()

