package com.skripsi.dija.kamusbio.model;

import com.google.gson.annotations.SerializedName;

public class KamusItem {

	@SerializedName("foto")
	private String foto;

	@SerializedName("id_kamus")
	private int idKamus;

	@SerializedName("nama_kamus")
	private String namaKamus;

	@SerializedName("latin")
	private String latin;

	@SerializedName("deskripsi")
	private String deskripsi;

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setIdKamus(int idKamus){
		this.idKamus = idKamus;
	}

	public int getIdKamus(){
		return idKamus;
	}

	public void setNamaKamus(String namaKamus){
		this.namaKamus = namaKamus;
	}

	public String getNamaKamus(){
		return namaKamus;
	}

	public void setLatin(String latin){
		this.latin = latin;
	}

	public String getLatin(){
		return latin;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	@Override
 	public String toString(){
		return 
			"CityItem{" + 
			"foto = '" + foto + '\'' + 
			",id_kamus = '" + idKamus + '\'' + 
			",nama_kamus = '" + namaKamus + '\'' + 
			",latin = '" + latin + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			"}";
		}
}