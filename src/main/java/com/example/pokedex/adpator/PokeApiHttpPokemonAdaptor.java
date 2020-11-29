package com.example.pokedex.adpator;

import java.io.IOException;

import com.example.pokedex.entity.Pokemon;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PokeApiHttpPokemonAdaptor implements IPokemonAdaptor {
    private String url = "https://pokeapi.co/api/v2/pokemon/";
    private HttpRequestBase request;
    private CloseableHttpClient client;

    @Override
    public Pokemon getPokemon(int id) {
        prepareRequest(id);
        HttpResponse response = null;
        Pokemon pokemon = null;
        try {
            response = issueRequest();
            pokemon = parseResponse(response);
        } catch (Exception e) {

        }
        return pokemon;
    }

    private HttpResponse issueRequest() throws ClientProtocolException, IOException {
        return client.execute(request);
    }

    private Pokemon parseResponse(HttpResponse response) throws org.apache.http.ParseException, IOException,
            ParseException {
        JSONParser parser = new JSONParser();
        String dump = EntityUtils.toString(response.getEntity(), "UTF-8");
        Object resultObject = parser.parse(dump);
        JSONObject obj = (JSONObject) resultObject;
        Long height = (Long)obj.get("height");
        Long weight = (Long)obj.get("weight");
        Long id = (Long)obj.get("order");
        return new Pokemon(id.intValue(), (String)obj.get("name"), height.intValue(), weight.intValue(), "");
    }

    private void prepareRequest(int id) {
        client = HttpClientBuilder.create().build();
        request = (HttpGet) new HttpGet(getUrl() + String.valueOf(id));
        request.addHeader("content-type", "application/json");
    }

    public String getUrl() {
        return url;
    }
}
