package pattern.global;

import pattern.shapes.*;

public class Constants {

    public enum ETools {
        eSelection("선택", new TSelection()),
        eRectangle("네모", new TRectangle()),
        eOval("동그라미", new TOval()),
        eLine("선", new TLine()),
        ePolygon("다각형", new TPolygon());

        private String label;
        private TShape tool;

        ETools(String label, TShape tool) {
            this.tool = tool;
            this.label = label;
        }

        public TShape newShape() {
            return this.tool.clone();
        }

        public String getLabel() {
            return this.label;
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
