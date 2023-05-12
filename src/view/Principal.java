package view;

import controller.INetflixController;
import controller.NetflixController;

public class Principal {

	public static void main(String[] args) {
		INetflixController netflix = new NetflixController();
		netflix.Netflix();
	}

}
