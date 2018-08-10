package org.spark.scala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckDelete {

    public static void main(String []a){
        List<List> arraFromSession = getFromSession(a[0]);
        List<List> finalSession = new ArrayList<List>();
        List<String> mediator = new ArrayList<String>();
        String fromReq = "manoj,kumar,ellary";
        if(arraFromSession != null && arraFromSession.size()>0)
        {
            finalSession.addAll(arraFromSession);
            mediator.addAll(Arrays.asList(fromReq.split(",")));
            boolean myCheck = checkMyLenth(mediator,finalSession);
            if(myCheck) {
                finalSession.add(mediator);
            }else{

            }

        }else{
            mediator.addAll(Arrays.asList(fromReq.split(",")));
            finalSession.add(mediator);
        }
        System.out.println(finalSession.toString());


    }

    private static boolean checkMyLenth(List<String> mediator, List<List> finalSession) {
        String checkStringLength = "";
        for(List<String> myL:finalSession)
        {
            for(String s: myL)
            {
                checkStringLength = checkStringLength+s;
            }

        }
        for(String s: mediator)
        {
            checkStringLength = checkStringLength+s;
        }

        if(checkStringLength.length()>250) {
            return false;
        }else{
            return true;
        }
    }

    private static List<List> getFromSession(String s) {
        List<List> finalSession = new ArrayList<List>();
        List<String> check = Arrays.asList(s.split(","));
        if(null !=s) {
            finalSession.add(check);
            return finalSession;
        }
        else
        {
            return null;
        }
    }
}
