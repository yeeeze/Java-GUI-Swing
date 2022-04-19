package pattern.global;

import pattern.shapes.*;

public class Constants {

    public enum ETools {
        eRectangle("네모", new TRectangle()),
        eOval("동그라미", new TOval()),
        eLine("라인", new TLine()),
        ePolygon("폴리곤", new TPolygon());

        private String lable;
        private TShape tool;

        ETools(String lable, TShape tool) {
            this.tool = tool;
            this.lable = lable;
        }

        public TShape newShape() {
            return this.tool.clone();
        }

        public String getLable() {
            return this.lable;
        }
    }
}
