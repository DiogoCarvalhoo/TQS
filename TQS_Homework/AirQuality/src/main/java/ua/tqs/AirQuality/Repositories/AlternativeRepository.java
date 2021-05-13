package ua.tqs.AirQuality.Repositories;

import org.springframework.stereotype.Repository;

import ua.tqs.AirQuality.Model.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.JSONObject;  

@Repository
public class AlternativeRepository {
    private static final Logger logger = LoggerFactory.getLogger(AlternativeRepository.class);

    private static final String API_URL = "https://api.weatherbit.io/v2.0/current/airquality";

    private static final String TOKEN = "a2f8dbe56ec14f48bd3734fb8cdea4e2";

    public AirData getDataByCity(String city) {
        AirData dados = null;

        try {
            var url = new URL(API_URL +"?city=" + city + "&key=" + TOKEN);
            logger.info("LOGGER: Sending request to url: {}", url);
            dados = this.getAlternativeApiContentAndConvertToAirData(url);
        }
        catch (Exception e) {
            logger.info("LOGGER: {}", e.toString());
        }
        return dados;
    }

    public AirData getDataByLatLon(Double lat, Double lon) {
        AirData dados = null;

        try {
            var url = new URL(API_URL +"?lat=" + lat + "&lon=" + lon + "&key=" + TOKEN);
            logger.info("LOGGER: Sending request to url: {}", url);
            dados = this.getAlternativeApiContentAndConvertToAirData(url);
        }
        catch (Exception e) {
            logger.info("LOGGER: {}", e.toString());

        }

        return dados;
    }


    //Metodo conversao do JSON recebido pela api alternativa para o formato de dados gerado pela API principal
    public AirData getAlternativeApiContentAndConvertToAirData(URL url) {
        AirData dados = null;
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            var in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            var builder = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                builder.append(inputLine);
            }
            var content = builder.toString();
            in.close();
            var json = new JSONObject(content);
            var newcity = new City();
            newcity.setName(String.valueOf(json.get("city_name")));
            List<Double> newlist = new ArrayList<>();
            newlist.add(Double.valueOf(String.valueOf(json.get("lat"))));
            newlist.add(Double.valueOf(String.valueOf(json.get("lon"))));
            newcity.setLoc(newlist);
            var jsonarray = json.getJSONArray("data");
            var data = jsonarray.getJSONObject(0);
            var newmetrics = new Metrics();
            Iterator<String> keys = data.keys();
            String[] lista = {"dew", "h", "no2", "o3", "p", "pm10", "pm25", "so2", "t", "w", "wg"};
            List<String> listaStrings = Arrays.asList(lista);
            while (keys.hasNext()) {
                String s = keys.next();
                if (listaStrings.contains(String.valueOf(s))) {
                    var newvalue = new Value();
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
            var newdata = new Data();
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