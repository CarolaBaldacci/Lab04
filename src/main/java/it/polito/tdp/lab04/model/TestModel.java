package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		/*
		 * 	Write here your test model
		 */
		// Punto 1
				StringBuilder sb = new StringBuilder();
				for (Corso corso : model.getTuttiICorsi()) {
					sb.append(String.format("%-8s ", corso.getCodins()));
					sb.append(String.format("%-2s ", corso.getCrediti()));
					sb.append(String.format("%-50s ", corso.getNome()));
					sb.append(String.format("%-4s ", corso.getPd()));
					sb.append("\n");
				}
				System.out.println(sb.toString());
		

	}

}
