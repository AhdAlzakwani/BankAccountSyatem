package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/loan")
public class LoanController {

    @Autowired
    SlackClient slackClient;
}
