package de.justinharder.base.domain.model.attribute;

import de.justinharder.old.domain.UUIDMapping;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.util.UUID;

@Getter
@Embeddable
@RequiredArgsConstructor
public class ID extends WertObjekt<UUID>
{
	@Serial
	private static final long serialVersionUID = -7279995861374733781L;

	@NonNull
	@Convert(converter = UUIDMapping.class)
	@Column(name = "ID", columnDefinition = "VARCHAR(36)", nullable = false)
	private UUID wert;

	public ID()
	{
		this(UUID.randomUUID());
	}

	public ID(@NonNull String wert)
	{
		this(UUID.fromString(wert));
	}
}
