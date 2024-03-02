package com.example.volley_juliangarrido_v2.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.volley_juliangarrido_v2.model.Personaje


class SharedViewModel : ViewModel() {
    private val personajeLiveData = MutableLiveData<Personaje>()
    private var randomNum = 1

    fun getPersonajeLiveData(): LiveData<Personaje> {
        return personajeLiveData
    }

    fun getPersonaje(context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://rickandmortyapi.com/api/character/$randomNum"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val name = response.getString("name")
                val imagen = response.getString("image")
                val jokeObject = Personaje(name, imagen)
                personajeLiveData.value = jokeObject
            },
            { error ->
                Log.e("Rick&MortyAPI", "Error: $error")
            }
        )

        queue.add(request)
    }

    fun newPersonaje(context: Context) {
        randomNum()
        getPersonaje(context)
    }

    fun randomNum() {
        randomNum = (1..183).random()
    }
}