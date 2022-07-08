package info.rayrojas.icecream.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import info.rayrojas.icecream.helpers.QueueUtils;
import info.rayrojas.icecream.ui.about.AboutFragment;
import info.rayrojas.icecream.ui.view.ViewFragment;

public class Productos {
        public String name;
        public String nickname;

        public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                                   final ArrayList<Productos> productoss,
                                                   final AboutFragment _interface) {
            String url = "https://fipo.equisd.com/api/users.json";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            if (response.has("objects")) {

                                try {
                                    JSONArray list = response.getJSONArray("objects");
                                    for (int i=0; i < list.length(); i++) {
                                        JSONObject o = list.getJSONObject(i);
                                        Productos p = new Productos();
                                        p.name = o.getString("name");
                                        p.nickname = o.getString("nickname");
                                        productoss.add(p);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                _interface.refreshList(); // Esta función debemos implementarla
                                // en nuestro activity
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            o.addToRequestQueue(jsonObjectRequest);
        }

        public static void injectSuppliersFromCloud(QueueUtils.QueueObject queueObject, ArrayList<Productos> productoss, ViewFragment viewFragment) {
        }
}
