package com.example.dlksdk.http.entity;

import java.util.List;

/**
 *  根据区域查询设备
 */
public class LightDevicesByAreaEntity extends BaseEntity {


    /**
     * datas : [{"id":1,"name":"一层","devices":[{"id":101,"type":"preset","name":"全开","state":{"value":0}},{"id":102,"type":"preset","name":"全关","state":{"value":0}},{"id":201,"type":"preset","name":"全开","state":{"value":0}},{"id":202,"type":"preset","name":"全关","state":{"value":0}},{"id":1,"type":"channel","name":"通道1","state":{"value":0}},{"id":2,"type":"channel","name":"通道2","state":{"value":0}}]},{"id":2,"name":"二层","devices":[{"id":101,"type":"preset","name":"全开","state":{"value":0}},{"id":102,"type":"preset","name":"全关","state":{"value":0}},{"id":201,"type":"preset","name":"全开","state":{"value":0}},{"id":202,"type":"preset","name":"全关","state":{"value":0}},{"id":1,"type":"channel","name":"通道1","state":{"value":0}},{"id":2,"type":"channel","name":"通道2","state":{"value":0}}]}]
     * no : 1591591785
     * type : device
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
         * name : 一层
         * devices : [{"id":101,"type":"preset","name":"全开","state":{"value":0}},{"id":102,"type":"preset","name":"全关","state":{"value":0}},{"id":201,"type":"preset","name":"全开","state":{"value":0}},{"id":202,"type":"preset","name":"全关","state":{"value":0}},{"id":1,"type":"channel","name":"通道1","state":{"value":0}},{"id":2,"type":"channel","name":"通道2","state":{"value":0}}]
         */

        private int id;
        private String name;
        private List<DevicesBean> devices;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DevicesBean> getDevices() {
            return devices;
        }

        public void setDevices(List<DevicesBean> devices) {
            this.devices = devices;
        }

        public static class DevicesBean {
            /**
             * id : 101
             * type : preset
             * name : 全开
             * state : {"value":0}
             */

            private int id;
            private String type;
            private String name;
            private StateBean state;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
}
