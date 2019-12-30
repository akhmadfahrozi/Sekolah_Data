package com.example.sekolah.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sekolah.*
import com.example.sekolah.ui.login.model.ResponseLogin
import com.example.sekolah.ui.login.network.NetworkModule
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_upload_gambar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tambah -> {
                val pindah = Intent(this@LoginActivity, Tambahmurid::class.java)
                startActivity(pindah)
            }
            R.id.tambahgambar -> {
                val pindah = Intent(this@LoginActivity, uploadActivity::class.java)
                startActivity(pindah)
            }
        }
    }


    lateinit var tambah: Button
    lateinit var tambahgmbr: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        val masuk = findViewById(R.id.login) as Button
        tambah = findViewById(R.id.tambah)
        tambahgmbr= findViewById(R.id.tambahgambar)

        tambah.setOnClickListener(this)
        tambahgmbr.setOnClickListener(this)

        masuk.setOnClickListener {
            masuk(username.text.toString(), password.text.toString())
            masuk.setError("AKUN BELUM TERDAFTAR")
            masuk.requestFocus()
        }
    }


    private fun masuk(username: String, password: String) {
        NetworkModule.getService().login(username, password)
            .enqueue(object : Callback<ResponseLogin> {
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {

                }


                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful) {
                        var data = response.body()?.data
                        data?.forEach {
                            val pindah = Intent(this@LoginActivity, Murid::class.java)
                            startActivity(pindah)
//                            Toast.makeText(this@LoginActivity, it.nama, Toast.LENGTH_SHORT).show()
                        }

                    } else {

                    }

                }

            })

    }
}


