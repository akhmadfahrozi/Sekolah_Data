package com.example.sekolah

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sekolah.ui.login.model.Delete_Murid.ResponseDeleteMurid
import com.example.sekolah.ui.login.model.murid.DataItem
import com.example.sekolah.ui.login.model.murid.ResponseMurid
import com.example.sekolah.ui.login.network.NetworkModule
import kotlinx.android.synthetic.main.activity_murid.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Murid : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_murid)

        NetworkModule.getService().datam()
            .enqueue(object : Callback<ResponseMurid> {
                override fun onFailure(call: Call<ResponseMurid>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponseMurid>,
                    response: Response<ResponseMurid>
                ) {
                    if (response.isSuccessful) {//menambah object DELETE
                        var data = response.body()?.data
                        var adapter = Murid_Adapter(
                            this@Murid,
                            data!!,
                            object : Murid_Adapter.OnmuridClickListener {
                                override fun onClick(list: DataItem, position: Int) {
                                    alert {
                                        positiveButton("Edit") {
                                            val intent = Intent(this@Murid, Edit_murid::class.java)
                                            intent.putExtra("murid", list)
                                            startActivity(intent)
                                        }
                                        negativeButton("hapus") { deletemurid(list) }
                                    }.show()
                                }

                            })
                        recyclerViewMurid.adapter = adapter
                        recyclerViewMurid.layoutManager = LinearLayoutManager(this@Murid)
                    } else {

                    }
                }
            })
    }

    //penambahan delete
    private fun deletemurid(dataitem: DataItem) {
        NetworkModule.getService().deletemurid(dataitem.idMurid)
            .enqueue(object : Callback<ResponseDeleteMurid> {
                override fun onFailure(call: Call<ResponseDeleteMurid>, t: Throwable) {


                }

                override fun onResponse(
                    call: Call<ResponseDeleteMurid>,
                    response: Response<ResponseDeleteMurid>
                ) {
                    toast("data murid berhasil di hapus")


                }


            })

    }

}

