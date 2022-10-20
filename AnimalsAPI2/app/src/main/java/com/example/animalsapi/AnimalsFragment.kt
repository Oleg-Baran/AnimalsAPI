package com.example.animalsapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsapi.data.Animal
import com.example.animalsapi.helpers.DataFromAPI
import com.example.animalsapi.helpers.RecyclerAdapter
import com.example.animalsapi.interfaces.DataAPI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class AnimalsFragment : Fragment() {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
    private var animalArrayList = arrayListOf<Animal>()
    private var dataFromAPI: DataFromAPI = DataFromAPI()
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       dataRequest()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        val animalsList = view.findViewById<RecyclerView>(R.id.recyclerView)
        animalsList.layoutManager = layoutManager
        animalsList.setHasFixedSize(true)
        adapter = RecyclerAdapter(animalArrayList)
        animalsList.adapter = adapter
    }

    private fun dataRequest() {
        dataFromAPI.getDataFromAPI()
        dataFromAPI.setData( object : DataAPI {
            @SuppressLint("NotifyDataSetChanged")
            override fun getDataOkHttp(result: String?) {
                val gson = Gson()
                val typeToken = object : TypeToken<List<Animal>>() {}.type
                val animal = gson.fromJson<List<Animal>>(result, typeToken)
                animalArrayList.addAll(animal)
                handler.post { adapter.notifyDataSetChanged() }
            }
            override fun getDataRetrofit(result: Response<MutableList<Animal>>) {
                animalArrayList.addAll(result.body() as List<Animal>)
            }
        } )
    }
}