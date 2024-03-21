package uz.akra.namozvaqtlari.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.akra.namozvaqtlari.models.HaftalikModel
import uz.akra.namozvaqtlari.models.MyNVKunlik

interface APIService {

    @GET("present/day")

    fun getNVkunlik(@Query("region") region:String): Call<MyNVKunlik>

    @GET("present/week")
    fun getNVHaftalik(@Query("region") region: String): Call<List<HaftalikModel>>

}