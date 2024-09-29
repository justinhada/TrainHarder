package de.justinharder.dietharder.domain.services.dto.datensatz;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AktualisierterDatensatz<T extends AktualisierterDatensatz<T>> extends AktualisiertesDTO<T>
{
	@JsonProperty(value = "datum", required = true)
	private String datum;

	@JsonProperty(value = "koerpergewicht", required = true)
	private String koerpergewicht;

	@JsonProperty(value = "koerperfettAnteil", required = true)
	private String koerperfettAnteil;
}
