// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import common.ChatIF;
import ocsf.client.AbstractClient;

import java.io.IOException;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  
  //Constructors ****************************************************
  
  /*
   * Constructs an instance of the chat client.
   * host - The server to connect to.
   * port - The port number to connect on.
   * clientUI - The interface type variable.
   * returns a new ChatClient instance
   */
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /*
   * This method handles all data that comes in from the server.
   * msg - The message from the server.
   * returns none
   */
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
  }

  /*
   * This method handles all data coming from the UI.
   * message - The message from the UI.
   * returns none
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
      if (message.equals("send")) {
        sendToServer("First Name: Adi, Last Name: Bishara, ID: 212084859, Address: Haifa");
      } else if (message.equals("quit")) {
        quit();
      } else {
        sendToServer(message);
      }
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /*
   * This method terminates the client.
   * parameters: none
   * returns none
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }

  /*
   * Hook method called after the connection has been closed.
   * parameters: none
   * returns none
   */
  @Override
  protected void connectionClosed() {
    clientUI.display("Connection to server closed.");
  }

  /*
   * Hook method called each time an exception is thrown by the client's thread that is reading messages from the server.
   * exception - the exception raised.
   * returns none
   */
  @Override
  protected void connectionException(Exception exception) {
    clientUI.display("Connection to server lost: " + exception.getMessage());
    quit();
  }
}
//End of ChatClient class
