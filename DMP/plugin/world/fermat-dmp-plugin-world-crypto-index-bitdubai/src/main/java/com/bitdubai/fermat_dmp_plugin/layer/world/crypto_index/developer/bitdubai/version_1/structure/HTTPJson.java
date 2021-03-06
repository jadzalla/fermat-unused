package com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.exceptions.CantGetBufferedReaderException;
import com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.exceptions.CantGetInputStreamException;
import com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.exceptions.CantGetJsonObjectException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

// TODO add a little description of the class here

public class HTTPJson {

    public HTTPJson() {
    }

    /**
     * this method is responsible for implementing all the methods of the class and return a JSON object
     *
     * @param url
     * @return
     */
    public JSONObject getJSONFromUrl(String url) {
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        JSONObject jsonObject = null;
        try {
            inputStream = getInputStream(url);
        } catch (CantGetInputStreamException cantGetInputStreamException) {
            cantGetInputStreamException.printStackTrace();
        }
        try {
            bufferedReader = getBufferedReader(inputStream);
        } catch (CantGetBufferedReaderException cantGetBufferedReaderException) {
            cantGetBufferedReaderException.printStackTrace();
        }
        try {
            jsonObject = getJsonObject(bufferedReader);
        } catch (CantGetJsonObjectException cantGetJsonObjectException) {
            cantGetJsonObjectException.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * InputStream This method returns the HTTP object variable from the url
     **/
    public InputStream getInputStream(String url) throws CantGetInputStreamException {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            return httpEntity.getContent();
        } catch (IOException e) {
            throw new CantGetInputStreamException(CantGetInputStreamException.DEFAULT_MESSAGE, e, "Probably the url is invalid", null);
        }
    }

    /**
     * The objective of this method is to return a variable of type BufferedReader to read the content of the url. In this case JSON is a text format.
     **/
    public BufferedReader getBufferedReader(InputStream is) throws CantGetBufferedReaderException {
        try {
            return new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
        } catch (UnsupportedEncodingException e) {
            throw new CantGetBufferedReaderException(CantGetBufferedReaderException.DEFAULT_MESSAGE, e, "Probably the InputStream parameter is not correct", null);
        }
    }

    /**
     * En este método se espera el ingreso de un parámetro del tipo BufferedReader para que el mismo sea leído, colocado en formato json y guardado como un JsonObject
     */
    public JSONObject getJsonObject(BufferedReader reader) throws CantGetJsonObjectException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            String json = stringBuilder.toString();
            return new JSONObject(json);
        } catch (IOException e) {
            throw new CantGetJsonObjectException(CantGetJsonObjectException.DEFAULT_MESSAGE, e, "Probably the Json object obtained not correct or is not within the expected format", null);
        }
    }
}