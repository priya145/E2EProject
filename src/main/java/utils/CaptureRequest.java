package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Optional;



public class CaptureRequest {
    ChromeDriver driver = new ChromeDriver();
    DevTools devTools = driver.getDevTools();

    public void captureHttpRequests(WebDriver driver, String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            devTools = ((ChromeDriver)driver).getDevTools();
        }else if(browserName.equalsIgnoreCase("firefox")){
            //dont have chromedriver support

        }else if(browserName.equalsIgnoreCase("edge")){
            devTools = ((EdgeDriver)driver).getDevTools();
        }
    }


}
