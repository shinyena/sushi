package com.example.sushi.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class KakaoLoginService {
    public String getToken(String code) {
        String reqURL = "https://kauth.kakao.com/oauth/token";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id=c524a7239cfdb94b1732cb913de178c9");
            stringBuilder.append("&redirect_uri=sushicaptain.com/sushi/kakao/login");
            stringBuilder.append("&code=" + code);
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();

            int responseCode = connection.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            System.out.println("result = " + result);


            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            String access_token = element.getAsJsonObject().get("access_token").getAsString();
            String refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_token);
            System.out.println("refresh_token : " + refresh_token);

            bufferedReader.close();
            bufferedWriter.close();

            return access_token;



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Map<String, Object> getUserInfo(String token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer {" + token + "}");

            int responseCode = connection.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            System.out.println("result = " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            String email;
            if (kakao_account.getAsJsonObject().get("email").isJsonNull()) {
                email = "";
            }
            else {
                email = kakao_account.getAsJsonObject().get("email").getAsString();
            }
            log.info("email = " + email);

            JsonObject profile = kakao_account.getAsJsonObject().get("profile").getAsJsonObject();
            String name = profile.getAsJsonObject().get("nickname").getAsString();

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("email", email);
            userInfo.put("name", name);

            return userInfo;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
