package de.justinharder.dietharder.domain.services.dto.hautfaltendicke;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AktualisierteHautfaltendicke extends AktualisiertesDTO<AktualisierteHautfaltendicke>
{
	@JsonProperty(value = "datum", required = true)
	private String datum;

	@JsonProperty(value = "brustfalte", required = true)
	private int brustfalte;

	@JsonProperty(value = "bauchfalte", required = true)
	private int bauchfalte;

	@JsonProperty(value = "beinfalte", required = true)
	private int beinfalte;

	@JsonProperty(value = "hueftfalte", required = true)
	private int hueftfalte;

	@JsonProperty(value = "achselfalte", required = true)
	private int achselfalte;

	@JsonProperty(value = "trizepsfalte", required = true)
	private int trizepsfalte;

	@JsonProperty(value = "rueckenfalte", required = true)
	private int rueckenfalte;
}
