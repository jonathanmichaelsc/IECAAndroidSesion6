import com.example.apiapp.LoginRequest
import com.example.apiapp.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}

