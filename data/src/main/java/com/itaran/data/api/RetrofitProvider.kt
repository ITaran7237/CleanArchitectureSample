package com.itaran.data.api
import com.google.gson.GsonBuilder
import com.itaran.data.prefs.IPrefs
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider(private val prefs: IPrefs) {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://dvsdvdvbs.com")
            .client(initClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    fun initClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                val request: Request
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type", "application/json")
                if (prefs.getIdToken().isNotEmpty()) {
                    requestBuilder.addHeader("Authorization", "Bearer " + prefs.getIdToken())
                }
                if (prefs.getRefreshToken().isNotEmpty()) {
                    requestBuilder.addHeader("refreshtoken", prefs.getRefreshToken())
                }
                if (prefs.getAccessToken().isNotEmpty()) {
                    requestBuilder.addHeader("accesstoken", prefs.getAccessToken())
                }
                request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }
}
