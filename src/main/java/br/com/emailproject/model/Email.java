package br.com.emailproject.model;

public class Email {
	private String destinatario;
	private String assunto;
	private String corpoDoEmail;

	public Email(String destinatario, String assunto, String corpoDoEmail) {
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.corpoDoEmail = corpoDoEmail;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpoDoEmail() {
		return corpoDoEmail;
	}

	public void setCorpoDoEmail(String corpoDoEmail) {
		this.corpoDoEmail = corpoDoEmail;
	}

}
