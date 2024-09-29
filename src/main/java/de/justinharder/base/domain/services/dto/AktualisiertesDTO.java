package de.justinharder.base.domain.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.ws.rs.PathParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AktualisiertesDTO<T extends AktualisiertesDTO<T>> extends DTO<T>
{
	@PathParam("id")
	@JsonProperty(value = "id", required = true)
	private String id;
}
