package main.java.os.impl;

import java.util.*;

import main.java.iodevices.abstracts.ICDDrive;
import main.java.iodevices.abstracts.IIODevice;
import main.java.iodevices.abstracts.IModem;
import main.java.iodevices.abstracts.IPrinter;
import main.java.iodevices.abstracts.IScanner;
import main.java.os.abstracts.IPCB;
import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.IProcessManager;
import main.java.pc.abstracts.IRAM;
import main.java.pc.impl.Status;
import main.java.utility.abstracts.IObserver;
import main.java.utility.enums.State;
import main.java.utility.impl.Publisher;

public class ProcessManager implements IProcessManager, IObserver {

    private final IRAM ram;
    private final List<IPrinter> printers;//2
    private final List<IScanner> scanners;//1
    private final List<IModem> modems;//1
    private final List<ICDDrive> cdDrivers;//2
    private int pid;

    public ProcessManager(IRAM ram,List<IPrinter> printers,List<IScanner> scanners,List<IModem> modems,List<ICDDrive> cdDrives) {
        this.pid=0;
        this.ram = ram;
        this.printers=printers;
        this.scanners=scanners;
        this.modems=modems;
        this.cdDrivers =cdDrives;
		Publisher.attach(this);
    }
    private String generateColor() {
        int colorCode = pid;
        return Integer.toString(colorCode);
    }
    @Override //oluşturmak için
    public IProcess createProcess(String[] requirements) {
    	// , State state, int programCounter, int workingTime, String processColor, List<IIODevice> ioDevices, Map<Integer, Integer> memoryOccupiedPageTable
		String redColor = "\u001B[31m";
		String resetColor = "\u001B[0m";
		if(ram.checkStatus(Integer.parseInt(requirements[3]) )==Status.DELETED) {
			System.out.printf(redColor+"NOT ENOUGH RAM! PROCESS RAM: %s",requirements[3]);
			System.out.println(" -- PROCESS DELETED"+resetColor);
			return null;
		}
    	else if(printers.size() < Integer.parseInt(requirements[4])) {
			System.out.printf(redColor+"NOT ENOUGH PRINTERS! REQUIRED PRINTERS: %s",requirements[4]);
			System.out.println(" -- PROCESS DELETED"+resetColor);
			return null;}
    	else if(scanners.size() < Integer.parseInt(requirements[5])){
			System.out.printf(redColor+"NOT ENOUGH SCANNERS! REQUIRED SCANNERS: %s",requirements[5]);
			System.out.println(" -- PROCESS DELETED"+resetColor);
			return null;}
    	else if(modems.size() < Integer.parseInt(requirements[6])) {
			System.out.printf(redColor+"NOT ENOUGH MODEMS! REQUIRED MODEMS: %s",requirements[6]);
			System.out.println(" -- PROCESS DELETED"+resetColor);
			return null;
		}
    	else if(cdDrivers.size() < Integer.parseInt(requirements[7])) {
			System.out.printf(redColor+"NOT ENOUGH CD DRIVERS! REQUIRED CD DRIVERS: %s",requirements[7]);
			System.out.println(" -- PROCESS DELETED"+resetColor);
			return null;
		}

    	IProcess process= new Process(requirements,pid,Integer.parseInt(requirements[1]));
    	// processColor, List<IIODevice> ioDevices, Map<Integer, Integer> memoryOccupiedPageTable


    	if(ram.checkStatus(Integer.parseInt(requirements[3]))==Status.WAITED || !checkDevices(requirements)) {
    		IPCB pcb =new PCB(pid,State.WAITING,0,0,Integer.parseInt(requirements[2]),generateColor(),null,Integer.parseInt(requirements[0]),null);
    		ram.addPCB(pcb);
    		pid++;
    		return process;
    	}


		 // Allocated
		List<IIODevice> liste=new ArrayList<>();
		int count =0;
		for (var data : printers) {
			if(count != Integer.parseInt(requirements[4])) {
				data.allocate(process);
				liste.add(data);
				count++;
			}
		}
		count=0;
		for (var data : scanners) {
			if(count != Integer.parseInt(requirements[5])) {
				data.allocate(process);
				liste.add(data);
				count++;
			}
		}
		count=0;
		for (var data : modems) {
			if(count != Integer.parseInt(requirements[6])) {
				data.allocate(process);
				liste.add(data);
				count++;
			}
		}
		count=0;
		for (var data : cdDrivers) {
			if(count != Integer.parseInt(requirements[7])) {
				data.allocate(process);
				liste.add(data);
				count++;
			}
		}



		IPCB pcb =new PCB(pid,State.READY,0,0,Integer.parseInt(requirements[2]),generateColor(),liste,Integer.parseInt(requirements[0]),ram.allocate(Integer.parseInt(requirements[3])));
		ram.addPCB(pcb);
		pid++;
		return process;


    }
    private boolean checkDevices(String[] requirements) {
    	int wantedPrinter=Integer.parseInt(requirements[4]);
    	int wantedScanner=Integer.parseInt(requirements[5]);
    	int wantedModem=Integer.parseInt(requirements[6]);
    	int wantedCDDriver=Integer.parseInt(requirements[7]);
		if(!checkAvailabilityOfDevices(wantedPrinter,printers))
			return false;
    	if(!checkAvailabilityOfDevices(wantedScanner,scanners))
			return false;
    	if(!checkAvailabilityOfDevices(wantedModem,modems))
			return false;
    	if(!checkAvailabilityOfDevices(wantedCDDriver, cdDrivers))
			return false;
		return true;
    }
	private boolean checkAvailabilityOfDevices(int wantedDeviceAmount,List<? extends IIODevice> deviceList){
	 	int count=0;
		for (var data : deviceList) {
			if(data.checkStatus()) count++;
		}
		return count>=wantedDeviceAmount;
	}

