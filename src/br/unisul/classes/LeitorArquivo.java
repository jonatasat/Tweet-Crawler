package br.unisul.classes;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import br.unisul.DAO.EntidadeDAO;
import br.unisul.javabean.Entidade;


public class LeitorArquivo {
	public static void ler(File arquivo){
		try{
			FileReader fr =new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			String linha = null;
			String propriedade=null;
			Entidade e;
			while((linha = br.readLine())!=null){
				if(linha.contains("]")){
					String[] info = linha.split("]");
					propriedade = info[0];
					propriedade = propriedade.substring(1);
				}else{
					String[] info2 = linha.split(";");
					e =new Entidade();
					e.setNome(info2[0]);
					e.setTipo(propriedade);
					EntidadeDAO.salvar(e);
				}
			}
			fr.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

