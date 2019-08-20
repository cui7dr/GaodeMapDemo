package com.cui.gaodemapdemo.json2bean;

import java.util.List;

/**
 * 站点经纬度信息实体类
 *
 * @author cui7dr by 2019/08/19
 * @{"code":"0","data":{"data_info":[{"ddwd":33.987567,"ddjd":113.862191,"jzmc":"南外环梨园转盘","jzbh":"A411023001","id":1,"status":1}],"page_data":{"currentPage":1,"currentRecord":0,"nextPage":1,"pageSize":20,"prePage":1,"totalPage":1,"totalRecord":8}},"info":"成功"}
 * @remark TY in 2019/08/19
 */

public class LatlngBean {
    private String code;
    private LatlngData latlngdata;
    private String info;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LatlngData getData() {
        return latlngdata;
    }

    public void setData(LatlngData data) {
        this.latlngdata = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * "data":
     * {"data_info":[{"ddwd":33.987567,"ddjd":113.862191,"jzmc":"南外环梨园转盘","jzbh":"A411023001","id":1,"status":1}],
     * "page_data":{"currentPage":1,"currentRecord":0,"nextPage":1,"pageSize":20,"prePage":1,"totalPage":1,"totalRecord":8}}
     */
    public static class LatlngData {
        private List<DataInfo> dataInfo;
        private PageData pageData;

        public List<DataInfo> getDataInfo() {
            return dataInfo;
        }

        public void setDataInfo(List<DataInfo> dataInfo) {
            this.dataInfo = dataInfo;
        }

        public PageData getPageData() {
            return pageData;
        }

        public void setPageData(PageData pageData) {
            this.pageData = pageData;
        }

        /**
         * "data_info":
         * [{"ddwd":33.987567,"ddjd":113.862191,"jzmc":"南外环梨园转盘","jzbh":"A411023001","id":1,"status":1}]
         */
        public static class DataInfo {
            private double ddwd; //地点维度
            private double ddjd; //地点经度
            private String jzmc; //检测名称
            private String jzbh; //检测编号
            private int id;
            private int status;

            public double getDdwd() {
                return ddwd;
            }

            public void setDdwd(double ddwd) {
                this.ddwd = ddwd;
            }

            public double getDdjd() {
                return ddjd;
            }

            public void setDdjd(double ddjd) {
                this.ddjd = ddjd;
            }

            public String getJzmc() {
                return jzmc;
            }

            public void setJzmc(String jzmc) {
                this.jzmc = jzmc;
            }

            public String getJzbh() {
                return jzbh;
            }

            public void setJzbh(String jzbh) {
                this.jzbh = jzbh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        /**
         * page_data":
         * {"currentPage":1,"currentRecord":0,"nextPage":1,"pageSize":20,"prePage":1,"totalPage":1,"totalRecord":8}
         */
        public static class PageData {
            private int currentPage;
            private int currentRecord;
            private int nextPage;
            private int pageSize;
            private int prePage;
            private int totalPage;
            private int totalRecord;

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getCurrentRecord() {
                return currentRecord;
            }

            public void setCurrentRecord(int currentRecord) {
                this.currentRecord = currentRecord;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPrePage() {
                return prePage;
            }

            public void setPrePage(int prePage) {
                this.prePage = prePage;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalRecord() {
                return totalRecord;
            }

            public void setTotalRecord(int totalRecord) {
                this.totalRecord = totalRecord;
            }
        }
    }
}
