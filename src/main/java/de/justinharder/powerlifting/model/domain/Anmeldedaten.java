package de.justinharder.powerlifting.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@NoArgsConstructor
@Entity
public class Anmeldedaten extends Entitaet
{
	private static final long serialVersionUID = 1607570632256351984L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mail;
	private String benutzername;
	private String passwort;
	@OneToOne
	@JoinColumn(nullable = false)
	private Benutzer benutzer;
}
