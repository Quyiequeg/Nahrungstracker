package model;

import view.TrackerFrame;

public class Macrotracker {

	public static void main(String[] args) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new TrackerFrame("Macrotracker");
				}
			});
		}
	}