package com.JovicaIgnjatic;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class BusHQ {

    private String name;
    private static final ArrayList<Station> allStations = new ArrayList<>();
    private static final List<BusLine> busLines = new LinkedList<>();
 
    static {
        try (BufferedReader br = new BufferedReader(new FileReader("stations.txt"))) {
            String input;
            while((input = br.readLine())!=null) {
                String[] data = input.split(",");

                String name = data[0];
                int id = Integer.parseInt(data[1]);
                System.out.println("Imported station: " + name + " of ID: "+id);

                Station st = new Station(name,id);
                allStations.add(st);
            }
        }catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }


    }


    public static void main(String[] args) {
//        BusLine dobojBihac = new BusLine("Doboj - Bihac", 0);
////        allBusStations();
//        dobojBihac.addStation(addStation("Doboj"));
//        dobojBihac.addStation(addStation("Banja Luka"));
//        dobojBihac.addStation(addStation("Bihac"));
////        dobojBihac.addStation(addStation("Bihac"));
//        dobojBihac.addArrival(11, 10, dobojBihac.getStation("Doboj"));
//        dobojBihac.addDeparture(11, 30, dobojBihac.getStation("Doboj"));
//        dobojBihac.addArrival(12, 30, dobojBihac.getStation("Banja Luka"));
//        dobojBihac.addDeparture(12, 45, dobojBihac.getStation("Banja Luka"));
//        dobojBihac.addArrival(14, 30, dobojBihac.getStation("Bihac"));
//        dobojBihac.addDeparture(14, 45, dobojBihac.getStation("Bihac"));
//
//        busLines.add(dobojBihac);
//        inverseLine(dobojBihac);

//
//        BusLine zagrebSarajevo = new BusLine("Zagreb - Sarajevo", 1);
//        zagrebSarajevo.addStation(addStation("Zagreb"));
//        zagrebSarajevo.addStation(addStation("Banja Luka"));
//        zagrebSarajevo.addStation(addStation("Doboj"));
//        zagrebSarajevo.addStation(addStation("Sarajevo"));
//        zagrebSarajevo.addArrival(10, 15, zagrebSarajevo.getStation("Zagreb"));
//        zagrebSarajevo.addDeparture(10, 30, zagrebSarajevo.getStation("Zagreb"));
//        zagrebSarajevo.addArrival(12, 10, zagrebSarajevo.getStation("Banja Luka"));
//        zagrebSarajevo.addDeparture(12, 20, zagrebSarajevo.getStation("Banja Luka"));
//        zagrebSarajevo.addArrival(13, 20, zagrebSarajevo.getStation("Doboj"));
//        zagrebSarajevo.addDeparture(13, 35, zagrebSarajevo.getStation("Doboj"));
//        zagrebSarajevo.addArrival(15, 30, zagrebSarajevo.getStation("Sarajevo"));
//        zagrebSarajevo.addDeparture(15, 40, zagrebSarajevo.getStation("Sarajevo"));
//
//        busLines.add(zagrebSarajevo);
//        inverseLine(zagrebSarajevo);
//
//        BusLine sarajevoBihac = new BusLine("Sarajevo - Bihac", 2);
//        sarajevoBihac.addStation((addStation("Sarajevo")));
//        sarajevoBihac.addStation((addStation("Doboj")));
//        sarajevoBihac.addStation((addStation("Banja Luka")));
//        sarajevoBihac.addStation((addStation("Bihac")));
//        sarajevoBihac.addArrival(7, 10, sarajevoBihac.getStation("Sarajevo"));
//        sarajevoBihac.addDeparture(7, 20, sarajevoBihac.getStation("Sarajevo"));
//        sarajevoBihac.addArrival(8, 10, sarajevoBihac.getStation("Doboj"));
//        sarajevoBihac.addDeparture(8, 20, sarajevoBihac.getStation("Doboj"));
//        sarajevoBihac.addArrival(9, 10, sarajevoBihac.getStation("Banja Luka"));
//        sarajevoBihac.addDeparture(9, 20, sarajevoBihac.getStation("Banja Luka"));
//        sarajevoBihac.addArrival(11, 20, sarajevoBihac.getStation("Bihac"));
//        sarajevoBihac.addDeparture(11, 30, sarajevoBihac.getStation("Bihac"));
//
//        busLines.add(sarajevoBihac);
//
//
//        BusLine sarajevoBeograd = new BusLine("Sarajevo - Beograd", 3);
//        busLines.add(sarajevoBeograd);
//
//        sarajevoBeograd.addStation((addStation("Sarajevo")));
//        sarajevoBeograd.addStation((addStation("Doboj")));
//        sarajevoBeograd.addStation((addStation("Beograd")));
//        sarajevoBeograd.addArrival(7, 10, sarajevoBeograd.getStation("Sarajevo"));
//        sarajevoBeograd.addDeparture(7, 20, sarajevoBeograd.getStation("Sarajevo"));
//        sarajevoBeograd.addArrival(8, 10, sarajevoBeograd.getStation("Doboj"));
//        sarajevoBeograd.addDeparture(8, 20, sarajevoBeograd.getStation("Doboj"));
//        sarajevoBeograd.addArrival(10, 10, sarajevoBeograd.getStation("Beograd"));
//        sarajevoBeograd.addDeparture(10, 20, sarajevoBeograd.getStation("Beograd"));

//        restoreBusLinesDat();
        restoreSerializable();
        saveSerializable();

        printAllLines();

        getStationArrivals(getStation("Banja Luka"));
        getStationDepartures(getStation("Banja Luka"));
        getStationDepartures(getStation("Bihac"));
//        printAllActiveStations();
        departingLines(getStation("Doboj"), getStation("Zagreb"));
        departingLines(getStation("Sarajevo"), getStation("Banja Luka"));
        arrivingLines(getStation("Doboj"), getStation("Banja Luka"));
        

    }
    //busline inversion for creating a bus line that goes in the opposite direction
    public static void inverseLine(BusLine busLine){
        List<Station> tempList = new LinkedList<>();
        List<LocalTime> timesArrivals = new ArrayList<>();
        List<LocalTime> timesDepartures = new ArrayList<>();

        for (int i = busLine.getLineStations().size() - 1; i >= 0; i--) {
            // Append the elements in reverse order
            tempList.add(busLine.getLineStations().get(i));
            timesArrivals.add(busLine.getLineStations().get(i).getArrival());
            timesDepartures.add(busLine.getLineStations().get(i).getDeparture());
        }
        String s = tempList.get(0).getName();
        String s1 = tempList.get(tempList.size()-1).getName();
        String name = s+" - "+s1;
        BusLine busLine1 = new BusLine(name,busLine.getId()+10);
        System.out.println("--------------------INVERSED METHOD-----------------------------");
        for (int i = 0; i < tempList.size(); i++) {
            System.out.println(tempList.get(i) + " ");
            busLine1.addStation(tempList.get(i));
            busLine1.addArrival(timesArrivals.get(i).getHour(),timesArrivals.get(i).getMinute(),tempList.get(i));
            busLine1.addDeparture(timesDepartures.get(i).getHour(),timesDepartures.get(i).getMinute(),tempList.get(i));

        }
        System.out.println("--------------------INVERSED METHOD-----------------------------");



        busLines.add(busLine1);
    }

    //Method that prints out all arriving lines that go from x station to y station
    public static void arrivingLines(Station startingStation, Station destinationStation){
        List<BusLine> startingLines = new LinkedList<>();
        for(BusLine s : busLines){
            if(s.containsStation(startingStation)){
                startingLines.add(s);
            }
        }
        startingLines.sort((o1, o2) -> -1);
        System.out.println("-----------LINES ARRIVING TO "+startingStation.getName()+" THAT GO THROUGH "+destinationStation.getName()+"----------------");
        for(BusLine busLine : startingLines){
            for(Station s : busLine.getLineStations()){
                if(busLine.indexOfStationinBusLine(startingStation)<busLine.indexOfStationinBusLine(s)){
                if(s.getName().equalsIgnoreCase(destinationStation.getName())){
//                    if(!busLine.isStarting(s,busLine)){
                        System.out.println(busLine.getName());
                        System.out.println(busLine.getStation(startingStation.getName()).getArrival());
//                    departures.add(s.getDeparture());
                    }
                }

            }

        }
    }
    //Method that prints out all departing lines that go from x station to y station
    public static void departingLines(Station startingStation, Station destinationStation){
        List<BusLine> startingLines = new LinkedList<>();
        for(BusLine s : busLines){
            if(s.containsStation(startingStation)){
                startingLines.add(s);
            }
        }

        startingLines.sort((o1, o2) -> -1);
        System.out.println(startingLines);
        System.out.println("-----------LINES DEPARTING FROM "+startingStation.getName()+" THAT GO THROUGH "+destinationStation.getName()+"----------------");
        for(BusLine busLine : startingLines){
            for(Station s : busLine.getLineStations()){
                if(busLine.indexOfStationinBusLine(startingStation)<busLine.indexOfStationinBusLine(s)){
                if(s.getName().equalsIgnoreCase(destinationStation.getName())){
//                    int n = busLine.indexOfStationinBusLine(s,busLine);
//                    int j = busLine.indexOfStationinBusLine(startingStation,busLine);
//                    System.out.println(n);
//                    System.out.println(j);

                        System.out.println(busLine.getName());
                        System.out.println(busLine.getStation(startingStation.getName()).getDeparture());
                    }
                }

            }

        }
    }
    //Method that prints out all active stations (that are present in a bus line)
    public static void printAllActiveStations(){
        Map<Integer,Station> activeStations = new HashMap<>();
        System.out.println("--------------ACTIVE STATIONS------------------------");
        System.out.println("All active stations:");
        for(BusLine busLine:busLines){
            for(Station s : busLine.getLineStations()){
                activeStations.put(s.getId(), s);
            }
        }
        for(Map.Entry<Integer,Station> stationEntry : activeStations.entrySet()){
            System.out.println(stationEntry.getValue().getName());
        }
    }


    public static Station getStation(String name){
        for(Station st : allStations){
            if(st.getName().equalsIgnoreCase(name)){
                return st;
            }
        }
        return null;
    }
    public static Station verifyStation(String name){
        for(Station st : allStations){
            if(st.getName().equalsIgnoreCase(name)){
                return new Station(st.getName(),st.getId());
            }
        }
        return null;
    }
    //prints all existing stations
    private static void allBusStations() {
		for(Station s : allStations) {
			System.out.println(s.getName());
		}
	}
	//prints out all arrivals in a station
	public static void getStationArrivals(Station station){
        List<BusLine> arrLines = new LinkedList<>();
        for(BusLine s : busLines){
            if(s.containsStation(station)){
                arrLines.add(s);
//                arrStat.add(s.getLineStations().get(s.indexOfStationinBusLine(station,s)));
            }
        }
        System.out.println("-----------------ARRIVALS------------------------------");

        System.out.println("Bus lines that go through "+station.getName()+ " are:");
        for(BusLine bl : arrLines){
            System.out.println(bl.getName());
            System.out.println("With the time arrivals of:" );
            Station station1 = bl.getStation(station.getName());
            System.out.println(station1.getArrival());
        }
    }
    //Prints out all departures in a station
    public static void getStationDepartures(Station station){
        List<BusLine> arrLines = new LinkedList<>();
        for(BusLine s : busLines){
            if(s.containsStation(station)){
                arrLines.add(s);
//                arrStat.add(s.getLineStations().get(s.indexOfStationinBusLine(station,s)));
            }
        }
        System.out.println("-----------------DEPARTURES------------------------------");
        System.out.println("Bus lines that go through "+station.getName()+ " are:");
        for(BusLine bl : arrLines){
            System.out.println(bl.getName());
            System.out.println("With the time departures of:" );
            Station station1 = bl.getStation(station.getName());
                LocalTime lt = station1.getDeparture();
                if(!bl.isEndingStation(station1))
                    System.out.println(station1.getDeparture());
                else
                    System.out.println("This is the last station");
        }
    }
    //prints all bus lines
    public static void printAllLines(){
        System.out.println("------------------ACTIVE BUS LINES-----------------");
        for(BusLine busLine : busLines){
            System.out.println(busLine.getName());
            System.out.println(busLine.getLineStations());
        }
    }
    //method that restores bus lines from a .dat file
    private static void restoreBusLinesDat() {
		boolean eof = false;
    	try(DataInputStream in = new DataInputStream(new FileInputStream("buslinesdat.dat"))){	
    		while(!eof) {
    		String name =  in.readUTF();
    		int id = in.readInt();
    		int amountOfStations = in.readInt();
    		BusLine bl = new BusLine(name,id);
    		for(int i=0;i<amountOfStations;i++) {
    			String statName = in.readUTF();
    			int stId = in.readInt();
    			int arrHour = in.readInt();
    			int arrMin = in.readInt();
    			int depHour = in.readInt();
    			int depMin = in.readInt();	
    			Station station = getStation(statName);
    			bl.addStation(verifyStation(station.getName()));
    			bl.addArrival(arrHour, arrMin, bl.getStation(station.getName()));
    			bl.addDeparture(depHour, depMin, bl.getStation(station.getName()));
    		}
    	
    		busLines.add(bl);
    		}
    	}catch(EOFException e) {
    		eof=true;
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
    //method that saves bus lines in a .dat file
    private static void saveBusLinesDat() {
    	try(DataOutputStream out = new DataOutputStream(new FileOutputStream("buslinesdat.dat"))){
    		for(BusLine busline :busLines) {
    			out.writeUTF(busline.getName());
    			out.writeInt(busline.getId());
    			out.writeInt(busline.getLineStations().size());
    			
    			for(Station station : busline.getLineStations()) {
    				out.writeUTF(station.getName());
    				out.writeInt(station.getId());
    				out.writeInt(station.getArrival().getHour());
    				out.writeInt(station.getArrival().getMinute());
    				out.writeInt(station.getDeparture().getHour());
    				out.writeInt(station.getDeparture().getMinute());
    			}
    		}
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //method that saves/serializes all busline objects
    private static void saveSerializable() {
    	try(ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("buslinesdatS.dat")))){
    		for(BusLine busline : busLines) {
    			locFile.writeObject(busline);
    		}
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //method that restores serialized bus lines
    private static void restoreSerializable() {
    	try(ObjectInputStream lofFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("buslinesdatS.dat")))){
    		boolean eof = false;
    		while (!eof) {
    			try {
    				BusLine busline = (BusLine) lofFile.readObject();
    				System.out.println(busline.getName());
    				busLines.add(busline);
    			}catch(EOFException e) {
    				System.out.println("eof");
    				eof=true;
    			} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //method that saves bus lines in a text file
    private static void saveBusLines() {
    	try (BufferedWriter br = new BufferedWriter(new FileWriter("buslines.txt"))){
    	for(BusLine busline : busLines) {
    		br.write(busline.getName()+","+busline.getId()+","+busline.getLineStations().size()+"\n");
    		for(Station station : busline.getLineStations()) {
    			br.write(station.getName()+","+station.getId()+","+station.getArrival().getHour()+","+station.getArrival().getMinute()+
    					","+station.getDeparture().getHour()+","+station.getDeparture().getMinute()+"\n");
    		}
    		System.out.println(busline.getName()+" saved.");
    	}
    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private static void restoreBusLines(){
                try(BufferedReader bf = new BufferedReader(new FileReader("buslines.txt"))){
        	String input;
        	while((input = bf.readLine())!=null) {
            	System.out.println("While");

        		String[] data = input.split(",");
        		String lineName = data[0];
        		String lineId = data[1];
        		BusLine busline = new BusLine(lineName,Integer.parseInt(lineId));
        		int n = Integer.parseInt(data[2]);
        		int i=0;

        		while(i<n) {
        			input = bf.readLine();
        			data = input.split(",");
        			String stationName = data[0];
            		String stationId = data[1];
            		String arrivalHour = data[2];
            		String arrivalMin = data[3];
            		String departureHour = data[4];
            		String depMin = data[5];
            		Station station =  getStation(stationName);//new Station (stationName, Integer.parseInt(stationId));
        			busline.addStation(verifyStation(stationName));
            		busline.addArrival(Integer.parseInt(arrivalHour), Integer.parseInt(arrivalMin), busline.getStation(station.getName()));
            		busline.addDeparture(Integer.parseInt(departureHour), Integer.parseInt(depMin), busline.getStation(station.getName()));
            		i++;
        		}
        		busLines.add(busline);
        	}
        } catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
}
