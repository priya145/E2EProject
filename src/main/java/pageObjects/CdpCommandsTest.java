package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v94.network.Network;
import org.openqa.selenium.devtools.v94.network.model.Request;
import org.openqa.selenium.devtools.v94.network.model.Response;

import java.util.Optional;

public class CdpCommandsTest {
    public static void main(String args[]){
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(), Optional.empty() ));

        //which request is frontend making
        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            if(req.getUrl().toString().matches(".*/fps-prod-na-frontdoor.+") && req.getMethod().toString().equals("POST")){
                System.out.println(req.getUrl());

            }

        });

        //Event(response) will get fired
        devTools.addListener(Network.responseReceived(), response ->
        {
            Response res = response.getResponse();
            if(res.getUrl().toString().matches(".*/fps-prod-na-frontdoor.+") && res.getStatus().toString().equals("POST")){
                System.out.println(res.getUrl());
                System.out.println("responce is: " + response.getResponse().getUrl());
                System.out.println("responce is: " +response.getResponse().getHeaders());
                System.out.println("responce is: " +response.getResponse().getStatus());

            }

        });

        driver.get("https://www.ford.com");
        //driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
    }
}
