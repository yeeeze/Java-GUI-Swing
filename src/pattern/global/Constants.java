package pattern.global;

import pattern.shapes.*;

import java.awt.*;

public class Constants {

    public enum ETransformationStyle {
        e2PTransformation,
        eNPTransformation
    }

    public enum ETools {
        eSelection("선택", new TSelection(), ETransformationStyle.e2PTransformation),
        eRectangle("네모", new TRectangle(), ETransformationStyle.e2PTransformation),
        eOval("동그라미", new TOval(), ETransformationStyle.e2PTransformation),
        eLine("선", new TLine(), ETransformationStyle.e2PTransformation),
        ePolygon("다각형", new TPolygon(), ETransformationStyle.eNPTransformation),
        eColorMode("색상 모드", null, null);

        private final String label;
        private final TShape tool;
        private final ETransformationStyle eTransformationStyle;

        ETools(String label, TShape tool, ETransformationStyle eTransformationStyle) {
            this.tool = tool;
            this.label = label;
            this.eTransformationStyle = eTransformationStyle;
        }

        public TShape newShape() {
            return this.tool.clone();
        }

        public String getLabel() {
            return this.label;
        }

        public ETransformationStyle getTransformationStyle() {
            return eTransformationStyle;
        }
    }

    public enum EFileMenu {
        eNew("새로만들기", ""),
        eOpen("열기", ""),
        eSave("저장하기", ""),
        eSaveAs("다른이름으로저장", ""),
        ePrint("프린트",""),
        eQuit("종료","");

        private final String label;
        private final String toolTipText;
        
        EFileMenu(String label, String toolTipText) {
            this.label = label;
            this.toolTipText = toolTipText;
        }

        public String getLabel() {
            return this.label;
        }
    }

    public enum EGrapicsMenu {
        eLine("라인 두께"),
        eLineColor("라인 색"),
        eFill("도형 채우기");

        private final String label;

        EGrapicsMenu(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    }

    private enum EColor {
        eColor(Color.white, Color.black);

        private final Color baseColor;
        private final Color subColor;

        EColor(Color baseColor, Color subColor) {
            this.baseColor = baseColor;
            this.subColor = subColor;
        }
    }

    public enum EColorMode {
        eRightMode(EColor.eColor.subColor, EColor.eColor.baseColor),
        eDarkMode(EColor.eColor.baseColor, EColor.eColor.subColor);

        private final Color eForeground;
        private final Color eBackground;

        EColorMode(Color eForeground, Color eBackground) {
            this.eForeground = eForeground;
            this.eBackground = eBackground;
        }

        public Color geteForeground() {
            return this.eForeground;
        }

        public Color geteBackground() {
            return eBackground;
        }
    }
}
