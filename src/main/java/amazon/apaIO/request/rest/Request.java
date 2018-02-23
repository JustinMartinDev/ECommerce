package amazon.apaIO.request.rest;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import amazon.apaIO.configuration.ConfigurationInterface;
import amazon.apaIO.operations.Search;
import amazon.apaIO.request.RequestInterface;
import amazon.apaIO.request.SignedRequestsHelper;
import amazon.apaIO.io.HttpResourceConnection;
import amazon.apaIO.io.ResourceConnection ;

import java.security.NoSuchAlgorithmException ;
import java.security.InvalidKeyException ;

public class Request implements RequestInterface {

	/**
	 * The endpoint
	 */
	protected String endpoint = "webservices.amazon.%s";
	/**
	 * @var ConfigurationInterface
	 */
	protected ConfigurationInterface configuration;
	/**
	 * ResourceConnection
	 */
	protected ResourceConnection resourceConnection;

	public Request(ResourceConnection resourceConnection)
	{
		this.resourceConnection = resourceConnection;
	}
	/**
	 * {@inheritdoc}
	 */
	public void setConfiguration(ConfigurationInterface configuration)
	{
		this.configuration = configuration;
	}
	/**
	 * {@inheritdoc}
	 */
	public String perform(Search operation)
	{	
		try {
			String requestUrl = new String() ;
			if (this.configuration.getEndPoint() == null)
				requestUrl = requestUrl.format(this.endpoint, this.configuration.getCountry()) ;
			else
				requestUrl = requestUrl.format(this.configuration.getEndPoint(), this.configuration.getCountry()) ;

			SignedRequestsHelper helper = SignedRequestsHelper.getInstance(requestUrl, this.configuration.getAccessKey(), this.configuration.getSecretKey());

			Map<String, String> preparedRequestParams = this.prepareRequestParams(operation);
			requestUrl = helper.sign(preparedRequestParams) ;

			String result = this.resourceConnection.getData(requestUrl) ;
			return result;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch(InvalidKeyException e) {
			e.printStackTrace();
		}
		return null ;
	}
	/**
	 * Prepares the parameters for the request
	 */
	protected Map<String, String> prepareRequestParams(Search operation)
	{
		Map<String, String> baseRequestParams = new HashMap<String, String>();
		baseRequestParams.put("Service", "AWSECommerceService");
		baseRequestParams.put("AWSAccessKeyId",  this.configuration.getAccessKey());
		baseRequestParams.put("AssociateTag",  this.configuration.getAssociateTag());
		baseRequestParams.put("Operation", operation.getName());
		baseRequestParams.put("Version",  "2011-08-01");

		baseRequestParams.put("SearchIndex",  operation.getParameter().get("SearchIndex"));
		baseRequestParams.put("Keywords",  operation.getParameter().get("Keywords"));
		baseRequestParams.put("ResponseGroup", operation.getParameter().get("ResponseGroup")) ;
		
		Map<String, String> fullParameterList = baseRequestParams ;
		return fullParameterList;
	}
}
