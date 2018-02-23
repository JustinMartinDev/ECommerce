package amazon.apaIO.operations;

import java.util.HashMap;
import java.util.Map;

public class Search {
	
	private Map<String, String> parameter;
	
    public Search() {
    	this.parameter = new HashMap<String, String>();
    }
	
    /**
     * {@inheritdoc}
     */
    public Map<String, String> getParameter() {
        return this.parameter ;
    }
    
    /**
     * {@inheritdoc}
     */
    public String getName() {
        return "ItemSearch" ;
    }
    /**
     * Sets the amazon category
     */
    public void setCategory(String category)
    {
    	this.parameter.put("SearchIndex",  category);
    }
    /**
     * Sets the keywords
     */
    public void setKeywords(String keywords)
    {
    	this.parameter.put("Keywords",  keywords);
    }
    /**
     * Sets the response group
     */
    public void setResponseGroup(String responseGroup)
    {
    	this.parameter.put("ResponseGroup",  responseGroup);
    }
    /**
     * Sets the resultpage to a specified value
     * Allows to browse resultsets which have more than one page
     */
    public void setPage(String page)
    {
        /*if (false === is_numeric($page) || $page < 1 || $page > 10) {
            throw new \InvalidArgumentException(sprintf(
                '%s is an invalid page value. It has to be numeric, positive and between 1 and 10',
                $page
            ));
        }*/
        this.parameter.put("ItemPage",  page);
    }

}
