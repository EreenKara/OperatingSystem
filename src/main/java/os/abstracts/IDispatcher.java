package main.java.os.abstracts;

public interface IDispatcher {
    // eski process'i döndür. Scheduling'e döndür.
    // yeni process'i CPU'ya ver.
    IProcess ContextSwitch(IProcess newProcess);
    boolean isProcessInFCFS();
}
