package nasa;


import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class nasa_api {

    public static void main(String[] args) throws  IOException{
        getPictureOfTheDay();
    }

    public static void getPictureOfTheDay() throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
        String apiKey = "Ep8YLBREniGlDTgZ1MYQTwQ5rYBSkK5pbGJIxzHt";

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.nasa.gov/planetary/apod").newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);
        String url = urlBuilder.build().toString();
        System.out.println("url:" + url);

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        try  {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
