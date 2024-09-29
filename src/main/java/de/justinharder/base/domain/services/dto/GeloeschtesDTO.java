package de.justinharder.base.domain.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.ws.rs.PathParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class GeloeschtesDTO<T extends GeloeschtesDTO<T>> extends DTO<T>
{
	@PathParam("id")
	@JsonProperty(value = "id", required = true)
	private String id;
}
