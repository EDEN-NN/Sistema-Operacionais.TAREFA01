package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController() {
		super();
	}
	
	public String si(String so) {
		so = System.getProperty("os.name");
		return so;
	}
	
	public String callProcess(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			InputStream fluxo = process.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			String result = "";
			
			while(linha!=null) {
				result+=linha;
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
			
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String ip(String so) {
		if(so.contains("Windows")) {
			String command = "ipconfig";
			String ip = callProcess(command);
			String vet[] = ip.split(" ");
			
			String result = "";
			
			for (int i = 0; i<vet.length; i++) {
				if(vet[i].contains("Adaptador") || vet[i].contains("WindowsAdaptador")) {
					result+="Adaptador ";
					for(int j = i+1; j<vet.length; j++) {
						if(!vet[j].contains(":")) {
							result+=vet[j]+" ";
						}else {
							result+= vet[j].replace(":", "\n");
							break;
						}
					}
					
				}else if (vet[i].equals("IPv4.")) {
					for (int j = i+1; j<vet.length; j++) {
						if (vet[j].length()>1) {
							result+="Ipv4: " + vet[j] + "\n\n";
							break;
						}
					}
				}
			} return result;
		} else {
			String command = "ifconfig";
			String ip = callProcess(command);
			String vet[] = ip.split(" ");
			
			String result = vet[0]+" ";
			
			for(int i = 1; i<vet.length; i++) {
				if(vet[i].equals("inet") || !vet[i].equals("inet6")) {
					result+= vet[i] + " ";
					if(vet[i].contains("1")) {
						result+= vet[i].split(".");
					}
					
				} if (vet[i].equals("collisions")) {
					result+= vet[i+2] + " ";
					if(vet[i].equals("inet") || !vet[i].equals("inet6")) {
						result+= vet[i] + " ";
						if(vet[i].contains("1")) {
							result+= vet[i].split(".");
						}
						
					}
					
				} 
			}
			return result;
		}
			
		} 
	}

