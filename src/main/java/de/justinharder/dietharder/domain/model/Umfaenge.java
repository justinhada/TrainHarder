package de.justinharder.dietharder.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.dietharder.domain.model.attribute.umfaenge.*;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serial;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Umfaenge extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 3865919704737224417L;

	@Setter
	@NonNull
	@Embedded
	private Datum datum;

	@Setter
	@NonNull
	@Embedded
	private HalsUmfang halsUmfang;

	@Setter
	@NonNull
	@Embedded
	private SchulterUmfang schulterUmfang;

	@Setter
	@NonNull
	@Embedded
	private BrustRueckenUmfang brustRueckenUmfang;

	@Setter
	@NonNull
	@Embedded
	private LinkerOberarmUmfang linkerOberarmUmfang;

	@Setter
	@NonNull
	@Embedded
	private RechterOberarmUmfang rechterOberarmUmfang;

	@Setter
	@NonNull
	@Embedded
	private LinkerUnterarmUmfang linkerUnterarmUmfang;

	@Setter
	@NonNull
	@Embedded
	private RechterUnterarmUmfang rechterUnterarmUmfang;

	@Setter
	@NonNull
	@Embedded
	private BauchUmfang bauchUmfang;

	@Setter
	@NonNull
	@Embedded
	private HueftUmfang hueftUmfang;

	@Setter
	@NonNull
	@Embedded
	private LinkerOberschenkelUmfang linkerOberschenkelUmfang;

	@Setter
	@NonNull
	@Embedded
	private RechterOberschenkelUmfang rechterOberschenkelUmfang;

	@Setter
	@NonNull
	@Embedded
	private LinkerUnterschenkelUmfang linkerUnterschenkelUmfang;

	@Setter
	@NonNull
	@Embedded
	private RechterUnterschenkelUmfang rechterUnterschenkelUmfang;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public Umfaenge(
		ID id,
		@NonNull Datum datum,
		@NonNull HalsUmfang halsUmfang,
		@NonNull SchulterUmfang schulterUmfang,
		@NonNull BrustRueckenUmfang brustRueckenUmfang,
		@NonNull LinkerOberarmUmfang linkerOberarmUmfang,
		@NonNull RechterOberarmUmfang rechterOberarmUmfang,
		@NonNull LinkerUnterarmUmfang linkerUnterarmUmfang,
		@NonNull RechterUnterarmUmfang rechterUnterarmUmfang,
		@NonNull BauchUmfang bauchUmfang,
		@NonNull HueftUmfang hueftUmfang,
		@NonNull LinkerOberschenkelUmfang linkerOberschenkelUmfang,
		@NonNull RechterOberschenkelUmfang rechterOberschenkelUmfang,
		@NonNull LinkerUnterschenkelUmfang linkerUnterschenkelUmfang,
		@NonNull RechterUnterschenkelUmfang rechterUnterschenkelUmfang,
		@NonNull Benutzer benutzer)
	{
		super(id);
		this.datum = datum;
		this.halsUmfang = halsUmfang;
		this.schulterUmfang = schulterUmfang;
		this.brustRueckenUmfang = brustRueckenUmfang;
		this.linkerOberarmUmfang = linkerOberarmUmfang;
		this.rechterOberarmUmfang = rechterOberarmUmfang;
		this.linkerUnterarmUmfang = linkerUnterarmUmfang;
		this.rechterUnterarmUmfang = rechterUnterarmUmfang;
		this.bauchUmfang = bauchUmfang;
		this.hueftUmfang = hueftUmfang;
		this.linkerOberschenkelUmfang = linkerOberschenkelUmfang;
		this.rechterOberschenkelUmfang = rechterOberschenkelUmfang;
		this.linkerUnterschenkelUmfang = linkerUnterschenkelUmfang;
		this.rechterUnterschenkelUmfang = rechterUnterschenkelUmfang;
		this.benutzer = benutzer;
	}
}
