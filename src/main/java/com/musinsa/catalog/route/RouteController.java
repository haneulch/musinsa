package com.musinsa.catalog.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

  @GetMapping("/view/**")
  public String redirect() {
    return "forward:/index.html";
  }
}
