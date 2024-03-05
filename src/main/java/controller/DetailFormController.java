package controller;

import bo.custom.DetailBo;
import bo.impl.DetailBoImpl;
import dto.Bookdto;
import dto.Detaildto;
import dto.Userdto;
import dto.tm.DetailTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Calendar;
import java.util.List;

public class DetailFormController {
    public TextField txtDid;
    public DatePicker txtSdate;
    public DatePicker txtLastDate;
    public ComboBox cmbuserId;
    public ComboBox cmbBookId;
    public Button btnsave;
    public Button btnupdate;
    public Button btndelete;
    public TableView<DetailTm> tblMian;
    public TableColumn tblDid;
    public TableColumn tblSdate;
    public TableColumn tblLdate;
    public TableColumn tblUserId;
    public TableColumn tblBookId;

    DetailBo detailBo=new DetailBoImpl();

    public static ObservableList<Userdto> userId = FXCollections.observableArrayList ();

    public void initialize(){
        tblDid.setCellValueFactory (new PropertyValueFactory<>("dId"));
        tblSdate.setCellValueFactory (new PropertyValueFactory<> ("sdate"));
        tblLdate.setCellValueFactory (new PropertyValueFactory<> ("edate"));
        tblUserId.setCellValueFactory (new PropertyValueFactory<> ("userId"));
          tblBookId.setCellValueFactory (new PropertyValueFactory<> ("bookId"));

       setId();
        loadAllDeatls();

    }

    private void setId() {
            List<String> uId = detailBo.getUserIds();
            ObservableList student = FXCollections.observableArrayList (uId);
            cmbuserId.setItems (student);

            List<String> bookId = detailBo.getBookIds ();
            ObservableList room = FXCollections.observableArrayList (bookId);
            cmbBookId.setItems (room);

        }


    public void save_OnAction(ActionEvent actionEvent) {
        Userdto userdto = getUserDetail();
        Bookdto bookdto = getBookDetail();
        String detId =txtDid.getText();
        java.sql.Date sqlSate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        java.sql.Date sqlEDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        if (checkDuplicate()) {
            boolean isSaveDetail = detailBo.saveDetail (
                    new Detaildto(
                            detId,
                            sqlSate,
                            sqlEDate,
                            userdto,
                            bookdto
                    ));
            if (isSaveDetail) {
                Bookdto book = getBookDetail();

                loadAllDeatls();
            }
        }
    }

    private void loadAllDeatls() {
        try {
            List<Detaildto> alldetail = detailBo.loadAll ();

            ObservableList<DetailTm> resList = FXCollections.observableArrayList ();

            for (Detaildto dto : alldetail) {
                resList.add (new DetailTm (
                      dto.getDId(),
                        dto.getSdate().toString(),
                        dto.getEdate().toString(),
                        dto.getUser().getUserId(),
                        dto.getUser().getPassword(),
                        dto.getBook().getBId(),
                        dto.getBook().getTitle()


                ));
            }

            tblMian.setItems (resList);

            System.out.println (resList);


        } catch (Exception e) {
            System.out.println (e);
        }
    }

    private boolean checkDuplicate() {
        String resId = txtDid.getText ();
        List<Detaildto> list = detailBo.loadAll ();

        for (Detaildto dto : list) {
            if (resId.equals (dto.getDId())) {
                new Alert (Alert.AlertType.CONFIRMATION, "Detail ADDED SUCCUSS").show ();
                return false;
            }
        }
        return true;
    }

    private Bookdto getBookDetail() {
        try {
            String bookId = cmbBookId.getValue ().toString ();
            System.out.println ("This IS Book ID"+bookId);
            return detailBo.getBook(bookId);
        } catch (Exception e) {
            System.out.println (e);
        }
        return null;
    }

    private Userdto getUserDetail() {
        String stId = cmbuserId.getValue ().toString ();
        return detailBo.getUser(stId);
    }

    public void update_OnAction(ActionEvent actionEvent) {
        String uid = cmbuserId.getValue ().toString ();
        String bid = cmbBookId.getValue ().toString ();
        String dId = txtDid.getText ();
        Userdto userdto=getUserDetail();
        Bookdto bookdto=getBookDetail();
        java.sql.Date sqlSDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        java.sql.Date sqlEDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        try {
            boolean isUpdate = detailBo.updateDetail(
                    new Detaildto (
                            dId,
                             sqlSDate,
                           sqlEDate,
                            userdto,
                             bookdto
                    ));
            loadAllDeatls();
        } catch (Exception e) {
            e.printStackTrace ();
        }

    }

    public void deleteOn_Action(ActionEvent actionEvent) {
        java.sql.Date sqlSDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        java.sql.Date sqlEDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        Userdto userdto=getUserDetail();
        Bookdto bookdto=getBookDetail();
          Detaildto detaildto=new Detaildto(
               txtDid.getText (),
                   sqlSDate,
                  sqlEDate,
                  userdto,
                  bookdto

        );

        boolean isDeleted = detailBo.deleteDetail(detaildto);

        if (isDeleted) {
            new Alert (Alert.AlertType.INFORMATION, "Detail Delete Succuss").show ();
            tblMian.getItems ().clear ();
           loadAllDeatls();
           setId();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }

    }
}
