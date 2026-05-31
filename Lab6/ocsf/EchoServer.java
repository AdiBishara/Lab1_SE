// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 3000;
  
  //Constructors ****************************************************
  
  /*
   * Constructs an instance of the echo server.
   * port - The port number to connect on.
   * returns a new EchoServer instance
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /*
   * This method handles any messages received from the client.
   * msg - The message received from the client.
   * client - The connection from which the message originated.
   * returns none
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
	    System.out.println("Message received: " + msg + " from " + client);
	    this.sendToAllClients(msg);
	  }

    
  /*
   * This method overrides the one in the superclass. Called when the server starts listening for connections.
   * parameters: none
   * returns none
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /*
   * This method overrides the one in the superclass. Called when the server stops listening for connections.
   * parameters: none
   * returns none
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }

  /*
   * Hook method called each time a new client connection is accepted.
   * client - the connection connected to the client.
   * returns none
   */
  @Override
  protected void clientConnected(ConnectionToClient client) {
    System.out.println("Client connected: " + client);
  }

  /*
   * Hook method called each time a client disconnects.
   * client - the connection with the client.
   * returns none
   */
  @Override
  synchronized protected void clientDisconnected(ConnectionToClient client) {
    System.out.println("Client disconnected: " + client);
  }

  /*
   * Hook method called each time an exception is thrown in a ConnectionToClient thread.
   * client - the client that raised the exception.
   * exception - the exception thrown.
   * returns none
   */
  @Override
  synchronized protected void clientException(ConnectionToClient client, Throwable exception) {
    System.out.println("Client exception: " + client + " - " + exception.getMessage());
  }

  /*
   * Hook method called when the server is closed.
   * parameters: none
   * returns none
   */
  @Override
  protected void serverClosed() {
    System.out.println("Server has been closed.");
  }

  /*
   * Hook method called when the server stops accepting connections because an exception has been raised.
   * exception - the exception raised.
   * returns none
   */
  @Override
  protected void listeningException(Throwable exception) {
    System.out.println("Listening exception: " + exception.getMessage());
  }
  
  //Class methods ***************************************************
  
  /*
   * This method is responsible for the creation of the server instance (there is no UI in this phase).
   * args - The command line arguments, args[0] is the port number to listen on.
   * returns none
   */
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of org.openjfx.demo1.EchoServer class
