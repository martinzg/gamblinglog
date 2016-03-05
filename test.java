public class CommonActions {

    WebDriver webDriver;

    public CommonActions (WebDriver webDriver){
        this.webDriver = webDriver;
        webDriver.manage().window().maximize();
    }
	
}