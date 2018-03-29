package commerce.catalogue.domaine.modele;

import javax.persistence.*;

/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * Author  A. Corbière
 * Version 2.0, 23/09/10
 */

@Entity (name="commerce.catalogue.domaine.modele.Account")
@DiscriminatorValue("account")
public class Account {
	private String refAccount;
	private String username;
	private String email;
	private String password; //todo hash

	public Account(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@Id
	public String getRefAccount() {
		return refAccount;
	}
	public void setRefAccount(String refAccount) {
		this.refAccount = refAccount;
	}

	@Basic
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean equals(Object o) {
		boolean retour = false ;
		if (!(o instanceof Account))
			retour = false ;
		else {
			Account inAccount = (Account)o ;
			if (this.getRefAccount().equals(inAccount.getRefAccount()))
				retour = true ;
			else
				retour = false ;
		}
		return retour ;
	}
}
