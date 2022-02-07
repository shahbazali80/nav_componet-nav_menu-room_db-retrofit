package com.example.navigationcomponenttutorial.api

import com.example.navigationcomponenttutorial.model.Students
import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {

    @POST("/posts")
    fun post(@Body userPost: Students) : Call<Students>

    @DELETE("/posts/{id}")
    fun delete(@Path("id") id: Int) : Call<Unit>

    @PUT("/posts/{id}")
    fun put(
        @Path("id") id: Int,
        @Body userPost: Students
    ) : Call<Students>

    @PUT("/posts/{id}")
    fun petch(
        @Path("id") id: Int,
        @Body userPost: Students
    ) : Call<Students>

}