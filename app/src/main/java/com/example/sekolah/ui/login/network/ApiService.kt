package com.example.sekolah.ui.login.network


import com.example.sekolah.ui.login.model.Delete_Murid.ResponseDeleteMurid
import com.example.sekolah.ui.login.model.EditMurid.ResponseEditMurid
import com.example.sekolah.ui.login.model.ResponseLogin
import com.example.sekolah.ui.login.model.TambahMurid.ResponseTambahMurid
import com.example.sekolah.ui.login.model.murid.ResponseMurid
import com.example.sekolah.ui.login.model.upload_gambar.ResponseUpload
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


//dari netshbean
interface ApiService {

    //GEt sesuaikan dengan funcation di controler
    //get_bisa_di_test_di_browser_,_kalau post tidak
    @GET("login_json")

    //value harus sama persis yang ada di get (controller), username:Srting
    //parameter : menampung yg akan dikirim
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
        //call alt+enter = call retrofit 2
    ): Call<ResponseLogin>

    @GET("datam_json")
    fun datam(): Call<ResponseMurid>

    @GET("delete_murid")
    fun deletemurid(@Query("id_murid") idmurid: String?): Call<ResponseDeleteMurid>

    @GET("edit_murid")
    fun editmurid(
        @Query("id_murid") id_murid: String?,
        @Query("nama_murid") nama_murid: String?,
        @Query("jk") jk: String?,
        @Query("kelas") kelas: String?,
        @Query("alamat")alamat:String?)
       :Call<ResponseEditMurid>

    @GET("tambah_murid")
    fun tambahmurid(
        @Query("id_murid") id_murid: String?,
        @Query("nama_murid") nama_murid: String?,
        @Query("jk") jk: String?,
        @Query("kelas") kelas: String?,
        @Query("alamat")alamat:String?)
            :Call<ResponseTambahMurid>

    @Multipart
        @POST("upload_file")
    fun uploadfile(
        @Part file: MultipartBody.Part,
        @Part("id_murid") id_murid: RequestBody,
        @Part("nama_murid") nama_murid: RequestBody,
        @Part("jk") jk: RequestBody,
        @Part("kelas") kelas: RequestBody,
        @Part("alamat") alamat: RequestBody
    ):Call<ResponseUpload>
}
