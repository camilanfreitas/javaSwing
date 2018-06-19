package gov.goias.sfr.desktop.gui;

import gov.goias.excecao.InfraExcecao;
import gov.goias.sfr.desktop.excecao.InconsistenciaExcecao;
import gov.goias.sfr.desktop.negocio.ConfiguracaoNegocio;
import gov.goias.sfr.excecao.NaoEncontradoExcecao;
import org.apache.log4j.Logger;

import javax.swing.*;

/**
 * Classe que inicia o fluxo principal do sistema.
 */
public abstract class Principal {
    private static Logger log = Logger.getLogger(Principal.class);
    /**
     * Método Inicial do sistema, valida o ambiente e logo em seguida inicializa a inteface gráfica.
     * @param args  Argumentos básicos (Não utilizados apenas existem pelo padrâo exigido.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
        } catch (Exception e) {
            log.error(e);
        }
        try {
            ConfiguracaoNegocio.getInstancia().validarAmbiente();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new FrFrequencia().setVisible(true);
                }
            });
        }catch(InconsistenciaExcecao e) {
            abortar(e);
        }catch(NaoEncontradoExcecao e){
            abortar(e);
        } catch (InfraExcecao e) {
            abortar(e);
        }catch(Exception e){
            abortar(e);
        }
    }

    private static void abortar(Exception e){
        log.error(e);
        JOptionPane.showMessageDialog(null, "Falha Grave !!! \n" + e.getMessage(), " SFR - Sistema de frequência", JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
    }

}