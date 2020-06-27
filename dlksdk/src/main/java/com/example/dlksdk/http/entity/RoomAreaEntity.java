package com.example.dlksdk.http.entity;

import java.util.List;

/**
 *  客控 搜索区域
 */
public class RoomAreaEntity extends BaseEntity  {


    /**
     * room : 8888
     * areas : [{"id":1,"name":"8888主房"},{"id":2,"name":"8888套间"}]
     * no : 1591591785
     * type : area
     */

    private String room;
    private List<AreasBean> areas;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<AreasBean> getAreas() {
        return areas;
    }

    public void setAreas(List<AreasBean> areas) {
        this.areas = areas;
    }

    public static class AreasBean {
        /**
         * id : 1
         * name : 8888主房
         */

        private int id;
        private String name;

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
    }
}
