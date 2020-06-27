package com.example.dlksdk.http.entity;

import java.util.List;

/**
 * 查询空调 客控
 */
public class RoomAirsEntity extends BaseEntity{


    /**
     * datas : [{"id":1,"state":{"fan":"low","mode":"fan","setT":25,"currentT":25}},{"id":2,"state":{"fan":"low","mode":"fan","setT":25,"currentT":25}}]
     * no : 1591591785
     * type : air
     */

    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 1
         * state : {"fan":"low","mode":"fan","setT":25,"currentT":25}
         */

        private int id;
        private StateBean state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public StateBean getState() {
            return state;
        }

        public void setState(StateBean state) {
            this.state = state;
        }

        public static class StateBean {
            /**
             * fan : low
             * mode : fan
             * setT : 25
             * currentT : 25
             */

            private String fan;
            private String mode;
            private int setT;
            private int currentT;

            public String getFan() {
                return fan;
            }

            public void setFan(String fan) {
                this.fan = fan;
            }

            public String getMode() {
                return mode;
            }

            public void setMode(String mode) {
                this.mode = mode;
            }

            public int getSetT() {
                return setT;
            }

            public void setSetT(int setT) {
                this.setT = setT;
            }

            public int getCurrentT() {
                return currentT;
            }

            public void setCurrentT(int currentT) {
                this.currentT = currentT;
            }
        }
    }
}
