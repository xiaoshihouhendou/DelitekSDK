package com.example.dlksdk.http.entity;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据区域查询设备 客控
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
        if (datas == null) {
            datas = new ArrayList<>();
        }
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
            return str(name);
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DevicesBean> getDevices() {
            if (devices == null) {
                devices = new ArrayList<>();
            }
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
                return str(type);
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return str(name);
            }

            public void setName(String name) {
                this.name = name;
            }

            public StateBean getState() {
                if (state == null) {
                    state = new StateBean();
                }
                return state;
            }

            public void setState(StateBean state) {
                this.state = state;
            }

            public static class StateBean {


                public String getSwitchs() {
                    return str(switchs);
                }

                public void setSwitchs(String switchs) {
                    this.switchs = switchs;
                }

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
                private String value;
                @SerializedName("switch")
                private String switchs;


                public String getValue() {

                    return str(value);
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getFan() {
                    return str(fan);
                }

                public void setFan(String fan) {
                    this.fan = fan;
                }

                public String getMode() {
                    return str(mode);
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

    public static String str(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }
}
