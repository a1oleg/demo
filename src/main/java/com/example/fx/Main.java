package com.example.fx;

import javafx.concurrent.Worker;
import javafx.scene.Group;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.jetbrains.annotations.NotNull;

public class Main {

    public static void main(String[] args) {
        WebView webView = new WebView();
        WebEngine webEngine = getWebEngine(webView);

        JSObject jsObject = (JSObject) webEngine.executeScript("window");
        jsObject.setMember("myClass", "hello");
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



//        primaryStage.setScene(new Scene(group, 400, 300));
//        primaryStage.show();
    }

    @NotNull
    private static WebEngine getWebEngine(WebView webView) {
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent ( "<div id='div'>hello world</div>" +
                "<script>document.getElementByld('div').style.color='blue';" +
                "function changeColor() {" +
                "	document.getElementByld('div').style.color='green'" +
                "	document.getElementByld('div').innerHTML = myClass.getHello('Oleg')" +
                "}</script>");
        return webEngine;
    }
}
