package com.example.sekolah

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sekolah.ui.login.model.EditMurid.ResponseEditMurid
import com.example.sekolah.ui.login.model.murid.DataItem
import com.example.sekolah.ui.login.network.NetworkModule
import kotlinx.android.synthetic.main.activity_edit_murid.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Edit_murid : AppCompatActivity() {

    private var jk = "L"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_murid)

        var murid = intent.getSerializableExtra("murid") as DataItem
        et_idMurid.setText(murid.idMurid)
        et_namaMurid.setText(murid.namaMurid)
        et_kelas.setText(murid.kelas)
        et_alamat.setText(murid.alamat)

        radio_jk.setOnCheckedChangeListener { group, checkedid ->
            when (checkedid) {
                R.id.radio_laki -> {
                    jk = "L"
                }
                R.id.radio_perempuan -> {
                    jk = "P"
                }
            }
        }
        Log.i("id_murid", murid.idMurid)

        if (murid.jk.equals("L", true)) {
            radio_jk.check(R.id.radio_laki)
        } else {
            radio_jk.check(R.id.radio_perempuan)
        }
        val button_simpan = findViewById<Button>(R.id.button_simpan)
        button_simpan.setOnClickListener {
            simpan(
                et_idMurid.text.toString(), et_namaMurid.text.toString(),
                jk, et_kelas.text.toString(), et_alamat.text.toString()
            )
        }

    }

    private fun simpan(//di samakan di Edit text
        idMurid: String?,
        namaMurid: String?,
        jk: String?,
        kelas: String?,
        alamat: String?
    ) {
        NetworkModule.getService().editmurid(
            idMurid, namaMurid, jk, kelas, alamat
        ).enqueue(object : Callback<ResponseEditMurid> {
            override fun onFailure(call: Call<ResponseEditMurid>, t: Throwable) {


            }

            override fun onResponse(
                call: Call<ResponseEditMurid>,
                response: Response<ResponseEditMurid>
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
