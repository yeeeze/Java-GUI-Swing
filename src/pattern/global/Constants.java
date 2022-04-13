package pattern.global;

import pattern.shapes.*;

public class Constants {

    public enum ETools {
        eRectangle(new TRectangle(), "rectangle"),
        eOval(new TOval(), "oval"),
        eLine(new TLine(), "line"),
        ePolygon(new TPolygon(), "polygon");

        private TShape tool;
        private String buttonName;

        ETools(TShape tool, String buttonName) {
            this.tool = tool;
            this.buttonName = buttonName;
        }

        public TShape newShape() {
            return this.tool.clone();
        }

        public String getButtonName() {
            return buttonName;
        }
    }
}
