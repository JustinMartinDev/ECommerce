/*
 * File: HibernateUtil.java
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * The contents of this file are subject to the terms and conditions of
 * the Common Development and Distribution License 1.0 (the "License").
 *
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the License by consulting the LICENSE.txt file
 * distributed with this file, or by consulting https://oss.oracle.com/licenses/CDDL
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file LICENSE.txt.
 *
 * MODIFICATIONS:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 */
 
package commerce.catalogue.domaine.utilitaire ;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.Entity;
import java.util.Set;

import org.reflections.Reflections;


/**
 * Source copied from http://docs.jboss.org/hibernate/orm/4.2/manual/en-US/html/ch01.html
 * http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html_single/#tutorial-firstapp-helpers
 * and adapted for functional testing of coherence-hibernate-second-level-cache.
 */
public class HibernateUtil
{

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

		//System.out.println(getEntityClasses("commerce.catalogue.domaine.modele").stream().count()) ;
        try {
			Configuration configuration = new Configuration();
            // Create the SessionFactory from hibernate.cfg.xml
            //return new Configuration().configure().buildSessionFactory();
			configuration.configure();
			Reflections reflections = new Reflections("commerce.catalogue.domaine.modele");
			
			// Create your SessionFactory with mappings for every `Entity` in a specific package
			Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);

			for(Class<?> clazz : classes) {
				configuration.addAnnotatedClass(clazz);
			}
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			return configuration.buildSessionFactory(serviceRegistry);	
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}