package main.java.pc.abstracts;

public interface IRAMFrames {
	boolean checkAllocated();
    void truncateFrame();
    void allocateFrame(int count);

}
