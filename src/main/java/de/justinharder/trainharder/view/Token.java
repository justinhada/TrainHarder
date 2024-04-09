package de.justinharder.trainharder.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Token(@JsonProperty(required = true) String token)
{}
