package gov.goias.sfr.desktop.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.apache.log4j.Logger;

/**
 * <bold>RedeUtil</bold> Classe que obtem o endereço mac da máquina.
 *
 * @author Marcos Fernando Costa.
 */
public  abstract  class RedeUtil {
	private static Logger log = Logger.getLogger(RedeUtil.class);

	/**
	 *  Método estático que retorna o valor do endereço Mac da máquina, varre as interfaces existentes
	 *  e ignora as interfaces logicas.
	 *
	 * @return String enderecoMac
	 */
	public static String obterEnderecoMac() {
		String macLocal = null;
		try {
			final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				final NetworkInterface interfaceRede = interfaces.nextElement();
				final byte[] bmac = interfaceRede.getHardwareAddress();
				if (bmac != null) {
					final StringBuilder sb = new StringBuilder();
					for (int i = 0; i < bmac.length; i++) {
						sb.append(String.format("%02X%s", bmac[i], ""));
					}
					String strMac = sb.toString();
					if(strMac.length() == 12 && !strMac.substring(0,6).contains("000000")){
						macLocal = strMac;
						break;
					}else{ //Para Windows
						sb.delete(0,sb.length());
						final byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
						for (int i = 0; i < mac.length; i++) {
							sb.append(String.format("%02X", mac[i]));
						}
						if(sb.length() == 12){
							macLocal = sb.toString();
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(String.format("Não foi possível capturar o endereço MAC. Motivo: %s", e.getMessage()));
		}
		//cpf vlidos para mac 2025644616F9 73697958100 86030310100
		//cpf invlidos para mac 2025644616F9 905.642.426-24
		//macLocal = obterFake() ;
		log.info(String.format("Mac Reconhecido: %s", macLocal));
		return macLocal;
	}

	private static String obterFake(){
		return "2025644616F9";
	}

}