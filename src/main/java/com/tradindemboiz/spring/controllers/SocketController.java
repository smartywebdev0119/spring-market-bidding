package com.tradindemboiz.spring.controllers;

import com.tradindemboiz.spring.services.SocketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Controller
public class SocketController extends TextWebSocketHandler {

  // NOTE: Can not use @Autowired here due to WebSocketConfig instantiating the SocketController and also adds some configs that we can not see.
  private SocketService socketService;
  public void setSocketService(SocketService socketService) {
    this.socketService = socketService;
  }

  // All trafic with sockets is handeled through this method.
  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
    System.out.println("Received msg: " + message.getPayload());

    /*
      DTO: Data Transfer Object
    * {
    *   action: "message",
    *   payload: message.getPayload(=
    * }
    * */

    // Demonstration purpose only: send back "Hello" + same message as received
    socketService.prepareSendToAll("Hello " + message.getPayload());

    // Example with a generic Map instead of converting the JSON to a specific class
    //    // Map keysAndValues = new Gson().fromJson(message.getPayload(), Map.class);
    //    // Get the value of a key named "firstname"
    //    // String firstname = keysAndValues.get("firstname");
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    socketService.addSession(session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    socketService.removeSession(session);
  }
}
