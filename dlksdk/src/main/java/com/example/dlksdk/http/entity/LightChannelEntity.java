package com.example.dlksdk.http.entity;

import java.util.List;

/**
 * 查询场景 客控
 */
public class LightChannelEntity extends BaseEntity{


    /**
     * datas : [{"id":1,"state":{"value":0}},{"id":2,"state":{"value":0}}]
     * no : 1591591785
     * type : channel
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
         * state : {"value":0}
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
             * value : 0
             */

            private int value;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}
