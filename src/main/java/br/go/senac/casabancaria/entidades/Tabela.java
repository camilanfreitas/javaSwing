package br.go.senac.casabancaria.entidades;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tabela extends JPanel{

	private double saldoInic;
	private double juros;
	private double amortizacao;
	private double prestacao;
	private double saldoFinal;
	private double totalJuros;
	private double totalAmort;
	private double totalPrestacao;
    
    public Tabela() {
    	
    }
    
    public Tabela(Emprestimo e, int width, int height){

    	String[] header = {"Per�odo", "Saldo Inicial", "Juros", "Amortiza��o", "Presta��o", "Saldo Final"};
    	
    	Object[][] data = null;
    	
    	/*Object[][] data = {
				{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
				{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
				{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
				{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
				{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
				{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
				{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
				{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
				{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
				{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
				{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
				{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
				{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
				{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
				{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
				{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
				{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
				{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
				{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
				{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
				{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
				{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
				{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
				{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
				{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)}
		};*/
        
        DefaultTableModel model = new DefaultTableModel(data,header);

        JTable table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(width, height));
        table.setFillsViewportHeight(true);

        JScrollPane js=new JScrollPane(table);
        js.setVisible(true);
        add(js);

    }

	public double getSaldoInic() {
		return saldoInic;
	}

	public void setSaldoInic(double saldoInic) {
		this.saldoInic = saldoInic;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

	public double getAmortizacao() {
		return amortizacao;
	}

	public void setAmortizacao(double amortizacao) {
		this.amortizacao = amortizacao;
	}

	public double getPrestacao() {
		return prestacao;
	}

	public void setPrestacao(double prestacao) {
		this.prestacao = prestacao;
	}

	public double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public double getTotalJuros() {
		return totalJuros;
	}

	public void setTotalJuros(double totalJuros) {
		this.totalJuros = totalJuros;
	}

	public double getTotalAmort() {
		return totalAmort;
	}

	public void setTotalAmort(double totalAmort) {
		this.totalAmort = totalAmort;
	}

	public double getTotalPrestacao() {
		return totalPrestacao;
	}

	public void setTotalPrestacao(double totalPrestacao) {
		this.totalPrestacao = totalPrestacao;
	}
    
    

}