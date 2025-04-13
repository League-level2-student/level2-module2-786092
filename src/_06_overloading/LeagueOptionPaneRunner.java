package _06_overloading;

import java.awt.Color;

public class LeagueOptionPaneRunner {
	public static void main(String[] args) {
		LeagueOptionPane.showMessageDialog("Anybody can code");
		LeagueOptionPane.showMessageDialog("Take a <break>", "The League");
		LeagueOptionPane.showMessageDialog("Anybody can code", "How to code", "java.png");
		LeagueOptionPane.showMessageDialog("Learn to Code", "The League", "java.png", Color.BLUE);
	}
}
