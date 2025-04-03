package com.example.quickity.api_feature.Network


import com.example.quickity.api_feature.apidata.Agents
import com.example.quickity.api_feature.apidata.Weapon
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("agents")
    fun getAllAgents() : Call<Agents>

    @GET("weapons")
    fun getAllWeapon() : Call<Weapon>
}