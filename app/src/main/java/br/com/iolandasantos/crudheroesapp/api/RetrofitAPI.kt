package br.com.iolandasantos.crudheroesapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor



class RetrofitAPI<T> {

    fun getClient(c: Class<T>) : T {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://crudheroesapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkhttpClient())
            .build()

        return retrofit.create(c)
    }
}

fun getOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()
}

fun getStudioAPI(): StudioAPI {
    return RetrofitAPI<StudioAPI>().getClient(StudioAPI::class.java)
}

fun getHeroAPI(): HeroAPI {
    return RetrofitAPI<HeroAPI>().getClient(HeroAPI::class.java)
}