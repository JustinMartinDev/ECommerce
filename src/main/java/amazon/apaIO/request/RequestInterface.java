package amazon.apaIO.request;


import amazon.apaIO.configuration.ConfigurationInterface ;
import amazon.apaIO.operations.Search ;

public interface RequestInterface {
	/**
     * Sets the configurationobject
     */
    public void setConfiguration(ConfigurationInterface configuration);
    /**
     * Performs the request
     */
    public String perform(Search operation);
}
