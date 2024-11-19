package estadio;

public class EspectadoresPorEvento {

	private int[] puertas = new int[15];
	private int cantidadTotalDeEspectadores = 0;

	public void ingresarEspectadoresPorLaPuerta(int puerta, int espectadores) throws NumPuertaException {
		if (puerta < 1 || puerta > 15) {
			throw new NumPuertaException("puerta incorrecta " + puerta);
		}
		this.puertas[puerta - 1] += espectadores;
		this.cantidadTotalDeEspectadores += espectadores;
	}

	public int getEspectadoresQueIngresaronPorLaPuerta(int puerta) throws NumPuertaException {
		if (puerta < 1 || puerta > 15) {
			throw new NumPuertaException("puerta incorrecta " + puerta);
		}
		return this.puertas[puerta - 1];
	}

	public int getCantidadTotalDeEspectadores() {
		return this.cantidadTotalDeEspectadores;
	}

}
