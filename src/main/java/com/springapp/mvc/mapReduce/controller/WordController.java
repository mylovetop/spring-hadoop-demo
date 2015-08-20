package com.springapp.mvc.mapReduce.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.mapreduce.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zdsoft on 15-8-18.
 */
@Controller
public class WordController {

    private static final Log log = LogFactory.getLog(WordController.class);

    @Autowired
    public Job mapReduceJob;



    @RequestMapping("/runJob")
    public void runJob(){

        try {
            Job mapReduceJob = new Job();
            mapReduceJob.waitForCompletion(false);
        } catch (Exception e){
            log.debug(e);
        }

    }
}
