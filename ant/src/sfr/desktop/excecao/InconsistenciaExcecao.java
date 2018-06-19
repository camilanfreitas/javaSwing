package gov.goias.sfr.desktop.excecao;

import gov.goias.excecao.NegocioExcecao;

/**
 * RegistroNaoEncontradoExcessao
 * Classe que representa a excessao genrica para a camada um registro pesquisado e no encontrado.
 *
 * @category Excessao
 *
 * @see NegocioExcecao
 *
 * @author Marcos Fernando.
 */
public class InconsistenciaExcecao extends NegocioExcecao {

    /**
     * Construtror para casos onde se aplica umm motivo fixo para lanar a exceo.
     *
     * @param motivo Motivo pelo qual a exceo foi lanada.
     */
    public InconsistenciaExcecao(final String motivo){
        super(motivo);
    }

}