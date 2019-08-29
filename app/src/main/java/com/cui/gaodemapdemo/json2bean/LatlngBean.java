package com.cui.gaodemapdemo.json2bean;

import java.util.List;

/**
 * 站点经纬度信息实体类
 *
 * @author cui7dr by 2019/08/19
 *
 * @remark TY in 2019/08/19 my dreams
 */
public class LatlngBean {
    /**
     * {"code":"0",
     * <p>
     * "data":{"data_info":[{"ddwd":33.987567,"ddjd":113.862191,"jzmc":"南外环梨园转盘","jzbh":"A411023001","id":1,"status":1}],
     * <p>
     * "page_data":{"currentPage":1,"currentRecord":0,"nextPage":1,"pageSize":20,"prePage":1,"totalPage":1,"totalRecord":8}},
     * <p>
     * "info":"成功"}
     */

    private String code;
    private String info;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    /**
     * "data":
     * {"data_info":[{"ddwd":33.987567,"ddjd":113.862191,"jzmc":"南外环梨园转盘","jzbh":"A411023001","id":1,"status":1}],
     * <br/>
     * "page_data":{"currentPage":1,"currentRecord":0,"nextPage":1,"pageSize":20,"prePage":1,"totalPage":1,"totalRecord":8}}
     */
    public static class DataBean {
        private List<LatlngDataBean> data_info;
        private PageDataBean page_data;

        public List<LatlngDataBean> getData_info() {
            return data_info;
        }

        public void setData_info(List<LatlngDataBean> data_info) {
            this.data_info = data_info;
        }

        public PageDataBean getPage_data() {
            return page_data;
        }

        public void setPage_data(PageDataBean page_data) {
            this.page_data = page_data;
        }

        /**
         * "data_info":
         * [{"ddwd":33.987567,"ddjd":113.862191,"jzmc":"南外环梨园转盘","jzbh":"A411023001","id":1,"status":1}]
         */
        public static class LatlngDataBean {
            private double ddwd;
            private double ddjd;
            private String jzmc;
            private String jzbh;
            private int id;
            private int state;

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

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

        }

        /**
         * page_data":
         * {"currentPage":1,"currentRecord":0,"nextPage":1,"pageSize":20,"prePage":1,"totalPage":1,"totalRecord":8}
         */
        public static class PageDataBean {
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
