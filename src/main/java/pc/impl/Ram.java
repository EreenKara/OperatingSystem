package main.java.pc.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import main.java.os.abstracts.IPCB;
import main.java.pc.abstracts.IRAM;
import main.java.pc.abstracts.IRAMFrames;

public class Ram implements IRAM {
	   private int TotalRam;
	   public int ramFrameSize;
	   private IRAMFrames[] RamFrames;
       private Dictionary<Integer, IPCB> allocated ;
       
	public Ram(int totalRam) {
		this.ramFrameSize=8;
		this.TotalRam=totalRam;
		for(int i = 0 ; i<totalRam/ramFrameSize;i++)
		{
			RamFrames[i]=new RamFrames(ramFrameSize);
		}
		allocated= new Hashtable<Integer, IPCB>();
	}
	
	   private IPCB getPCBByID(int processID) {
	        return allocated.get(processID);
	    }
	   public Status checkStatus(int memorysize) {
			if(memorysize>TotalRam||memorysize<0) {
				return Status.DELETED;		   
			}
			int frameMiktari=(int)(Math.ceil(memorysize/8));
			int count =0;
			for(int i = 0;i<TotalRam/ramFrameSize;i++) {
				if(!(RamFrames[i].checkAllocated())) count++;
				if(count==frameMiktari) return Status.ALLOCATED;;
			}
			return Status.WAITED;
	   }
	   public Dictionary<Integer, Integer> allocate(int memorysize){
		   int frameMiktari=(int)(Math.ceil(memorysize/8));
		   Dictionary<Integer,Integer> pageTable= new Hashtable<Integer, Integer>();
		   int count =0;
		   for(int i = 0;i<TotalRam/ramFrameSize;i++) {
				if(!(RamFrames[i].checkAllocated())) {
					pageTable.put(count, i);
					count++;
					RamFrames[i].allocateFrame(8);
					if(count == frameMiktari) break;
				}
			
			}
		   return pageTable;
		   
	   }

//	public boolean CheckPCB() {
//		IPCB pcb;
//        if (pcb == null) {
//            System.out.println("Invalid PCB.");
//            return false;
//        }
//
//        if (allocated.containsKey(pcb.getProcessId())) {
//            System.out.println("Process ID " + pcb.getProcessId() + " is already allocated.");
//            return false;
//        }
//
//        if (pcb.getMemorySize() <= 0 || pcb.getMemorySize() > TotalRam) {
//            System.out.println("Invalid memory size for Process ID " + pcb.getProcessId() + ".");
//            return false;
//        }
//
//        // İlgili işlemin bellek alanlarını işaretleyerek tahsis et
//        int startIndex = findAvailableMemory(pcb.getMemorySize());
//        if (startIndex == -1) {
//            System.out.println("Insufficient memory for Process ID " + pcb.getProcessId() + ".");
//            return false;
//        }
//
//        for (int i = 0; i < pcb.getMemorySize(); i++) {
//            RamFrames[startIndex + i] = true;
//        }
//
//        // IPCB'yi bellek bloğuyla ilişkilendir
//        allocated.put(pcb.getProcessId(), pcb);
//
//        System.out.println("Process ID " + pcb.getProcessId() + " allocated successfully.");
//        return true;
//    }
//	
//	  private int findAvailableMemory(int requiredMemory) {
//	        int consecutiveFreeFrames = 0;
//	        for (int i = 0; i < TotalRam; i++) {
//	            if (!RamFrames[i]) {
//	                consecutiveFreeFrames++;
//	                if (consecutiveFreeFrames == requiredMemory) {
//	                    return i - requiredMemory + 1;
//	                }
//	            } else {
//	                consecutiveFreeFrames = 0;
//	            }
//	        }
//	        return -1; // Yeterli bellek alanı bulunamadı
//	    }
	
	   
	   

	@Override
	public IPCB search(int processID) {
		return allocated.get(processID);
	}
    // sonradan
 	public int getTotalRam() {
	return  TotalRam;
	}
	public void setTotalRam(int TotalRam) 
	{
		this.TotalRam=TotalRam;
	}
	
	public IRAMFrames[] getRamFrames() {
	return  RamFrames;
	}
	public void setRamFrames(IRAMFrames[] RamFrames)
	{
		this.RamFrames=RamFrames;
	}
	@Override
	public boolean addPCB(IPCB pcb) {
		allocated.put(pcb.getProcessId(),pcb);
		return true;
	}
	
}
