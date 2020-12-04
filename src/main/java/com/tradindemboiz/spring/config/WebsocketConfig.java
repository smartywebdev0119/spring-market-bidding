package com.tradindemboiz.spring.config;

import com.tradindemboiz.spring.controllers.SocketController;
import com.tradindemboiz.spring.services.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

  @Autowired
  SocketService socketService;

  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      SocketController sc = new SocketController();
      sc.setSocketService(socketService);

    // Detta är adressen som vi ska ansluta vår socket till. Precis som en endpoint i princip.
    registry.addHandler(sc, "/your-socket-route").setAllowedOrigins("*");
  }
}
