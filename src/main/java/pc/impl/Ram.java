package main.java.pc.impl;

import java.util.Hashtable;
import java.util.Map;

import main.java.os.abstracts.IPCB;
import main.java.pc.abstracts.IRAM;
import main.java.pc.abstracts.IRAMFrames;

public class Ram implements IRAM {
	   private final int totalRam;
	   public int ramFrameSize;
	   private final IRAMFrames[] ramFrames; // ilk 8 frame gerçek zamanlı processler için tahsil edilecek.
       private final Map<Integer, IPCB> allocated ;
	   private int frameFinishNumberForRealTimeProcessNeeds; // ilk 8 tane frame ( 0-7 ) Real time process'ler için ayrıldı.
       
	public Ram(int totalRam) {
		this.ramFrameSize=8;
		this.totalRam =totalRam; // Örneğin Toplam 1024 MB bellek
		// frameFinish şuanda User process için aranamya başlanması gereken index'i ifade ediyor.
		this.frameFinishNumberForRealTimeProcessNeeds=(64/ramFrameSize); // 64 MB real time için ayırdım.
		int size=(totalRam/ramFrameSize); // size => frame sayısı
		ramFrames= new RamFrames[size];
		for(int i = 0 ; i<size;i++)
		{
			ramFrames[i]=new RamFrames(ramFrameSize);
		}
		allocated= new Hashtable<>();
	}
	
		public Status checkStatusForRealTimeProcesses(int memorySize){
			if(memorySize> totalRam ||memorySize<0) {
				return Status.DELETED;
			}
			int frameMiktari=(int)(Math.ceil((double)memorySize/8));
			int count =0;
			for(int i = 0; i< frameFinishNumberForRealTimeProcessNeeds; i++) {
				if(!(ramFrames[i].checkAllocated())) count++;
				if(count==frameMiktari) return Status.ALLOCATED;
			}
			return Status.WAITED;
		}
	   public Status checkStatus(int memorySize) {
			if(memorySize> totalRam ||memorySize<0) {
				return Status.DELETED;		   
			}
			int frameMiktari=(int)(Math.ceil((double)memorySize/8));
			int count =0;
			for(int i = frameFinishNumberForRealTimeProcessNeeds; i< totalRam /ramFrameSize; i++) {
				if(!(ramFrames[i].checkAllocated())) count++;
				if(count==frameMiktari) return Status.ALLOCATED;
			}
			return Status.WAITED;
	   }

	@Override
	public Map<Integer, IPCB> getPCBList() {
		return allocated;
	}

	public Map<Integer,Integer> allocateForRealTimeProcesses(int memorySize){
		int frameMiktari=(int)Math.ceil((double)memorySize/8);
		Map<Integer,Integer> pageTable= new Hashtable<>();
		int count =0;
		for(int i = 0; i< frameFinishNumberForRealTimeProcessNeeds; i++) {
			if(!(ramFrames[i].checkAllocated())) {
				pageTable.put(count, i);
				count++;
				ramFrames[i].allocateFrame(8); // normalde burada 8 yerine tam olarak vermemiz gerek
				// ama frame'de 1 tane dolu olması ile bütün frame'in dolu olması
				// arasında fark olamdığından dolayı bunu böyle yapıp geçtik
				if(count == frameMiktari) break;
			}

		}
		return pageTable;
	}
	public Map<Integer, Integer> allocate(int memorySize){
		   int frameMiktari=(int)Math.ceil((double)memorySize/8);
		   Map<Integer,Integer> pageTable= new Hashtable<>();
		   int count =0;
		   for(int i = frameFinishNumberForRealTimeProcessNeeds; i< totalRam /ramFrameSize; i++) {
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
