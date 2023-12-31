package main.java.pc.impl;

import main.java.pc.abstracts.IRAMFrames;

import java.util.Arrays;

public class RamFrames implements IRAMFrames{

	private final int frameSize;
	private final boolean[] perMByte;
	public RamFrames(int frameSize) {
		this.frameSize = frameSize;
		this.perMByte=new boolean[this.frameSize];
		
	}

	
	@Override
	public void truncateFrame() {
        Arrays.fill(perMByte, false);
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
			if(b) return true;
		}
		return false;
	}
	


}
