package com.amsidh.mvc.controller;

import com.amsidh.mvc.splitter.MessageSplittingLogger;
import lombok.CustomLog;
import lombok.extern.flogger.Flogger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final Logger logger
            = new MessageSplittingLogger(HelloController.class);

    @GetMapping
    public String sayHello() {
        logger.info("I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412101I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412102I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412103I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412104I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412105I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412106I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412107I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412108I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412109I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412110I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412101I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412102I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412103I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412104I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412105I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412106I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412107I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412108I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412109I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412110I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412101I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412102I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412103I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412104I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412105I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412106I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412107I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412108I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412109I am Amsidh Babasha Lokhande living in Pride World City Charholi Budruk Pune Maharashtra Pune-412110");
        return "Hello World";
    }


}
