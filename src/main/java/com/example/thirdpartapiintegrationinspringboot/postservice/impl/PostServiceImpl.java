package com.example.thirdpartapiintegrationinspringboot.postservice.impl;

import com.example.thirdpartapiintegrationinspringboot.postservice.PostService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    String baseUrl = "https://jsonplaceholder.typicode.com/";

    StringBuilder stringBuilder  = new StringBuilder(baseUrl);

    String POST  = "/posts";

    String POSTBYID  = "/posts/";

   @Autowired
   private RestTemplate   restTemplate;
    @Override
    public List<Map<String, Object>> getPosts() {
        HttpEntity <Void> httpEntity  = new HttpEntity<>(gethttpHeaders());
       String url = stringBuilder.append(POST).toString();
        val response  =  restTemplate.exchange(url , HttpMethod.GET ,httpEntity,List.class); ;
        return  response.getBody();
    }

    @Override
    public Map<String, Object> getPostById(int id) {
        HttpEntity <Void> httpEntity  = new HttpEntity<>(gethttpHeaders());
        String url = stringBuilder.append(POSTBYID).append(id).toString();
        val response  =  restTemplate.exchange(url , HttpMethod.GET ,httpEntity,Map.class); ;
        return  response.getBody();
    }

    @Override
    public Map<String, Object> insertPosts(Map<String, Object> payload) {
        HttpEntity <Map> httpEntity  = new HttpEntity<>(payload,gethttpHeaders());
        String url = stringBuilder.append(POST).toString();
        val response  =  restTemplate.exchange(url , HttpMethod.POST ,httpEntity,Map.class); ;
        return  response.getBody();
    }

    @Override
    public Map<String, Object> updatePosts(Map<String, Object> payload, int id) {
        HttpEntity <Map> httpEntity  = new HttpEntity<>(payload,gethttpHeaders());
        String url = stringBuilder.append(POSTBYID).append(id).toString();
        val response  =  restTemplate.exchange(url , HttpMethod.PUT ,httpEntity,Map.class); ;
        return  response.getBody();
    }

    @Override
    public Map<String, Object> deletePost(int id) {
        HttpEntity <Map> httpEntity  = new HttpEntity<>(gethttpHeaders());
        String url = stringBuilder.append(POSTBYID).append(id).toString();
        val response  =  restTemplate.exchange(url , HttpMethod.DELETE ,httpEntity,Map.class); ;
        return  response.getBody();
    }

    private HttpHeaders gethttpHeaders(){
       HttpHeaders headers = new HttpHeaders();
       headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
       headers.setContentType(MediaType.APPLICATION_JSON);
       return headers;
    }
}
