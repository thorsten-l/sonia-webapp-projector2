/*
 * Copyright 2022 Thorsten Ludewig (t.ludewig@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sonia.webapp.projector2.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sonia.webapp.projector2.phonesystem.PhoneSystemService;
import sonia.webapp.projector2.phonesystem.RoomPhone;

@Controller
public class ServiceController
{
  private final static SimpleDateFormat DATE_TIME = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss");

  private final static Logger LOGGER = LoggerFactory.getLogger(
    ServiceController.class.getName());

  private final static PhoneSystemService PHONE_SYSTEM_SERVICE
    = PhoneSystemService.getInstance();

  @GetMapping(path = "/service.xml",
              produces = MediaType.TEXT_XML_VALUE)
  public String service(
    @RequestParam(name = "mac", required = false) String mac,
    @RequestParam(name = "model", required = false) String model,
    @RequestParam(name = "cmd", required = false) String cmd,
    HttpServletRequest request, Model data)
  {
    LOGGER.info("\n\nSERVICE mac={}, model={}, cmd={}", mac, model, cmd);

    // TODO: Remove fake MAC Address
    // RoomPhone roomPhone = PHONE_SYSTEM_SERVICE.getRoomPhone(mac);
    RoomPhone roomPhone = PHONE_SYSTEM_SERVICE.getRoomPhone("805E0C16C344");

    LOGGER.debug("phone {}", roomPhone.getPhone().toString());
    LOGGER.debug("room {}", roomPhone.getRoom().toString());

    LOGGER.debug("local addr={}", request.getLocalAddr());
    LOGGER.debug("local name={}", request.getLocalName());
    LOGGER.debug("local port={}", request.getLocalPort());

    LOGGER.debug("context path={}", request.getContextPath());

    LOGGER.debug("request uri={}", request.getRequestURI());
    String baseUrl = request.getRequestURL().toString();
    LOGGER.debug("request url={}", baseUrl);
    baseUrl = baseUrl.substring(0, baseUrl.indexOf('/', 8));
    LOGGER.debug("base url={}", baseUrl);
    LOGGER.debug("query string={}", request.getQueryString());

    LOGGER.debug("remote addr={}", request.getRemoteAddr());
    LOGGER.debug("remote host={}", request.getRemoteHost());

    data.addAttribute("serverDateTime", DATE_TIME.format(new Date()));
    data.addAttribute("baseUrl", baseUrl);
    data.addAttribute("mac", mac);
    data.addAttribute("model", model);

    return roomPhone.getPhone().getType().toString() + "/service.xml";
  }
}
