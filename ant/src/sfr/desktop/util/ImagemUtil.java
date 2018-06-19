package gov.goias.sfr.desktop.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * <bold>ImagemUtil</bold> Classe que trata de ler as imagens do jar.
 * Quando necessário salvar temporariamente em disco.
 */
public abstract  class ImagemUtil {

    /**
     * Método estático que le a imagem do jar pelo caminho por um Stream, e constroi um ImagemIcon.
     *
     * @param caminho Caminho da imagem no jar.
     *
     * @return ImageIcon Imagem desejada.
     */
    public static ImageIcon obterImagem(final String caminho){
        if(caminho != null) {
            final Image imagem = obterArquivoImagem(caminho);
            if (imagem != null) {
                return new ImageIcon(imagem);
            }
        }
        return null;
    }

    /**
     * Método estático que le a imagem do jar pelo caminho por um Stream, constroi um ImagemIcon em tamanho específico.
     *
     * @param caminho Caminho da imagem no jar.
     *
     * @param largura Altura desejada.
     * @param altura Largura desejada.
     *
     * @return ImageIcon Imagem desejada.
     */
    public static ImageIcon obterImagem(final String caminho, final int largura, final int altura){
        final BufferedImage b = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        final Graphics2D g = b.createGraphics();
        final Image i = obterArquivoImagem(caminho);
        if(i != null) {
            g.drawImage(i, 0, 0, largura, altura, null);
            g.dispose();
            return new ImageIcon(b);
        }
        return null;
    }

    /**
     * Método estático que le a imagem do jar pelo caminho por um Stream.
     *
     * @param caminho caminho da imagem no jar.
     *
     * @return Image Imagem desejada.
     */
    private static Image obterArquivoImagem(final String caminho){
        try {
            final InputStream p = ImagemUtil.class.getClassLoader().getResourceAsStream(caminho);
            return  ImageIO.read(p);
        }catch(Exception e){
            return null;
        }
    }

    /**
     * Método estático que extrai uma imagem do jar e escreve no arquivo temporário da mâquina.
     *
     * @param nome Nome do arquivo temporário.
     * @param tipo Tipo do arquivo temporário(extensão).
     * @param caminho Caminho do arquivo no jar.
     *
     * @return File Arquivo temporário.
     */
    public static File obterImagemTemporaria(final String nome, final String tipo, final String caminho) {
        try {
            final File f = File.createTempFile(nome, tipo);
            f.deleteOnExit();
            final InputStream i = ImagemUtil.class.getClassLoader().getResourceAsStream(caminho);
            final OutputStream out = new FileOutputStream(f);
            final byte[] buf = new byte[1024];
            int len;
            while((len = i.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            i.close();
            return f;
        }catch(Exception e){
            return null;
        }
    }

}