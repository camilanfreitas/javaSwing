package gov.goias.sfr.desktop.gui;

import gov.goias.excecao.InfraExcecao;
import gov.goias.sfr.desktop.dao.Repositorio;
import gov.goias.sfr.desktop.entidade.Biometria;
import gov.goias.sfr.desktop.excecao.InconsistenciaExcecao;
import gov.goias.sfr.desktop.negocio.BiometriaNegocio;
import gov.goias.sfr.desktop.negocio.ConfiguracaoNegocio;
import gov.goias.sfr.desktop.negocio.FrequenciaNegocio;
import gov.goias.sfr.desktop.util.CaixaTexto;
import gov.goias.sfr.desktop.util.PropriedadeUtil;
import gov.goias.sfr.excecao.NaoEncontradoExcecao;
import gov.goias.sfr.excecao.ValidacaoFrequenciaExcecao;
import gov.goias.sfr.service.ServicoGriaule;
import gov.goias.sfr.service.ServicoProxy;
import gov.goias.util.CircuitBrakerStates;
import org.apache.log4j.Logger;
import gov.goias.sfr.desktop.util.DataUtil;
import gov.goias.sfr.desktop.util.ImagemUtil;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FrFrequencia extends JFrame implements Observer{
    private final Logger log = Logger.getLogger(FrFrequencia.class);
    private static final Color corNormal = new Color(30, 88, 131);
    private static final Color corOk     = new Color(54, 116, 54);
    private static final Color corErro   = new Color(146, 30, 28);
    private static final int JANELA_INICIO = 1130;
    private static final int JANELA_FIM = 1230;

    private Color corAtual = corNormal;
    private Timer tmMensagem     = null;
    private Timer tmNaoEncontrado= null;
    private Timer tmNormal       = null;
    private JLabel lbHora        = null;
    private JLabel lbData        = null;
    private JLabel lbNome        = null;
    private JLabel lbStatus      = null;
    private JLabel lbImgAguarde     = null;
    private JLabel lbAguarde = null;
    private JLabel lbInstrucao   = null;
    private JLabel lbMensagem    = null;
    private JLabel lbStatusTexto = null;
    private JPanel pnExterno  = null;
    private JPanel pnInterno  = null;
    private JPanel pnInicio      = null;
    private JPanel pnResultado   = null;
    private JTextField txCpf     = null;
    private String modoOperacao = null;
    private Image background = null;     
    private Dimension dimensaoMinima = null;
    private JPanel pnFundo = null;
    private JLabel lbAtencao = null;
    private ScheduledExecutorService agendador;
    private Calendar dataHoraLocal;
    private SimpleDateFormat sdf;

    public FrFrequencia()  {
        try {
            setTitle("SFR - Sistema de Frequência " + getVersao());
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            background = ImagemUtil.obterImagem("imagem/fundo.jpg", Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height).getImage();
            pnExterno = new JPanel(){
                public void paintComponent(final Graphics g) {
                    g.drawImage(background, 0, 0, this);
                }
            };
            pnExterno.setLayout(null);
            setLayout(null);
            setContentPane(pnExterno);
            setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
            setUndecorated(true);
            modoOperacao = System.getProperty(ConfiguracaoNegocio.MODO_OPERACAO);
            dimensaoMinima = new Dimension(908, 718);
            construirPainelInterno();
            construirPainelHorario();
            construirPainelResultado();
            construirPainelInicio();
            construirPainelAguarde();
            construirPainelFundo();//Deve ser o ultimpo painel a ser construido
            tmNormal = new Timer(1600, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    estadoInicial();
                }
            });
            tmMensagem = new Timer(3000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    estadoInicial();
                }
            });
            tmNaoEncontrado = new Timer(800, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    estadoInicial();
                }
            });
            estadoInicial();
            dataHoraLocal = Calendar.getInstance();            
            construirAutomacao();
            ServicoProxy.getInstance().addObserver(this);

        }catch(Exception e){
            log.error(e);
            lbAtencao.setVisible(true);
        }
    }

    public void construirPainelFundo(){
        pnFundo = new JPanel(){
            public void paintComponent(final Graphics grafico) {
                final Graphics2D g = (Graphics2D) grafico;
                final RoundRectangle2D r = new RoundRectangle2D.Float(0,0,(int)dimensaoMinima.getWidth(),(int)dimensaoMinima.getHeight(),50,50);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(corAtual);
                g.fill(r);
                g.dispose();
            }
        };
        pnFundo.setBounds(0,0,(int)dimensaoMinima.getWidth(),(int)dimensaoMinima.getHeight());
        pnInterno.add(pnFundo);
        pnInterno.setComponentZOrder(pnFundo,pnInterno.getComponentCount() -1);
    }

    private void construirPainelInterno(){
    	pnInterno = new JPanel();
        pnInterno.setLayout(null);
        final int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().width - dimensaoMinima.getWidth()) /2;
        final int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().height - dimensaoMinima.getHeight()) /2;
        pnInterno.setOpaque(false);
        pnInterno.setBounds(x,y,(int)dimensaoMinima.getWidth(),(int)dimensaoMinima.getHeight());
        getContentPane().add(pnInterno);
        getContentPane().setComponentZOrder(pnInterno, 0);

        final JLabel lbLogo = new JLabel("", JLabel.CENTER);
        lbLogo.setBounds(4,4, 901, 67);
        lbLogo.setIcon(ImagemUtil.obterImagem("imagem/topo.png"));
        pnInterno.add(lbLogo);

        final JLabel lbVersao = new JLabel("", JLabel.LEFT);
        lbVersao.setBounds(25, 693, 80, 20);
        lbVersao.setForeground(Color.white);
        lbVersao.setFont(new Font("Helvetica", Font.BOLD, 11));
        lbVersao.setText(getVersao());
        pnInterno.add(lbVersao);

        lbStatus = new JLabel("", JLabel.CENTER);
        lbStatus.setBounds(850, 657, 46, 40);
        lbStatus.setIcon(ImagemUtil.obterImagem("imagem/online.png"));
        pnInterno.add(lbStatus);

        lbStatusTexto = new JLabel("Online", JLabel.CENTER);
        lbStatusTexto.setForeground(Color.white);
        lbStatusTexto.setFont(new Font("HelveticaNeue", Font.BOLD, 11));
        lbStatusTexto.setBounds(851, 693, 46, 20);
        pnInterno.add(lbStatusTexto);

        lbAtencao = new JLabel("",JLabel.CENTER);
        lbAtencao.setBounds(30,650,32,32);
        lbAtencao.setIcon(ImagemUtil.obterImagem("imagem/atencao.png"));
        lbAtencao.setVisible(false);
        pnInterno.add(lbAtencao);
    }

    private void construirPainelHorario(){
        final JPanel pnHorario = new JPanel();
        pnHorario.setBounds(0, 160,(int)dimensaoMinima.getWidth(), 140);
        pnHorario.setOpaque(false);
        pnHorario.setLayout(null);
        pnInterno.add(pnHorario);

        lbHora = new JLabel("", JLabel.CENTER);
        lbHora.setVerticalAlignment(SwingConstants.CENTER);
        lbHora.setBounds(327, 0, 260, 80);
        lbHora.setForeground(Color.white);
        lbHora.setFont(new Font("Helvetica-Narrow", Font.PLAIN, 80));
        pnHorario.add(lbHora);

        lbData = new JLabel("", JLabel.CENTER);
        lbData.setBounds(280, 75, 350, 58);
        lbData.setForeground(Color.white);
        lbData.setFont(new Font("Helvetica Narrow", Font.PLAIN, 26));
        pnHorario.add(lbData);
    }

    private void construirPainelInicio(){
        pnInicio = new JPanel();
        pnInicio.setBounds(0, 328,(int)dimensaoMinima.getWidth(), 390);
        pnInicio.setLayout(null);
        pnInicio.setOpaque(false);
        pnInterno.add(pnInicio);

        final JLabel lbBenvindo = new JLabel("BEM-VINDO!", JLabel.CENTER);
        lbBenvindo.setFont(new Font("HelveticaRounded", Font.BOLD, 50));
        lbBenvindo.setBounds(256, 0, 400, 105);
        lbBenvindo.setForeground(Color.white);
        pnInicio.add(lbBenvindo);

        lbInstrucao = new JLabel("", JLabel.CENTER);
        lbInstrucao.setFont(new Font("HelveticaNeue", Font.PLAIN, 18));
        lbInstrucao.setBounds(256, 170, 400, 22);
        lbInstrucao.setForeground(Color.white);
        pnInicio.add(lbInstrucao);

        if (!ConfiguracaoNegocio.MODO_BIOMETRICO.equals(modoOperacao)) {
            lbInstrucao.setText("Informe seu CPF.");
            txCpf = new CaixaTexto();
            txCpf.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    final int keyCode = (int)e.getKeyChar();
                    if(!(keyCode >= 48 && keyCode <= 57 || keyCode == 8 ) || txCpf.getText().length() >10){
                        e.consume();
                    }
                }
                public void keyReleased(KeyEvent e) {
                    if (txCpf.getText().trim().length() == 11) {
                        registrarFrequencia(txCpf.getText().trim());
                    }
                }
            });
            txCpf.setBounds(324, 195, 255, 45);
            txCpf.setTransferHandler(null);
            txCpf.setFont(new Font("Arial", Font.PLAIN, 40));
            pnInicio.add(txCpf);
        } else {
            ServicoGriaule.getInstancia().addObserver(this);
			final JLabel lbDigital = new JLabel();
            lbDigital.setBounds(416, 200, 80, 110);
			lbDigital.setIcon(ImagemUtil.obterImagem("imagem/digital.png"));
            lbDigital.setOpaque(false);
            pnInicio.add(lbDigital);
            lbInstrucao.setText("Use o leitor biomêtrico ao lado.");
        }
    }

    private void construirPainelAguarde() {
        lbAguarde = new JLabel("Aguarde", JLabel.CENTER);
        lbAguarde.setFont(new Font("HelveticaNeue", Font.BOLD, 30));
        lbAguarde.setForeground(Color.white);
        lbAguarde.setBounds(330,480,255,50);
        lbAguarde.setVisible(false);

        lbImgAguarde = new JLabel("", JLabel.CENTER);
        lbImgAguarde.setBounds(330, 540, 255, 50);
        lbImgAguarde.setVisible(false);
        try {
            final ImageIcon i = new ImageIcon(ImagemUtil.obterImagemTemporaria("dots", ".gif", "imagem/dots.gif").toURI().toURL());
            i.setImageObserver(lbImgAguarde);
            lbImgAguarde.setIcon(i);
        }catch(MalformedURLException e){
            log.error("Não encontrou imagem do Aguarde...");
            lbAtencao.setVisible(true);
        }
        pnInterno.add(lbImgAguarde);
        pnInterno.add(lbAguarde);
    }

    private void construirPainelResultado(){
        pnResultado = new JPanel();
        pnResultado.setOpaque(false);
        pnResultado.setBounds(0, 0, (int)dimensaoMinima.getWidth(), 750);
        pnResultado.setLayout(null);
        pnResultado.setOpaque(false);
        pnInterno.add(pnResultado);
        lbNome = new JLabel("",JLabel.CENTER);
        lbNome.setFont(new Font("HelveticaRounded", Font.BOLD, 50));
        lbNome.setBounds(0, 350, pnResultado.getWidth(), 150);
        lbNome.setForeground(Color.white);
        pnResultado.add(lbNome);
        lbMensagem = new JLabel("", JLabel.CENTER);
        lbMensagem.setFont(new Font("Helvetica Narrow", Font.PLAIN, 24));
        lbMensagem.setForeground(Color.white);
        lbMensagem.setBounds(10, 400, (int)dimensaoMinima.getWidth() - 20 ,220);
        pnResultado.add(lbMensagem);
    }

    private void construirAutomacao() {
        sdf = new SimpleDateFormat("HHmm");
        agendador = Executors.newScheduledThreadPool(5);
        agendador.scheduleAtFixedRate(new Runnable() {
            public void run() {
                dataHoraLocal.add(Calendar.SECOND, 1);
                lbHora.setText(DataUtil.formatar(dataHoraLocal.getTime(), DataUtil.HORA_MINUTO));
                lbData.setText(DataUtil.porExetenso(dataHoraLocal.getTime()));
            }
        },0, 1, TimeUnit.SECONDS);
        agendador.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    final Date data = ConfiguracaoNegocio.getInstancia().obterDataHora(dataHoraLocal.getTime());
                    dataHoraLocal.setTime(data);
                } catch (InconsistenciaExcecao e) {
                    lbAtencao.setVisible(true);
                    log.error(e.getMessage());
                    estadoErro(e.getMessage());
                    desabilitarFuncionamento();
                }catch(InfraExcecao e){
                    lbAtencao.setVisible(true);
                }
            }
        },0, 1, TimeUnit.MINUTES);
        agendador.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if(!isJanelaBackground()) {
                    try {
                        BiometriaNegocio.getInstancia().sincronizar();
                        if (ConfiguracaoNegocio.MODO_BIOMETRICO.equals(modoOperacao)) {
                            ServicoGriaule.getInstancia().setBiometrias(BiometriaNegocio.getInstancia().listarTodas());
                        }
                    } catch (InfraExcecao e) {
                        log.debug(e);
                        lbAtencao.setVisible(true);
                    }
                }
            }
        }, 0, 4, TimeUnit.HOURS);
        agendador.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if(!isJanelaBackground()) {
                    try {
                        FrequenciaNegocio.getInstancia().enviarFrequencias(dataHoraLocal.getTime());
                    } catch (InfraExcecao e) {
                        lbAtencao.setVisible(true);
                    }
                }
            }
        },0, 10, TimeUnit.MINUTES);
        agendador.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if(!isJanelaBackground()) {
                    try {
                        FrequenciaNegocio.getInstancia().removerFrequenciasEnviadas(dataHoraLocal.getTime());
                    } catch (InfraExcecao e) {
                        lbAtencao.setVisible(true);
                    }
                }
            }
        }, 0, 1, TimeUnit.DAYS);
    }

    private boolean isJanelaBackground(){
        final int t = Integer.parseInt(sdf.format(dataHoraLocal.getTime()));
        return t >= 1130 && t <= 1230;
    }

    private void fechar(){
        try {
            Repositorio.getInstancia().fecharFrequencia();
            Repositorio.getInstancia().fechar();
            System.exit(0);
        }catch(InfraExcecao e){
            log.debug(e);
            System.exit(-1);
        }

    }

    private void estadoErro(final String mensagem) {
        corAtual = corErro;
        lbAguarde.setVisible(false);
        lbImgAguarde.setVisible(false);
        lbMensagem.setText(mensagem);
        pnInicio.setVisible(false);
        pnResultado.setVisible(true);
    }

    private void estadoMensagem(final String mensagem){
        corAtual = corErro;
        lbAguarde.setVisible(false);
        lbImgAguarde.setVisible(false);
        lbMensagem.setText(String.format("<html>%s</html>",mensagem));
        pnInicio.setVisible(false);
        pnResultado.setVisible(true);
        tmMensagem.start();
    }

    private void estadoNaoEncontrado(final String mensagem){
        corAtual = corErro;
        lbAguarde.setVisible(false);
        lbImgAguarde.setVisible(false);
        lbNome.setText(mensagem);
        pnResultado.setVisible(true);
        tmNaoEncontrado.start();
    }

    private void estadoOk(final String mensagem) {
        corAtual = corOk;
        lbAguarde.setVisible(false);
        lbImgAguarde.setVisible(false);
        pnInicio.setVisible(false);
        lbMensagem.setText(mensagem);
        pnResultado.setVisible(true);
        tmNormal.start();
    }

    private void estadoInicial() {
        corAtual = corNormal;
        pnInicio.setVisible(true);
        lbAguarde.setVisible(false);
        lbImgAguarde.setVisible(false);
        pnResultado.setVisible(false);
        tmMensagem.stop();
        tmNormal.stop();
        tmNaoEncontrado.stop();
        if (!ConfiguracaoNegocio.MODO_BIOMETRICO.equals(modoOperacao)) {
            txCpf.setText(null);
            txCpf.requestFocus();
        }
    }

    private void estadoAguardando() {
        lbAguarde.setVisible(true);
        lbImgAguarde.setVisible(true);
        lbNome.setText("");
        lbMensagem.setText("");
        pnInicio.setVisible(false);
        pnResultado.setVisible(false);
    }

    private String getVersao() {
        return "Versão " +  PropriedadeUtil.getInstancia().obterPropriedade("versao");
    }

    private void desabilitarFuncionamento(){
        if(ConfiguracaoNegocio.MODO_BIOMETRICO.equals(modoOperacao)){
        	ServicoGriaule.getInstancia().pararLeitor();
        }else{
            txCpf.setEnabled(false);
        }
    }

    public void update(final Observable observado,final  Object retorno) {
    	if(observado instanceof ServicoProxy){
	        if(CircuitBrakerStates.OPEN  == retorno) {
	            lbStatus.setIcon(ImagemUtil.obterImagem("imagem/offline.png"));
	            lbStatusTexto.setText("Offline");
	        }else{
	            lbStatus.setIcon(ImagemUtil.obterImagem("imagem/online.png"));
	            lbStatusTexto.setText("Online");
	        }
    	}
    	if(observado instanceof ServicoGriaule){
    		final String cpf = retorno != null ? String.valueOf(retorno) : null; 
			registrarFrequencia(cpf);
    	}
    }

    private void registrarFrequencia(final String cpf)  {
        estadoAguardando();
        final Thread t = new Thread(){
            public void run() {
                try{
                    if(cpf == null){
                        throw new NaoEncontradoExcecao("cpf");
                    }
                    final Biometria b = BiometriaNegocio.getInstancia().obterPorCpf(cpf);
                    lbNome.setText(String.format("<html>%s</html>", b.getNome()));
                    final String tipo = FrequenciaNegocio.getInstancia().registrarFrequencia(dataHoraLocal.getTime(),cpf);
                    if(FrequenciaNegocio.ENTRADA.equals(tipo)){
                        estadoOk("Entrada registrada com sucesso!!!");
                    }else if(FrequenciaNegocio.SAIDA.equals(tipo)){
                        estadoOk("Saída registrada com sucesso!!!");
                    }else if(FrequenciaNegocio.OFF_LINE.equals(tipo)) {
                        estadoOk("Registro Off-line registrado com sucesso!!!");
                    }
                }catch(NaoEncontradoExcecao e) {
                    if(ConfiguracaoNegocio.MODO_BIOMETRICO.equals(modoOperacao)){
                        estadoNaoEncontrado("Digital não encontrada.");
                    }else{
                        estadoNaoEncontrado("CPF não encontrado.");
                    }
                }catch(ValidacaoFrequenciaExcecao e){
                    estadoMensagem(e.getMessage());
                } catch (InfraExcecao e) {
                    estadoMensagem(e.getMessage());
                }
            }
        };
        t.start();
    }

}