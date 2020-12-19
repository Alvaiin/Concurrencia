package punto1.archivoinfo;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ArchivoInfoTask extends RecursiveTask<List<ArchivoInfo>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File archivo;
	

	public ArchivoInfoTask(File archivo) {
		this.archivo = archivo;
	}

	@Override
	protected List<ArchivoInfo> compute() {
		List<ArchivoInfo> listArchivos = new LinkedList<ArchivoInfo>();
		
		if (archivo.isDirectory()) {//Procesar si es directorio
			
			File[] archivos = archivo.listFiles();
			List<ArchivoInfoTask> subTareas = new ArrayList<ArchivoInfoTask>();
			for (File archivo : archivos) {
				subTareas.add(new ArchivoInfoTask(archivo));
			}
			
			long peso = 0;
			for (ArchivoInfoTask task : ForkJoinTask.invokeAll(subTareas)){
				for (ArchivoInfo archivoInfo : task.join()) {
					listArchivos.add(archivoInfo);
					peso +=archivoInfo.getPeso();
				}			}
			listArchivos.add(0,new ArchivoInfo(archivo.getAbsolutePath(),peso));
			
		} else {//procesar si es archivo
			listArchivos.add(new ArchivoInfo(archivo.getAbsolutePath(),archivo.length()));
		}
		return listArchivos;
	}
}