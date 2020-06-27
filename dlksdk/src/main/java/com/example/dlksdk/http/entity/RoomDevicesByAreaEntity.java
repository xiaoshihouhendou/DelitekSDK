package com.example.dlksdk.http.entity;

import java.util.List;

/**
 *  根据区域查询设备 客控
 */
public class RoomDevicesByAreaEntity extends BaseEntity {


    /**
     * room : 8888
     * datas : [{"id":1,"name":"8888主房","devices":[{"id":1,"type":"preset","name":"欢迎","state":{}},{"id":2,"type":"preset","name":"午休","state":{}},{"id":1,"type":"channel","name":"吊灯","state":{"value":0}},{"id":2,"type":"channel","name":"壁灯","state":{"value":0}},{"id":1,"type":"air","name":"空调1","state":{"fan":"low","mode":"fan","setT":25,"currentT":25}},{"id":2,"type":"air","name":"空调2","state":{"fan":"low","mode":"fan","setT":25,"currentT":25}},{"id":1,"type":"curtain","name":"窗帘","state":{}},{"id":2,"type":"curtain","name":"纱帘","state":{}}]},{"id":2,"name":"8888套间","devices":[{"id":1,"type":"preset","name":"欢迎","state":{}},{"id":2,"type":"preset","name":"午休","state":{}},{"id":1,"type":"channel","name":"吊灯","state":{"value":0}},{"id":2,"type":"channel","name":"壁灯","state":{"value":0}},{"id":1,"type":"air","name":"空调1","state":{"fan":"low","mode":"fan","setT":25,"currentT":25}},{"id":2,"type":"air","name":"空调2","state":{"fan":"low","mode":"fan","setT":25,"currentT":25}},{"id":1,"type":"curtain","name":"窗帘","state":{}},{"id":2,"type":"curtain","name":"纱帘","state":{}}]}]
     * no : 1591591785
     * type : device
     */

    private String room;
    private List<DatasBean> datas;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 1
         * name : 8888主房
         * devices : [{"id":1,"type":"preset","name":"欢迎","state":{}},{"id":2,"type":"preset","name":"午休","state":{}},{"id":1,"type":"channel","name":"吊灯","state":{"value":0}},{"id":2,"type":"channel","name":"壁灯","state":{"value":0}},{"id":1,"type":"air","name":"空调1","state":{"fan":"low","mode":"fan","setT":25,"currentT":25}},{"id":2,"type":"air","name":"空调2","state":{"fan":"low","mode":"fan","setT":25,"currentT":25}},{"id":1,"type":"curtain","name":"窗帘","state":{}},{"id":2,"type":"curtain","name":"纱帘","state":{}}]
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
             * id : 1
             * type : preset
             * name : 欢迎
             * state : {}
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
            }
        }
    }
}
