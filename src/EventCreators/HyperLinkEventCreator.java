package EventCreators;


import EventListeners.HyperLinkListener;

public interface HyperLinkEventCreator extends EventCreator{

	public void addListener(HyperLinkListener listener);

	public  void removeListener(HyperLinkListener listener) ;

	
}
