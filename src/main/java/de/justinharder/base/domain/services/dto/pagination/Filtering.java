package de.justinharder.base.domain.services.dto.pagination;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public abstract class Filtering<T extends GespeichertesDTO<T>>
{}
