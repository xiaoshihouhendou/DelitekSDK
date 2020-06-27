package com.example.dlksdk.http.entity;

import java.util.List;

/**
 * 查询场景
 */
public class LightPresetEntity extends BaseEntity{

    /**
     * datas : [{"id":101,"state":{"value":0}},{"id":102,"state":{"value":0}}]
     * no : 1591605557
     * func : request
     * type : preset
     * business : light
     * time : 2020-06-26 20:26:07
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
         * id : 101
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
