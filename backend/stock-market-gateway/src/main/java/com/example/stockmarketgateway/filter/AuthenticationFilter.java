package com.example.stockmarketgateway.filter;

import com.example.stockmarketgateway.util.JwtUtil;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                    try {
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                        boolean isValidated = jwtUtil.validateToken(authHeader);

                        if(isValidated) {
                            String username = jwtUtil.getUserNameFromJwtToken(authHeader);
                            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                                    .header("X-USERNAME", username) // Add username to the header
                                    .build();
                            ServerWebExchange modifiedExchange = exchange.mutate().request(modifiedRequest).build();
                            return chain.filter(modifiedExchange);
                        } else {
                            System.out.println("invalid");
                        }
                    } catch (Exception e) {
                        System.out.println("invalid access...!");
                        throw new RuntimeException("Unauthorized access to application");
                    }
                }

            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
