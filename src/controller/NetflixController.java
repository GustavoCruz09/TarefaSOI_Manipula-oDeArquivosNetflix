package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import GustavoCruz.FilaObject.Fila;
import model.serie;

public class NetflixController implements INetflixController {


	@Override
	public void Netflix() {
		try {
			GeraFila();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void GeraFila() throws IOException {

		File arq = new File("C:\\TEMP\\netflix_originals_series_2.csv");
		int ctd = 0;
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitorfluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitorfluxo);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			while (linha != null) {
				String vtlinha[] = linha.split(";");
				String genero = vtlinha[0];
				Fila filaaux = new Fila();
				while (genero.contains(vtlinha[0]) && linha != null) {
					serie s = new serie();
					s.major_genre = vtlinha[0];
					s.title = vtlinha[1];
					s.subgenre = vtlinha[2];
					s.premiere_year = vtlinha[4];
					s.episodes = (vtlinha[10]);
					s.status = vtlinha[6];
					s.imdb_rating = Integer.parseInt(vtlinha[11]);
					filaaux.insert(s);
					linha = buffer.readLine();
					if (linha != null) {
						vtlinha = linha.split(";");
					}
				}
				GeraArquivo(filaaux, genero);
			}
		} else {
			throw new IOException("Arquivo invalido");
		}

	}

	private void GeraArquivo(Fila fila, String genero) throws IOException {

		File file = new File("C:\\TEMP", genero + ".csv");
		StringBuffer buffer = new StringBuffer();
		buffer.append("Major_Gender;Title;Subgender;Year;Episodes;Status;IMDB_Rating\n");
		FileWriter filewrite = new FileWriter(file);
		PrintWriter print = new PrintWriter(filewrite);
		print.write(buffer.toString());
		print.flush();
		print.close();
		filewrite.close();
		
		int tamanho = fila.size();
		for (int i = 0; i < tamanho; i++) {
			serie s = new serie();
			try {
				s = (serie) fila.remove();
				buffer.append(s);
				FileWriter filewrite1 = new FileWriter(file);
				PrintWriter print1 = new PrintWriter(filewrite1);
				print1.write(buffer.toString());
				print1.flush();
				print1.close();
				filewrite1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
