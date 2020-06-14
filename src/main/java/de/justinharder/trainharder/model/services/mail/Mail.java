package de.justinharder.trainharder.model.services.mail;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail
{
	private MailAdresse sender;
	private List<MailAdresse> empfaenger;
	private List<MailAdresse> kopie;
	private List<MailAdresse> blindkopie;
	private String betreff;
	private String inhalt;
}
