package br.com.apifoodeducation.service;

import java.util.ArrayList;
import java.util.List;

import br.com.apifoodeducation.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicasAlimentaresService {

//	@Autowired
//    private EdamamClient edamamClient;
	@Autowired
	private RefeicaoDiariaService refeicaoDiariaService;

    public List<DicaAlimentar> sugerirDicasAlimentares(UsuarioComum usuarioComum, List<ConsumoAlimento> consumoAlimentos) {
        List<DicaAlimentar> dicas = new ArrayList<>();

        // Obter informações nutricionais dos alimentos informados pelo usuário
        for (ConsumoAlimento consumo: consumoAlimentos) {
//        	System.out.println(consumo.getAlimento().getNomeAlimento());
            RefeicaoDiaria informacoesNutricionais =  refeicaoDiariaService.getRefeicaoDiariaByDescricaoAlimentos(consumo.getAlimento().getDescricaoAlimentos());
//            Alimento informacoesNutricionais =  alimentoService.getAlimentoById(consumo.getAlimento().getId());
//            System.out.println(informacoesNutricionais.getNomeAlimento());
            String descricao = gerarDescricaoDica(informacoesNutricionais, usuarioComum);
            dicas.add(new DicaAlimentar(descricao));
        }

        return dicas;
    }

    private String gerarDescricaoDica(RefeicaoDiaria refeicaoDiaria, UsuarioComum usuarioComum) {
        StringBuilder descricao = new StringBuilder("Dica para " + refeicaoDiaria.getDescricaoAlimentos() + ": ");

        // Analisar as informações nutricionais do alimento e gerar dicas com base nelas
        if (refeicaoDiaria != null && ! refeicaoDiaria.equals("")) {
            double calorias = (double) refeicaoDiaria.getCalorias();
            double proteinas = (double) refeicaoDiaria.getProteinas();
            double gorduras = (double) refeicaoDiaria.getLipideos();
            double carboidratos = (double) refeicaoDiaria.getCarboidratos();

            // Considerar recomendações específicas com base na idade, sexo e altura do usuário
            if (usuarioComum.getIdade() > 50) {
                 descricao.append("Como você tem mais de 50 anos, é importante priorizar alimentos ricos em cálcio e fibras. ");
            }
            if (usuarioComum.getSexo().equals("feminino") && usuarioComum.getIdade() >= 18 && usuarioComum.getIdade() <= 50) {
                descricao.append("Mulheres em idade fértil devem garantir uma ingestão adequada de ferro e ácido fólico. ");
            }
            if (usuarioComum.getAltura() > 180) {
                descricao.append("Pessoas com altura elevada geralmente precisam de uma ingestão calórica maior para manter o peso. ");
            }
            // Adicionar dicas baseadas nas propriedades nutricionais do alimento
            if (calorias > 300) {
                descricao.append("Este alimento é rico em calorias, consuma com moderação. ");
            }
            if (proteinas < 10) {
                descricao.append("Adicione outras fontes de proteína à sua dieta para garantir um bom equilíbrio nutricional. ");
            }
            if (gorduras > 15) {
                descricao.append("Atenção às gorduras deste alimento, prefira opções com baixo teor de gordura. ");
            }
            if (carboidratos > 30) {
                descricao.append("Consuma este alimento com cautela se estiver controlando a ingestão de carboidratos. ");
            }
        }

        // Se nenhuma dica específica for gerada, fornecer uma dica genérica
        if (descricao.toString().equals("Dica para " + refeicaoDiaria.getDescricaoAlimentos() + ": ")) {
            descricao.append("Incluir este alimento na sua dieta é uma ótima fonte de nutrientes!");
        }

        return descricao.toString();
    }
}
