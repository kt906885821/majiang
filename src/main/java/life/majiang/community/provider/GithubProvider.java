package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;

import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws Exception {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
            try {
                String s = response.body().string();
                System.out.println(s);
                String s1 = s.split("&")[0].split("=")[1];
                return s1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    public GithubUser githubUser(String accessToken){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request builder = new Request.Builder().url("https://api.github.com/user?access_token=" + accessToken).build();
        try {
            Response response = okHttpClient.newCall(builder).execute();
            String string = response.body().string();
            GithubUser user = JSON.parseObject(string, GithubUser.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
