package atlantafx.xyz.hashdog.rdm.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class PieController implements Initializable {
    public PieChart pie;
    public StackPane stackPane;
    public Circle circle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 创建默认数据
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("数据1", 25),
                new PieChart.Data("数据2", 35),
                new PieChart.Data("数据3", 15),
                new PieChart.Data("数据4", 25)
        );

        // 将数据设置到饼图中
        pie.setData(pieChartData);

        // 可选：设置饼图标题
        pie.setTitle("默认数据示例");
        for (PieChart.Data datum : pie.getData()) {
            datum.getNode().setOnMouseClicked(event -> {
                System.out.println("点击了数据：" + datum.getName());
                stackPane.setStyle("-fx-background-color: red;");
            });
        }
        stackPane.styleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.startsWith("-fx-background-color:")){
                Color backgroundColor = extractBackgroundColor(newValue);
                if (backgroundColor != null) {
                    circle.setFill(backgroundColor);
                }
            }
            System.out.println("样式属性改变：" + newValue);
        });

    }
    private Color extractBackgroundColor(String style) {
        if (style != null && style.contains("-fx-background-color:")) {
            // 简单解析颜色值
            String colorPart = style.substring(style.indexOf("-fx-background-color:") + 21);
            colorPart = colorPart.trim();
            if (colorPart.endsWith(";")) {
                colorPart = colorPart.substring(0, colorPart.length() - 1);
            }
            colorPart = colorPart.trim();

            // 处理常见颜色名称
            switch (colorPart.toLowerCase()) {
                case "red": return Color.RED;
                case "blue": return Color.BLUE;
                case "green": return Color.GREEN;
                case "yellow": return Color.YELLOW;
                case "white": return Color.WHITE;
                case "black": return Color.BLACK;
                default:
                    // 如果是十六进制颜色值
                    if (colorPart.startsWith("#") && colorPart.length() >= 7) {
                        try {
                            return Color.web(colorPart);
                        } catch (Exception e) {
                            // 解析失败时返回 null
                        }
                    }
                    break;
            }
        }
        return null;
    }

}
