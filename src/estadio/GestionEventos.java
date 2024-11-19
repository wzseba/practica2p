package estadio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class GestionEventos {

	private Map<String, EspectadoresPorEvento> estadio;

	public GestionEventos() {
		this.estadio = new TreeMap<String, EspectadoresPorEvento>();
	}

	// carga los datos en estadio.
	public void cargarDatosdeLosEventos(String archivo) {

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;

			while ((linea = br.readLine()) != null) {

				// Por cada linea voy a splitear datos
				String[] datos = linea.split(",");

				String codEvento = datos[0];
				int numPuerta = Integer.parseInt(datos[1]);
				int cantEspectadores = Integer.parseInt(datos[2]);

				EspectadoresPorEvento especPorEvento = estadio.getOrDefault(codEvento, new EspectadoresPorEvento());

				try {
					especPorEvento.ingresarEspectadoresPorLaPuerta(numPuerta, cantEspectadores);
				} catch (NumPuertaException e) {
					e.printStackTrace();
				}

				estadio.put(codEvento, especPorEvento);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void generarEspectadoresPorEvento(String salida) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(salida))) {

			for (Map.Entry<String, EspectadoresPorEvento> entry : estadio.entrySet()) {
				String codEvent = entry.getKey();
				EspectadoresPorEvento espec = entry.getValue();

				bw.write("Evento: " + codEvent + "\n");

				for (int i = 0; i < 15; i++) {
					try {
						bw.write("puerta: " + (i + 1) + " ingresaron: "
								+ espec.getEspectadoresQueIngresaronPorLaPuerta(i + 1) + "\n");
					} catch (NumPuertaException e) {
						e.printStackTrace();
					}

				}
				bw.write("total " + espec.getCantidadTotalDeEspectadores() + "\n");
				bw.write("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		GestionEventos gevent = new GestionEventos();
		gevent.cargarDatosdeLosEventos("eventos.csv");
		gevent.generarEspectadoresPorEvento("espectadores.csv");
	}

}
