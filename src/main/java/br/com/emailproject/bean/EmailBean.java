package br.com.emailproject.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.emailproject.dto.EmailLayout;
import br.com.emailproject.model.Email;
import br.com.emailproject.service.EmailService;

@Named
@RequestScoped
public class EmailBean implements Serializable{
	private static final long serialVersionUID = 4538755582654584073L;
	@Inject
	private EmailService emailService;
	private final String DESTINATARIO="cursojavajdev@gmail.com";
	private final String ASSUNTO="Teste do Email service";
	
	public String enviarEmail() {
		emailService.enviarEmail(montarEmail());
		return null;
	}
	
	private Email montarEmail() {
		EmailLayout layout=new EmailLayout();
		return layout.montarEmailAdm(DESTINATARIO,ASSUNTO);
	}

}
