package de.justinharder.dietharder.domain.services.dto.datensatz;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NeuerDatensatz extends NeuesDTO<NeuerDatensatz>
{
	@JsonProperty(value = "datum", required = true)
	private String datum;

	@JsonProperty(value = "koerpergewicht", required = true)
	private String koerpergewicht;

	@JsonProperty(value = "koerperfettAnteil", required = true)
	private String koerperfettAnteil;

	@JsonProperty(value = "benutzerId", required = true)
	private String benutzerId;
}
