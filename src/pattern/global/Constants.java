package pattern.global;

import pattern.shapes.*;

public class Constants {

    public enum ETools {
        eRectangle("네모", new TRectangle()),
        eOval("동그라미", new TOval()),
        eLine("라인", new TLine()),
        ePolygon("폴리곤", new TPolygon());

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
        eNew("새로만들기"),
        eOpen("열기"),
        eSave("저장하기"),
        eSaveAs("다른이름으로저장"),
        ePrint("프린트"),
        eQuit("종료");

        private String label;
        private EFileMenu(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    }
}
