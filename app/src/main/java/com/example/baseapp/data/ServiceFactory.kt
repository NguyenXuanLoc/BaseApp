package com.example.baseapp.data

import com.chauthai.swipereveallayout.BuildConfig
import com.example.baseapp.common.util.CommonUtil
import com.example.baseapp.data.response.BaseResponse
import com.example.baseapp.data.response.ProfileResponse
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ServiceFactory {

    companion object {
        private const val REQUEST_TIMEOUT = 15L
        private const val WRITE_TIMEOUT = 60L
        private const val BASE_URL = "https://api.github.com/"
        private const val HEADER_APP_CODE = "App-Code"
        private const val HEADER_AUTHENTICATION = "Authentication"
        private const val VICALL = "vicall"

        fun create(): ServiceFactory {
            val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader(HEADER_APP_CODE, VICALL)
                        .apply {
                            CommonUtil.getDeviceToken()?.also { token ->
                                addHeader(HEADER_AUTHENTICATION, "Basic:$token")
                            }
                        }
                        .build()

                    chain.proceed(newRequest)
                }

            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            }

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClientBuilder.build())
                .build()

            return retrofit.create(ServiceFactory::class.java)
        }
    }

    @FormUrlEncoded
    @POST()
    fun getProfile(): Single<ProfileResponse>

}