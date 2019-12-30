package com.example.sekolah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.sekolah.ui.login.model.TambahMurid.ResponseTambahMurid
import com.example.sekolah.ui.login.model.murid.DataItem
import com.example.sekolah.ui.login.network.NetworkModule
import kotlinx.android.synthetic.main.activity_edit_murid.*
import kotlinx.android.synthetic.main.activity_tambahmurid.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Tambahmurid : AppCompatActivity() {

    private var jk = "L"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambahmurid)
//        var murid = intent.getSerializableExtra("murid") as DataItem
//        et_idMurid1.setText(murid.idMurid)
//        et_namaMurid1.setText(murid.namaMurid)-------------------------->KHUSUS EDITTTTTT
//        et_kelas1.setText(murid.kelas)
//        et_alamat1.setText(murid.alamat)

        radio_jk1.setOnCheckedChangeListener { group, checkedid ->
            when (checkedid) {
                R.id.radio_laki1 -> {
                    jk = "L"
                }
                R.id.radio_perempuan1 -> {
                    jk = "P"
                }
            }
        }
//        Log.i("id_murid", murid.idMurid) ---------------->
//
//        if (murid.jk.equals("L", true)) {
//            radio_jk.check(R.id.radio_laki1)--------------------->KHUSUS EDIT
//        } else {
//            radio_jk.check(R.id.radio_perempuan1)
//        }
        val button_simpan = findViewById<Button>(R.id.button_simpan1)
        button_simpan.setOnClickListener {
            simpan(
                et_idMurid1.text.toString(), et_namaMurid1.text.toString(),
                jk, et_kelas1.text.toString(), et_alamat1.text.toString()
            )
        }

    }

    private fun simpan(//di samakan di ------------Data item Murid
        idMurid: String?,
        namaMurid: String?,
        jk: String?,
        kelas: String?,
        alamat: String?
    ) {
        NetworkModule.getService().tambahmurid(
            idMurid, namaMurid, jk, kelas, alamat
        ).enqueue(object : Callback<ResponseTambahMurid> {
            override fun onFailure(call: Call<ResponseTambahMurid>, t: Throwable) {


            }

            override fun onResponse(
                call: Call<ResponseTambahMurid>,
                response: Response<ResponseTambahMurid>
            ) {
                if (response.isSuccessful) {
                    var data = response.body()?.message
                    toast(data.toString())
                } else {

                }
            }

        })
    }


}
