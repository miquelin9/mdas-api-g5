package com.ccm.pokemon.pokemonTypes.infrastructure.client;

import com.ccm.pokemon.pokemonTypes.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemonTypes.domain.interfaces.PokemonTypeGetterInterface;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

@ApplicationScoped
@Named("Http")
public class PokemonTypeGetterClient implements PokemonTypeGetterInterface {

    private static final String HOST_ENDPOINT = "https://pokeapi.co/api/v2/pokemon/";
    private static final double TIMEOUT = 3;

    @Override
    public JSONObject getPokemonTypeJsonByPokemonName(String pokemonName) throws PokemonNotFoundException, TimeoutException, NetworkConnectionException, UnknownException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(HOST_ENDPOINT + pokemonName);
        RequestConfig.Builder requestConfig = RequestConfig.custom();
        requestConfig.setConnectTimeout((int) (TIMEOUT * 1000.0));
        requestConfig.setConnectionRequestTimeout((int) (TIMEOUT * 1000.0));
        requestConfig.setSocketTimeout((int) (TIMEOUT * 1000.0));
        request.setConfig(requestConfig.build());

        try {
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            JSONParser parser = new JSONParser();
            String str = EntityUtils.toString(entity);

            if (str.equals("Not found")) {
                throw new PokemonNotFoundException("Pokemon '" + pokemonName + "' not found");
            }
            return (JSONObject) parser.parse(str);
        } catch (ConnectTimeoutException | SocketTimeoutException e) {
            throw new TimeoutException("Timeout period expired. Response took too long to arrive");
        } catch (UnknownHostException e) {
            throw new NetworkConnectionException("Unable to reach specified host (maybe the network is down)");
        } catch (Exception e) {
            throw new UnknownException("There was some unknown problem while processing the request to the external API");
        }
    }
}

