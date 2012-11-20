package ide_ces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class CesFile {

	public static void salvar(String arquivo, String conteudo, boolean adicionar) throws IOException { 
	
		FileWriter fw = new FileWriter(arquivo, adicionar);
		
		fw.write(conteudo);
		fw.close();
	}
	public static String LeArquivo(String file_name) throws IOException{
		
	
		BufferedReader b = new BufferedReader(new FileReader(file_name));
		String t_result = b.readLine()+"\n";
		String text;
		while((text = b.readLine()) != null){
			t_result += text+"\n";
		}
		b.close();
		return t_result;
	}
	public static String LeOutputCes(String file_name) throws IOException{
		
		BufferedReader b = new BufferedReader(new FileReader(file_name));
		String text="", buf = b.readLine(), buf2 = buf;
		while((buf = b.readLine()) != null){
			text += buf2+"\n";
			buf2 = buf;
		}
		b.close();
		return buf2+text;
	}
}