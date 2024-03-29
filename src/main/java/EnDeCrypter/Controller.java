package EnDeCrypter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button one_time_pad_file, one_time_pad, caesar_cipher, home;

    @FXML
    private TextArea textAreaA, plaintext, ciphertext, plaintextCaesar, ciphertextCaesar;

    @FXML
    private TextArea textAreaB;

    @FXML
    private Label textA;

    @FXML
    private Label textB;

    @FXML
    private ChoiceBox<Integer> caesar_shift;

    @FXML
    protected void stringEncryptionAction(ActionEvent event) throws Exception {
        ciphertext.setText(EncryptionOTP.startStringEncrption(plaintext.getText(), "key.bin"));
    }
    @FXML
    protected void fileChooserEncryption(ActionEvent event) throws Exception {
        FileChooser fileChooserA = new FileChooser();
        fileChooserA.setTitle("Choose the plaintext file (.txt)");
        fileChooserA.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        String inputFile = fileChooserA.showOpenDialog(MainApplication.stage).getAbsolutePath();

        FileChooser fileChooserB = new FileChooser();
        fileChooserB.setTitle("Choose the output file (.txt)");
        fileChooserB.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        String outputFile = fileChooserB.showSaveDialog(MainApplication.stage).getAbsolutePath();

        FileChooser fileChooserC = new FileChooser();
        fileChooserC.setTitle("Chose the key file (.bin)");
        fileChooserC.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Binary Files", "*.bin")
        );
        String keyPath = fileChooserC.showSaveDialog(MainApplication.stage).getAbsolutePath();

        textAreaA.setText(EncryptionOTP.startFileEncryption(inputFile, outputFile, keyPath));
        textA.setText("Ciphertext stored at : " + outputFile + "\nKey binary file stored at : " + keyPath);
    }

    @FXML
    protected void fileChooserDecryption(ActionEvent event) throws Exception {
        FileChooser fileChooserA = new FileChooser();
        fileChooserA.setTitle("Choose the plaintext file (.txt)");
        fileChooserA.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        String inputFile = fileChooserA.showOpenDialog(MainApplication.stage).getAbsolutePath();

        FileChooser fileChooserB = new FileChooser();
        fileChooserB.setTitle("Choose the output file (.txt)");
        fileChooserB.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        String outputFile = fileChooserB.showSaveDialog(MainApplication.stage).getAbsolutePath();

        FileChooser fileChooserC = new FileChooser();
        fileChooserC.setTitle("Chose the key file (.bin)");
        fileChooserC.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Binary Files", "*.bin")
        );
        String keyPath = fileChooserC.showOpenDialog(MainApplication.stage).getAbsolutePath();

        textAreaB.setText(DecryptionOTP.startFileDecryption(inputFile, outputFile, keyPath));
        textB.setText("Decrypted ciphertext stored at : " + outputFile);
    }

    @FXML
    protected void stringDecryptionAction(ActionEvent event) throws Exception {
        ciphertext.setText(DecryptionOTP.startStringDecryption(ciphertext.getText(), "key.bin"));
    }

    @FXML
    protected void changeToOneTimePad(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("onetimepad_user.fxml"));
        Stage stage = (Stage) one_time_pad.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 800, 400));
    }

    @FXML
    protected void changeToFileOTP(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("onetimepad_file.fxml"));
        Stage stage = (Stage) one_time_pad_file.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 800, 400));
    }

    @FXML
    protected void changeToCaesar(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("caesar.fxml"));
        Stage stage = (Stage) caesar_cipher.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 800, 400));
    }

    @FXML
    protected void caesarInit() throws Exception {
        Integer[] numbers = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        caesar_shift.getItems().addAll(numbers);
    }

    @FXML
    protected void caesarEncryption() {
        ciphertextCaesar.setText(Caesar.Encrypt(plaintextCaesar.getText(), caesar_shift.getValue()));
    }

    @FXML
    protected void caesarDecryption() {
        ciphertextCaesar.setText(Caesar.Decrypt(ciphertextCaesar.getText(), caesar_shift.getValue()));
    }

    @FXML
    protected void home(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("welcome_scene.fxml"));
        Stage stage = (Stage) home.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 800, 400));
    }
}