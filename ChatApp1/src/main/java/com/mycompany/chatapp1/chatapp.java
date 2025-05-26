/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//chatapp:chatapp.java
package com.mycompany.chatapp1;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import javax.swing.*;



/**
 *
 * @author RC_Student_lab
 */
public class chatapp extends javax.swing.JFrame {
    private JComboBox<String> recipientComboBox;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    
    private String[] registeredUser;
    private int maxMessages = 0;
    private int messageCount = 0;

    public chatapp() {
        registeredUser = new String[]{"0637229986","0692521074"};
        initComponents();

        // Set combo box items to your registered users
        jComboBox1.setModel(new DefaultComboBoxModel<>(registeredUser));

        startApp(); // Start your menu loop
    }

    private void startApp() {
        JOptionPane.showMessageDialog(this, "Welcome to Quick Chat!");

        // Ask how many messages user wants to enter
        while (true) {
            String input = JOptionPane.showInputDialog(this, "How many messages do you want to send?");
            if (input == null) {
                System.exit(0);
            }
            try {
                maxMessages = Integer.parseInt(input);
                if (maxMessages <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive number.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid number. Please enter an integer.");
            }
        }
    }

    private void sendMessage() {
        String recipient = (String) jComboBox1.getSelectedItem();
        String message = jTextField3.getText().trim();
        
        if (!message.isEmpty()) {
             String messageHash = createMessageHash(messageCount, message);
            jTextArea1.append("To " + recipient + ": " + message + "MESSAGE HASH"+messageHash+"\n");
            jTextField3.setText("");
            messageCount++;
            
            //ask user what to do with this message
            String[] options = {"Send message", "Disregard message", "Store message", "Send later"};
        int choice = JOptionPane.showOptionDialog(
            this,
            "Choose an action for the message:",
            "Message Action",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
         switch (choice) {
            case 0: // Send message
                jTextArea1.append("To " + recipient + ": " + message + "\n");
                String responder = getOtherUser(recipient);
                if (responder != null) {
                    jTextArea1.append(responder + ": I got your message: " + message + "\n");
                }
                break;
            case 1: // Disregard message
                jTextArea1.append("Message disregarded.\n");
                break;
            case 2: // Store message (you can implement JSON storage here)
                jTextArea1.append("Message stored.\n");
                JSONObject messageObj = new JSONObject();
                messageObj.put("id", messageCount);
                messageObj.put("recipient", recipient);
                messageObj.put("message", message);
                messageObj.put("status", "stored");
                try {
            File file = new File("messages.json");
            JSONArray messagesArray;

        if (file.exists()) {
            // Load existing JSON
            FileReader reader = new FileReader(file);
            char[] buffer = new char[(int) file.length()];
            reader.read(buffer);
            reader.close();

            String content = new String(buffer);
            messagesArray = new JSONArray(content);
        } else {
            // Create new array
            messagesArray = new JSONArray();
        }

        // Add new message
        messagesArray.put(messageObj);

        // Write back to file
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(messagesArray.toString(4)); // Pretty-print
        fileWriter.flush();
        fileWriter.close();

    } catch (IOException e) {
        e.printStackTrace();
    }

    break;
                // TODO: Add your JSON storage logic here
                
            case 3: // Send later
                jTextArea1.append("Message will be sent later.\n");
                // TODO: Add scheduling logic here if needed
                break;
            default:
                jTextArea1.append("No valid action selected.\n");
        }

        jTextField3.setText("");


            
            String responder = getOtherUser(recipient);
            if(responder !=null){
             jTextArea1.append(responder + ": i got your message"+":"+message+"\"\n");   
            }
            

            if (messageCount >= maxMessages) {
                JOptionPane.showMessageDialog(this, "You have reached the max number of messages.");
                jButton1.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a message.");
        }
            }
    private String createMessageHash(int messageId, String message) {
    String msgIdStr = String.valueOf(messageId);
    String firstTwoDigits = msgIdStr.length() >= 2 ? msgIdStr.substring(0, 2) : msgIdStr;

    String[] words = message.trim().split("\\s+");
    String firstWord = words.length > 0 ? words[0] : "";
    String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

    String hash = firstTwoDigits + ":" + msgIdStr + " " + firstWord + " " + lastWord;
    return hash.toUpperCase();

}
    private String getOtherUser(String recipient) {
    for (String user : registeredUser) {
        if (!user.equals(registeredUser) && !user.equals(recipient)) {
            // Return any user different from loggedInUser and recipient (if any)
            return user;
        }
    }
    // If none found, just return recipient (or null)
    return null;
}


   
    /**
     * Creates new form chatapp
     */
    public chatapp(String[]  registeredUser) {
        if(registeredUser == null) {
            this.registeredUser = new String[0];
        }else{
        this.registeredUser = registeredUser;
        }
        initComponents();
        jComboBox1.setModel(new DefaultComboBoxModel<>(registeredUser));
    startApp();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Quit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Welcome to Quick Chat ");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jButton1))))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            sendMessage();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new login().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(chatapp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chatapp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chatapp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chatapp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chatapp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
