package googleAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Maps;

public class GoogleAPI extends AbstractSample {
    final String baseUrl;
    final Map<String, String> params;
    ReadFromFile readFromFile;
    final String filename;

    ArrayList<String> towns;

    public GoogleAPI() throws IOException, JSONException {

        baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";
        params = Maps.newHashMap();
       readFromFile = new ReadFromFile();
        filename = "qwer.txt";
        towns = readFromFile.read(filename);
        params.put("sensor", "false");

        for (String s:towns) {
            geocoding(s);
        }
    }

    public static void main(final String[] args) throws IOException, JSONException {
        GoogleAPI googleAPI = new GoogleAPI();
    }

    private void geocoding(String town) throws IOException, JSONException {
        params.put("address", town);

        final String url = baseUrl + '?' + encodeParams(params);
        System.out.println(url);
        final JSONObject response = ReadJson.read(url);

        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");
        final double lng = location.getDouble("lng");
        final double lat = location.getDouble("lat");
        System.out.println(String.format("%s %f,%f",town, lat, lng));



    }

}
