package punto1.archivoinfo;

public class ArchivoInfo {

	private long peso;

	private String path;

	public ArchivoInfo(String path, long peso) {
		this.peso = peso;
		this.path = path;
	}

	public long getPeso() {
		return peso;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {

		int i = 0;
		String unidad;
		while (peso >= 1024 && i < 4) {
			peso = peso / 1024L;
			i++;
		}
		switch (i) {
		case 0:
			unidad = "Bytes";
			break;
		case 1:
			unidad = "Kb";
			break;
		case 2:
			unidad = "Mb";
			break;
		case 3:
			unidad = "Gb";
			break;
		case 4:
			unidad = "Tb";
			break;
		default:
			unidad = "";
		}

		return String.format("%s (%d %s)", this.path, this.peso, unidad);
	}

}
