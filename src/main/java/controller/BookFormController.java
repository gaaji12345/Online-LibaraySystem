package controller;

import bo.custom.BookBo;
import bo.impl.BookBoImpl;
import dto.Bookdto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class BookFormController {
    public TextField txtID;//751 684
    public TextField txtTitle;
    public TextField txtAuthor;
    public TextField txtGenre;
    public ComboBox cmbStatus;
    public TableView tblLMainBook;
    public TableColumn tblID;
    public TableColumn tblTitle;
    public TableColumn tblAuthor;
    public TableColumn tblGenre;
    public TableColumn tblStatus;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;

    BookBo bookBo=new BookBoImpl();

    public void initialize(){
        tblID.setCellValueFactory (new PropertyValueFactory<>("bId"));
        tblTitle.setCellValueFactory (new PropertyValueFactory<> ("title"));
        tblAuthor.setCellValueFactory (new PropertyValueFactory<> ("author"));
        tblGenre.setCellValueFactory (new PropertyValueFactory<> ("genre"));
        tblStatus.setCellValueFactory (new PropertyValueFactory<> ("status"));



      loadAllBooks();
        setStatus();

    }

    private void setStatus() {
        ObservableList<String> data = FXCollections.observableArrayList ("AVALILABLE", "NOTAVALAIBLE");
        cmbStatus.setItems (data);
    }

    private void loadAllBooks() {
        try {
            List allBook = bookBo.loadAll ();
            ObservableList observableList = FXCollections.observableArrayList (allBook);
            tblLMainBook.setItems (observableList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }




    public void save_OnAction(ActionEvent actionEvent) {

        String bookId=txtID.getText();
        String title=txtTitle.getText();
        String author=txtAuthor.getText();
        String genre=txtGenre.getText();
        String status=cmbStatus.getValue().toString();

        Bookdto bookdto = new Bookdto (
                bookId,
                title,
                author,
                genre,
                status
        );

        try {

            List<Bookdto> allBooks = bookBo.loadAll ();

            if (checkduplicate ()) {

                bookBo.saveBook (bookdto);
                loadAllBooks();
                setBookId();
            }

        } catch (Exception e) {
            e.printStackTrace ();
        }

    }

    private void setBookId() {
    }

    private boolean checkduplicate() {
        String bookId = txtID.getText ();
        List<Bookdto> allBooks = bookBo.loadAll ();
        for (Bookdto books : allBooks) {
            if (bookId.equals (books.getBId())) {
                new Alert(Alert.AlertType.ERROR, "This ID Already Have").show ();
                return false;
            }
        }
        return true;
    }

    public void update_OnAction(ActionEvent actionEvent) {

        Bookdto bookdto = new Bookdto (
              txtID.getText(),
                txtTitle.getText (),
                txtAuthor.getText (),
                txtGenre.getText(),
                cmbStatus.getValue().toString()

        );

        boolean isUpdate = bookBo.updateBook (bookdto);

        if (isUpdate) {
            new Alert (Alert.AlertType.INFORMATION, " Update Complete...!").show ();
           // tblRoom.getItems ().clear ();
            clearDataText();
            loadAllBooks();
            setBookId ();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    private void clearDataText() {
    }

    public void deleteOn_Action(ActionEvent actionEvent) {
    }
}
