package amazon.apaIO.configuration;

public class GenericConfiguration implements ConfigurationInterface {
	/**
     * The country
     */
    protected String country = "fr" ;
    /**
     * The accesskey
     */
    protected String accessKey;
    /**
     * The string
     */
    protected String secretKey;
    /**
     * The associate Tag
     */
    protected String associateTag = "ASSOCIATE" ;
    /**
     * The end point
     */
    protected String endPoint;
    /**
     * The requestclass
     * By default its set to the provided restful request
     */
    protected String request = "\\ApaiIO\\Request\\Rest\\Request";
    /**
     * The responsetransformerclass
     * By default its set to null which means that no transformer will be applied
     */
    protected String responseTransformer = null;

    public String getCountry()
    {
        return this.country;
    }
    /**
     * Sets an validates the country
     */
    public void setCountry(String country)
    {
        Country.isValidCountry(country, false);
        this.country = country.toLowerCase();
    }
    /**
     * {@inheritdoc}
     */
    public String getAccessKey()
    {
        return this.accessKey;
    }
    /**
     * Sets the accesskey
     */
    public void setAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
    }
    /**
     * {@inheritdoc}
     */
    public String getSecretKey()
    {
        return this.secretKey;
    }
    /**
     * Sets the secretkey
     *
     */
    public void setSecretKey(String secretKey)
    {
        this.secretKey = secretKey;
    }
    /**
     * {@inheritdoc}
     */
    public String getAssociateTag()
    {
        return this.associateTag;
    }
    /**
     * Sets the associatetag
     */
    public void setAssociateTag(String associateTag)
    {
        this.associateTag = associateTag;
    }
    /**
     * {@inheritdoc}
     */
    public String getRequest()
    {
        return this.request;
    }
    /**
     * Sets the requestclass
     */
    public void setRequest(String request)
    {
        this.request = request;
    }
    /**
     * {@inheritdoc}
     */
    public String getEndPoint()
    {
        return this.endPoint;
    }
    /**
     * Sets the requestclass
     */
    public void setEndPoint(String endPoint)
    {
        this.endPoint = endPoint;
    }
    /**
     * {@inheritdoc}
     */
    public String getResponseTransformer()
    {
        return this.responseTransformer;
    }
    /**
     * Sets the responsetransformerclass
     */
    public void setResponseTransformer(String responseTransformer)
    {
        this.responseTransformer = responseTransformer;
    }

}
