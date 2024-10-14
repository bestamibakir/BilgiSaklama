package com.bestamibakir.bilgisaklama

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bestamibakir.bilgisaklama.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    var alinanKullaniciIsmi : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = this.getSharedPreferences("com.bestamibakir.bilgisaklama",
            Context.MODE_PRIVATE)

        alinanKullaniciIsmi = sharedPreferences.getString("isim","")
        if(alinanKullaniciIsmi == "")
            binding.textView.text = "Kaydedilen İsim :"
        else
            binding.textView.text = "Kaydedilen İsim : ${alinanKullaniciIsmi}"
    }

    fun kaydet(view : View){
        val kullaniciIsmi = binding.editText.text.toString()
        if(kullaniciIsmi == "")
            Toast.makeText(this@MainActivity,"Lütfen İsim Giriniz",Toast.LENGTH_LONG).show()
        else{
            sharedPreferences.edit().putString("isim",kullaniciIsmi).apply()
            binding.textView.text = "Kaydedilen İsim : ${kullaniciIsmi}"
        }

    }

    fun sil(view : View){
        alinanKullaniciIsmi = sharedPreferences.getString("isim","")
        if (alinanKullaniciIsmi != "") {
            sharedPreferences.edit().remove("isim").apply()
            binding.textView.text = "Kaydedilen İsim :"
        }
    }
}