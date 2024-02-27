package controller;

import bo.custom.UserBo;
import bo.impl.UserBoImpl;
import dto.Userdto;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.Mail;
import util.SessionFactoryConfig;

import javax.mail.MessagingException;

import java.util.List;

//import static sun.security.krb5.internal.ccache.FileCredentialsCache.checkValidation;

public class UserFormController {

    public TextField txtID;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public PasswordField txtReEnPW;
    public Button btnCreate;
    public TextField txtEmail;

    UserBo userBo=new UserBoImpl();



    public void create_ONAction(ActionEvent actionEvent) throws MessagingException {
        String pass=txtPassword.getText ();
        String rePass=txtReEnPW.getText ();
        String userId = txtID.getText ();
        String userName = txtUserName.getText ();
        String email=txtEmail.getText ();


        if (checkDuplidate ()){
           // if (checkValidation ()){
                if(pass.equals (rePass)){
                    userBo.saveUser (new Userdto(
                            userId,
                            userName,
                            pass
                    ));
                    new Alert(Alert.AlertType.CONFIRMATION, "USER ACCOUNT CREATED SUCCUSS").show ();
                  Mail.outMail ("YOU ARE USER IN Online Libraray",email,"BookWorm");
                    clearFeilds();
                    setUserId ();

                }else {
                    new Alert (Alert.AlertType.ERROR, "Check your Password and Try Again").show ();
                }

    }else{
        new Alert (Alert.AlertType.ERROR, "THIS USER ID ALREADY GET").show ();
        clearFeilds ();
    }
        }
    public String nextUserID() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select userId from User order by userId desc");

        String nextId = "U001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("U00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.valueOf (id);

            id = "U00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }
    }

    private void setUserId() {
        String userID=nextUserID ();
        txtID.setText (userID);
    }

    private void clearFeilds() {
    }


    private boolean checkDuplidate() {
        String userId=txtID.getText ();
        List<Userdto> allRoom = userBo.loadAll ();
        for (Userdto u : allRoom) {
            if (userId.equals (u.getUserId ())){
                return false;
            }
        }
        return  true;

    }
}
