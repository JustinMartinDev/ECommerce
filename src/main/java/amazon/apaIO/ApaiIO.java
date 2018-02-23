package amazon.apaIO;

import amazon.apaIO.io.HttpResourceConnection;
import amazon.apaIO.configuration.ConfigurationInterface;
import amazon.apaIO.operations.Search ;
import amazon.apaIO.request.rest.Request ;

public class ApaiIO {
	
    /**
     * Configuration
     */
    protected ConfigurationInterface configuration;
    
    /**
     * ResourceConnection
     */
    protected HttpResourceConnection resourceConnection;
    /**
     * @param ConfigurationInterface $configuration
     */
    public ApaiIO(HttpResourceConnection resourceConnection)
    {
    	this.resourceConnection = resourceConnection;
    }
    /**
     */
    public ApaiIO()
    {
    	this.resourceConnection = new HttpResourceConnection();
    }
    /**
     * @param ConfigurationInterface $configuration
     */
    public void setConfiguration(ConfigurationInterface configuration)
    {
        this.configuration = configuration;
    }
    
    /**
     * Runs the given operation
     */
    public String runOperation(Search operation)
    {
        Request requestObject = new Request(resourceConnection) ;
        requestObject.setConfiguration(this.configuration);
        String response = requestObject.perform(operation);
        return response ;
        //return $this->applyResponseTransformer($response, $configuration);
    }
    /**
     * Applies a responsetransformer
     *
     * @param mixed                  $response      The response of the request
     * @param ConfigurationInterface $configuration The configurationobject
     *
     * @return mixed
     */
    /*
    protected function applyResponseTransformer($response, ConfigurationInterface $configuration)
    {
        if (true === is_null($configuration->getResponseTransformer())) {
            return $response;
        }
        $responseTransformer = ResponseTransformerFactory::createResponseTransformer($configuration);
        return $responseTransformer->transform($response);
    }*/

}
