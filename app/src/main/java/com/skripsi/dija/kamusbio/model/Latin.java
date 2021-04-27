package com.skripsi.dija.kamusbio.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Latin {

	@SerializedName("deskripsi_latin")
	private String deskripsiLatin;

	@SerializedName("foto")
	private String foto;

	@SerializedName("id_latin")
	private String idLatin;

	@SerializedName("nama_latin")
	private String namaLatin;

	@SerializedName("nama_indo")
	private String namaIndo;


	@Override
 	public String toString(){
		return 
			"Latin{" +
			"deskripsi_latin = '" + deskripsiLatin + '\'' + 
			",foto = '" + foto + '\'' + 
			",id_latin = '" + idLatin + '\'' + 
			",nama_latin = '" + namaLatin + '\'' + 
			",nama_indo = '" + namaIndo + '\'' + 
			"}";
		}
}