package com.purchasing.support.user;

/**
 * @author vanessa
 */
public class BodyEmail {

    private String nameUser;
    private String newPassword;
    
    public BodyEmail(String nameUser, String newPassword) {
        this.nameUser = nameUser;
        this.newPassword = newPassword;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Object getBody(){
        Object body= "<html>" +
                "<head></head>" +
                "<body>"+
                "<p>Ola <b> " + this.getNameUser() + "</b>,</p> "+
                "<p> Você solicitou uma nova senha para o sistema de compras, sua nova senha é: <b>"+this.getNewPassword()+"</b>.</p> </br>"+
                "<h5> Por favor não responda este e-mail, pois o mesmo não será recebido, qualquer duvida entre em contado com o setor de TI. </h5> </body></html>";
        return body;
    }

}
