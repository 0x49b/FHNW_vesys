module bank {
	exports bank;
	exports bank.local;
	exports bank.gui;

	requires java.desktop;
	requires java.rmi;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.swing;
}