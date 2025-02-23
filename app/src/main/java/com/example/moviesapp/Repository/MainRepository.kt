package com.example.moviesapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.Domain.FilmItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase= FirebaseDatabase.getInstance()

    //MutableLiveData--> UI'nın otomatik olarak güncellenmesini sağlayan canlı veri (LiveData) nesnesidir.
    fun loadUpcoming():LiveData<MutableList<FilmItemModel>> {
       val listData= MutableLiveData<MutableList<FilmItemModel>>()
        val ref= firebaseDatabase.getReference("Upcomming")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<FilmItemModel>()
                for (childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue(FilmItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value= lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO()
            }
        })
        return listData
    }


    fun loadItems():LiveData<MutableList<FilmItemModel>> {
        val listData= MutableLiveData<MutableList<FilmItemModel>>()
        val ref= firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<FilmItemModel>()
                for (childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue(FilmItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value= lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO()
            }
        })
        return listData
    }

}