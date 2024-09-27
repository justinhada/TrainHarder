package de.justinharder.trainharder.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Token(@JsonProperty(required = true) String token)
{}
