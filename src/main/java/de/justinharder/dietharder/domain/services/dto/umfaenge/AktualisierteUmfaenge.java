package de.justinharder.dietharder.domain.services.dto.umfaenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AktualisierteUmfaenge extends AktualisiertesDTO<AktualisierteUmfaenge>
{
	@JsonProperty(value = "datum", required = true)
	private String datum;

	@JsonProperty(value = "halsUmfang", required = true)
	private String halsUmfang;

	@JsonProperty(value = "schulterUmfang", required = true)
	private String schulterUmfang;

	@JsonProperty(value = "brustRueckenUmfang", required = true)
	private String brustRueckenUmfang;

	@JsonProperty(value = "linkerOberarmUmfang", required = true)
	private String linkerOberarmUmfang;

	@JsonProperty(value = "rechterOberarmUmfang", required = true)
	private String rechterOberarmUmfang;

	@JsonProperty(value = "linkerUnterarmUmfang", required = true)
	private String linkerUnterarmUmfang;

	@JsonProperty(value = "rechterUnterarmUmfang", required = true)
	private String rechterUnterarmUmfang;

	@JsonProperty(value = "bauchUmfang", required = true)
	private String bauchUmfang;

	@JsonProperty(value = "hueftUmfang", required = true)
	private String hueftUmfang;

	@JsonProperty(value = "linkerOberschenkelUmfang", required = true)
	private String linkerOberschenkelUmfang;

	@JsonProperty(value = "rechterOberschenkelUmfang", required = true)
	private String rechterOberschenkelUmfang;

	@JsonProperty(value = "linkerUnterschenkelUmfang", required = true)
	private String linkerUnterschenkelUmfang;

	@JsonProperty(value = "rechterUnterschenkelUmfang", required = true)
	private String rechterUnterschenkelUmfang;
}
