package clientStuff;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import messaging.AccountConfirmationMessage;
import messaging.AccountCreationMessage;
import messaging.LoginMessage;
import messaging.Message;

public class AccountCreationState extends State
{

    private JTextField emailText;
    private JTextField newUsernameText;
    private JTextField newPasswordText;
    private JTextField verifyPasswordText;
    private JButton newcreateAccount;
    private JButton backToLogin;
    static JLabel error;
    private JPanel south;
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(emailText) || e.getSource().equals(newUsernameText) 
                || e.getSource().equals(newPasswordText) || e.getSource().equals(verifyPasswordText) || e.getSource().equals(newcreateAccount)){
            if(!(emailText.getText().equals("") || newUsernameText.getText().equals("") || newPasswordText.getText().equals("") || verifyPasswordText.getText().equals(""))){
                if(newPasswordText.getText().equals(verifyPasswordText.getText())){
                    StateMachine.sendMessage(new AccountCreationMessage(newUsernameText.getText(), newPasswordText.getText(), emailText.getText()));
                }
            }else {

            }
        }  else if(e.getSource().equals(backToLogin)){
            StateMachine.getFrame().back();
        }
    }

    @Override
    public void MessageRecieved(Message message)
    {
        if(message instanceof AccountConfirmationMessage) {
            if(((AccountConfirmationMessage) message).isConfirmed()) {
                StateMachine.sendMessage(new LoginMessage(newUsernameText.getText(), newPasswordText.getText()));
            } else {
                error.setText("Account not Confirmed");
            }
        }
    }

    @Override
    public void onInitialize(StateMachine stater)
    {
        south = new JPanel();
        south.setLayout(new FlowLayout());
        emailText = new JTextField("Enter Email");
        newUsernameText  = new JTextField("Enter Username");
        newPasswordText = new JTextField("Enter Password");
        verifyPasswordText = new JTextField("Verify Password");
        newcreateAccount = new JButton("Create Account");
        backToLogin = new JButton("Back To Login");
        south.add(emailText);
        emailText.addActionListener(this);
        south.add(newUsernameText);
        newUsernameText.addActionListener(this);
        south.add(newPasswordText);
        newPasswordText.addActionListener(this);
        south.add(verifyPasswordText);
        verifyPasswordText.addActionListener(this);
        south.add(newcreateAccount);
        newcreateAccount.addActionListener(this);
        south.add(backToLogin);
        backToLogin.addActionListener(this);
        error = new JLabel("");
        south.add(error);
        this.add(south);
    }

    @Override
    public void onBegin(StateMachine stater)
    {
        stater.setSize(735, 70);

    }

    @Override
    public void onLeave(StateMachine stater)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestroy(StateMachine stater)
    {
        // TODO Auto-generated method stub

    }

}
