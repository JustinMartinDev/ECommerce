<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.gestion.AccountManager" %>
<%@ page import="commerce.catalogue.domaine.modele.Account" %>
<%@ page import="commerce.web.utilitaire.Password" %>
<%
    final String ERREUR_ACCOUNT_ALREADY = "error_account_already";
    final String ERREUR_PASSWORD_DIFF = "error_password_diff";
    final String ERREUR_CHECKBOX = "error_checkbox";
    final String ERREUR_CHAMPS = "error_champs";




    if(request.getParameter("email")!=null
            && request.getParameter("password")!=null
            && request.getParameter("repassword")!=null
            && request.getParameter("checkbox")!=null
            && request.getParameter("username")!=null){
        AccountManager accountManager = new AccountManager();
        if(accountManager.rechercherAccountByEmail(request.getParameter("email").replaceAll("\\<.*?\\>", ""))!=null)
            out.println(ERREUR_ACCOUNT_ALREADY);
        else {
            if(!request.getParameter("password").equals(request.getParameter("repassword")))
                out.println(ERREUR_PASSWORD_DIFF);
            else {
                if (!request.getParameter("checkbox").equals("on"))
                    out.println(ERREUR_CHECKBOX);
                else {
                    try {
                        accountManager.soumettreAccount(new Account(
                                request.getParameter("username").replaceAll("\\<.*?\\>", ""),
                                request.getParameter("email").replaceAll("\\<.*?\\>", ""),
                                Password.getSaltedHash(request.getParameter("password").replaceAll("\\<.*?\\>", ""))));

                        out.println("success");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    else {
        out.println(ERREUR_CHAMPS);
    }
%>