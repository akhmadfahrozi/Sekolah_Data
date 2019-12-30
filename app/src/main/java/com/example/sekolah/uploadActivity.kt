package com.example.sekolah

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.example.sekolah.ui.login.model.upload_gambar.ResponseUpload
import com.example.sekolah.ui.login.network.NetworkModule
import kotlinx.android.synthetic.main.activity_upload_gambar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class uploadActivity : upload_gambar() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_gambar)

        upload_file.setOnClickListener{
            if (flagUpload)
                doUploadFile()
            else
                Toast.makeText(this,"Anda belum memiliki berkas",Toast.LENGTH_SHORT).show()
        }


        filePicker.setOnClickListener {
            openFilePicker()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILEPICKERREQ && resultCode == Activity.RESULT_OK) {
            data?.data?.let { getMetaData(it, imagePreview) }
            flagUpload = true
        }
    }

    private fun decodeImageToByte(): ByteArray {
        val bmp = (imagePreview.drawable as BitmapDrawable).bitmap

        //buat object baru untuk
        val bs = ByteArrayOutputStream()

        //convert draweble dari image view ke jpg dengan quality 100
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bs)
        val imageInBye = bs.toByteArray()
        return imageInBye
    }

    private fun doUploadFile() {
        val files = RequestBody.create(MediaType.parse("image/*"), decodeImageToByte())
        val imageInput = MultipartBody.Part.createFormData(
            "file", "${System.nanoTime()}.jpg",
            files
        )

        val id_murid = RequestBody.create(MediaType.parse("text/plain"), id_Murid1.text.toString())
        val nama_murid =
            RequestBody.create(MediaType.parse("text/plain"), namamurid.text.toString())
        val jk = RequestBody.create(MediaType.parse("text/plain"), jk1.text.toString())
        val kelas = RequestBody.create(MediaType.parse("text/plain"), kelas1.text.toString())
        val alamat = RequestBody.create(MediaType.parse("text/plain"), alamat1.text.toString())

        NetworkModule.getService()
            .uploadfile(imageInput, id_murid, nama_murid, jk, kelas, alamat)
            .enqueue(object : Callback<ResponseUpload> {
                override fun onFailure(call: Call<ResponseUpload>, t: Throwable) {
                    Toast.makeText(this@uploadActivity, t.message.toString(), Toast.LENGTH_SHORT)
                        .show()

                }

                override fun onResponse(
                    call: Call<ResponseUpload>,
                    response: Response<ResponseUpload>
                ) {
                    if (response.isSuccessful) {
                        val code = response.body()?.code
                        if (code == 200) {
                            Toast.makeText(
                                this@uploadActivity,
                                response.body()?.message.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()

                        } else {
                            Toast.makeText(
                                this@uploadActivity,
                                response.body()?.message.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                    } else {
                        Toast.makeText(
                            this@uploadActivity,
                            response.errorBody()?.toString(),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }


            })
    }
}