package ua.tqs.Air_Quality.Repositories;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import ua.tqs.Air_Quality.Model.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;  
import org.json.JSONArray;
import org.json.JSONException;

@Repository
public class AlternativeRepository {
    private static final Logger logger = Logger.getLogger(AlternativeRepository.class.getName());

    private static final String API_URL = "https://api.weatherbit.io/v2.0/current/airquality";

    private static final String TOKEN = "a2f8dbe56ec14f48bd3734fb8cdea4e2";

    public AirData getDataByCity(String city) {
        AirData dados = null;
        String loggerOutput;

        try {
            URL url = new URL(API_URL +"?city=" + city + "&key=" + TOKEN);
            loggerOutput = "LOGGER: Sending request to url: " + url;
            logger.log(Level.INFO, loggerOutput);
            dados = this.getAlternativeApiContentAndConvertToAirData(url);
        }
        catch (Exception e) {
            loggerOutput = "LOGGER:" + e.toString();
            logger.log(Level.WARNING, loggerOutput);
        }
        return dados;
    }

    public AirData getDataByLatLon(Double lat, Double lon) {
        AirData dados = null;
        String loggerOutput;

        try {
            URL url = new URL(API_URL +"?lat=" + lat + "&lon=" + lon + "&key=" + TOKEN);
            loggerOutput = "LOGGER: Sending request to url: " + url;
            logger.log(Level.INFO, loggerOutput);
            dados = this.getAlternativeApiContentAndConvertToAirData(url);
        }
        catch (Exception e) {
            loggerOutput = "LOGGER:" + e.toString();
            logger.log(Level.WARNING, loggerOutput);
        }

        return dados;
    }


    //Metodo conversao do JSON recebido pela api alternativa para o formato de dados gerado pela API principal
    public AirData getAlternativeApiContentAndConvertToAirData(URL url) {
        AirData dados = null;
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            String content ="";
            while ((inputLine = in.readLine()) != null) {
                content += inputLine;
            }
            in.close();
            JSONObject json = new JSONObject(content);
            City newcity = new City();
            newcity.setName(String.valueOf(json.get("city_name")));
            List<Double> newlist = new ArrayList<Double>();
            newlist.add(Double.valueOf(String.valueOf(json.get("lat"))));
            newlist.add(Double.valueOf(String.valueOf(json.get("lon"))));
            newcity.setLoc(newlist);
            JSONArray jsonarray = json.getJSONArray("data");
            JSONObject data = jsonarray.getJSONObject(0);
            Metrics newmetrics = new Metrics();
            Iterator<String> keys = data.keys();
            String[] lista = {"dew", "h", "no2", "o3", "p", "pm10", "pm25", "so2", "t", "w", "wg"};
            while (keys.hasNext()) {
                String s = keys.next();
                if (Arrays.asList(lista).contains(String.valueOf(s))) {
                    Value newvalue = new Value();
                    newvalue.setValue(Double.valueOf(String.valueOf(data.get(String.valueOf(s)))));
                    if (String.valueOf(s).equals("dew"))
                        newmetrics.setDew(newvalue);
                    else if (String.valueOf(s).equals("h"))
                        newmetrics.setH(newvalue);
                    else if (String.valueOf(s).equals("no2"))
                        newmetrics.setNo2(newvalue);
                    else if (String.valueOf(s).equals("p"))
                        newmetrics.setP(newvalue);
                    else if (String.valueOf(s).equals("pm10"))
                        newmetrics.setPm10(newvalue);
                    else if (String.valueOf(s).equals("pm25"))
                        newmetrics.setPm25(newvalue);
                    else if (String.valueOf(s).equals("so2"))
                        newmetrics.setSo2(newvalue);
                    else if (String.valueOf(s).equals("t"))
                        newmetrics.setT(newvalue);
                    else if (String.valueOf(s).equals("w"))
                        newmetrics.setW(newvalue);
                    else if (String.valueOf(s).equals("wg"))
                        newmetrics.setWg(newvalue);
                }
            }
            Data newdata = new Data();
            newdata.setCity(newcity);
            newdata.setMetrics(newmetrics);
            dados = new AirData();
            dados.setData(newdata);
            return dados;
        } catch (Exception e) {
            return null;
        }
    }
}