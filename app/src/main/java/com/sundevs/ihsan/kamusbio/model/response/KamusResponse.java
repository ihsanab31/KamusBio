package com.sundevs.ihsan.kamusbio.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.sundevs.ihsan.kamusbio.model.Kamus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KamusResponse{

	@SerializedName("data")
	private List<Kamus> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<Kamus> data){
		this.data = data;
	}

	public List<Kamus> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"KamusResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}