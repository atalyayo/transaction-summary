package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BaseResponse<T> {

	@JsonProperty("data")
	private T data;
	
	@JsonProperty("metadata")
	@JsonInclude(JsonInclude.Include.NON_NULL)  
	private MetadataResponse metadata;

	
	public BaseResponse(T data) {
		super();
		this.data = data;
	}

}
