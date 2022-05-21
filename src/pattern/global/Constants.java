package pattern.global;

import pattern.shapes.*;

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
        ePolygon("다각형", new TPolygon(), ETransformationStyle.eNPTransformation);

        private String label;
        private TShape tool;
        private ETransformationStyle eTransformationStyle;

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

        private String label;
        private String toolTipText;
        
        private EFileMenu(String label, String toolTipText) {
            this.label = label;
            this.toolTipText = toolTipText;
        }

        public String getLabel() {
            return this.label;
        }
    }
    
    public enum SaveState {
    	exist,
    	done	
    }
}
