package de.justinharder.dietharder.domain.services.dto.hautfaltendicke;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NeueHautfaltendicke extends NeuesDTO<NeueHautfaltendicke>
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

	@JsonProperty(value = "benutzerId", required = true)
	private String benutzerId;
}
