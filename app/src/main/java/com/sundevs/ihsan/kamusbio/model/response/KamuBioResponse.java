package com.sundevs.ihsan.kamusbio.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.sundevs.ihsan.kamusbio.model.KamusItem;

public class KamuBioResponse{

	@SerializedName("city")
	private List<KamusItem> city;

	@SerializedName("status")
	private String status;

	public void setCity(List<KamusItem> city){
		this.city = city;
	}

	public List<KamusItem> getCity(){
		return city;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"KamuBioResponse{" + 
			"city = '" + city + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}