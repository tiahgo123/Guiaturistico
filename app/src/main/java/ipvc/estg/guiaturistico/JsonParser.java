package ipvc.estg.guiaturistico;

/**
 * Created by tiago on 21/06/2015.
 */

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {



    public List<List<HashMap<String, Integer>>> parse(JSONObject jObject)
    {
        List<List<HashMap<String, Integer>>> routes = new ArrayList<List<HashMap<String, Integer>>>();
        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;
        JSONObject jDistance = null;
        JSONObject jDuration = null;

        try
        {
            jRoutes = jObject.getJSONArray("routes");
/** Traversing all routes */
            for (int i = 0; i < jRoutes.length(); i++)
            {
                jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
                List<HashMap<String, Integer>> path = new ArrayList<HashMap<String, Integer>>();
/** Traversing all legs */
                for (int j = 0; j < jLegs.length(); j++)
                {
/** Getting distance from the json data */
                    jDistance = ((JSONObject) jLegs.get(j)).getJSONObject("distance");
                    HashMap<String, Integer> hmDistance = new HashMap<String, Integer>();
                    hmDistance.put("distance", jDistance.getInt("value"));





/** Adding distance object to the path */
                    path.add(hmDistance);

/** Adding duration object to the path */
                  //  path.add(hmDuration);

                    jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");

/** Traversing all steps */
                    for (int k = 0; k < jSteps.length(); k++)
                    {
                        String polyline = "";
                        polyline = (String) ((JSONObject) ((JSONObject) jSteps.get(k)).get("polyline")).get("points");
                        //List<LatLng> list = this.decodePoly(polyline);

/** Traversing all points */
                      /*  for (int l = 0; l < list.size(); l++)
                        {
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("lat", Double.toString((list.get(l)).latitude));
                            hm.put("lng", Double.toString((list.get(l)).longitude));
                            path.add(hm);
                        }*/
                    }
                }
                routes.add(path);
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
        }

        return routes;
    }
}
