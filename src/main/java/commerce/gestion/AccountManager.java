package commerce.gestion;

import org.hibernate.Session;
import org.hibernate.Query;

import commerce.catalogue.domaine.modele.Account;
import commerce.catalogue.domaine.utilitaire.HibernateUtil;
import commerce.catalogue.domaine.utilitaire.UniqueKeyGenerator;
import commerce.web.utilitaire.Password;

public class AccountManager {

    private Account account;

    public void soumettreAccount(Account inAccount){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
        try {
            session.beginTransaction();
            if (inAccount.getRefAccount() == null) {
                inAccount.setRefAccount(new UniqueKeyGenerator().getUniqueId()); ;
                session.save(inAccount) ;
            }
            else {
                session.saveOrUpdate(inAccount) ;
            }
            session.getTransaction().commit();
            account = inAccount;
        }
        catch (RuntimeException e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            throw e;
        }
    }

    public Account rechercherAccountByEmail(String email){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
        Account accountSearch;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from commerce.catalogue.domaine.modele.Account a where a.email = :email");
            query.setString("email", email);
            accountSearch = (Account) query.uniqueResult();
        }
        catch (RuntimeException e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            throw e;
        }
        return accountSearch;
    }

    public Account getAccount() {
        return account;
    }

    public boolean connect(String email, String password){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
        Account accountConnect;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from commerce.catalogue.domaine.modele.Account a where a.email = :email");
            query.setString("email", email);
            accountConnect = (Account) query.uniqueResult();
            if(accountConnect != null && Password.check(accountConnect.getPassword(), password)) return true;
            else return false;
        }
        catch (RuntimeException e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
