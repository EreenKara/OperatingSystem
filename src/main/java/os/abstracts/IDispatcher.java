package main.java.os.abstracts;

public interface IDispatcher {
    // eski process'i döndür.
    // yeni process'i CPU'ya ver.
    public IProcess ContextSwitch(IProcess yeniProcess);
}
