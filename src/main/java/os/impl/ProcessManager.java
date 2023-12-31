package main.java.os.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import main.java.iodevices.abstracts.ICDDrive;
import main.java.iodevices.abstracts.IIODevice;
import main.java.iodevices.abstracts.IModem;
import main.java.iodevices.abstracts.IPrinter;
import main.java.iodevices.abstracts.IScanner;
import main.java.os.abstracts.IPCB;
import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.IProcessManager;
import main.java.pc.abstracts.IRAM;
import main.java.pc.impl.Ram;
import main.java.pc.impl.Status;
import main.java.utility.enums.State;

public class ProcessManager implements IProcessManager {

    private final IRAM ram;
    private List<IPrinter> printers;//2
    private List<IScanner> scanners;//1
    private List<IModem> modems;//1
    private List<ICDDrive> cdDrives;//2
    private int pid;

    public ProcessManager(IRAM ram,List<IPrinter> printers,List<IScanner> scanners,List<IModem> modems,List<ICDDrive> cdDrives) {
        this.pid=0;
        this.ram = ram;
        this.printers=printers;
        this.scanners=scanners;
        this.modems=modems;
        this.cdDrives=cdDrives;
    }
    private String generateColor() {
        int colorCode = new Random().nextInt()%256;
        return Integer.toString(colorCode);
    }
    @Override //oluşturmak için
    public IProcess createProcess(String[] requirements) {
    	// , State state, int programCounter, int workingTime, String processColor, List<IIODevice> ioDevices, Map<Integer, Integer> memoryOccupiedPageTable
    	if(ram.checkStatus(Integer.parseInt(requirements[3]) )==Status.DELETED) return null; 
    	else if(printers.size() < Integer.parseInt(requirements[4])) return null;
    	else if(scanners.size() < Integer.parseInt(requirements[5])) return null;
    	else if(modems.size() < Integer.parseInt(requirements[6])) return null;
    	else if(cdDrives.size() < Integer.parseInt(requirements[7])) return null;
    	
    	IProcess process= new Process(requirements,pid,Integer.parseInt(requirements[1]));    	
    	// processColor, List<IIODevice> ioDevices, Map<Integer, Integer> memoryOccupiedPageTable
    	
    	
    	if(ram.checkStatus(Integer.parseInt(requirements[3]))==Status.WAITED || !checkDevices(requirements)) {
    		IPCB pcb =new PCB(Integer.parseInt(requirements[1]),State.WAITING,0,0,generateColor(),null,null,Integer.parseInt(requirements[0]));
    		ram.addPCB(pcb);
    		pid++;
    		return process;
    	}
    	
    	
		 // Allocated
		List<IIODevice> liste=new ArrayList<IIODevice>();
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
		for (var data : cdDrives) {
			if(count != Integer.parseInt(requirements[7])) {
				data.allocate(process);
				liste.add(data);
				count++;
			}
		}
		
		
		
		IPCB pcb =new PCB(Integer.parseInt(requirements[1]),State.READY,0,0,generateColor(),liste,ram.allocate(Integer.parseInt(requirements[3])),Integer.parseInt(requirements[0]));
		ram.addPCB(pcb);
		pid++;
		return process;
    	

    }
    private boolean checkDevices(String[] requirements) {
    	int wantedPrinter=Integer.parseInt(requirements[4]);
    	int wantedScanner=Integer.parseInt(requirements[5]);
    	int wantedModem=Integer.parseInt(requirements[6]);
    	int wantedCDDriver=Integer.parseInt(requirements[7]);
    	int count=0;
    	for (var data : printers) {
    		if(data.checkStatus()) count++;
		}
    	if(count < wantedPrinter) return false;
    	count=0;
    	for (var data : scanners) {
    		if(data.checkStatus()) count++;
		}
    	if(count < wantedScanner) return false;
    	count=0;
    	for (var data : modems) {
    		if(data.checkStatus()) count++;
		}
    	if(count < wantedModem) return false;
    	count=0;
    	for (var data : cdDrives) {
    		if(data.checkStatus()) count++;
		}
    	if(count < wantedCDDriver) return false;
    	
    	return true;
    }
    @Override
    public IProcess allocateDevicesAndRam(IProcess process) {
		if(checkDevices(process.getProcessProperties())&& ram.checkStatus(Integer.parseInt(process.getProcessProperties()[3]))==Status.ALLOCATED)
		{
			IPCB pcb = ram.search(process.getProcessId());

			pcb.setState(State.READY);
			pcb.setMemoryOccupiedPageTable(ram.allocate(Integer.parseInt(process.getProcessProperties()[3])));
			// Allocated
			List<IIODevice> liste=new ArrayList<IIODevice>();
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
			for (var data : cdDrives) {
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
    private int getUniqueProcessId()
    {
        return pid++;
    }
    
    
//    //sonlandırmak için
//    public boolean terminateProcess(int processID) {
//        IPCB terminatedProcess = null;
//
//        for (IPCB process : processes) {
//            if (process.getProcessId() == processID) {
//                terminatedProcess = process;
//                break;
//            }
//        }
//
//        if (terminatedProcess != null) {
//            // İşlemi sonlandır
//            processes.remove(terminatedProcess);
//            System.out.println("Process terminated successfully - Process ID: " + processID);
//            return true;
//        } else {
//            System.out.println("Process not found - Process ID: " + processID);
//            return false;
//        }
//    }
    
//    public void displayProcesses() {
//        System.out.println("Current Processes:");
//        for (IPCB process : processes) {
//            System.out.println("Process ID: " + process.getProcessId() +
//                    ", Memory Size: " + process.getMemorySize()); 
//        }
//    }
    
//    public boolean addProcess(int processID, int memorySize) {
//    	// ??
//
//        // İşlemi MemoryManager'a ekleyerek bellek tahsis et
//        boolean allocationResult = PCB.addPCB(newProcess);
//
//        if (allocationResult) {
//            processes.add(newProcess);
//            System.out.println("Process added successfully - Process ID: " + processID +
//                    ", Memory Size: " + memorySize);
//            return true;
//        } else {
//            System.out.println("Failed to add process - Process ID: " + processID +
//                    ", Memory Size: " + memorySize);
//            return false;
//        }
//    }






}
