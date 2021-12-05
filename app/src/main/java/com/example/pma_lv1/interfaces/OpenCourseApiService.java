package com.example.pma_lv1.interfaces;

import com.example.pma_lv1.models.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenCourseApiService {
    @GET("/v1/courses")
    Call<ResponseAPI> getCourses();
}
