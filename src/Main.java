import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.concurrent.Worker.State;
import netscape.javascript.JSObject;
//import Worker.State;


public class Main extends Application {
    public static void main(String[] args) { launch(args); }
    //©Override
    public void start(Stage primaryStage) throws Exception {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        //webEngine.load("file://");
        webEngine.loadContent ( "" +
        "<div id='div'>hello world</div>" +
                "<script>document.getElementByld('div').style.color='blue';" +
                "function changeColor() {" +
                "	document.getElementByld('div').style.color='green'" +
        "	document.getElementByld('div').innerHTML = myClass.getHello('Oleg')" +
        "}</script>" +
                "");

        JSObject jsObject = (JSObject) webEngine.executeScript("window");
        jsObject.setMember("myClass", new MyClass());
        webEngine.getLoadWorker()
                .stateProperty()
                .addListener( (obs, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED) {
                        webEngine.executeScript("changeColor();");
                    }
                });

        Group group = new Group();
        group.getChildren().addAll(webView);
        group.getChildren().addAll();

        primaryStage.setScene(new Scene(group, 400, 300));
        primaryStage.show();
    }
    public class MyClass {
        String getHello(String name){
            return "hello" + name;
        }
    }
}

