package com.cui.gaodemapdemo.json2bean;

import java.util.List;

/**
 * 点位实时数据实体类
 *
 * @author cui7dr by 2019/08/29
 */

public class RealtimeBean {

    private String code;
    private RealtimeData data;
    private String info;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RealtimeData getData() {
        return data;
    }

    public void setData(RealtimeData data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private static class RealtimeData {
        private List<RealDataInfo> dataInfoList;

        public List<RealDataInfo> getDataInfoList() {
            return dataInfoList;
        }

        public void setDataInfoList(List<RealDataInfo> dataInfoList) {
            this.dataInfoList = dataInfoList;
        }

        private static class RealDataInfo {
            private double ddwd;
            private int pdjg;
            private double cojg;
            private double hcjg;
            private int ptInfoId;
            private double nojg;
            private double fs;
            private double wd;
            private double dqy;
            private double ydz;
            private String ptJzmc;
            private double sd;
            private int hpys;
            private double ddjd;
            private long createTime;
            private int isHg;
            private int rlzl;
            private String hphm;
            private int noIsHg;
            private long testDate;

            public double getDdwd() {
                return ddwd;
            }

            public void setDdwd(double ddwd) {
                this.ddwd = ddwd;
            }

            public int getPdjg() {
                return pdjg;
            }

            public void setPdjg(int pdjg) {
                this.pdjg = pdjg;
            }

            public double getCojg() {
                return cojg;
            }

            public void setCojg(double cojg) {
                this.cojg = cojg;
            }

            public double getHcjg() {
                return hcjg;
            }

            public void setHcjg(double hcjg) {
                this.hcjg = hcjg;
            }

            public int getPtInfoId() {
                return ptInfoId;
            }

            public void setPtInfoId(int ptInfoId) {
                this.ptInfoId = ptInfoId;
            }

            public double getNojg() {
                return nojg;
            }

            public void setNojg(double nojg) {
                this.nojg = nojg;
            }

            public double getFs() {
                return fs;
            }

            public void setFs(double fs) {
                this.fs = fs;
            }

            public double getWd() {
                return wd;
            }

            public void setWd(double wd) {
                this.wd = wd;
            }

            public double getDqy() {
                return dqy;
            }

            public void setDqy(double dqy) {
                this.dqy = dqy;
            }

            public double getYdz() {
                return ydz;
            }

            public void setYdz(double ydz) {
                this.ydz = ydz;
            }

            public String getPtJzmc() {
                return ptJzmc;
            }

            public void setPtJzmc(String ptJzmc) {
                this.ptJzmc = ptJzmc;
            }

            public double getSd() {
                return sd;
            }

            public void setSd(double sd) {
                this.sd = sd;
            }

            public int getHpys() {
                return hpys;
            }

            public void setHpys(int hpys) {
                this.hpys = hpys;
            }

            public double getDdjd() {
                return ddjd;
            }

            public void setDdjd(double ddjd) {
                this.ddjd = ddjd;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getIsHg() {
                return isHg;
            }

            public void setIsHg(int isHg) {
                this.isHg = isHg;
            }

            public int getRlzl() {
                return rlzl;
            }

            public void setRlzl(int rlzl) {
                this.rlzl = rlzl;
            }

            public String getHphm() {
                return hphm;
            }

            public void setHphm(String hphm) {
                this.hphm = hphm;
            }

            public int getNoIsHg() {
                return noIsHg;
            }

            public void setNoIsHg(int noIsHg) {
                this.noIsHg = noIsHg;
            }

            public long getTestDate() {
                return testDate;
            }

            public void setTestDate(long testDate) {
                this.testDate = testDate;
            }
        }
    }
}
