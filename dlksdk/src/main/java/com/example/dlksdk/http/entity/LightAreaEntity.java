package com.example.dlksdk.http.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 灯控搜索区域
 */
public class LightAreaEntity extends BaseEntity {


    /**
     * areas : [{"id":1,"name":"一层"},{"id":2,"name":"二层"}]
     * no : 1591591785
     * type : area
     */

    private List<AreasBean> areas;

    public List<AreasBean> getAreas() {
        if (areas==null){
            areas=new ArrayList<>();
        }
        return areas;
    }

    public void setAreas(List<AreasBean> areas) {
        this.areas = areas;
    }

    public static class AreasBean {
        /**
         * id : 1
         * name : 一层
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

        @Override
        public String toString() {
            return ">>name==" + name;
        }
    }


}