    @Override
    public IProcess allocateDevicesAndRam(IProcess process) {
		if(checkDevices(process.getProcessProperties())&& ram.checkStatus(Integer.parseInt(process.getProcessProperties()[3]))==Status.ALLOCATED)
		{
			IPCB pcb = ram.search(process.getProcessId());

			pcb.setState(State.READY);
			pcb.setMemoryOccupiedPageTable(ram.allocate(Integer.parseInt(process.getProcessProperties()[3])));
			// Allocated
			List<IIODevice> liste=new ArrayList<>();
			int count =0;
			for (var data : printers) {
				if(count != Integer.parseInt(process.getProcessProperties()[4])) {
					data.allocate(process);
					liste.add(data);
					count++;
				}
			}
			count=0;
			for (var data : scanners) {
				if(count != Integer.parseInt(process.getProcessProperties()[5])) {
					data.allocate(process);
					liste.add(data);
					count++;
				}
			}
			count=0;
			for (var data : modems) {
				if(count != Integer.parseInt(process.getProcessProperties()[6])) {
					data.allocate(process);
					liste.add(data);
					count++;
				}
			}
			count=0;
			for (var data : cdDrivers) {
				if(count != Integer.parseInt(process.getProcessProperties()[7])) {
					data.allocate(process);
					liste.add(data);
					count++;
				}
			}
			pcb.setIoDevices(liste);

		}

        return process;
    }

	@Override
	public void update() {
		Iterator<Map.Entry<Integer, IPCB>> iterator = ram.getPCBList().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, IPCB> entry = iterator.next();
			IPCB pcb = entry.getValue();

			if (pcb.getState() == State.TERMINATED) {
				pcb.getIoDevices().forEach(IIODevice::deAllocate);
				ram.deAllocate(pcb.getMemoryOccupiedPageTable());

				// Remove the entry from the map using the iterator
				iterator.remove();
			}
		}
	}

	@Override
	public int getSequenceNumber() {
		return 0;
	}






}
