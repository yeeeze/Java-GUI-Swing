package pattern.global;

import pattern.shapes.*;

public class Constants {

    public enum ETools {
        eRectangle(new TRectangle()),
        eOval(new TOval()),
        eLine(new TLine()),
        ePolygon(new TPolygon());

        private TShape tool;

        ETools(TShape tool) {
            this.tool = tool;
        }

        public TShape newShape() {
            return this.tool.clone();
        }
    }
}
