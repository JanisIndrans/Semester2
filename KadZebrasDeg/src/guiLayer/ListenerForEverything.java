package guiLayer;
import java.util.EventListener;

import exceptionsLayer.DatabaseException;


public interface ListenerForEverything extends EventListener {
	public void AnyEventOcurred(AnyEvent anyEvent) throws DatabaseException;

}
