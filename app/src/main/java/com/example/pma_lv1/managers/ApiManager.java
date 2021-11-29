package com.example.pma_lv1.managers;

import com.example.pma_lv1.interfaces.OpenCourseApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    static ApiManager instance;
    private OpenCourseApiService service;
    private ApiManager(){
        Retrofit.Builder builder = new Retrofit.Builder();
        //postavljanje retrofit-a
        Retrofit retrofit = builder.baseUrl("https://catalog-api.udacity.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(OpenCourseApiService.class);
    }
    public static ApiManager getInstance(){
        if (instance == null){
            instance = new ApiManager();
        }
        return instance;
    }
    public OpenCourseApiService service(){
        return service;
    }
}
