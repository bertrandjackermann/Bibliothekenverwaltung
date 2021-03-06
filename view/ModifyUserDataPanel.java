package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.User;

import controller.MyAccount;

import core.Exception.InvalidFormatException;
import core.Exception.UnequalPasswordsException;


/**
 * Die Klasse ModifyUserDataPanel wird in einem der Tabs im Hauptfenster aufgerufen.
 * Es werden sechs Panel samt Textfeld angelegt: Vorname, Nachname, E-mail, Passwort, Passwort wiederholen und Speichern-Button.
 *
 */
public class ModifyUserDataPanel extends JPanel {
    
	private static final long serialVersionUID = 1L;
	private JTextField firstNameTextField;
    private JTextField secondNameTextField;
    private JTextField emailTextField;
    private JPasswordField passwordTextField;
    private JPasswordField repeatPasswordTextField;

    /**
     * Das neue Panel wird mit einem Grid-Layout angelegt.
     *
     * Der aktuell angeloggte Nutzer wird geholt.
     * So koennen der bisher gespeicherte Vor- und Nachname sowie E-mail-Adresse
     * des Nutzers in die jeweiligen Textfelder vorgetragen werden.
     */
         
    public ModifyUserDataPanel() {   
        this.setLayout(new GridLayout(6, 0));
        this.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 30));
       
        User loggedInUser = MyAccount.getLoggedInUser();       
       
        JPanel firstName = new JPanel();
        firstName.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel firstNameLabel = new JLabel("Vorname ändern");
        firstName.add(firstNameLabel);
        firstNameTextField = new JTextField(20);
        firstNameTextField.setText(loggedInUser.getFirstName());
        firstName.add(firstNameTextField);
        this.add(firstName);
               
        JPanel secondName = new JPanel();
        secondName.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel secondNameLabel = new JLabel("Nachname ändern");
        secondName.add(secondNameLabel);
        secondNameTextField = new JTextField(20);
        secondNameTextField.setText(loggedInUser.getLastName());
        secondName.add(secondNameTextField);
        this.add(secondName);
       
        JPanel email = new JPanel();
        email.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel emailLabel = new JLabel("neue E-mail-Adresse");
        email.add(emailLabel);
        emailTextField = new JTextField(20);
        emailTextField.setText(loggedInUser.getEmail());
        email.add(emailTextField);
        this.add(email);
       
        JPanel password = new JPanel();
        password.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel passwordLabel = new JLabel("neues Passwort");
        password.add(passwordLabel);
        passwordTextField = new JPasswordField(20);
        password.add(passwordTextField);
        this.add(password);
       
        JPanel repeatPassword = new JPanel();
        repeatPassword.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel repeatPasswordLabel = new JLabel("Passwort wiederholen");
        repeatPassword.add(repeatPasswordLabel);
        repeatPasswordTextField = new JPasswordField(20);
        repeatPassword.add(repeatPasswordTextField);
        this.add(repeatPassword);
       
        JPanel save = new JPanel();
        JButton saveButton = new JButton("Speichern");
        
        saveButton.addActionListener(new ActionListener() {
        	/**
        	 * Der Speicher-Button bekommt einen ActionListener.
        	 * Dieser faengt gegebenenfalls auch Exceptions:
        	 * 1. Exception: Wenn Passwort und wiederholtes Passwort nicht übereinstimmen und
        	 * 2. Exception: Wenn in der E-mail-Adresse kein @ enthalten ist.
        	 */
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {       
                    save();   
                } catch(UnequalPasswordsException e) {
                    JOptionPane.showMessageDialog(ModifyUserDataPanel.this, "Bitte überprüfen Sie, ob Ihre Passwörter übereinstimmen!!");
                } catch(InvalidFormatException e){
                    JOptionPane.showMessageDialog(ModifyUserDataPanel.this, "Ihre E-mail-Adresse muss ein @ enthalten.");
                }
            }
        });
       
        save.add(saveButton);
        this.add(save);
       
        this.setVisible(true);
    }
    /**
     * Die "save"-Methode speichert die vom Nutzer vorgenommenen Aenderungen.
     * Bei fehlerhaften Eingaben, werden Exceptions geworfen, die wiederum vom ActionListener des
     * Speicher-Buttons gefangen werden.
     * Das Passwort wird nur neu gesetzt, wenn das Passwort-Textfeld nicht leer ist.
     * @throws UnequalPasswordsException
     * @throws InvalidFormatException
     */
    public void save() throws UnequalPasswordsException, InvalidFormatException {
        String firstName = firstNameTextField.getText();
        String secondName = secondNameTextField.getText();
        String email = emailTextField.getText();
        String password = String.valueOf(passwordTextField.getPassword());   
        String password2 = String.valueOf(repeatPasswordTextField.getPassword());
       
        if(!password.equals(password2)) {
            passwordTextField.setText("");
            repeatPasswordTextField.setText("");
            throw new UnequalPasswordsException();
        }
        if(!email.contains("@"))  {
            emailTextField.setText("");
            throw new InvalidFormatException();
        }
       
        User loggedInUser = MyAccount.getLoggedInUser();
        loggedInUser.setFirstName(firstName);
        loggedInUser.setLastName(secondName);
       
        if (!(loggedInUser.getPassword() == null) || !(loggedInUser.getPassword().equals("")));
        {
            loggedInUser.setPassword(password);
        }
       
        loggedInUser.setEmail(email);
    }
}