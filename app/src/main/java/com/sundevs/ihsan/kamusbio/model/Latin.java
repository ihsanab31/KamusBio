package com.sundevs.ihsan.kamusbio.model;

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

	public void setDeskripsiLatin(String deskripsiLatin){
		this.deskripsiLatin = deskripsiLatin;
	}

	public String getDeskripsiLatin(){
		return deskripsiLatin;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setIdLatin(String idLatin){
		this.idLatin = idLatin;
	}

	public String getIdLatin(){
		return idLatin;
	}

	public void setNamaLatin(String namaLatin){
		this.namaLatin = namaLatin;
	}

	public String getNamaLatin(){
		return namaLatin;
	}

	public void setNamaIndo(String namaIndo){
		this.namaIndo = namaIndo;
	}

	public String getNamaIndo(){
		return namaIndo;
	}

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