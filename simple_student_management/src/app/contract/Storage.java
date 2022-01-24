package app.contract;

public interface Storage {

    public void store(String key, RawData value);

    public void delete(String key);

    public boolean exists(String key);

    public void clear();

    public RawData get(String key,RawData type);

    public RawData[] all(RawData type);

}
