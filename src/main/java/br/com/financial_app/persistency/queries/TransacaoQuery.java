package br.com.financial_app.persistency.queries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Transacao;

public class TransacaoQuery implements IStrategyQuery {
	
	private Map<String,String> mapQuery;
	private Map<String,List<Object>> mapParameters;
	private Transacao transacao;
	private String tipoConsulta;
	private String NOME_CLASSE ="Transacao";
	
	public TransacaoQuery(EntidadeDominio entidade) {
		mapQuery = new HashMap<String,String>();
		mapParameters = new HashMap<String,List<Object>>();
		
		mapQuery.put("diaria", "from Transacao where data = :param1 "
				+ " order by id");
		
	}
	private void createSettingMaps(EntidadeDominio entidade) {
		
		List<Object> listConsultaDiaria = new ArrayList<Object>();
		mapQuery.put("diaria", "from Transacao where data = :param1 "
				+ " order by id");
		
		Date dataAtual = new Date();
		try {
			DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			dataAtual = formato.parse(formato.format(dataAtual));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.transacao.setData(dataAtual);
		listConsultaDiaria.add(this.transacao.getData());
		mapParameters.put("diaria", listConsultaDiaria);
	}
	
	@Override
	public void setMaps(EntidadeDominio entidade) {
		if(mapParameters.isEmpty()) {
			if(entidade.getClass().getSimpleName().equals(NOME_CLASSE)) {
				this.transacao = (Transacao) entidade;
				createSettingMaps(this.transacao);
			}
		}
	}
	
	@Override
	public String gerarString(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
		return mapQuery.get(tipoConsulta);
	}

	@Override
	public List<Object> retornoParametros() {
		return mapParameters.get(this.tipoConsulta);
	}







}
