package com.sundevs.ihsan.kamusbio.model.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse{

	@SerializedName("nama")
	private String nama;

	@SerializedName("success")
	private int success;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("message")
	private String message;

	@SerializedName("nomor_hp")
	private String nomorHp;

	@SerializedName("token")
	private String token;


	@Override
 	public String toString(){
		return 
			"UserResponse{" + 
			"nama = '" + nama + '\'' + 
			",success = '" + success + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",message = '" + message + '\'' + 
			",nomor_hp = '" + nomorHp + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}