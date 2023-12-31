package main.java.pc.impl;

import java.util.Hashtable;
import java.util.Map;

import main.java.os.abstracts.IPCB;
import main.java.pc.abstracts.IRAM;
import main.java.pc.abstracts.IRAMFrames;

public class Ram implements IRAM {
	   private final int totalRam;
	   public int ramFrameSize;
	   private final IRAMFrames[] ramFrames;
       private final Map<Integer, IPCB> allocated ;
       
	public Ram(int totalRam) {
		this.ramFrameSize=8;
		this.totalRam =totalRam;
		int size=totalRam/ramFrameSize;
		ramFrames= new RamFrames[size];
		for(int i = 0 ; i<size;i++)
		{
			ramFrames[i]=new RamFrames(ramFrameSize);
		}
		allocated= new Hashtable<>();
	}
	

	   public Status checkStatus(int memorySize) {
			if(memorySize> totalRam ||memorySize<0) {
				return Status.DELETED;		   
			}
			int frameMiktari=(int)(Math.ceil((double)memorySize/8));
			int count =0;
			for(int i = 0; i< totalRam /ramFrameSize; i++) {
				if(!(ramFrames[i].checkAllocated())) count++;
				if(count==frameMiktari) return Status.ALLOCATED;
			}
			return Status.WAITED;
	   }

	@Override
	public Map<Integer, IPCB> getPCBList() {
		return allocated;
	}

	public Map<Integer, Integer> allocate(int memorySize){
		   int frameMiktari=(int)Math.ceil((double)memorySize/8);
		   Map<Integer,Integer> pageTable= new Hashtable<>();
		   int count =0;
		   for(int i = 0; i< totalRam /ramFrameSize; i++) {
				if(!(ramFrames[i].checkAllocated())) {
					pageTable.put(count, i);
					count++;
					ramFrames[i].allocateFrame(8);
					if(count == frameMiktari) break;
				}
			
			}
		   return pageTable;
		   
	   }
	   public void deAllocate(Map<Integer,Integer> map){
		   for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			   ramFrames[entry.getValue()].truncateFrame();
		   }
	   }

	@Override
	public IPCB search(int processID) {
		return allocated.get(processID);
	}
    // sonradan


	@Override
	public void addPCB(IPCB pcb) {
		allocated.put(pcb.getProcessId(),pcb);
	}
	
}
