package util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class Helper {
	public static List<String> leerLineas(String file) {
		List<String> r = null;
		try {
			r = Files.readAllLines(Paths.get(file), Charset.defaultCharset());
		} catch (NoSuchFileException e) {
			System.out.println("El archivo en la ruta " + file + " no existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
}
