package com.frank.springproject.controllers;

import com.frank.springproject.thedata.Answer;
import com.frank.springproject.thedata.Question;
import com.frank.springproject.thedata.UmaData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // Tell the server there are controllers in here to handle paths

public class myControllers {
        @GetMapping(value="/")   // handle the "root path - /
        public String anyNameYouWant() {
                System.out.println("--- Root path (/) handler called ---");
                return "some string";
        }
        @GetMapping(value="/dinosaurs")   // handle the path - /dinosaurs for GET
        public String theAnimals() {
                System.out.println("--- HTTP GET for path (/dinosaurs) handler called ---");
                return "Not the mama!";
        }
        @PostMapping(value="/dinosaurs")   // handle the path - /dinosaurs for POST
        public String theTVShow() {
                System.out.println("--- HTTP POST for path (/dinosaurs) handler called ---");
                return "Fred Flintstone";
        }
        @PostMapping(value="/dinosaurs/matthew")   // handle the path - /dinosaurs/matthew for POST
        public String frank() {
                System.out.println("--- HTTP POST for path (/dinosaurs) handler called ---");
                return "Frank";
        }
        @GetMapping(value="/uma")   // handle the path - /uma for GET
        public UmaData theUmaMethod() {
                System.out.println("--- HTTP GET for path (/dinosaurs) handler called ---");

                UmaData responseObject = new UmaData();

                responseObject.setQuestion("Where is the Sprint 2 Canvas stuff?");
                responseObject.setAnswer("Patience, Uma");

                return responseObject;
        }
        // Data objects are normally NOT sent with a GET HTTP request
        // POST request used since we want to send data to server
        // Note use of @RequestBody in parameter to method
        //      this tells teh server to convert the JSON in the request
        //      to the Object specified - the  default ctro and setters for class
        //      are used to instantiate and populate the new object.
        //
        //      The JSON data attribute names must match the name of the data members in the class
        //
        @PostMapping(value="/askQuestion")   // handle the path - /askQuestion for POST
        public Answer questionMethod(@RequestBody Question aQuestion) {
                System.out.println("--- HTTP POST for path (/askQuestion) handler called with question: "
                                    + aQuestion.getQuestionText() + " ---");

                Answer anAnswer = new Answer();

                switch(aQuestion.getQuestionText())   {
                        case "Who is teaching unit 6?":
                                anAnswer.setTheAnswer("Frank");
                                break;
                        case "Who is teaching unit 1?":
                                anAnswer.setTheAnswer("Brian");
                                break;
                        case "Who is teaching unit 2?":
                                anAnswer.setTheAnswer("Daniel");
                                break;
                        case "Who is teaching unit 4?":
                                anAnswer.setTheAnswer("Mauli");
                                break;
                        case "Who is teaching unit 5?":
                                anAnswer.setTheAnswer("Tom");
                                break;
                        default:
                                anAnswer.setTheAnswer("You have stumped me!");
                }

                return anAnswer;
        }

}
