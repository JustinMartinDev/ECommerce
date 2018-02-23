package amazon.apaIO.configuration;

public interface ConfigurationInterface {
    /**
     * Gets the country
     *
     * @return string
     */
    public String getCountry();
    /**
     * Gets the accesskey
     *
     * @return string
     */
    public String getAccessKey();
    /**
     * Gets the secretkey
     *
     * @return string
     */
    public String getSecretKey();
    /**
     * Gets the associatetag
     *
     * @return string
     */
    public String getAssociateTag();
    /**
     * Gets the requestclass
     *
     * @return string
     */
    public String getRequest();
    /**
     * {@inheritdoc}
     */
    public String getEndPoint();
    /**
     * Gets the responsetransformerclass
     *
     * @return string
     */
    public String getResponseTransformer();
	 
}
