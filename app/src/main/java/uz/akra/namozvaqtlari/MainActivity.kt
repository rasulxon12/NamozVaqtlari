package uz.akra.namozvaqtlari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.akra.namozvaqtlari.databinding.ActivityMainBinding
import uz.akra.namozvaqtlari.models.MyNVKunlik
import uz.akra.namozvaqtlari.retrofit.APIClient
import kotlin.math.log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

    }
}