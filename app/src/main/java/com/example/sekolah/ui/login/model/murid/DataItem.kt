package com.example.sekolah.ui.login.model.murid

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Generated("com.robohorse.robopojogenerator")
data class DataItem(

	@field:SerializedName("nama_murid")
	val namaMurid: String? = null,

	@field:SerializedName("jk")
	val jk: String? = null,

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("id_murid")
	val idMurid: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
):Serializable
//agar data item bisa dikirim pakai intent