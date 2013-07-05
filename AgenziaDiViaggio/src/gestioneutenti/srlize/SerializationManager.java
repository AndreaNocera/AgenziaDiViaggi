/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.dao
 * 
 * @name  SerializationManager.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */
package gestioneutenti.srlize;

import gestioneutenti.model.bean.UtenteBean;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class SerializationManager {

	private static SerializationManager SingletonSerializationManager = null; 

	private String percorso="data";
	private String nomefile="fileUtenti.txt";
	private File file=null;
	private File path=null;

	private boolean endofFileReached;

	private SerializationManager() throws IOException {

		path = new File(percorso);
		if (!path.exists() || !path.isDirectory()) {
			boolean mkdirSucceeded = path.mkdir();
			if (!mkdirSucceeded) throw new IOException("Fallimento creazione directory: '" + path.getAbsolutePath() + "'");
		}

		if (file == null){
			file = new File(path, nomefile);    
			boolean createFileSucceeded = false;
			try {
				createFileSucceeded = file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!createFileSucceeded) {
				System.out.println("Rilevato file di utenti:  '" + file.getAbsolutePath() + "'");
			}
		}	    
	}

	public static synchronized SerializationManager getInstance() throws IOException {
		if(SingletonSerializationManager == null) SingletonSerializationManager = new SerializationManager();

		return SingletonSerializationManager;
	}

	public synchronized boolean serializza_utenti(ArrayList<UtenteBean> Utenti) throws IOException{
		if(Utenti!=null){
		FileOutputStream FOS=null;
		ObjectOutputStream OOS=null;
		int index=0;
		try{

			FOS=new FileOutputStream(file);
			OOS= new ObjectOutputStream(FOS);
			
			while(index<Utenti.size()){
				OOS.writeObject(Utenti.get(index));
				index++;
			}
		}catch(IOException i){i.printStackTrace();return false;}
		finally{
			if(OOS!=null)OOS.close();
			if(FOS!=null)FOS.close();
		}
		return true;
		}else return false;
	}

	public synchronized ArrayList<UtenteBean> deserealizza_utenti() throws IOException{
		ArrayList<UtenteBean> lista=new ArrayList<>();
		FileInputStream FIS=null;
		ObjectInputStream OIS=null;
		UtenteBean u=null;
		endofFileReached=false;
		
		try{
			FIS=new FileInputStream(file);
			OIS= new ObjectInputStream(FIS);
			
			while(!endofFileReached){
					u=(UtenteBean)OIS.readObject();
					if(u!=null){
						lista.add(u);
					}
					else endofFileReached=true;
			}

		}catch(EOFException eofe){return lista;}catch( IOException | ClassNotFoundException i){i.printStackTrace();return null;}
		finally{
			if(FIS!=null)FIS.close();
			if(OIS!=null)OIS.close();
		}
		return lista;
	}
	
	public synchronized boolean svuotaFile() throws IOException{
		FileOutputStream FOS=null;
		try {
			FOS = new FileOutputStream(file, false);
			FOS.flush();
			}
		 catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			FOS.close();
		}
	return true;
	}
	

}

