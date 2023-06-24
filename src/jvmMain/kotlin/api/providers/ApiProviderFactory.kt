package api.providers

import api.PointingPokerApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import provider.ProviderFactory
import retrofit2.Retrofit

class JsonProviderFactory : ProviderFactory<Json> {
    override fun get(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }
}

class OkHttpClientProviderFactory: ProviderFactory<OkHttpClient> {
    override fun get(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}

class RetrofitBuilderProviderFactory(
    private val okHttpClient: OkHttpClient = OkHttpClientProviderFactory().get(),
    private val json: Json = JsonProviderFactory().get()
) : ProviderFactory<Retrofit.Builder> {
    override fun get(): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
    }
}

class RetrofitProviderFactory(
    private val baseUrl: HttpUrl = HttpUrl.get(BASE_URL),
    private val builder: Retrofit.Builder = RetrofitBuilderProviderFactory().get()
): ProviderFactory<Retrofit> {
    override fun get(): Retrofit {
        return builder.baseUrl(baseUrl)
            .build()
    }

    companion object {
        private const val BASE_URL = "https://www.pointingpoker.com/"
    }
}

class ApiProviderFactory(
    private val retrofit: Retrofit = RetrofitProviderFactory().get()
): ProviderFactory<PointingPokerApi> {
    override fun get(): PointingPokerApi {
        return  retrofit.create(PointingPokerApi::class.java)
    }
}