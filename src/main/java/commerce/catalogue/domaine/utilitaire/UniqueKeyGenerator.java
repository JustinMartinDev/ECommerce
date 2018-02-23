/**
 * Title:        OpenUSS - Open Source University Support System
 * Description:  Utility for OpenUSS
 * Copyright:    Copyright (c) B. Lofi Dewanto
 * Company:      University of Muenster
 * @author  B. Lofi Dewanto
 * @version 1.0
 */
package commerce.catalogue.domaine.utilitaire ;

/**
 * Unique key generator.
 * This is used to calculate primary keys in all
 * the EJBs.
 *
 * @author  B. Lofi Dewanto
 * @version 1.0
 */
public class UniqueKeyGenerator {
    // Calculate the id
    private static long uniqueId = System.currentTimeMillis();

    /**
     * Get the id.
     */
    public static synchronized String getUniqueId() {
        return String.valueOf(uniqueId++);
    }
}