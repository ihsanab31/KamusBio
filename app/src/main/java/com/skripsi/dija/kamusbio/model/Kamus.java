package com.skripsi.dija.kamusbio.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Kamus {

	@SerializedName("foto")
	private String foto;

	@SerializedName("id_kamus")
	private String idKamus;

	@SerializedName("nama_kamus")
	private String namaKamus;

	@SerializedName("latin")
	private String latin;

	@SerializedName("deskripsi")
	private String deskripsi;

	@Override
 	public String toString(){
		return 
			"Kamus{" +
			"foto = '" + foto + '\'' + 
			",id_kamus = '" + idKamus + '\'' + 
			",nama_kamus = '" + namaKamus + '\'' + 
			",latin = '" + latin + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			"}";
		}
}