package com.sundevs.ihsan.kamusbio.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.sundevs.ihsan.kamusbio.model.Latin;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LatinResponse{

	@SerializedName("data")
	private List<Latin> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<Latin> data){
		this.data = data;
	}

	public List<Latin> getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"LatinResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}