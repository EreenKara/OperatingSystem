package main.java.pc.impl;

import main.java.pc.abstracts.IRAMFrames;

public class RamFrames implements IRAMFrames{

	private int frameSize;
	private boolean[] perMByte;
	public RamFrames(int frameSize) {
		this.frameSize = frameSize;
		this.perMByte=new boolean[frameSize];
		
	}

	
	@Override
	public void truncateFrame() {
		for (boolean b : perMByte) {
			b=false;
		}
	}

	@Override
	public void allocateFrame(int count) {
		for (int i = 0;i<count;i++) {
			perMByte[i]=true;
		}
		
	}
	@Override
	public boolean checkAllocated() {
		for (boolean b : perMByte) {
			if(b==true) return true;
		}
		return false;
	}
	


}
