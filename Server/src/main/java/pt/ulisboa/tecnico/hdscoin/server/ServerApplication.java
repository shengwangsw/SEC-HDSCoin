package pt.ulisboa.tecnico.hdscoin.server;

import pt.ulisboa.tecnico.hdscoin.interfaces.RemoteServerInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerApplication {

	public static void main(String args[]) {
		
		try {
			int serversize = 7;
			
			Scanner reader = new Scanner(System.in);
			/*
			System.out.println("Write number of servers you want to run:");
			String number=reader.nextLine();
			try{
				serversize=Integer.parseInt(number.trim());
			}catch(Exception e){
				System.out.println("'"+number.trim()+"'"+" is not correct!");
			}
			*/
			Server[] servers=new Server[serversize];
			for(int i=0; i<servers.length; i++){
				servers[0]=new Server(i+1);
			}
			while(true){
				System.out.println(serversize+" servers are running. Choose a server to manage!");
				String chosenServer=reader.nextLine();
				int chosenServerInt=0;
				try{
					chosenServerInt=Integer.parseInt(chosenServer.trim());
				}catch(Exception e){
					System.out.println("'"+chosenServer.trim()+"'"+" is not correct!");
					continue;
				}
				boolean manageServer=true;
				while(manageServer){
					System.out.println("1-crash");
					System.out.println("2-recover");
					System.out.println("0-exit");
					String option=reader.nextLine();
					if(Integer.parseInt(option.trim())==1){
						servers[chosenServerInt].setServerFault(true);
					}else if(Integer.parseInt(option.trim())==2){
						servers[chosenServerInt].setServerFault(false);
					}else if(Integer.parseInt(option.trim())==0){
						manageServer=false;
						break;
					}else{
						System.out.println("\nThe '"+option+ "' is not a valid number!");
					}
				}
			}
		} catch (RemoteException e) {
			System.out.println("Connection Problem");
		} catch (AlreadyBoundException e) {
			System.out.println("Already Bound");
		}

    }
}
