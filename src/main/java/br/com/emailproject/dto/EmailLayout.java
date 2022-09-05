package br.com.emailproject.dto;

import br.com.emailproject.model.Email;

public class EmailLayout {
    private final String QUEBRA_LINHA_DUPLA="<br><br>";
    
	public Email montarEmailAdm(String destinatario,String assunto) {
		StringBuilder texto=new StringBuilder();
		texto.
		append("A/C Administrador")
		.append(this.QUEBRA_LINHA_DUPLA);
		
		texto
		.append("Solicito alteração de senha do sistema!")
		.append(this.QUEBRA_LINHA_DUPLA);
		
		this.gerarAssinatura(texto);
		
		this.gerarRodape(texto);
		
		return new Email(destinatario,assunto,texto.toString());
	}
	
	
	private String gerarAssinatura(StringBuilder texto) {
		return texto.append("Ass: Michael Soares"+this.QUEBRA_LINHA_DUPLA).toString();
	}
	

	private String gerarRodape(StringBuilder texto) {
		return texto.append("Solicito alteração de senha do sistema!").toString();
	}
	
	
}
