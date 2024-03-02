package com.example.volley_juliangarrido_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.volley_juliangarrido_v2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        viewModel.getPersonajeLiveData().observe(this) { personaje ->
            Log.d("Rick&MortyAPI", "name: ${personaje.name}")
            binding.jokeTextView.text = personaje.name
            Log.d("Rick&MortyAPI", "image: ${personaje.image}")
            Glide.with(this).load(personaje.image).into(binding.iconImageView)
        }

        viewModel.getPersonaje(this)

        binding.pantallita.setOnClickListener {
            viewModel.newPersonaje(this)
        }
    }


}